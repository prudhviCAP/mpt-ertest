package com.capgemini.xyx.ui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Loan;
import com.capgemini.xyz.exception.XYZException;
import com.capgemini.xyz.service.ILoanService;
import com.capgemini.xyz.service.LoanService;

public class ExecuterMain {

	static Scanner sc = new Scanner(System.in);
	static ILoanService iLoanService = null;
	static LoanService loanService = null;
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, XYZException {
		
		
		System.out.println(" welcome to XYZ Application");
		System.out.println("---*---*---*---*---*---*---");
		System.out.println(" 1. Register Customer ");
		System.out.println(" 2. exit       ");
		
		int option = 0;
		System.out.println(" enter your option in given menu");
		option = sc.nextInt();
			switch(option) {
			case 1:
				Customer customer = new Customer();
				System.out.println(" enter customer details ");
				System.out.println(" enter customer name ");
				String custName = sc.next();
				customer.setCustName(custName);
				
				System.out.println("enter your address");
				String address = sc.next();
				customer.setAddress(address);
				
				System.out.println(" enter your email ");
				String email = sc.next();
				customer.setEmail(email);
				
				System.out.println(" enter your mobile number ");
				String mobileNo = sc.next();
				customer.setMobile(mobileNo);
				long custId;
				iLoanService = new LoanService();
				if(iLoanService.validateCustomer(customer)) {
					custId = iLoanService.insertCust(customer);
					System.out.println("customer details successfully added with id :"+custId);
					
					System.out.println("do you wish to apply for loan ?(Yes / No)");
					String applyLoan = sc.next();
					switch(applyLoan) {
						case "Yes":
							Loan loan = new Loan();
							System.out.println(" enter loan amount ");
							double amount = sc.nextDouble();
							loan.setLoanAmount(amount);
							
							loan.setCustId(custId);
							
							System.out.println("enter duration of loan");
							int duration = sc.nextInt();
							loan.setDuration(duration);
							
							Loan returnLoan = new Loan();
							returnLoan = iLoanService.applyLoan(loan);
							System.out.println(returnLoan.getLoanID()+" "+returnLoan.getLoanAmount()+" "+returnLoan.getDuration()+" ");
							
							double calculatedEMI = 0;
							calculatedEMI = iLoanService.calculateEMI(loan.getLoanAmount(), loan.getDuration());
							System.out.println(" emi to be paid on applied loan is :"+calculatedEMI);
							System.out.println(" please pay EMI in time ");
							break;
						case "No":
							System.exit(0);
					}
					break; 
				}
				else {
					System.out.println(" you have entered invalid details");
				}	
			case 2:
				System.exit(0);
			default:
				System.out.println(" you entered invalid option");
				break;	
			}
	}
}
