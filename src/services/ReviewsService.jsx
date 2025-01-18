import axios from "axios"

const BASE_URL="http://localhost:8080/reviewsapi/v1"

class ReviewsService{

  createReviews(review,token){
    return axios({
        method:'post',
        url: BASE_URL+"/createreview",
        data: review ,
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Authorization': `Bearer ${token}`
        }
    });
  }

  getReviewsByUserID(id,token){
    return axios({
        method: 'get',
        url: BASE_URL+"/getReviewsByuserId"+id,
        responseType: 'json',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Authorization': `Bearer ${token}`
        }

    });
  }
  getReviewsByVehicleID(id,token){
    return axios({
        method: 'get',
        url: BASE_URL+"/getReviewsByvehicleId"+id,
        responseType: 'json',
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Authorization': `Bearer ${token}`
        }

    });
  }
}
export default new ReviewsService();