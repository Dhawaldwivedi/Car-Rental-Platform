import React, { useEffect, useState } from 'react';
 // Assuming you have a ReviewService for API calls
import { Link, useNavigate } from 'react-router-dom';
import ReviewsService from '../services/ReviewsService';


const ListReviews = () => {
  const [reviews, setReviews] = useState([]);
  const [deleteStatus, setDeleteStatus] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    console.log("useEffect hook invoked");
    ReviewsService.getReviewsByUserID().then((response) => {
      console.log("Response received from API", response.data);
      setReviews(response.data);
    }).catch((error) => {
      console.log(error);
    });
    
  }, [deleteStatus]);

  return (
    <div className='container'>
      {console.log("App rendered")}
      <h2 className='text-center'>Reviews</h2>
      <Link to="/AddReview" className='btn btn-dark mb-2'>Add Review</Link>
      <table className='table table-bordered table-striped table-hover'>
        <thead>
          <tr>
            <th>User ID</th>
            <th>Vehicle ID</th>
            <th>Rating</th>
            <th>Review Text</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {reviews.map((review, key) => (
            <tr key={key}>
              <td>{review.userId}</td>
              <td>{review.vehicleId}</td>
              <td>{review.rating}</td>
              <td>{review.reviewText}</td>
              <td>
                <Link to={'/edit-review/' + review.id} className='btn btn-info'>Update</Link>

              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ListReviews;