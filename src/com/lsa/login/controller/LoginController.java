package com.lsa.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.db.ConnectToDB;
import com.lsa.util.Authenticator;
import com.pojo.UserProfileDetails;

public class LoginController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("uname");
		String pass = req.getParameter("psw");
		RequestDispatcher rd = null;
		Authenticator auth = new Authenticator();
		
		String result = auth.authenticate(userName, pass);
		/*UserProfileDetails userDetails = null;
			ReadExcel readExcelData = new ReadExcel();
			userDetails = readExcelData.readExcelData(userName, LSSUtil.getPath());*/
		
		ConnectToDB connectToDB = new ConnectToDB();
		UserProfileDetails userProfileDetails = null;
		try {
			userProfileDetails = connectToDB.selectData(userName);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			req.setAttribute("userDetails", userProfileDetails);
			rd = req.getRequestDispatcher("/home.jsp");
		rd.forward(req, resp);
	}
}
