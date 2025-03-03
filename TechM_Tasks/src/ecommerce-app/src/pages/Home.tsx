import React, { useState } from 'react';
import ProductCard from '../components/ProductCard';
import { products } from '../data/products';
import { ShoppingBag, Truck, CreditCard, LifeBuoy, AlertCircle, CheckCircle } from 'lucide-react';

const Home: React.FC = () => {
  const [email, setEmail] = useState('');
  const [emailError, setEmailError] = useState('');
  const [subscribed, setSubscribed] = useState(false);

  const validateEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  const handleSubscribe = () => {
    if (!email) {
      setEmailError('Email is required');
      return;
    }

    if (!validateEmail(email)) {
      setEmailError('Please enter a valid email address');
      return;
    }

    setEmailError('');
    setSubscribed(true);
    setTimeout(() => {
      setSubscribed(false);
      setEmail('');
    }, 3000);
  };

  return (
    <div className="container mx-auto px-4 py-10">
      {/* Hero Section */}
      <div className="text-center mb-12">
        <h1 className="text-4xl font-bold text-gray-800 mb-4">Welcome to FinBuy</h1>
        <p className="text-gray-600 max-w-2xl mx-auto">Discover premium products at unbeatable prices. Shop our curated collection of high-quality items designed to enhance your lifestyle.</p>
      </div>
      
      {/* Promotional Banner */}
      <div className="mb-12 bg-gradient-to-r from-purple-600 to-indigo-600 text-white p-8 rounded-2xl shadow-lg">
        <div className="flex flex-col md:flex-row items-center justify-between">
          <div className="mb-6 md:mb-0">
            <h2 className="text-2xl font-bold mb-2">Summer Sale</h2>
            <p className="text-purple-100">Get up to 40% off on selected items</p>
          </div>
          <button className="bg-white text-purple-700 px-6 py-2 rounded-lg font-semibold hover:bg-purple-100 transition-colors">
            Shop Now
          </button>
        </div>
      </div>
      
      {/* Features Section */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-6 mb-16">
        <div className="bg-white p-6 rounded-xl shadow-md text-center">
          <div className="flex justify-center mb-4">
            <ShoppingBag size={32} className="text-purple-600" />
          </div>
          <h3 className="font-semibold text-lg mb-2">Secure Shopping</h3>
          <p className="text-gray-600 text-sm">Shop with confidence with our secure payment system</p>
        </div>
        
        <div className="bg-white p-6 rounded-xl shadow-md text-center">
          <div className="flex justify-center mb-4">
            <Truck size={32} className="text-purple-600" />
          </div>
          <h3 className="font-semibold text-lg mb-2">Fast Delivery</h3>
          <p className="text-gray-600 text-sm">Get your products delivered within 2-3 business days</p>
        </div>
        
        <div className="bg-white p-6 rounded-xl shadow-md text-center">
          <div className="flex justify-center mb-4">
            <CreditCard size={32} className="text-purple-600" />
          </div>
          <h3 className="font-semibold text-lg mb-2">Easy Returns</h3>
          <p className="text-gray-600 text-sm">30-day hassle-free return policy on all purchases</p>
        </div>
        
        <div className="bg-white p-6 rounded-xl shadow-md text-center">
          <div className="flex justify-center mb-4">
            <LifeBuoy size={32} className="text-purple-600" />
          </div>
          <h3 className="font-semibold text-lg mb-2">24/7 Support</h3>
          <p className="text-gray-600 text-sm">Our customer support team is always ready to help</p>
        </div>
      </div>
      
      {/* Featured Products Section */}
      <h2 className="text-3xl font-bold text-gray-800 mb-8 text-center">Featured Products</h2>
      
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8 mb-16">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
      
      {/* Testimonials Section */}
      <div className="mb-16">
        <h2 className="text-3xl font-bold text-gray-800 mb-8 text-center">What Our Customers Say</h2>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-8">
          <div className="bg-white p-6 rounded-xl shadow-md">
            <div className="flex items-center mb-4">
              <div className="h-12 w-12 rounded-full bg-purple-100 flex items-center justify-center text-purple-700 font-bold text-xl">
                RS
              </div>
              <div className="ml-4">
                <h4 className="font-semibold">Rahul Sharma</h4>
                <div className="flex text-yellow-400">
                  {'★'.repeat(5)}
                </div>
              </div>
            </div>
            <p className="text-gray-600">"The quality of products from FinBuy is exceptional. I've been a loyal customer for over a year now and have never been disappointed."</p>
          </div>
          
          <div className="bg-white p-6 rounded-xl shadow-md">
            <div className="flex items-center mb-4">
              <div className="h-12 w-12 rounded-full bg-purple-100 flex items-center justify-center text-purple-700 font-bold text-xl">
                AP
              </div>
              <div className="ml-4">
                <h4 className="font-semibold">Ananya Patel</h4>
                <div className="flex text-yellow-400">
                  {'★'.repeat(5)}
                </div>
              </div>
            </div>
            <p className="text-gray-600">"Fast delivery and excellent customer service. The wireless headphones I purchased exceeded my expectations in terms of sound quality."</p>
          </div>
          
          <div className="bg-white p-6 rounded-xl shadow-md">
            <div className="flex items-center mb-4">
              <div className="h-12 w-12 rounded-full bg-purple-100 flex items-center justify-center text-purple-700 font-bold text-xl">
                VK
              </div>
              <div className="ml-4">
                <h4 className="font-semibold">Vikram Kumar</h4>
                <div className="flex text-yellow-400">
                  {'★'.repeat(4)}{'☆'.repeat(1)}
                </div>
              </div>
            </div>
            <p className="text-gray-600">"Great selection of products at competitive prices. The checkout process is smooth and hassle-free. Will definitely shop here again."</p>
          </div>
        </div>
      </div>
      
      {/* Newsletter Section */}
      <div className="bg-gradient-to-r from-purple-600 to-indigo-600 text-white p-8 rounded-2xl shadow-lg mb-16">
        <div className="text-center mb-6">
          <h2 className="text-2xl font-bold mb-2">Subscribe to Our Newsletter</h2>
          <p className="text-purple-100">Stay updated with our latest products and exclusive offers</p>
        </div>
        
        <div className="max-w-md mx-auto">
          <div className="flex flex-col sm:flex-row gap-2 mb-2">
            <div className="flex-grow relative">
              <input 
                type="email" 
                placeholder="Enter your email" 
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                  if (emailError) setEmailError('');
                }}
                className={`w-full px-4 py-2 rounded-lg focus:outline-none text-gray-800 ${
                  emailError ? 'border-2 border-red-500' : ''
                }`}
              />
              {subscribed && (
                <div className="absolute right-3 top-1/2 transform -translate-y-1/2">
                  <CheckCircle className="text-green-500" size={20} />
                </div>
              )}
            </div>
            <button 
              onClick={handleSubscribe}
              className="bg-white text-purple-700 px-6 py-2 rounded-lg font-semibold hover:bg-purple-100 transition-colors"
            >
              Subscribe
            </button>
          </div>
          
          {emailError && (
            <div className="text-red-200 text-sm flex items-center mt-1">
              <AlertCircle size={14} className="mr-1" />
              {emailError}
            </div>
          )}
          
          {subscribed && (
            <div className="text-green-200 text-sm flex items-center mt-1">
              <CheckCircle size={14} className="mr-1" />
              Thank you for subscribing!
            </div>
          )}
        </div>
      </div>
      
      {/* Categories Section */}
      <div className="mb-16">
        <h2 className="text-3xl font-bold text-gray-800 mb-8 text-center">Shop by Category</h2>
        
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-6">
          <div className="relative h-48 rounded-xl overflow-hidden group">
            <img 
              src="https://images.unsplash.com/photo-1546868871-7041f2a55e12?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80" 
              alt="Electronics" 
              className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
              <div className="p-4 w-full">
                <h3 className="text-white font-bold text-xl mb-1">Electronics</h3>
                <button className="text-white text-sm hover:underline">Shop Now →</button>
              </div>
            </div>
          </div>
          
          <div className="relative h-48 rounded-xl overflow-hidden group">
            <img 
              src="https://images.unsplash.com/photo-1542291026-7eec264c27ff?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80" 
              alt="Fashion" 
              className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
              <div className="p-4 w-full">
                <h3 className="text-white font-bold text-xl mb-1">Fashion</h3>
                <button className="text-white text-sm hover:underline">Shop Now →</button>
              </div>
            </div>
          </div>
          
          <div className="relative h-48 rounded-xl overflow-hidden group">
            <img 
              src="https://images.unsplash.com/photo-1616486338812-3dadae4b4ace?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80" 
              alt="Home & Kitchen" 
              className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
              <div className="p-4 w-full">
                <h3 className="text-white font-bold text-xl mb-1">Home & Kitchen</h3>
                <button className="text-white text-sm hover:underline">Shop Now →</button>
              </div>
            </div>
          </div>
          
          <div className="relative h-48 rounded-xl overflow-hidden group">
            <img 
              src="https://images.unsplash.com/photo-1511067007398-7e4b90cfa4bc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1000&q=80" 
              alt="Sports & Outdoors" 
              className="w-full h-full object-cover group-hover:scale-110 transition-transform duration-500"
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent flex items-end">
              <div className="p-4 w-full">
                <h3 className="text-white font-bold text-xl mb-1">Sports & Outdoors</h3>
                <button className="text-white text-sm hover:underline">Shop Now →</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
