import axios from "axios"

const BASE_URL="http://localhost:8080/admindataapi/v1"
class AdminService{

    updateadmin(id,admin,token){
        return axios({
            method: 'put',
            url: BASE_URL+'/updateadmin'+id,
            data: admin,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    getAdminByEmail(email,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getAdminByEmail"+email,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    deleteAdminById(id,token){
        return axios({
            method: 'delete',
            url: BASE_URL + "/deleteAdminById" + id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    getAllAdmins(token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getAllAdmins",
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    registerAdmin(admin){
        return axios.post('http://localhost:8080/auth/registeradmin',admin)
    }

    loginAdmin(admin){
        return axios.post('http://localhost:8080/auth/adminlogin',admin)
    }


}
export default new AdminService();