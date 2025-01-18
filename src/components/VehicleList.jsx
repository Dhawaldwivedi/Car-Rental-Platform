import "../App.css"
import React, { useContext, useEffect, useState } from 'react'
import AuthContext from '../context/Authprovider'
import VehiclesService from '../services/VehiclesService'
import { useNavigate } from "react-router-dom"

export const VehicleList = () => {
    const [vehicles, setVehicles] = useState([])
    const{auth}=useContext(AuthContext)
    const navigate=useNavigate()
    const [searchId, setSearchId] = useState(''); 
    const [searchStatus, setSearchStatus] = useState(''); 
    const [filteredVehicleById, setFilteredVehicleById] = useState(null);
    const [filteredVehiclesByStatus, setFilteredVehiclesByStatus] = useState([]);


    useEffect(()=>{
        console.log("useEffect invoked....")
        console.log("auth.accessToken")
        VehiclesService.getallVehicles(auth.accessToken).then((response)=>{
            console.log("response from getallvehicles API.....",response.data)
            setVehicles(response.data)

        }).catch((error)=>{console.log("error from API ",error)})
      },[])
      //[] -> dependency array is used to stop the unlimited execution

      const handleSearchById = () => {
        if (searchId.trim() === '') {
            alert('Please enter a valid Vehicle ID');
            return;
        }

        VehiclesService.getVehicldById(searchId, auth.accessToken)
            .then((response) => {
                console.log("Vehicle found: ", response.data);
                setFilteredVehicleById(response.data);
                setFilteredVehiclesByStatus([]); 
            })
            .catch((error) => {
                console.error("Error fetching vehicle by ID: ", error);
                setFilteredVehicleById(null);
                alert('Vehicle not found!');
            });
    };

    const handleSearchByStatus = () => {
      const allowedStatuses = ['Available', 'Reserved', 'Unavailable'];
      
      if (searchStatus.trim() === '') {
          alert('Please enter a valid status (e.g., Available, Reserved,Unavailable)');
          return;
      }
  
      // Check for case-insensitive match
      const normalizedStatus = searchStatus.trim().toLowerCase();
      const isValidStatus = allowedStatuses.some(status => status.toLowerCase() === normalizedStatus);
  
      if (!isValidStatus) {
          alert('Please enter a valid status (Available, Reserved, Unavailable)');
          return;
      }
  
      VehiclesService.getVehicldByStatus(searchStatus, auth.accessToken)
          .then((response) => {
              console.log("Vehicles found by status: ", response.data);
              setFilteredVehiclesByStatus(response.data);
              setFilteredVehicleById(null);
          })
          .catch((error) => {
              console.error("Error fetching vehicles by status: ", error);
              setFilteredVehiclesByStatus([]);
              alert('No vehicles found for the specified status!');
          });
  };
  

    const handleShowAllVehicles = () => {
      setFilteredVehicleById(null);
      setFilteredVehiclesByStatus([]);
      setSearchId('');
      setSearchStatus('');
  };

      //JSX 
    return (<>
            

        <div id="background3" className="container">
            {console.log("App rendered")}
          <h2 className='text-center'>Vehicles Data</h2>

          {/* Search Bar for Vehicle ID */}
          <div className="search-container">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter Vehicle ID"
                        value={searchId}
                        onChange={(e) => setSearchId(e.target.value)}
                    />
                    <button className="btn-grey" onClick={handleSearchById}>
                        Search by ID
                    </button>
                </div>

                {/* Search Bar for Vehicle Status */}
                <div className="search-container mt-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Enter Vehicle Status (Available/Reserved/Unavailable)"
                        value={searchStatus}
                        onChange={(e) => setSearchStatus(e.target.value)}
                    />
                    <button className="btn-grey" onClick={handleSearchByStatus}>
                        Search by Status
                    </button>
                </div>

                <button onClick={handleShowAllVehicles}>List of All Vehicles</button>

               {/* Display filtered vehicle if searched by ID */}
               {filteredVehicleById && (
                   <div className="search-result mt-4">
                        <h4>Vehicle Details</h4>
                    <table className="table table-bordered table-striped table-hover">
                    <thead>
                    <tr>
                    <th>ID</th>
                    <th>Make</th>
                    <th>Model</th>
                    <th>Image URL</th>
                    <th>Year</th>
                    <th>Capacity</th>
                    <th>Price Per Day</th>
                    <th>Availability Status</th>
                    <th>Reservation</th>
                    </tr>
                    </thead>
                 <tbody>
                 <tr>
                    <td>{filteredVehicleById.vehicleId}</td>
                    <td>{filteredVehicleById.make}</td>
                    <td>{filteredVehicleById.model}</td>
                    <td>{filteredVehicleById.imageURL}</td>
                    <td>{filteredVehicleById.year}</td>
                    <td>{filteredVehicleById.capacity}</td>
                    <td>{filteredVehicleById.pricePerDay}</td>
                    <td>{filteredVehicleById.availabilityStatus}</td>
                    <td>
                        <button
                            className="reserve-button"
                            disabled={filteredVehicleById.availabilityStatus !== 'Available'}
                            onClick={() =>
                                navigate('/createreservation/' + auth.userId + '/' + filteredVehicleById.vehicleId)
                            }
                        >
                            Reserve
                        </button>
                    </td>
                  </tr>
                   </tbody>
                  </table>
                </div>
              )}


            {/* Display vehicles filtered by status */}
            {filteredVehiclesByStatus.length > 0 && (
                <div className="search-result mt-4">
                    <h4>Vehicles with Status: {searchStatus}</h4>
                    <table className="table table-bordered table-striped table-hover">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Make</th>
                                <th>Model</th>
                                <th>Image URL</th>
                                <th>Year</th>
                                <th>Capacity</th>
                                <th>Price Per Day</th>
                                <th>Availability Status</th>
                                <th>Reservation</th>
                            </tr>
                        </thead>
                        <tbody>
                            {filteredVehiclesByStatus.map((vehicle, key) => (
                                <tr key={key}>
                                    <td>{vehicle.vehicleId}</td>
                                    <td>{vehicle.make}</td>
                                    <td>{vehicle.model}</td>
                                    <td>{vehicle.imageURL}</td>
                                    <td>{vehicle.year}</td>
                                    <td>{vehicle.capacity}</td>
                                    <td>{vehicle.pricePerDay}</td>
                                    <td>{vehicle.availabilityStatus}</td>
                                    <td>
                                        <button
                                            className="reserve-button"
                                            disabled={vehicle.availabilityStatus !== 'Available'}
                                            onClick={() =>
                                                navigate('/createreservation/' + auth.userId + '/' + vehicle.vehicleId)
                                            }
                                        >
                                            Reserve
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}

          {/* Display all vehicles */}
          {(filteredVehicleById === null && filteredVehiclesByStatus.length === 0) && (
                <div>
          <h4 className="mt-4">All Vehicles</h4>
          <table className='table table-bordered table-striped table-hover'>
              <tr>
                <th>ID</th>
                <th>Make</th>
                <th>Model</th>
                <th>Image URL</th>
                <th>Year</th>
                <th>Capacity</th>
                <th>Price Per Day</th>
                <th>Availability Status</th>
                <th>Reservation</th>
              </tr>
            <tbody>
              {vehicles.map((vehicle,key) => (<tr key={key}>
                  <td>{vehicle.vehicleId}</td>
                  <td>{vehicle.make}</td>
                  <td>{vehicle.model}</td>
                  <td>{vehicle.imageURL}</td>
                  <td>{vehicle.year}</td>
                  <td>{vehicle.capacity}</td>
                  <td>{vehicle.pricePerDay}</td>
                  <td>{vehicle.availabilityStatus}</td>
                  <td>
                <button className="reserve-button" disabled={vehicle.availabilityStatus !== 'Available'} onClick={() => navigate('/createreservation/'+auth.userId+'/'+vehicle.vehicleId)}>
                  Reserve
                </button>
              </td>
                </tr>))}
            </tbody>
          </table>
        </div>
        )}
        </div>

        </>
    
      )
    }
    
    
