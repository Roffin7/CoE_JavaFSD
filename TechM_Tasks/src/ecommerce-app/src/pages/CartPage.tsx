import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import { ShoppingBag, ArrowLeft, CreditCard, AlertCircle, CheckCircle, Calendar, User } from 'lucide-react';
import CartItem from '../components/CartItem';
import { useCart } from '../context/CartContext';

const CartPage: React.FC = () => {
  const { state } = useCart();
  const [email, setEmail] = useState('');
  const [emailError, setEmailError] = useState('');
  const [cardNumber, setCardNumber] = useState('');
  const [cardNumberError, setCardNumberError] = useState('');
  const [expiryDate, setExpiryDate] = useState('');
  const [expiryDateError, setExpiryDateError] = useState('');
  const [cvv, setCvv] = useState('');
  const [cvvError, setCvvError] = useState('');
  const [name, setName] = useState('');
  const [nameError, setNameError] = useState('');
  const [address, setAddress] = useState('');
  const [addressError, setAddressError] = useState('');
  const [isCheckoutAttempted, setIsCheckoutAttempted] = useState(false);
  const [orderPlaced, setOrderPlaced] = useState(false);

  const validateEmail = (email: string) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  };

  const validateCardNumber = (number: string) => {
    const re = /^[0-9]{16}$/;
    return re.test(number);
  };

  const validateExpiryDate = (date: string) => {
    const re = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;
    if (!re.test(date)) return false;
    
    const [month, year] = date.split('/').map(Number);
    const currentYear = new Date().getFullYear() % 100;
    const currentMonth = new Date().getMonth() + 1;
    
    if (year < currentYear) return false;
    if (year === currentYear && month < currentMonth) return false;
    
    return true;
  };

  const validateCVV = (cvv: string) => {
    const re = /^[0-9]{3,4}$/;
    return re.test(cvv);
  };

  const formatCardNumber = (value: string) => {
    const v = value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    const matches = v.match(/\d{4,16}/g);
    const match = matches && matches[0] || '';
    const parts = [];

    for (let i = 0, len = match.length; i < len; i += 4) {
      parts.push(match.substring(i, i + 4));
    }

    if (parts.length) {
      return parts.join(' ');
    } else {
      return value;
    }
  };

  const formatExpiryDate = (value: string) => {
    const v = value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    
    if (v.length >= 3) {
      return `${v.substring(0, 2)}/${v.substring(2)}`;
    }
    
    return value;
  };

  const handleCheckout = () => {
    setIsCheckoutAttempted(true);
    let isValid = true;

    // Email validation
    if (!email || !validateEmail(email)) {
      setEmailError('Please enter a valid email address');
      isValid = false;
    } else {
      setEmailError('');
    }

    // Name validation
    if (!name.trim()) {
      setNameError('Please enter your full name');
      isValid = false;
    } else {
      setNameError('');
    }

    // Address validation
    if (!address.trim()) {
      setAddressError('Please enter your shipping address');
      isValid = false;
    } else {
      setAddressError('');
    }

    // Card number validation
    if (!cardNumber || !validateCardNumber(cardNumber.replace(/\s/g, ''))) {
      setCardNumberError('Please enter a valid 16-digit card number');
      isValid = false;
    } else {
      setCardNumberError('');
    }

    // Expiry date validation
    if (!expiryDate || !validateExpiryDate(expiryDate)) {
      setExpiryDateError('Please enter a valid expiry date (MM/YY)');
      isValid = false;
    } else {
      setExpiryDateError('');
    }

    // CVV validation
    if (!cvv || !validateCVV(cvv)) {
      setCvvError('Please enter a valid CVV (3-4 digits)');
      isValid = false;
    } else {
      setCvvError('');
    }

    if (isValid) {
      setOrderPlaced(true);
    }
  };

  if (orderPlaced) {
    return (
      <div className="container mx-auto px-4 py-10">
        <div className="max-w-md mx-auto bg-white p-8 rounded-xl shadow-md text-center">
          <div className="flex justify-center mb-6">
            <div className="h-16 w-16 bg-green-100 rounded-full flex items-center justify-center">
              <CheckCircle size={32} className="text-green-500" />
            </div>
          </div>
          <h1 className="text-2xl font-bold text-gray-800 mb-4">Order Placed Successfully!</h1>
          <p className="text-gray-600 mb-6">Thank you for your purchase. Your order has been received and is being processed.</p>
          <p className="text-gray-600 mb-8">A confirmation email has been sent to {email}.</p>
          <Link 
            to="/" 
            className="inline-block bg-gradient-to-r from-purple-600 to-indigo-600 text-white py-3 px-6 rounded-lg hover:from-purple-700 hover:to-indigo-700 transition-colors"
          >
            Continue Shopping
          </Link>
        </div>
      </div>
    );
  }

  return (
    <div className="container mx-auto px-4 py-10">
      <h1 className="text-3xl font-bold text-gray-800 mb-8 flex items-center justify-center">
        <ShoppingBag className="mr-2" />
        Your Shopping Cart
      </h1>

      {state.items.length === 0 ? (
        <div className="text-center py-16 bg-white rounded-xl shadow-md">
          <div className="text-gray-500 mb-6 text-lg">Your cart is empty</div>
          <Link 
            to="/" 
            className="inline-flex items-center text-purple-600 hover:text-purple-800 font-medium"
          >
            <ArrowLeft size={16} className="mr-2" />
            Continue Shopping
          </Link>
        </div>
      ) : (
        <div className="lg:grid lg:grid-cols-12 lg:gap-8">
          <div className="lg:col-span-8">
            <div className="bg-white rounded-xl shadow-md p-6 mb-6">
              <h2 className="text-xl font-semibold mb-6 pb-2 border-b">Cart Items</h2>
              
              <div className="divide-y divide-gray-200">
                {state.items.map((item) => (
                  <CartItem 
                    key={item.id}
                    id={item.id}
                    name={item.name}
                    price={item.price}
                    image={item.image}
                    quantity={item.quantity}
                  />
                ))}
              </div>
            </div>
            
            {/* Shipping Information */}
            <div className="bg-white rounded-xl shadow-md p-6 mb-6">
              <h2 className="text-xl font-semibold mb-6 pb-2 border-b">Shipping Information</h2>
              
              <div className="space-y-4">
                <div>
                  <label htmlFor="name" className="block text-sm font-medium text-gray-700 mb-1">
                    Full Name
                  </label>
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <User size={16} className="text-gray-400" />
                    </div>
                    <input
                      type="text"
                      id="name"
                      value={name}
                      onChange={(e) => {
                        setName(e.target.value);
                        if (isCheckoutAttempted && !e.target.value.trim()) {
                          setNameError('Please enter your full name');
                        } else {
                          setNameError('');
                        }
                      }}
                      className={`w-full pl-10 px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                        nameError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                      }`}
                      placeholder="John Doe"
                    />
                  </div>
                  {nameError && (
                    <p className="mt-1 text-red-500 text-sm flex items-center">
                      <AlertCircle size={14} className="mr-1" />
                      {nameError}
                    </p>
                  )}
                </div>
                
                <div>
                  <label htmlFor="email" className="block text-sm font-medium text-gray-700 mb-1">
                    Email Address
                  </label>
                  <input
                    type="email"
                    id="email"
                    value={email}
                    onChange={(e) => {
                      setEmail(e.target.value);
                      if (isCheckoutAttempted) {
                        if (!validateEmail(e.target.value)) {
                          setEmailError('Please enter a valid email address');
                        } else {
                          setEmailError('');
                        }
                      }
                    }}
                    className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                      emailError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                    }`}
                    placeholder="your@email.com"
                  />
                  {emailError && (
                    <p className="mt-1 text-red-500 text-sm flex items-center">
                      <AlertCircle size={14} className="mr-1" />
                      {emailError}
                    </p>
                  )}
                </div>
                
                <div>
                  <label htmlFor="address" className="block text-sm font-medium text-gray-700 mb-1">
                    Shipping Address
                  </label>
                  <textarea
                    id="address"
                    value={address}
                    onChange={(e) => {
                      setAddress(e.target.value);
                      if (isCheckoutAttempted && !e.target.value.trim()) {
                        setAddressError('Please enter your shipping address');
                      } else {
                        setAddressError('');
                      }
                    }}
                    rows={3}
                    className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                      addressError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                    }`}
                    placeholder="123 Main St, Apartment 4B, City, State, ZIP"
                  />
                  {addressError && (
                    <p className="mt-1 text-red-500 text-sm flex items-center">
                      <AlertCircle size={14} className="mr-1" />
                      {addressError}
                    </p>
                  )}
                </div>
              </div>
            </div>
          </div>
          
          <div className="lg:col-span-4 mt-6 lg:mt-0">
            <div className="bg-white rounded-xl shadow-md p-6 sticky top-4">
              <h2 className="text-xl font-semibold mb-6 pb-2 border-b">Order Summary</h2>
              
              <div className="space-y-4 mb-6">
                <div className="flex justify-between border-b border-gray-200 pb-4">
                  <span>Subtotal</span>
                  <span>₹{(state.totalPrice * 83).toFixed(2)}</span>
                </div>
                
                <div className="flex justify-between border-b border-gray-200 pb-4">
                  <span>Shipping</span>
                  <span>Free</span>
                </div>
                
                <div className="flex justify-between font-semibold text-lg">
                  <span>Total</span>
                  <span>₹{(state.totalPrice * 83).toFixed(2)}</span>
                </div>
              </div>
              
              <h3 className="font-semibold text-gray-700 mb-3">Payment Details</h3>
              
              <div className="mb-4">
                <label htmlFor="cardNumber" className="block text-sm font-medium text-gray-700 mb-1">
                  Card Number
                </label>
                <input
                  type="text"
                  id="cardNumber"
                  value={cardNumber}
                  onChange={(e) => {
                    const formattedValue = formatCardNumber(e.target.value);
                    setCardNumber(formattedValue);
                    if (isCheckoutAttempted) {
                      if (!validateCardNumber(formattedValue.replace(/\s/g, ''))) {
                        setCardNumberError('Please enter a valid 16-digit card number');
                      } else {
                        setCardNumberError('');
                      }
                    }
                  }}
                  maxLength={19}
                  className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                    cardNumberError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                  }`}
                  placeholder="1234 5678 9012 3456"
                />
                {cardNumberError && (
                  <p className="mt-1 text-red-500 text-sm flex items-center">
                    <AlertCircle size={14} className="mr-1" />
                    {cardNumberError}
                  </p>
                )}
              </div>
              
              <div className="grid grid-cols-2 gap-4 mb-6">
                <div>
                  <label htmlFor="expiryDate" className="block text-sm font-medium text-gray-700 mb-1">
                    Expiry Date
                  </label>
                  <div className="relative">
                    <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
                      <Calendar size={16} className="text-gray-400" />
                    </div>
                    <input
                      type="text"
                      id="expiryDate"
                      value={expiryDate}
                      onChange={(e) => {
                        const value = e.target.value.replace(/[^0-9]/g, '');
                        if (value.length <= 4) {
                          const formatted = value.length >= 3 
                            ? `${value.substring(0, 2)}/${value.substring(2)}`
                            : value;
                          setExpiryDate(formatted);
                          
                          if (isCheckoutAttempted && value.length === 4) {
                            const formatted = `${value.substring(0, 2)}/${value.substring(2)}`;
                            if (!validateExpiryDate(formatted)) {
                              setExpiryDateError('Invalid expiry date');
                            } else {
                              setExpiryDateError('');
                            }
                          }
                        }
                      }}
                      maxLength={5}
                      className={`w-full pl-10 px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                        expiryDateError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                      }`}
                      placeholder="MM/YY"
                    />
                  </div>
                  {expiryDateError && (
                    <p className="mt-1 text-red-500 text-sm flex items-center">
                      <AlertCircle size={14} className="mr-1" />
                      {expiryDateError}
                    </p>
                  )}
                </div>
                
                <div>
                  <label htmlFor="cvv" className="block text-sm font-medium text-gray-700 mb-1">
                    CVV
                  </label>
                  <input
                    type="text"
                    id="cvv"
                    value={cvv}
                    onChange={(e) => {
                      const value = e.target.value.replace(/\D/g, '');
                      setCvv(value);
                      if (isCheckoutAttempted) {
                        if (!validateCVV(value)) {
                          setCvvError('Invalid CVV');
                        } else {
                          setCvvError('');
                        }
                      }
                    }}
                    maxLength={4}
                    className={`w-full px-3 py-2 border rounded-lg focus:ring-2 focus:outline-none ${
                      cvvError ? 'border-red-500 focus:ring-red-200' : 'border-gray-300 focus:ring-purple-200 focus:border-purple-500'
                    }`}
                    placeholder="123"
                  />
                  {cvvError && (
                    <p className="mt-1 text-red-500 text-sm flex items-center">
                      <AlertCircle size={14} className="mr-1" />
                      {cvvError}
                    </p>
                  )}
                </div>
              </div>
              
              <button 
                onClick={handleCheckout}
                className="w-full bg-gradient-to-r from-purple-600 to-indigo-600 text-white py-3 px-4 rounded-lg hover:from-purple-700 hover:to-indigo-700 transition-colors flex items-center justify-center"
              >
                <CreditCard size={18} className="mr-2" />
                Place Order
              </button>
              
              <Link 
                to="/" 
                className="block text-center text-purple-600 hover:text-purple-800 mt-4"
              >
                Continue Shopping
              </Link>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default CartPage;
