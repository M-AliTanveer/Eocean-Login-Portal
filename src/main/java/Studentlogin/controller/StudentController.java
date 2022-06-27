package Studentlogin.controller;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import Studentlogin.helper.RegistrationForm;
import Studentlogin.model.*;
import Studentlogin.service.AccountsService;
import Studentlogin.service.StudentsService;
import Studentlogin.helper.EmailService;

//import net.javaguides.springmvc.service.CustomerService;

@Controller
public class StudentController {

	
	boolean LoggedIn=false;
	Accounts LoggedAccount = null;
	@Autowired 
	PasswordEncoder PasswordEncoder;
	@Autowired
    private StudentsService studentService;
	@Autowired
	private AccountsService accountService;
	@Autowired
	private EmailService EmailService;
	private Logger logger = Logger.getLogger(StudentController.class);
	
	@RequestMapping("/")
	public ModelAndView IndexPage()
	{
		ModelAndView mv = new ModelAndView();
		Accounts currAccount;
		String gender;
		byte[] encodeBase64;
		String base64Encoded;
		try
		{
			logger.info("Entered StudentController::IndexPage controller.");
			if(LoggedIn==false)
			{
				
				mv.setViewName("/view/index");
				
			}
			else
			{
				currAccount = LoggedAccount;
				mv.setViewName("/view/profile");
				mv.addObject("fullname", currAccount.getStudents().getFullname());
				mv.addObject("email", currAccount.getEmail());
				mv.addObject("DOB", currAccount.getStudents().getDateOfBirth());
				if(currAccount.getStudents().getGender()=='M')
					gender = "Male";
				else
					gender = "Female";
				mv.addObject("gender", gender);
				mv.addObject("batch", currAccount.getStudents().getBatch());
				mv.addObject("program", currAccount.getStudents().getProgram());
				encodeBase64 = Base64.getEncoder().encode(currAccount.getAvatar());
				base64Encoded = new String(encodeBase64, "UTF-8");
				mv.addObject("profilepicture", base64Encoded);
			}
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::IndexPAge controller: " + e.toString());
		}
		logger.info("Exited StudentController::IndexPage Controller!");
		return mv;
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ModelAndView RegisterUser(@ModelAttribute("form") RegistrationForm form)
	{
		ModelAndView mv = new ModelAndView();
		File file;
		FileInputStream input;
		MultipartFile multipartFile;
		Students newStudent;
		String encodedPass;
		Accounts newAccount;
		long OTP;
		//account for no image chosen
		try 
		{
			logger.info("Entered StudentController::RegisterUser controller.");
			if(form.getProfile().getSize()==0)
			{
				file = new File("D:/New Eclipse Workspace/Studentlogin/src/main/webapp/resources/temporary/avatar.jpg");
				input = new FileInputStream(file);
				multipartFile = new MockMultipartFile("file",
				            file.getName(), "text/plain", IOUtils.toByteArray(input));
				form.setProfile(multipartFile);
			}
				
			newStudent = new Students(form.getEmail(), form.getFullname(), form.getBatch(), form.getGender().charAt(0), form.getDob(),form.getProgram(),new Date(),null,null,null);
			encodedPass = PasswordEncoder.encode(form.getConf_pass());
			newAccount = new Accounts(null,encodedPass, form.getProfile().getBytes(),0,null,null,null,null,false, new Date(),"User Using Site",null,null,form.getEmail());
			studentService.SaveStudent(newStudent);
			newAccount.setStudents(newStudent);
			OTP = OTPGenerator();
			newAccount.setOtp((int)OTP);
			newAccount.setOtpExpireTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
			accountService.SaveAccount(newAccount);
			EmailService.sendSimpleMessage(form.getEmail(), "Confirm Your Registration!", Long.toString(OTP));
			mv.setViewName("/view/OTP");
			LoggedAccount = newAccount;
	//		LoggedIn=true;
		
		} 
		catch (Exception e) {
			logger.error("Exception in StudentController::RegisterUser controller: " + e.toString());
			
		}
		logger.info("Exited StudentController::RegisterUser controller.");
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView LoginUser(@RequestParam("email") String email, @RequestParam("password") String password)
	{
		ModelAndView mv = new ModelAndView();
		Accounts currAccount;
		long OTP;
		String gender;
		byte[] encodeBase64;
		String base64Encoded;
		try
		{	
			logger.info("Entered StudentController::LoginUser controller.");
			
			currAccount = accountService.GetAccount(email);
			if(currAccount==null)
			{
				mv.addObject("error_login", "There exists no account with the given email!");
				mv.setViewName("/view/index");
			}
			else if(currAccount!=null && !PasswordEncoder.matches(password, currAccount.getPass()))
			{
				currAccount.setIncorrectCount(currAccount.getIncorrectCount()+1);
				if(currAccount.getIncorrectCount()>=3)
				{
					currAccount.setActiveStatus(false);
					mv.addObject("error_login", "Your Account has been locked due to too many incorrect tries! Contact Administrator or Reset Your Password.");
					mv.setViewName("/view/index");
				}
				else
				{
					accountService.UpdateAccount(currAccount);
					mv.addObject("error_login", "Incorrect Password Entered! Tries Left: " + Integer.toString(3-currAccount.getIncorrectCount()));	
					mv.setViewName("/view/index");
				}
			}
			else if(currAccount!=null && currAccount.getActiveStatus()==false)
			{
				OTP = OTPGenerator();
				currAccount.setOtp((int)OTP);
				currAccount.setOtpExpireTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
				accountService.UpdateAccount(currAccount);
				EmailService.sendSimpleMessage(currAccount.getEmail(), "Unlock Your Account!", Long.toString(OTP));
				mv.setViewName("/view/OTP");
				LoggedAccount = currAccount;

			}
			else
			{
				LoggedIn = true;
				LoggedAccount = currAccount;
				mv.setViewName("/view/profile");
				mv.addObject("fullname", currAccount.getStudents().getFullname());
				mv.addObject("email", currAccount.getEmail());
				mv.addObject("DOB", currAccount.getStudents().getDateOfBirth());
				
				if(currAccount.getStudents().getGender()=='M')
					gender = "Male";
				else
					gender = "Female";
				mv.addObject("gender", gender);
				mv.addObject("batch", currAccount.getStudents().getBatch());
				mv.addObject("program", currAccount.getStudents().getProgram());

				
				encodeBase64 = Base64.getEncoder().encode(currAccount.getAvatar());
				base64Encoded = new String(encodeBase64, "UTF-8");
				mv.addObject("profilepicture", base64Encoded);
				
				currAccount.setLastLogin(new Date());
				currAccount.setIncorrectCount(0);
				currAccount.setOtp(null);
				currAccount.setOtpExpireTime(null);
				accountService.UpdateAccount(currAccount);
			}
				
			
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::LoginUser controller: " + e.toString());
		}
		logger.info("Exited StudentController::LoginUser controller.");
		return mv;
		
	}
	
	@RequestMapping("/register")
	public ModelAndView RegisterForm(@RequestParam("fullname") String fullname, @RequestParam("email") String email, @RequestParam("password") String password) //For Debugging controller loading only
	{
		ModelAndView mv = new ModelAndView();
		Accounts currAccount;
		try 
		{
			logger.info("Entered StudentController::RegisterForm function");
			currAccount = accountService.GetAccount(email);
			
			if(currAccount!=null)
			{
				mv.setViewName("/view/index");
				mv.addObject("isError",
		                "signup");
				mv.addObject("error_signup","There is already an account associated with the given email!");
			}
			else
			{
			    mv.setViewName("/view/register");
			    mv.addObject("fullname",
			                 fullname);
			    mv.addObject("email",
			    		email);
			    mv.addObject("password",
			    		password);
			}
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::RegisterForm controller: " + e.toString());
		}
	    logger.info("Exited StudentController::RegisterForm function");
	    return mv;
	}
	
	@RequestMapping("/forgot_pass")
	public ModelAndView ForgotPassword()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			logger.info("Entered StudentController::ForgotPassword controller.");
		
			mv.setViewName("/view/forgot_pass");
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::ForgotPassword controller: " + e.toString());
		}
		logger.info("Exited StudentController::ForgotPassword controller.");
		return mv;

	}
	
