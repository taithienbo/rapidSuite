package employee;


import java.io.Serializable;


// Employee class implements Serializable so that an Employee object can 
// be passed to an activity via Intent 
public class Employee implements Serializable
{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String name, pictureLink, status, lastStatusUpdate, gender, birthday, department,
	position, phoneNumber, email, address, currentAddress, currentLongitude, currentLatitude, 
	lastLocationUpdate;
	
	
	public static final String RETRIEVAL_KEY = "Employee Object";
	public static final String RETRIEVAL_KEY_OTHER = "Employee";


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


	public String getName()
	{
		return name;
	}


	public String getPictureLink()
	{
		return pictureLink;
	}

	public String getStatus()
	{
		return status;
	}


	public String getLastStatusUpdate()
	{
		return lastStatusUpdate;
	}


	public String getGender()
	{
		return gender;
	}


	public String getBirthday()
	{
		return birthday;
	}


	public String getDepartment()
	{
		return department;
	}




	public String getPosition()
	{
		return position;
	}


	public String getPhoneNumber()
	{
		StringBuilder answer = new StringBuilder("(");
		answer.append(phoneNumber.substring(0, 3))
		.append(")")
		.append("-")
		.append(phoneNumber.substring(3,6))
		.append("-")
		.append(phoneNumber.substring(6,10));
		return answer.toString();
	}

	public String getEmail(){
		return email;
	}


	public String getAddress()
	{
		return address;
	}


	public String getCurrentAddress()
	{
		return currentAddress;
	}


	public String getCurrentLongitude()
	{
		return currentLongitude;
	}


	public String getCurrentLatitude()
	{
		return currentLatitude;
	}


	public String getLastLocationUpdate()
	{
		return lastLocationUpdate;
	}


	public int getEmployeeId()
	{
		return employee_id;
	}


	
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