package com.sunilos.inheritanc;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "Credit")
@Table(name = "Credit")
//@PrimaryKeyJoinColumn(name = "account_id")
public class Credit extends Account{
	
	private int creditLimit;

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	

}
