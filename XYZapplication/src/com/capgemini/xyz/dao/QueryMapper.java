package com.capgemini.xyz.dao;

public interface QueryMapper {

	//Queries to enter and retrieve customer details
	String INSERT_CUSTOMER_QUERY = "insert into customer_table values(cust_seq.nextval,?,?,?,?)";
	String GET_CUSTOMERID_QUERY = "select cust_id from customer_table where cust_name=?";
	
	//Queries to apply for loan and get details
	String INSERT_LOAN_DETAILS = "insert into loan_table values(loan_seq.nextval,?,?,?)";
	String GET_LOAN_DETAILS = "select * from loan_table where cust_id=?";
}
