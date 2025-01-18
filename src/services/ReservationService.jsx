import axios from "axios"

const BASE_URL="http://localhost:8080/reservationapi/v1"

class ReservationService{

    createreservations(userId,vehicleId,reservation,token){
        return axios({
            method: 'post',
            url: BASE_URL+"/createreservations/"+userId+"/"+vehicleId,
            data: reservation,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    getReservationsByuserId(id,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getReservationsByuserId/"+id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    updatereservation(id,users,token){
        return axios({
            method: 'put',
            url: BASE_URL+'/updatereservation'+id,
            data: users,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }


}

export default new ReservationService();