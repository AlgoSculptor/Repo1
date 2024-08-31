package cn.shivam.model;

public class Order extends Product {
	private int orderId;
	private int uId;
	private int quantity;
	private String date;
	private String deliveryAddress;
		
	public Order() {}

	public Order(int orderId, int uId, int quantity, String date) {
		super();
		this.orderId = orderId;
		this.uId = uId;
		this.quantity = quantity;
		this.date = date;
	}



	public Order(int uId, int quantity, String date) {
		super();
		this.uId = uId;
		this.quantity = quantity;
		this.date = date;
	}
	
	public Order(int orderId, int uId, int quantity, String date, String deliveryAddress) {
        super();
        this.orderId = orderId;
        this.uId = uId;
        this.quantity = quantity;
        this.date = date;
        this.deliveryAddress = deliveryAddress;
    }




	public int getOrderId() {
		return orderId;
	}

	public int getuId() {
		return uId;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	 public String getDeliveryAddress() {
	        return deliveryAddress;
	    }
	
	
	
	// Setters
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uId=" + uId + ", quantity=" + quantity + ", date=" + date + " + address = address ]";
	}
	
	
}
