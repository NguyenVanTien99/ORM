package com.la101.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Pay_Receiptnum")
	private int payReceiptnum;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Pay_Date")
	private Date payDate;

	@Column(name = "Pay_Method")
	private String payMethod;

	@Column(name = "Pay_Amount")
	private Double payAmount;

	@ManyToOne
	@JoinColumn(name = "Bill_Number")
	private Bill bill;

	@ManyToOne
	@JoinColumn(name = "Pat_id")
	private Patient patient;

	public Payment() {
		super();
	}

	public Payment(Date payDate, String payMethod, Double payAmount) {
		super();
		this.payDate = payDate;
		this.payMethod = payMethod;
		this.payAmount = payAmount;
	}

	public Payment(Date payDate, String payMethod, Double payAmount, Bill bill, Appointment appointment) {
		super();
		this.payDate = payDate;
		this.payMethod = payMethod;
		this.payAmount = payAmount;
	}

	public Payment(int payReceiptnum, Date payDate, String payMethod, Double payAmount, Bill bill, Patient patient) {
		super();
		this.payReceiptnum = payReceiptnum;
		this.payDate = payDate;
		this.payMethod = payMethod;
		this.payAmount = payAmount;
		this.bill = bill;
		this.patient = patient;
	}

	public int getPayReceiptnum() {
		return payReceiptnum;
	}

	public void setPayReceiptnum(int payReceiptnum) {
		this.payReceiptnum = payReceiptnum;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
