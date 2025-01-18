import "./register.css"
import React, { useContext, useRef, useState } from 'react'
import AuthContext from '../context/Authprovider'
import { Link, useLocation, useNavigate } from 'react-router-dom'
import AdminService from "../services/AdminService"

export const AdminLoginComponent = () => {
    const {setAuth}=useContext(AuthContext)
    const navigate=useNavigate()
    const adminRef=useRef()
    const errorRef=useRef()
    const[adminName,setAdminName]=useState('')
    const[password,setPassword]=useState('')
    const[errMsg,setErrMsg]=useState('')
    const[success,setSuccess]=useState(false)

  const handleSubmit=(e)=>{
          e.preventDefault()
          console.log("Adminname received from form",adminName+password)
          const obj={"adminName":adminName,"password":password}
          setAdminName("")
          setPassword("")
          AdminService.loginAdmin(obj).then(response=>{
              console.log("response received from login API",response.data)
              const accessToken=response.data.accessToken
              console.log("accessTiken",accessToken)

              const adminName=response.data.adminDto.adminName
              const role=response.data.adminDto.role
              const adminId = response.data.adminDto.adminId;  // Ensure adminId is present in the response
              console.log("Admin ID from login response:", adminId);
              setAuth({adminName,role,adminId,accessToken})
              navigate('/userslist');
              //navigate(from,{replace:true})
          }).catch(error=>{
              console.log("Error from login API",error)
          })
          //setSuccess(true)
      }
  
    return (
      <div id="background3"className="p-5">
          <section>
              <p ref={errorRef} className={errMsg?"errmsg":"offscreen"}
                  aria-live="assertive">{errMsg}</p>
  
                  <h1>Sign In</h1>
                  <form onSubmit={handleSubmit}>
                      <label htmlFor="adminName">Admin Name</label>
                      <input type='text' id='adminName' ref={adminRef} autoComplete='off' onChange={(e)=>{setAdminName(e.target.value)}} value={adminName} required/>
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
                  <Link to="/adminregister">Register</Link>
          </span>
          </p>
          </div>
      </div>
  )
}
