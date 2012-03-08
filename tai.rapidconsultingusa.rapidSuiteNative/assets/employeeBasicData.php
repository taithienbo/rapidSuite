<?php
include_once("http.php");

class EmployeeData
{
	private $db;
	function __construct()
	{
		if (isset($_POST["dburl"]) && isset($_POST["dbuser"]) && isset($_POST["dbpass"]) && isset($_POST["dbname"]))
		{
			$dburl = $_POST["dburl"];
			$dbuser = $_POST["dbuser"];
			$dbpass = $_POST["dbpass"];
			$dbname = $_POST["dbname"];
			$this -> db =  new mysqli($dburl, $dbuser, $dbpass, $dbname);
			$this -> db -> autocommit(FALSE);
		}
		else
		{
			sendResponse(400, 'construct failed');
			return false;
		}
	}
	
	function __destruct()
	{
		$this->db->close();
	}
	
	function getEmployeeData()
	{
		$query = "SELECT id, picture, firstname, lastname, gender, birthday, phone,
			email, department, position, addrno, street, suite, city, state, 
			zip, status, statusUpdate, locationUpdate, latitude, longitude,
			currentAddrno, currentStreet, currentCity, currentState, currentZip, employeeID 
			FROM  `hrdirectory` ORDER BY firstname, lastname";
		$stmt = $this->db->prepare($query);
		$stmt->execute();
		$stmt->bind_result($id, $picture, $firstname, $lastname, $gender, $birthday, $phone,
		$email, $department, $position, $addrno, $street, $suite, $city, $state, 
		$zip, $status, $statusUpdate, $locationUpdate, $latitude, $longitude, 
		$currentAddrno, $currentStreet, $currentCity, $currentState,
		$currentZip, $employeeID);
		
		$employeeBasicData = array();
		$count = 0;
		
		while($stmt -> fetch())  
		{
			$row = array("id" => $id, "picture" => $picture, "firstname" => $firstname, 
			"lastname" => $lastname, "gender" => $gender, "birthday" => $birthday,
			"phone" => $phone, "email" => $email, "department" => $department, "position" => $position,
			"addrno" => $addrno, "street" => $street, "suite" => $suite, "city" => $city,
			"state" => $state, "zip" => $zip, "status" => $status, "statusUpdate" => $statusUpdate,
			"locationUpdate" => $locationUPdate, "latitude" => $latitude, "longitude" => $longitude,
			"currentAddrno" => $currentAddrno, "currentStreet" => $currentStreet, 
			"currentCity" => $currentCity, "currentState" => $currentState, 
			"currentZip" => $currentZip, "employeeID" => $employeeID);
			
			$employeeBasicData[$count] = $row;
			$count = $count + 1;
		}
		sendResponse(200, json_encode($employeeBasicData));

	}
}

$employeeData = new EmployeeData();

$employeeData -> getEmployeeData();

?>	
