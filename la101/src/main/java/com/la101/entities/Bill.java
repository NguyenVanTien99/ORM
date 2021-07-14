package com.la101.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Bill_Number")
	private int billNumber;

	@Column(name = "Bill_Date")
	@Temporal(TemporalType.DATE)
	private Date billDate;

	@Column(name = "Bill_Status")
	private String billStatus;

	@OneToOne
	@JoinColumn(name = "App_Id")
	private Appointment appointment;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Payment> payments;

	public Bill() {
		super();
	}

	public Bill(Date billDate, String billStatus) {
		super();
		this.billDate = billDate;
		this.billStatus = billStatus;
	}

	public Bill(Date billDate, String billStatus, Appointment appointment, List<Payment> payments) {
		super();
		this.billDate = billDate;
		this.billStatus = billStatus;
		this.appointment = appointment;
		this.payments = payments;
	}

	public int getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	@Override
	public String toString() {
		return "Bill [billNumber=" + billNumber + ", billDate=" + billDate + ", billStatus=" + billStatus + "]";
	}
	
	

}
