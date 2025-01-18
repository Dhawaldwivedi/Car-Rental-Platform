import React, { useEffect, useState } from 'react';
import ReviewsService from '../services/ReviewsService'; // Assuming you have a ReviewService for API calls
import { useNavigate, Link, useParams } from 'react-router-dom';

const AddReview = () => {
  const [userId, setUserId] = useState("");
  const [vehicleId, setVehicleId] = useState("");
  const [rating, setRating] = useState("");
  const [reviewText, setReviewText] = useState("");
  const navigate = useNavigate();
  const { id } = useParams(); // Assuming 'id' is the review ID for editing

  const changeTitle = () => {
    if (id) {
      return <h2 className='text-center'>Update Review</h2>;
    } else {
      return <h2 className='text-center'>Add Review</h2>;
    }
  };

  useEffect(() => {
    if (id) {
      ReviewsService.getReviewsByUserId(id).then((res) => {
        setUserId(res.data.userId);
        setVehicleId(res.data.vehicleId);
        setRating(res.data.rating);
        setReviewText(res.data.reviewText);
      }).catch((e) => {
        console.log("Error found", e);
      });
    }
  }, [id]);

  useEffect(() => {
    if (vehicleId) {
      ReviewsService.getReviewsByVehicleId(vehicleId).then((res) => {
        setUserId(res.data.userId);
        setVehicleId(res.data.vehicleId);
        setRating(res.data.rating);
        setReviewText(res.data.reviewText);
      }).catch((e) => {
        console.log("Error found", e);
      });
    }
  }, [vehicleId]);

  const saveReview = (e) => {
    e.preventDefault();
    const obj = { userId, vehicleId, rating, reviewText };
    if (id) {
      ReviewsService.updateReview(id, obj).then((res) => {
        console.log("Response Received From API", res.data);
        navigate("/Reviews"); // Redirect to the reviews list
      }).catch((error) => {
        console.log("Error found", error);
      });
    } else {
      ReviewsService.createReviews(obj).then((res) => {
        console.log("Response Received From API", res.data);
        navigate("/Reviews"); // Redirect to the reviews list
      }).catch((error) => {
        console.log("Error found", error);
      });
    }
  };

  return (
    <div>
      <div className='container'>
        <div className='card col-md-6 offset-md-3'>
          {changeTitle()}
          <div className='card-body'>
            <form>
              <div className='form-group mb-2'>
                <label className='form-label'>User  ID</label>
                <input type="text" placeholder='Enter User ID' name='userId'
                  className='form-control' value={userId} onChange={(e) => setUserId(e.target.value)} />
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Vehicle ID</label>
                <input type="text" placeholder='Enter Vehicle ID' name='vehicleId'
                  className='form-control' value={vehicleId} onChange={(e) => setVehicleId(e.target.value)} />
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Rating</label>
                <input type='number' placeholder='Enter Rating (1-5)' 
                  className='form-control' value={rating} name='rating'
                  onChange={(e) => setRating(e.target.value)} min="1" max="5" />
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Review Text</label>
                <textarea placeholder='Enter your review' 
                  className='form-control' value={reviewText} name='reviewText'
                  onChange={(e) => setReviewText(e.target.value)} />
              </div>
              <button className='btn btn-success' onClick={(e) => saveReview(e)}>Save</button>
              <Link to='/Reviews' className='btn btn-danger'>Cancel</Link>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddReview;