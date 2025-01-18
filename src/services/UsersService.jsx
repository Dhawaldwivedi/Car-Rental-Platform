import axios from "axios"

const BASE_URL="http://localhost:8080/userapi/v1"
class UsersService{

    createUser(user,token){
        return axios({
            method: 'post',
            url: BASE_URL+"/createusers",
            data: user,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    updateUser(id,user,token){
        return axios({
            method: 'put',
            url: BASE_URL+'/updateuser'+id,
            data: user,       // Include the object in the request
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        });
    }

    getUserById(id,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getUserById"+id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    getUserByEmail(email,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getUserByEmail"+email,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    deleteuserbyId(id,token){
        return axios({
            method: 'delete',
            url: BASE_URL + "/deleteuserbyId" + id,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    getallUsers(token){
        return axios({
            method: 'get',
            url: BASE_URL+"/getallUsers",
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
        })
    }

    isemailregistered(email,token){
        return axios({
            method: 'get',
            url: BASE_URL+"/isemailregistered"+email,
            responseType: 'json',
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Authorization': `Bearer ${token}`
            }
 
        })
    }

    registerUser(user){
        return axios.post('http://localhost:8080/auth/registerUser',user)
    }

    loginUser(user){
        return axios.post('http://localhost:8080/auth/login',user)
    }

}
export default new UsersService();