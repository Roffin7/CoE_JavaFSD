import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { ShoppingCart, Home, Menu, X } from 'lucide-react';
import { useCart } from '../context/CartContext';

const Navbar: React.FC = () => {
  const { state } = useCart();
  const [isMenuOpen, setIsMenuOpen] = useState(false);

  return (
    <nav className="bg-gradient-to-r from-purple-700 to-indigo-700 text-white shadow-lg">
      <div className="container mx-auto px-4 py-4">
        <div className="flex justify-between items-center">
          <Link to="/" className="text-2xl font-bold flex items-center">
            <span className="mr-2 font-serif tracking-wider">FinBuy</span>
          </Link>
          
          {/* Mobile menu button */}
          <div className="md:hidden">
            <button 
              onClick={() => setIsMenuOpen(!isMenuOpen)}
              className="text-white focus:outline-none"
            >
              {isMenuOpen ? <X size={24} /> : <Menu size={24} />}
            </button>
          </div>
          
          {/* Desktop menu */}
          <div className="hidden md:flex items-center space-x-8">
            <Link to="/" className="flex items-center hover:text-purple-200 transition-colors">
              <Home size={20} className="mr-1" />
              <span>Home</span>
            </Link>
            
            <Link to="/cart" className="flex items-center hover:text-purple-200 transition-colors relative">
              <ShoppingCart size={20} className="mr-1" />
              <span>Cart</span>
              {state.totalItems > 0 && (
                <span className="absolute -top-2 -right-2 bg-pink-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center">
                  {state.totalItems}
                </span>
              )}
            </Link>
          </div>
        </div>
        
        {/* Mobile menu */}
        {isMenuOpen && (
          <div className="md:hidden mt-4 space-y-4 pb-3">
            <Link 
              to="/" 
              className="block py-2 hover:bg-purple-600 px-3 rounded"
              onClick={() => setIsMenuOpen(false)}
            >
              <div className="flex items-center">
                <Home size={20} className="mr-2" />
                <span>Home</span>
              </div>
            </Link>
            
            <Link 
              to="/cart" 
              className="block py-2 hover:bg-purple-600 px-3 rounded relative"
              onClick={() => setIsMenuOpen(false)}
            >
              <div className="flex items-center">
                <ShoppingCart size={20} className="mr-2" />
                <span>Cart</span>
                {state.totalItems > 0 && (
                  <span className="ml-2 bg-pink-500 text-white text-xs rounded-full h-5 w-5 flex items-center justify-center">
                    {state.totalItems}
                  </span>
                )}
              </div>
            </Link>
          </div>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
