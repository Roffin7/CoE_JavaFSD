import React, { createContext, useContext, useReducer, ReactNode } from 'react';

// Define types
export interface Product {
  id: number;
  name: string;
  price: number;
  image: string;
  description: string;
}

interface CartItem extends Product {
  quantity: number;
}

interface CartState {
  items: CartItem[];
  totalItems: number;
  totalPrice: number;
}

type CartAction =
  | { type: 'ADD_TO_CART'; payload: Product }
  | { type: 'REMOVE_FROM_CART'; payload: { id: number } }
  | { type: 'UPDATE_QUANTITY'; payload: { id: number; quantity: number } };

interface CartContextType {
  state: CartState;
  addToCart: (product: Product) => void;
  removeFromCart: (id: number) => void;
  updateQuantity: (id: number, quantity: number) => void;
}

// Create context
const CartContext = createContext<CartContextType | undefined>(undefined);

// Initial state
const initialState: CartState = {
  items: [],
  totalItems: 0,
  totalPrice: 0,
};

// Reducer function
const cartReducer = (state: CartState, action: CartAction): CartState => {
  switch (action.type) {
    case 'ADD_TO_CART': {
      const existingItemIndex = state.items.findIndex(
        (item) => item.id === action.payload.id
      );

      let updatedItems: CartItem[];

      if (existingItemIndex >= 0) {
        // Item exists, update quantity
        updatedItems = state.items.map((item, index) =>
          index === existingItemIndex
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      } else {
        // Add new item
        updatedItems = [...state.items, { ...action.payload, quantity: 1 }];
      }

      // Calculate totals
      const totalItems = updatedItems.reduce(
        (total, item) => total + item.quantity,
        0
      );
      const totalPrice = updatedItems.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );

      return {
        items: updatedItems,
        totalItems,
        totalPrice,
      };
    }

    case 'REMOVE_FROM_CART': {
      const updatedItems = state.items.filter(
        (item) => item.id !== action.payload.id
      );

      // Calculate totals
      const totalItems = updatedItems.reduce(
        (total, item) => total + item.quantity,
        0
      );
      const totalPrice = updatedItems.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );

      return {
        items: updatedItems,
        totalItems,
        totalPrice,
      };
    }

    case 'UPDATE_QUANTITY': {
      const { id, quantity } = action.payload;
      
      // Don't allow negative quantities
      if (quantity <= 0) {
        return {
          ...state,
          items: state.items.filter(item => item.id !== id),
          totalItems: state.items
            .filter(item => item.id !== id)
            .reduce((total, item) => total + item.quantity, 0),
          totalPrice: state.items
            .filter(item => item.id !== id)
            .reduce((total, item) => total + item.price * item.quantity, 0),
        };
      }

      const updatedItems = state.items.map((item) =>
        item.id === id ? { ...item, quantity } : item
      );

      // Calculate totals
      const totalItems = updatedItems.reduce(
        (total, item) => total + item.quantity,
        0
      );
      const totalPrice = updatedItems.reduce(
        (total, item) => total + item.price * item.quantity,
        0
      );

      return {
        items: updatedItems,
        totalItems,
        totalPrice,
      };
    }

    default:
      return state;
  }
};

// Provider component
interface CartProviderProps {
  children: ReactNode;
}

export const CartProvider: React.FC<CartProviderProps> = ({ children }) => {
  const [state, dispatch] = useReducer(cartReducer, initialState);

  const addToCart = (product: Product) => {
    dispatch({ type: 'ADD_TO_CART', payload: product });
  };

  const removeFromCart = (id: number) => {
    dispatch({ type: 'REMOVE_FROM_CART', payload: { id } });
  };

  const updateQuantity = (id: number, quantity: number) => {
    dispatch({ type: 'UPDATE_QUANTITY', payload: { id, quantity } });
  };

  return (
    <CartContext.Provider
      value={{ state, addToCart, removeFromCart, updateQuantity }}
    >
      {children}
    </CartContext.Provider>
  );
};

// Custom hook to use the cart context
export const useCart = (): CartContextType => {
  const context = useContext(CartContext);
  if (context === undefined) {
    throw new Error('useCart must be used within a CartProvider');
  }
  return context;
};
