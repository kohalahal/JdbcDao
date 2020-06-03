package com.hal;

import java.util.Calendar;
import java.util.Date;

public class User {
	final static java.text.SimpleDateFormat sqlDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");

	private String user_Id;
	private String password;
	private String name;
	private String email;
	private Date in_date;
	private Date up_date;
	
	public User(String user_Id, String password, String name, String email, Date in_date, Date up_date) {
		super();
		this.user_Id = user_Id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.in_date = removeTime(in_date);
		this.up_date = removeTime(up_date);
	}
	public User(String user_Id, String password, String name, String email, Date in_date) {
		super();
		this.user_Id = user_Id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.in_date = removeTime(in_date);
		this.up_date = removeTime(in_date);
	}
	public User(String user_Id, String password, String name, String email) {
		super();
		this.user_Id = user_Id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.in_date = removeTime(new Date());
		this.up_date = removeTime(in_date);
	}
	public String getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getIn_date() {
		return in_date;
	}
	public void setIn_date(Date in_date) {
		this.in_date = in_date;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
	@Override
	public String toString() {
		return "('" + user_Id + "', '" + password + "', '" + name + "', '" + email
				+ "', '" + sqlDateFormat.format(in_date) + "', '" + sqlDateFormat.format(up_date) + "')";
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		User compare = (User)obj;
		System.out.println(toString());
		System.out.println(compare.toString());
		if(user_Id.equals(compare.getUser_Id()) && password.equals(compare.getPassword()) && name.equals(compare.getName()) 
				&& email.equals(compare.getEmail()) && in_date.equals(compare.getIn_date()) && up_date.equals(compare.getUp_date())) {
			return true;
		}
		return false;
	}
	

	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return user_Id.hashCode();
	}
	
    private static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
