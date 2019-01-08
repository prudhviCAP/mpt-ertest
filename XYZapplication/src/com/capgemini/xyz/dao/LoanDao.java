package com.capgemini.xyz.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.capgemini.xyz.bean.Customer;
import com.capgemini.xyz.bean.Loan;
import com.capgemini.xyz.exception.XYZException;
import com.capgemini.xyz.util.DBConnection;

public class LoanDao implements ILoanDao {

	@Override
	public Loan applyLoan(Loan loan) throws ClassNotFoundException, SQLException, IOException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet = null;
		Loan returnLoan = new Loan();
		
		preparedStatement = connection.prepareStatement(QueryMapper.INSERT_LOAN_DETAILS);
		preparedStatement.setDouble(1, loan.getLoanAmount());
		preparedStatement.setLong(2,loan.getCustId());
		preparedStatement.setInt(3, loan.getDuration());
		preparedStatement.executeUpdate();
		
		preparedStatement1 = connection.prepareStatement(QueryMapper.GET_LOAN_DETAILS);
		preparedStatement1.setLong(1, loan.getCustId());
		resultSet = preparedStatement1.executeQuery();
		while(resultSet.next()) {
			returnLoan.setLoanID(resultSet.getLong(1));
			returnLoan.setLoanAmount(resultSet.getDouble(2));
			returnLoan.setCustId(resultSet.getLong(3));
			returnLoan.setDuration(resultSet.getInt(4));
		}
		return returnLoan;
	}

	@Override
	public long insertCust(Customer cust) throws ClassNotFoundException, SQLException, IOException, XYZException {
		Connection connection = DBConnection.getConnection();
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement1 = null;
		ResultSet resultSet = null;
		long custId = 0;
		
		preparedStatement = connection.prepareStatement(QueryMapper.INSERT_CUSTOMER_QUERY);
		preparedStatement.setString(1, cust.getCustName());
		preparedStatement.setString(2, cust.getAddress());
		preparedStatement.setString(3, cust.getEmail());
		preparedStatement.setString(4, cust.getMobile());
		preparedStatement.executeUpdate();
		
		preparedStatement1 = connection.prepareStatement(QueryMapper.GET_CUSTOMERID_QUERY);
		preparedStatement1.setString(1, cust.getCustName());
		resultSet = preparedStatement1.executeQuery();
		while(resultSet.next()) {
			custId = resultSet.getLong(1);
		}
		return custId;
	}

}
