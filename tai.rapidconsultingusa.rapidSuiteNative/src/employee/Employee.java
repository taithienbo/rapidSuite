package employee;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class Employee{
	

	/**
	 * 
	 * @param name
	 * @param employee_id
	 * @param pictureLink
	 * @param status
	 * @param lastStatusUpdate
	 * @param gender
	 * @param birthday
	 * @param department
	 * @param position
	 * @param phone
	 * @param email
	 * @param address
	 * @param currentAddress
	 * @param current_longitude
	 * @param current_latitude
	 * @param lastlocationUpdate
	 */
	public Employee(String name, int employee_id, 
			String pictureLink, String status, String lastStatusUpdate,
			String gender, String birthday, String department, String position, 
			String phone, String email, String address, String currentAddress, 
			String current_longitude, String current_latitude, String lastlocationUpdate){
		
		this.name = name;
		this.pictureLink = pictureLink;
		this.status = status;
		this.lastStatusUpdate = lastStatusUpdate;
		this.gender = gender;
		this.birthday = birthday;
		this.department = department;
		this.position = position;
		this.phoneNumber = phone;
		this.email = email;
		this.address = address;
		this.currentAddress = currentAddress;
		this.currentLongitude = current_longitude;
		this.currentLatitude = current_latitude;
		this.lastLocationUpdate = lastlocationUpdate;
		this.employee_id = employee_id;
		
	}
	
	
	public String getName(){
		return name;
	}
	
	
	public String getPictureLink(){
		return pictureLink;
	}
	
	public String getStatus(){
		return status;
	}
	
	
	public String getLastStatusUpdate(){
		return lastStatusUpdate;
	}
	
	
	public String getGender(){
		return gender;
	}
	
	
	public String getBirthday(){
		return birthday;
	}
	
	
	public String getDepartment(){
		return department;
	}
	

	
	
	public String getPosition(){
		return position;
	}
	
	
	public String getPhoneNumber(){
		return phoneNumber;
	}
	
	public String getEmail(){
		return email;
	}
	
	
	public String getAddress(){
		return address;
	}
	
	
	public String getCurrentAddress(){
		return currentAddress;
	}
	
	
	public String getCurrentLongitude(){
		return currentLongitude;
	}
	
	
	public String getCurrentLatitude(){
		return currentLatitude;
	}
	
	
	public String getLastLocationUpdate(){
		return lastLocationUpdate;
	}
	
	
	public int getEmployeeId(){
		return employee_id;
	}
	

	private String name, pictureLink, status, lastStatusUpdate, gender, birthday, department,
			position, phoneNumber, email, address, currentAddress, currentLongitude, currentLatitude, 
			lastLocationUpdate;
	
	private int employee_id;
	
	
	


}





/**
* 		
id	code MUST BE > 100	username	firstname	lastname	gender	dateofbirth	phone	email	
department	position	picture	addrno	street	suite	city	state	
zip	website	status	statusUpdate	locationUpdate	birthday	phoneNumber
latitude	longitude	currentAddrno	currentStreet	currentSuite	
currentCity	currentState	currentZip	employeeID
	sendResponse(200, json_encode($employeeBasicData));
*/