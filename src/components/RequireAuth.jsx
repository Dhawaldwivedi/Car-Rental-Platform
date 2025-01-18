import React, { useContext } from 'react'
import AuthContext from '../context/Authprovider'
import { Navigate, Outlet, useLocation } from 'react-router-dom'

 const RequireAuth = () => {
    const{auth}=useContext(AuthContext)
    const location=useLocation()
  return (
    auth?.accessToken?<Outlet/>:<Navigate to="/welcome" state={{from:location}} replace/>
  );
}
export default RequireAuth;
