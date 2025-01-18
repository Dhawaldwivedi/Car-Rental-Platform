import axios from "axios"

const BASE_URL="http://localhost:8080/vehicleapi/v1"

class VehiclesService{

    createvehicle(vehicle,token){
        return axios({
            method: 'post',
            url: BASE_URL+"/createvehicle",
            data: vehicle,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    updatevehicle(id,vehicle,token){
        return axios({
            method: 'put',
            url: BASE_URL+'/updatevehicle/'+id,
            data: vehicle,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    getVehicldById(id,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getVehicldById/"+id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    getVehicldByStatus(status,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getVehicldByStatus/"+status,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    getallVehicles(token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getallVehicles",
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    deletevehiclebyId(id,token){
        return axios({
            method: 'delete',
            url: BASE_URL + "/deletevehiclebyId/" + id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

}
export default new VehiclesService();