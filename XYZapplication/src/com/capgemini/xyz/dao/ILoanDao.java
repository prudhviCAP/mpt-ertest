package com.capgemini.xyz.dao;

import java.io.IOException;
import java.sql.SQLException;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Loan;
import com.capgemini.xyz.exception.XYZException;

public interface ILoanDao {

	public Loan applyLoan(Loan loan) throws ClassNotFoundException, SQLException, IOException;
	public long insertCust(Customer cust) throws ClassNotFoundException, SQLException, IOException, XYZException;
}
