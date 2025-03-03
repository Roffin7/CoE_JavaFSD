import React, { useState } from 'react';
import { ShoppingCart, Check } from 'lucide-react';
import { Product, useCart } from '../context/CartContext';

interface ProductCardProps {
  product: Product;
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const { addToCart } = useCart();
  const [isAdded, setIsAdded] = useState(false);

  const handleAddToCart = () => {
    addToCart(product);
    setIsAdded(true);
    setTimeout(() => setIsAdded(false), 1500);
  };

  return (
    <div className="bg-white rounded-xl shadow-lg overflow-hidden transition-all duration-300 hover:shadow-xl transform hover:-translate-y-1">
      <div className="h-56 overflow-hidden">
        <img 
          src={product.image} 
          alt={product.name} 
          className="w-full h-full object-cover transition-transform duration-500 hover:scale-110"
        />
      </div>
      
      <div className="p-5">
        <h3 className="text-xl font-semibold text-gray-800 mb-2">{product.name}</h3>
        <p className="text-gray-600 text-sm mb-4 line-clamp-2">{product.description}</p>
        
        <div className="flex justify-between items-center">
          <span className="text-purple-700 font-bold text-lg">₹{(product.price * 83).toFixed(2)}</span>
          
          <button 
            onClick={handleAddToCart}
            disabled={isAdded}
            className={`px-4 py-2 rounded-lg flex items-center transition-colors ${
              isAdded 
                ? 'bg-green-500 text-white' 
                : 'bg-gradient-to-r from-purple-600 to-indigo-600 text-white hover:from-purple-700 hover:to-indigo-700'
            }`}
          >
            {isAdded ? (
              <>
                <Check size={16} className="mr-1" />
                Added
              </>
            ) : (
              <>
                <ShoppingCart size={16} className="mr-1" />
                Add to Cart
              </>
            )}
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
