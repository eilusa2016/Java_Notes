package http.test;

import java.io.Serializable;

public class Tp implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;

	private String customerName;

	private String customerPhoneName;

	private String customerPhonenum;

	public Tp() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Tp(String customerId, String customerName, String customerPhoneName,
			String customerPhonenum) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhoneName = customerPhoneName;
		this.customerPhonenum = customerPhonenum;
	}


	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneName() {
		return customerPhoneName;
	}

	public void setCustomerPhoneName(String customerPhoneName) {
		this.customerPhoneName = customerPhoneName;
	}

	public String getCustomerPhonenum() {
		return customerPhonenum;
	}

	public void setCustomerPhonenum(String customerPhonenum) {
		this.customerPhonenum = customerPhonenum;
	}
	
}