	@RequestMapping("/logout")
	public ModelAndView LogOut()
	{
		ModelAndView mv = new ModelAndView();
		try
		{
			logger.info("Entered StudentController::LogOut controller.");
			LoggedIn = false;
			LoggedAccount.setLastLogout(new Date());
			accountService.UpdateAccount(LoggedAccount);
			LoggedAccount = null;
			mv.setViewName("/view/index");
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::LogOut controller: " + e.toString());
		}
		logger.info("Exited StudentController::LogOut controller.");
		return mv;
	}

	@RequestMapping("/verifyOTP")
	public ModelAndView VerifyOTP(@RequestParam("OTP") int OTP)
	{
		ModelAndView mv = new ModelAndView();
		int savedOTP;
		Date OtpExpiry;
		long generatedOTP;
		try {
			logger.info("Entered StudentController::VerifyOTP controller.");
			savedOTP = LoggedAccount.getOtp();
			OtpExpiry = LoggedAccount.getOtpExpireTime();
			if(System.currentTimeMillis() > OtpExpiry.getTime() + TimeUnit.MINUTES.toMillis(5) || OTP!=savedOTP)
			{
				//expired or wrong
				mv.addObject("error", "The OTP is incorrect or expired! Another was sent to your email!");
				generatedOTP = OTPGenerator();
				Accounts currAccount = LoggedAccount;
				currAccount.setOtp((int)generatedOTP);
				currAccount.setOtpExpireTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
				accountService.UpdateAccount(currAccount);
				EmailService.sendSimpleMessage(currAccount.getEmail(), "Verify OTP", Long.toString(generatedOTP));
				mv.setViewName("/view/OTP");
			}
			else
			{
				if(OTP == savedOTP)
				{
					LoggedAccount.setOtp(null);
					LoggedAccount.setOtpExpireTime(null);
					LoggedAccount.setActiveStatus(true);
					LoggedAccount.setIncorrectCount(0);
					accountService.UpdateAccount(LoggedAccount);
					LoggedAccount = null;
					mv.addObject("error_login", "Account Activated! Please Log in to access.");
					mv.addObject("success",true);
					mv.setViewName("/view/index");
					
				}
			}
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::VerifyOTP controller: " + e.toString());
		}
		logger.info("Exited StudentController::VerifyOTP controller.");
		return mv;
	}
	
