
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { UserList } from './components/UserList';
import { RegisterComponent } from './components/RegisterComponent';
import { LoginComponent } from './components/LoginComponent';
import RequireAuth from './components/RequireAuth';
import { VehicleList } from './components/VehicleList';
import { ReservationList } from './components/ReservationList';
import { PaymentForm } from './components/PaymentForm';
import { WelcomePage } from './components/WelcomePage';
import { AdminLoginComponent } from './components/AdminLoginComponent';
import { AdminRegisterComponent } from './components/AdminRegisterComponent';
// import AddReview from './components/AddReview';
// import ListReviews from './components/ListReviews';



function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <div className='AppNew'>
          <Routes>
            <Route path="/" element={<WelcomePage />} />
            <Route path="/welcome" element={<WelcomePage />} />

            {/* Protect routes requiring authentication */}
            <Route element={<RequireAuth />}>
              <Route path="/userslist" element={<UserList />} />
              <Route path="/vehicleslist" element={<VehicleList />} />
              <Route path="/createreservation/:userId/:vehicleId" element={<ReservationList />} />
              <Route path="/createpayments/:reservationId" element={<PaymentForm />} />
              {/* <Route path="/reviews" element={<ListReviews/>} /> 
              <Route path="/addreview" element={<AddReview/>} />  */}
            </Route>

            <Route path="/userregister" element={<RegisterComponent />} />
            <Route path="/userlogin" element={<LoginComponent />} />
            <Route path="/adminregister" element={<AdminRegisterComponent />} />
            <Route path="/adminlogin" element={<AdminLoginComponent />} />
          </Routes>
        </div>
      </BrowserRouter>
    </div>
  );
}

export default App;