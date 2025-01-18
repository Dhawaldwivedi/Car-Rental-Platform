import React, { useContext, useState, useEffect } from 'react';
import { useNavigate, useParams, useLocation } from 'react-router-dom';
import AuthContext from '../context/Authprovider';
import PaymentService from '../services/PaymentService';

export const PaymentForm = () => {
  const { auth } = useContext(AuthContext);
  const navigate = useNavigate();
  const { reservationId } = useParams(); 
  const location = useLocation(); 
  const [paymentMethod, setPaymentMethod] = useState('');
  const [amount, setAmount] = useState(0); 
  const [baseAmount, setBaseAmount] = useState(0); 
  const gst = 100; 
  const paymentMethods = ['CASH', 'CREDIT_CARD', 'DEBIT_CARD', 'NET_BANKING', 'UPI'];

  useEffect(() => {
    if (location.state?.baseAmount) {
      setBaseAmount(location.state.baseAmount);
      setAmount(parseFloat(location.state.baseAmount) + gst); // Calculate total
    } else {
      console.error('Base amount not provided. Please go back and create a reservation.');
    }
  }, [location.state]);

  // Save payment details
  const savePayment = (e) => {
    e.preventDefault();

    if (!paymentMethod) {
      alert('Please select a payment method.');
      return;
    }

    const paymentData = {
      reservationId,
      paymentMethod,
      amount,
    };

    console.log('Payment Data:', paymentData);

    PaymentService.createpayments(reservationId, paymentData, auth.accessToken)
      .then((response) => {
        console.log('Response from createPayments API:', response.data);
        navigate('/userlogin'); // Redirect to login or another desired page
      })
      .catch((error) => {
        console.error('Error from createPayments API:', error);
      });
  };

  return (
    <div id="background6" className="p-5">
      <h1 className="text-center">Payment Form</h1>
      <form>
        <div className="form-group">
          <label className="form-label">Payment Method</label>
          
          <select
            className="form-control"
            value={paymentMethod}
            onChange={(e) => setPaymentMethod(e.target.value)}
            required
          >
            <option value="">Select Payment Method</option>
            {paymentMethods.map((method, index) => (
              <option key={index} value={method}>{method}</option>
            ))}
          </select>

        </div>
        <div className="form-group">
          <label className="form-label">Base Amount (₹)</label>
          <input
            type="text"
            className="form-control"
            value={baseAmount.toFixed(2)} // Display base amount
            readOnly
          />
        </div>
        <div className="form-group">
          <label className="form-label">GST (₹)</label>
          <input type="text" className="form-control" value={gst.toFixed(2)} readOnly />
        </div>
        <div className="form-group">
          <label className="form-label">Total Amount (₹)</label>
          <input
            type="text"
            className="form-control"
            value={amount.toFixed(2)} // Display total amount including GST
            readOnly
          />
        </div>
        <button
            type="submit"
            className="btn-grey"
            onClick={(e) => {
              savePayment(e); // Call the savePayment function
              alert("Payment Successful!"); // Display the alert message
            }}
          >
            Make Payment
          </button>

      </form>
    </div>
  );
};
