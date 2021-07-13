package com.la101.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "App_Date", "Pat_id" }))
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "App_Id")
	private int id;

	@Column(name = "App_Date")
	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name = "App_Time")
	@Temporal(TemporalType.TIME)
	private Date time;

	@Column(name = "App_Duration")
	private int duration;

	@Column(name = "App_Reason")
	private String reason;

	@ManyToOne
	@JoinColumn(name = "Doc_Number")
	private Doctor doctor;

	@ManyToOne
	@JoinColumn(name = "Pat_id")
	private Patient patient;
	
	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	private Bill bill;

	public Appointment() {
		super();
	}

	public Appointment(Date date, Date time, int duration, String reason) {
		super();
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reason = reason;
	}

	public Appointment(Date date, Date time, int duration, String reason, Doctor doctor, Patient patient) {
		super();
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reason = reason;
		this.doctor = doctor;
		this.patient = patient;
	}
	
	

	public Appointment(Date date, Date time, int duration, String reason, Doctor doctor, Patient patient, Bill bill) {
		super();
		this.date = date;
		this.time = time;
		this.duration = duration;
		this.reason = reason;
		this.doctor = doctor;
		this.patient = patient;
		this.bill = bill;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", time=" + time + ", duration=" + duration + ", reason="
				+ reason + ", doctor=" + doctor.getNumber() + ", patient=" + patient.getId() + "]";
	}
	

}
