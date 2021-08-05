package com.sunilos.inheritanc;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Debit")
@Table(name = "Debit")
//@PrimaryKeyJoinColumn(name = "account_id")
public class Debit extends Account {

	private int overdraftFee;

	public int getOverdraftFee() {
		return overdraftFee;
	}

	public void setOverdraftFee(int overdraftFee) {
		this.overdraftFee = overdraftFee;
	}
	
	
}
