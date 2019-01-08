package com.capgemini.xyz.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Loan;
import com.capgemini.xyz.dao.ILoanDao;
import com.capgemini.xyz.dao.LoanDao;
import com.capgemini.xyz.exception.XYZException;

public class LoanService implements ILoanService {

	ILoanDao iLoanDao = null;
	
	@Override
	public Loan applyLoan(Loan loan) throws ClassNotFoundException, SQLException, IOException {
		iLoanDao = new LoanDao();
		Loan returnLoan= new Loan();
		returnLoan = iLoanDao.applyLoan(loan); 
		return returnLoan;
	}

	@Override
	public boolean validateCustomer(Customer customer) {
		int count = 0;
		if(isValidName(customer.getCustName())) {
			count++;
		}
		if(isValidEmail(customer.getEmail())) {
			count++;
		}
		if(count==2) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public long insertCust(Customer cust) throws ClassNotFoundException, SQLException, IOException, XYZException {
		iLoanDao = new LoanDao();
		long custId = iLoanDao.insertCust(cust);
		return custId;
	}

	@Override
	public double calculateEMI(double amount, int duration) {
		double calculatedEMI;
		float rate = 9.5f;
		calculatedEMI = (amount*rate*(1+rate)*duration)/((1+rate)*duration-1);
		return calculatedEMI;
	}
	
	public boolean isValidName(String name) {
		Pattern namePattern = Pattern.compile("^[A-Z][a-z]{3,}$");
		Matcher nameMatcher = namePattern.matcher(name);
		return nameMatcher.matches();
	}
	
	public boolean isValidEmail(String email) {
		Pattern emailPattren = Pattern.compile("^[A-Za-z][A-Za-z0-9]{3,}[@][a-z]{3,5}[.][a-z]{2,3}$");
		Matcher emailMatcher = emailPattren.matcher(email);
		return emailMatcher.matches();
	}

}
