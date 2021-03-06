package approval;

public class Approvals
{

	// Item statuses 
	public static final String STATUS_PENDING = "Pending";
	public static final String STATUS_APPROVED = "Approved";
	public static final String STATUS_REJECTED = "Rejected";

	// Field to retrieve processed items
	public static final String PROCESSED = "Processed";

	// Field to retrieve pending items
	public static final String PROCEEDING = "Pending";
	
	
	// Action fields
	public static final String ACTION_APPROVE = "approve";
	public static final String ACTION_REJECT = "reject";
	public static final String ACTION_REPROCESS = "set_as_pending";



	// Approvals Database fields:
	/**
	 *  id, date, code, item, requestorFirstName, requestorLastName, quantity, 
		 cost, note, status   
	 */
	/**
	 * 
	 * @param id
	 * @param date
	 * @param code
	 * @param item
	 * @param requestorName
	 * @param quantity
	 * @param cost
	 * @param note
	 * @param status
	 */
	public Approvals(int id, String date, String code, String item, 
			String requestorName, String quantity,
			String cost, String note, String status){

		this.id = id;
		this.date = date;
		this.code = code;
		this.item = item;

		this.quantity = quantity;
		this.cost = cost;
		this.note = note;
		this.status = status;
		this.requestorName = requestorName;
	} 


	public int getId(){
		return id;
	}


	public String getDate(){
		return date;
	}


	public String getCode(){
		return code;
	}


	public String getItemName(){
		return item;
	}


	public String getRequestorName(){
		return requestorName;	
	}


	public String getQuantity(){
		return quantity;
	}

	/**
	 *  id, date, code, item, requestorFirstName, requestorLastName, quantity, 
		 cost, note, status   
	 */
	public String getCost(){
		return cost;
	}


	public String getNote(){
		return note;
	}


	public String getStatus(){
		return status;
	}
	private int id;
	private String date, code, item, requestorName,
	quantity, cost, note, status;
}
