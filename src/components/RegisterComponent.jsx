import "./register.css"
import React, { useEffect, useRef, useState } from 'react'
import UsersService from '../services/UsersService'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCheck, faInfoCircle, faTimes } from '@fortawesome/free-solid-svg-icons'
import { Link, useNavigate } from 'react-router-dom'

const FIRST_NAME_REGEX=/^[a-zA-Z]{1,25}$/
const LAST_NAME_REGEX=/^[a-zA-Z]{1,25}$/
const PHONE_NUMBER_REGEX=/^[0-9]{10}$/
const USER_REGEX=/^[a-zA-Z0-9_]{3,23}$/
const EMAIL_REGEX=/^[a-z0-9.]+@gmail\.com$/
const PWD_REGEX=/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/

export const RegisterComponent = () => {
    const navigate = useNavigate();
    //allows to focus on user input when component loads
    const userRef=useRef()
    //allows to focus on error
    const errorRef=useRef()

    //firstname input 
    const [firstname,setfirstname]=useState('')
    const [validfirstName,setvalidfirstName]=useState(false)
    const [firstnameFocus,setfirstNameFocus]=useState(false)

    //lastname input 
    const [lastname,setlastname]=useState('')
    const [validlastName,setvalidlastName]=useState(false)
    const [lastnameFocus,setlastNameFocus]=useState(false)

    //username input 
    const [username,setUsername]=useState('')
    const [validUserName,setvalidUserName]=useState(false)
    const [userFocus,setUserFocus]=useState(false)

    //email input 
    const [email,setemail]=useState('')
    const [validEmail,setvalidEmail]=useState(false)
    const [emailFocus,setEmailFocus]=useState(false)

     //phonenumber input 
     const [phoneNumber,setphoneNumber]=useState('')
     const [validPhonenumber,setvalidPhoneNumber]=useState(false)
     const [phoneNumberFocus,setPhoneNumberFocus]=useState(false)

    //pwd
    const [password,setPassword]=useState('')
    const[validPassword,setValidPassword]=useState(false)
    const[passwordFocus,setPasswordFocus]=useState(false)
    //confirm pwd
    const [matchPassword,setmatchPassword]=useState('')
    const [validMatch,setvalidMatch]=useState(false)
    const[matchFocus,setMatchFocus]=useState(false)
    //errors
    const[errMsg,setErrMsg]=useState('')
    const[success,setSuccess]=useState(false)

    const saveUserRegistration=(e)=>{
        e.preventDefault();
        const userdata={"firstName":firstname,"lastName":lastname,"username":username,"email":email,"password": password,"phoneNumber":phoneNumber}
        UsersService.registerUser(userdata).then(response=>{
        console.log("response received from register API",response.data)
        navigate('/userlogin'); 
        }).catch(error=>{
            console.log("Error received from register API",error)
            if(!error?.response){
                setErrMsg("No Server response")
            }
            else if(error.response?.status===409){
                setErrMsg("Username Taken")
            }
            else{
                setErrMsg("registration failed")
            }
        })
    }

     //first useEffect
useEffect(()=>{
    //set focus on user input
    userRef.current.focus()
    console.log("First useEffect() invoked. Focus set on user input")
},[])

//validates firstname
useEffect(()=>{
    const result=FIRST_NAME_REGEX.test(firstname)
    console.log("Result of regex on first name",result)
    setvalidfirstName(result)
},[firstname])

//validates lastname
useEffect(()=>{
    const result=LAST_NAME_REGEX.test(lastname)
    console.log("Result of regex on last name",result)
    setvalidlastName(result)
},[lastname])

//validates username
useEffect(()=>{
    const result=USER_REGEX.test(username)
    console.log("Result of regex on username",result)
    setvalidUserName(result)
},[username])

//validates email
useEffect(()=>{
    const result=EMAIL_REGEX.test(email)
    console.log("Result of regex on email",result)
    setvalidEmail(result)
},[email])

//validates phonenumber
useEffect(()=>{
    const result=PHONE_NUMBER_REGEX.test(phoneNumber)
    console.log("Result of regex on phonenumber",result)
    setvalidPhoneNumber(result)
},[phoneNumber])



//validates password and checks if it matches to matchpassword
useEffect(()=>{
    const result=PWD_REGEX.test(password)
    console.log("Third useEffect invoked.....result of regex test on username",result)
    setValidPassword(result)
    console.log("validPassword state variable gets the value of regex test",result)
    const matchResult=password===matchPassword
    setvalidMatch(matchResult)
    console.log("validmatch state variable gets the value of regex test",matchResult)
},[password,matchPassword])

useEffect(()=>{
    console.log("Second useeffect get invoked....")
    setErrMsg('')
},[username,password,matchPassword])

  return (
    <div id="background5">
        <section>
            <p ref={errorRef}className={errMsg?"errmsg":"offscreen"} aria-live="assertive">{errMsg}</p>
            <h1>Register</h1>
            <form>
                
                <form className="leftside">
                {/*firstname*/}
                <label htmlFor="firstname">First Name
                    <span className={validfirstName?"valid":"hide"}>
                        <FontAwesomeIcon icon={faCheck}/>
                    </span>
                    <span className={validfirstName||firstname?"hide":"invalid"}>
                         <FontAwesomeIcon icon={faTimes}/>
                    </span>
                </label>
                <input type="text" id="firstname" ref={userRef} autoComplete="off" 
                    onChange={(e)=>{setfirstname(e.target.value)}}
                    required aria-invalid={validfirstName?"false":"true"}
                    aria-describedby="uidnote"
                    onFocus={()=>{setfirstNameFocus(true)}}
                    onBlur={()=>{setfirstNameFocus(false)}}
                />
                <p id="uidnote" className={firstnameFocus && firstname && !validfirstName?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    1 to 25 characters.<br/>
                    Only Letters allowed<br/>
                </p>
                

                {/*username*/}
                <label htmlFor="username">Username
                    <span className={validUserName?"valid":"hide"}>
                        <FontAwesomeIcon icon={faCheck}/>
                    </span>
                    <span className={validUserName||username?"hide":"invalid"}>
                         <FontAwesomeIcon icon={faTimes}/>
                    </span>

                </label>
                <input type="text" id="username" ref={userRef} autoComplete="off" 
                    onChange={(e)=>{setUsername(e.target.value)}}
                    required aria-invalid={validUserName?"false":"true"}
                    aria-describedby="uidnote"
                    onFocus={()=>{setUserFocus(true)}}
                    onBlur={()=>{setUserFocus(false)}}
                />
                <p id="uidnote" className={userFocus && username && !validUserName?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    4 to 24 characters.<br/>
                    Must begin with a letter<br/>
                    Letters,Numbers,Underscores allowed.
                </p>

                {/*password*/}
                <label htmlFor="password" >Password
                    <FontAwesomeIcon icon={faCheck} className={validPassword?"valid":"hide"}/>
                    <FontAwesomeIcon icon={faTimes} className={validPassword||password?"hide":"invalid"}/>

                </label>
                <input type="password" id="password" 
                onChange={(e)=> setPassword(e.target.value)}value={password} required 
                aria-invalid={validPassword?"false":"true"}
                aria-describedby="pwdnote"
                 onFocus={()=>{setPasswordFocus(true)}} 
                 onBlur={()=>{setPasswordFocus(false)}}
                />
                <p id="pwdnote" className={passwordFocus&&password&&!validPassword?"instructions":"Offscreen"}>
                <FontAwesomeIcon icon={faInfoCircle}/>
                8 to 24 characters<br/>
                Must include uppercase & lowercase letters, a number and a special symbol<br/>
                Allowed special characters :
                <span aria-label="exclamation-mark">!</span>
                <span aria-label="at symbol">@</span>
                <span aria-label="hashtagk">#</span>
                <span aria-label="dollar-sign">$</span>
                <span aria-label="percent">%</span>
                </p>

                {/*confirm password*/}
                <label htmlFor="confirm-pwd" >Confirm Password
                    {/*gives tick mark*/}
                    <FontAwesomeIcon icon={faCheck} className={validMatch&& matchPassword?"valid":"hide"}/>
                    <FontAwesomeIcon icon={faTimes} className={validMatch||matchPassword?"hide":"invalid"}/>
                </label>
                <input type="password" id="conform-pwd"
                onChange={(e)=>{setmatchPassword(e.target.value)}}
                value={matchPassword} required
                aria-invalid={validMatch?"false":"true"}
                aria-describedby="confirmed"
                onFocus={()=>{setMatchFocus(true)}}
                onBlur={()=>{setMatchFocus(false)}}/>

                <p id="confirmnote" className={matchFocus&&!validMatch?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    Must match the first password input field
                </p>
                <br/>
                <button disabled={!validUserName||!validPassword||!validMatch?true:false} onClick={(e)=>{saveUserRegistration(e)}}>Sign Up</button>
                </form>

               
                <form className="rightside">
                {/*lastname*/}
                <label htmlFor="lastname">Last Name
                    <span className={validlastName?"valid":"hide"}>
                        <FontAwesomeIcon icon={faCheck}/>
                    </span>
                    <span className={validlastName||lastname?"hide":"invalid"}>
                         <FontAwesomeIcon icon={faTimes}/>
                    </span>

                </label>
                <input type="text" id="lastname" ref={userRef} autoComplete="off" 
                    onChange={(e)=>{setlastname(e.target.value)}}
                    required aria-invalid={validlastName?"false":"true"}
                    aria-describedby="uidnote"
                    onFocus={()=>{setlastNameFocus(true)}}
                    onBlur={()=>{setlastNameFocus(false)}}
                />
                <p id="uidnote" className={lastnameFocus && lastname && !validlastName?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    1 to 25 characters.<br/>
                    Only Letters allowed<br/>
                </p>

                
                {/*email*/}
                <label htmlFor="email">Email
                    <span className={validEmail?"valid":"hide"}>
                        <FontAwesomeIcon icon={faCheck}/>
                    </span>
                    <span className={validEmail||email?"hide":"invalid"}>
                         <FontAwesomeIcon icon={faTimes}/>
                    </span>

                </label>
                <input type="text" id="email" ref={userRef} autoComplete="off" 
                    onChange={(e)=>{setemail(e.target.value)}}
                    required aria-invalid={validEmail?"false":"true"}
                    aria-describedby="uidnote"
                    onFocus={()=>{setEmailFocus(true)}}
                    onBlur={()=>{setEmailFocus(false)}}
                />
                <p id="uidnote" className={emailFocus && email && !validEmail?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    should be in email format Eg:abc@gmail.com<br/>
                </p>

                {/*phonenumber*/}
                <label htmlFor="phonenumber">Phone Number
                    <span className={validPhonenumber?"valid":"hide"}>
                        <FontAwesomeIcon icon={faCheck}/>
                    </span>
                    <span className={validPhonenumber||phoneNumber?"hide":"invalid"}>
                         <FontAwesomeIcon icon={faTimes}/>
                    </span>

                </label>
                <input type="text" id="phonenumber" ref={userRef} autoComplete="off" 
                    onChange={(e)=>{setphoneNumber(e.target.value)}}
                    required aria-invalid={validPhonenumber?"false":"true"}
                    aria-describedby="uidnote"
                    onFocus={()=>{setPhoneNumberFocus(true)}}
                    onBlur={()=>{setPhoneNumberFocus(false)}}
                />
                <p id="uidnote" className={phoneNumberFocus && phoneNumber && !validPhonenumber?"instructions":"offscreen"}>
                    <FontAwesomeIcon icon={faInfoCircle}/>
                    Must be 10 digits<br/>
                    Numbers only allowed<br/>
                </p>
                </form>

            </form>
            
        </section>
        <div className="already">
            <p>Already Registered?<br/>
            <span className="line">
                <Link to="/userlogin">Sign In</Link>
            </span>
            </p>
        </div>
        
    </div>
  )
}
