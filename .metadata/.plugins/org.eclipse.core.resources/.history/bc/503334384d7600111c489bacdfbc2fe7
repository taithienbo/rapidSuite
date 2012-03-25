package inventory;

import java.io.Serializable;

import android.util.Log;

public class Inventory implements Serializable
{


	// id, code, name, category, lastUpdate, manufacturer, wholesalePrice,
	// msrp, availability, addrno, street, suite, city, state, zip, 
	// latitude, longitude 

	
	
	public static final String INVENTORY_RETRIEVAL_KEY = "inventory object";


	/**
	 * 
	 * @param id
	 * @param code
	 * @param name
	 * @param category
	 * @param lastUpdate
	 * @param manufacturer
	 * @param wholesalePrice
	 * @param msrp
	 * @param availability
	 * @param address
	 * @param latitude
	 * @param longitude
	 */
	public Inventory(int id, String code, String name, String category, 
			String lastUpdate, String manufacturer, String wholesalePrice,
			String msrp, String availability, String address, 
			String latitude, String longitude){

		this.id 				=	 	id;
		this.code 				=	 	code;
		this.name 				= 		name;
		this.category			= 		category;
		this.lastUpdate			= 		lastUpdate;
		this.manufacturer 		= 		manufacturer; 
		this.wholesalePrice 	= 		wholesalePrice;
		this.msrp 				= 		msrp;
		this.availability 		= 		availability;
		this.longitude 			= 		longitude;
		this.latitude 			= 		latitude;
		this.address 			= 		address;
		
		Log.d(LOG_INFO_TAG, "Inventory Constructor: Value of wholesalePrice"
			+	" is: " + wholesalePrice);
		
	}


	public int getId()
	{
		return id;
	}


	public String getName()
	{
		return name;
	}


	public String getCode()
	{
		return code;
	}


	public String getCategory()
	{
		return category;
	}


	public String getLastLocationUpdate()
	{
		return lastUpdate;
	}


	public String getManufacturer()
	{
		return manufacturer;
	}


	public String getWholesalePrice()
	{
		Log.d(LOG_INFO_TAG, "Inventory.getWholesalePirce() wholeSalePrice " +
				"is: " + wholesalePrice);
		return wholesalePrice;
	}

	public String getMSRP()
	{
		return msrp;
	}


	public String getAvailability()
	{
		return availability;
	}


	public String getAddress()
	{
		return address;
	}


	public String getCurrentLongitude()
	{
		return longitude;
	}


	public String getCurrentLatitude()
	{
		return latitude;
	}

	
	
	private static final String LOG_INFO_TAG = "Inventory";
	private int id;
	private String code, name, category, lastUpdate, manufacturer, wholesalePrice,
	msrp, availability, address, longitude, latitude;
}
