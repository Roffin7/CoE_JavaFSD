import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import Home from './pages/Home';
import CartPage from './pages/CartPage';
import { CartProvider } from './context/CartContext';

function App() {
  return (
    <CartProvider>
      <Router>
        <div className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-100 flex flex-col">
          <Navbar />
          <main className="flex-grow">
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/cart" element={<CartPage />} />
            </Routes>
          </main>
          <footer className="bg-gradient-to-r from-purple-800 to-indigo-800 text-white py-6">
            <div className="container mx-auto px-4 text-center">
              <p>&copy; 2025 FinBuy. All rights reserved.</p>
            </div>
          </footer>
        </div>
      </Router>
    </CartProvider>
  );
}

export default App;
