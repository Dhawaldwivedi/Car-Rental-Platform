import "./register.css"
import React, { useContext, useRef, useState } from 'react'
import AuthContext from '../context/Authprovider'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import UsersService from '../services/UsersService'

export const LoginComponent = () => {
    const {setAuth}=useContext(AuthContext)
    const navigate=useNavigate()
    const userRef=useRef()
    const errorRef=useRef()
    const[username,setUsername]=useState('')
    const[password,setPassword]=useState('')
    const[errMsg,setErrMsg]=useState('')
    const[success,setSuccess]=useState(false)
    //const location=useLocation()
    //const from=location.state?.from?.pathname||"/vehicles"


    const handleSubmit=(e)=>{
        e.preventDefault()
        console.log("Username received from form",username+password)
        const obj={"username":username,"password":password}
        setUsername("")
        setPassword("")
        UsersService.loginUser(obj).then(response=>{
            console.log("response received from login API",response.data)
            const accessToken=response.data.accessToken
            const username=response.data.usersDto.username
            const role=response.data.usersDto.role
            const userId = response.data.usersDto.userId;  // Ensure userId is present in the response
            console.log("User ID from login response:", userId);
            setAuth({username,role,userId,accessToken})
            navigate('/vehicleslist');
            //navigate(from,{replace:true})
        }).catch(error=>{
            console.log("Error from login API",error)
        })
        //setSuccess(true)
    }

  return (
    <div id="background2" className="login">
        <section>
            <p ref={errorRef} className={errMsg?"errmsg":"offscreen"}
                aria-live="assertive">{errMsg}</p>

                <h1>Sign In</h1>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="username">Username</label>
                    <input type='text' id='username' ref={userRef} autoComplete='off' onChange={(e)=>{setUsername(e.target.value)}} value={username} required/>
                    <label htmlFor='password'>Password</label>
                    <input type='password' id='password' onChange={(e)=>{setPassword(e.target.value)}}
                        value={password}
                    />
                    <button>Sign In</button>
                    
                </form>
        </section>
        <div className="already registered">
        <p>Need an account?<br/>
        <span className="line">
                <Link to="/userregister">Register</Link>
        </span>
        </p>
        </div>
    </div>
  )
}
