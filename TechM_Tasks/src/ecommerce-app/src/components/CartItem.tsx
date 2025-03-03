import React from 'react';
import { Trash, Plus, Minus } from 'lucide-react';
import { useCart } from '../context/CartContext';

interface CartItemProps {
  id: number;
  name: string;
  price: number;
  image: string;
  quantity: number;
}

const CartItem: React.FC<CartItemProps> = ({ id, name, price, image, quantity }) => {
  const { removeFromCart, updateQuantity } = useCart();
  const priceInRupees = price * 83;

  return (
    <div className="flex items-center py-5 border-b border-gray-200">
      <div className="h-24 w-24 flex-shrink-0 overflow-hidden rounded-lg">
        <img 
          src={image} 
          alt={name} 
          className="h-full w-full object-cover object-center"
        />
      </div>
      
      <div className="ml-6 flex flex-1 flex-col">
        <div className="flex justify-between text-base font-medium text-gray-900">
          <h3 className="text-lg">{name}</h3>
          <p className="ml-4 text-purple-700 font-semibold">₹{priceInRupees.toFixed(2)}</p>
        </div>
        
        <div className="flex items-center justify-between mt-4">
          <div className="flex items-center border border-gray-300 rounded-lg overflow-hidden">
            <button 
              onClick={() => updateQuantity(id, quantity - 1)}
              className="px-3 py-1 text-gray-600 hover:bg-gray-100 transition-colors"
              aria-label="Decrease quantity"
            >
              <Minus size={16} />
            </button>
            
            <span className="px-4 py-1 text-gray-800 bg-gray-50">{quantity}</span>
            
            <button 
              onClick={() => updateQuantity(id, quantity + 1)}
              className="px-3 py-1 text-gray-600 hover:bg-gray-100 transition-colors"
              aria-label="Increase quantity"
            >
              <Plus size={16} />
            </button>
          </div>
          
          <button 
            onClick={() => removeFromCart(id)}
            className="text-red-500 hover:text-red-700 flex items-center transition-colors"
            aria-label="Remove item"
          >
            <Trash size={16} className="mr-1" />
            Remove
          </button>
        </div>
      </div>
    </div>
  );
};

export default CartItem;
