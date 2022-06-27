package Studentlogin.helper;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class RegistrationForm {
	
	private String fullname;
	private String email;
	private Date dob;
	private String password;
	private String program;
	private String conf_pass;
	private String batch;
	private MultipartFile profile;
	private String gender;
	
	public RegistrationForm()
	{
		
	}

	public String getFullname() {
		return fullname;
		
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
		
	}

	public String getEmail() {
		return email;
		
	}

	public void setEmail(String email) {
		this.email = email;
		
	}

	public Date getDob() {
		return dob;
		
	}

	public void setDob(Date dob) {
		this.dob = dob;
		
	}

	public String getPassword() {
		return password;
		
	}

	public void setPassword(String password) {
		this.password = password;
		
	}

	public String getProgram() {
		return program;
		
	}

	public void setProgram(String program) {
		this.program = program;
		
	}

	public String getConf_pass() {
		return conf_pass;
		
	}

	public void setConf_pass(String conf_pass) {
		this.conf_pass = conf_pass;
		
	}

	public String getBatch() {
		return batch;
		
	}

	public void setBatch(String batch) {
		this.batch = batch;
		
	}



	public String getGender() {
		return gender;
		
	}

	public void setGender(String gender) {
		this.gender = gender;
		
	}

	public MultipartFile getProfile() {
		return profile;
		
	}

	public void setProfile(MultipartFile profile) {
		this.profile = profile;
		
	}

}
