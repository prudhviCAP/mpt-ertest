package com.capgemini.xyz.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Loan;
import com.capgemini.xyz.dao.ILoanDao;
import com.capgemini.xyz.dao.LoanDao;
import com.capgemini.xyz.exception.XYZException;

public class DaoTest {

	@Test
	public void testAddCustomer() throws ClassNotFoundException, SQLException, IOException, XYZException
	{
		Customer customer = new Customer();
		ILoanDao iLoanDao = new LoanDao();
		
		customer.setCustName("Nithish");
		customer.setAddress("Chennai");
		customer.setEmail("nithish@gmail.com");
		customer.setMobile("7877879595");
		assertEquals(10006,iLoanDao.insertCust(customer));
		System.out.println(" test case ");
	}
	
	
	@Test
	public void testApplyLoan() throws ClassNotFoundException, SQLException, IOException
	{
		ILoanDao iLoanDao1 = new LoanDao();
		Loan loan = new Loan();
		loan.setLoanAmount(600000);
		loan.setCustId(10006);
		loan.setDuration(4);
		assertEquals(50003,iLoanDao1.applyLoan(loan).getCustId());
		
	}
	
	@BeforeEach
	public void testDemo1()
	{
		System.out.println(" run before every test ");
		//assertEquals(1,1);
	}
}
