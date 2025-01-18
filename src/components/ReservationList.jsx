import React, { useState, useContext, useEffect } from 'react';
import AuthContext from '../context/Authprovider';
import { useNavigate, useParams } from 'react-router-dom';
import ReservationService from '../services/ReservationService';
// import { HeaderComponent } from './HeaderComponent';
import DateTimePicker from 'react-datetime-picker';
import 'react-calendar/dist/Calendar.css'; // Default CSS
import '../dateTime.css'; // Custom CSS for styling
import VehiclesService from '../services/VehiclesService';

export const ReservationList = () => {
  const [reservations, setReservations] = useState([]);
  const { auth } = useContext(AuthContext);
  const [pickupDateTime, setPickupDateTime] = useState(new Date());
  const [dropOffDateTime, setDropOffDateTime] = useState(
    new Date(new Date().setDate(new Date().getDate() + 1)) // Drop-Off defaults to 1 day later
  );
  const [totalCost, setTotalCost] = useState(0); // Store total cost as a Number (float/double equivalent)
  const navigate = useNavigate();
  const { vehicleId } = useParams();
  const { userId } = useParams();
  const [pricePerDay, setPricePerDay] = useState(0);
  
  

  VehiclesService.getVehicldById(vehicleId, auth.accessToken)
    .then((response) => {
        const vehicle = response.data;
        console.log('Vehicle details:', vehicle);
        setPricePerDay(vehicle.pricePerDay);
    })
    .catch((error) => console.error('Error fetching vehicle details:', error));

  

  // Function to calculate total cost based on dates
const handleCalculateCost = () => {
  if (pickupDateTime && dropOffDateTime) {
    const diffInMillis = dropOffDateTime - pickupDateTime;
    const diffInDays = Math.ceil(diffInMillis / (1000 * 60 * 60 * 24));

    if (diffInDays >= 1) {
      const cost = (diffInDays*pricePerDay).toFixed(2); // Ensure 2 decimal places
      setTotalCost(parseFloat(cost)); // Ensure it's stored as float
    } else {
      alert('Drop-off date must be at least 1 day later than pick-up date.');
      setDropOffDateTime(new Date(pickupDateTime.getTime() + 24 * 60 * 60 * 1000)); // Adjust drop-off
      setTotalCost(0); // Reset cost if invalid dates
    }
  }
};


  // Function to save the reservation
  const saveReservation = (e) => {
    e.preventDefault();
    if (!pickupDateTime || !dropOffDateTime || totalCost <= 0) {
      alert('Please enter valid dates and ensure cost is calculated.');
      return;
    }

    // Format the dates by removing milliseconds and 'Z' using string manipulation
    const formatDateTime = (date) => {
      const isoString = date.toISOString();
      return isoString.split('.')[0]; // Remove milliseconds and 'Z'
    };

    const reservationData = {
      userId,
      vehicleId,
      pickupDateTime: formatDateTime(pickupDateTime),
      dropOffDateTime: formatDateTime(dropOffDateTime),
      totalCost: totalCost.toFixed(1), // Pass as a float/double equivalent
    };

    console.log('Data received from the form....', reservationData);
    console.log('Access Token:', auth.accessToken);
    ReservationService.createreservations(userId,vehicleId, reservationData, auth.accessToken).then((response) => {
        console.log('Response from createReservation API:', response.data);
        const reservationId = response.data?.reservationId; // Access reservationId safely using optional chaining
      console.log('Reservation ID:', reservationId);
        navigate("/createpayments/"+reservationId, { state: { baseAmount: totalCost } }); // Pass data to PaymentForm
      })
      .catch((error) => {
        console.log('Error from createReservation API:', error);
      });
  };

  return (
    <div id= "background4" className='p-5'>
      {/* <HeaderComponent /> */}
      <h2>Create Reservation</h2>
      <form>
        <div className='form-group'>
          <label>Pickup Date and Time:</label>
          <DateTimePicker
            onChange={(date) => {
              setPickupDateTime(date);
              handleCalculateCost(); // Recalculate cost when pickup date changes
            }}
            value={pickupDateTime}
          />
          <br />

          <label>Drop-Off Date and Time:</label>
          <DateTimePicker
            onChange={(date) => {
              setDropOffDateTime(date);
              handleCalculateCost(); // Recalculate cost when drop-off date changes
            }}
            value={dropOffDateTime}
          />
          <p>
            Pickup: {pickupDateTime.toLocaleString()} | Drop-Off: {dropOffDateTime.toLocaleString()}
          </p>
        </div>

        <div className='form-group'>
          <label>Total Cost (₹):</label>
          <input type='text' value={totalCost} readOnly />
        </div>
        <button type='submit' className='btn btn-primary' onClick={(e)=>saveReservation(e)}>
          Submit Reservation
        </button>
      </form>
    </div>
  );
};