	@RequestMapping("/passreset")
	public ModelAndView PasswordReset(@RequestParam(value="email", required=false) String email, @RequestParam(value = "OTP", required = false) Integer OTP, @RequestParam(value="password", required=false) String password)
	{
		ModelAndView mv = new ModelAndView();
		Accounts currAccount;
		long generatedOTP;
		int savedOTP;
		Date OtpExpiry;
		String encodedPass;
		try 
		{
			logger.info("Entered StudentController::PasswordReset controller.");
			if(password==null && OTP==null)
			{
				currAccount = accountService.GetAccount(email);
				LoggedAccount=currAccount;
				mv.addObject("sent", true);
				generatedOTP = OTPGenerator();
				currAccount.setOtp((int)generatedOTP);
				currAccount.setOtpExpireTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
				accountService.UpdateAccount(currAccount);
				EmailService.sendSimpleMessage(email, "Reset Your Password", Long.toString(generatedOTP));
				mv.addObject("email", email);
				mv.addObject("isemail", true);
				mv.setViewName("/view/forgot_pass");
			}
			else
			{
				currAccount = accountService.GetAccount(LoggedAccount.getEmail());
				savedOTP = currAccount.getOtp();
				OtpExpiry = currAccount.getOtpExpireTime();
				if((System.currentTimeMillis() < OtpExpiry.getTime() + TimeUnit.MINUTES.toMillis(5)) && savedOTP == OTP)
				{
					encodedPass = PasswordEncoder.encode(password);
					currAccount.setPass(encodedPass);
					currAccount.setIncorrectCount(0);
					currAccount.setActiveStatus(true);
					currAccount.setOtp(null);
					currAccount.setOtpExpireTime(null);
					currAccount.setUpdatedOn(new Date());
					currAccount.setUpdatedBy("User through Site");
					accountService.UpdateAccount(currAccount);
					mv.setViewName("/view/index");
					mv.addObject("success", true);
					mv.addObject("error_login", "Password Has been Reset! Please Login to access account!");
					LoggedAccount=null;
				}
				else
				{
					mv.addObject("error", "Incorrect OTP or OTP Expired! OTP Sent again to your email!");
					mv.setViewName("/view/forgot_pass");
					mv.addObject("email", email);
					mv.addObject("isemail", true);
					mv.addObject("sent", true);
				}
							
			}
		}
		catch(Exception e)
		{
			logger.error("Exception in StudentController::PasswordReset controller: " + e.toString());
		}
		logger.info("Exited StudentController::PasswordReset controller.");		
		return mv;
	}
	
	@RequestMapping("/resendOTP")
	public ModelAndView ResendOTP()
	{
		long generatedOTP;
		Accounts currAccount;
		ModelAndView mv= new ModelAndView();
		try 
		{
			logger.info("Entered StudentController::ResendOTP controller.");
			currAccount = accountService.GetAccount(LoggedAccount.getEmail());
			generatedOTP = OTPGenerator();
			currAccount.setOtp((int)generatedOTP);
			currAccount.setOtpExpireTime(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)));
			accountService.UpdateAccount(currAccount);
			EmailService.sendSimpleMessage(currAccount.getEmail(), "Reset Your Password", Long.toString(generatedOTP));
			mv.setViewName("/view/forgot_pass");
			mv.addObject("email", currAccount.getEmail());
			mv.addObject("isemail", true);
			mv.addObject("sent", true);
			mv.addObject("error", "New OTP sent");
		}
		catch(Exception e)
		{
			logger.error("Exceptio in StudentController::ResendOTP controller: " + e.toString());
		}
		logger.info("Exited StudentController::ResendOTP controller."); 
		return mv;
	}
	
	private long OTPGenerator()
	{
		Random random = new Random();
		long OTP = random.nextInt(10000-1000)+1000;
		return OTP;

	}
}