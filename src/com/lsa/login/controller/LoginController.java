package com.lsa.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excel.ReadExcel;
import com.lsa.util.Authenticator;
import com.lsa.util.LSSUtil;
import com.pojo.UserProfileDetails;

public class LoginController extends HttpServlet {
	
	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userName = req.getParameter("uname");
		String pass = req.getParameter("psw");
		RequestDispatcher rd = null;
		Authenticator auth = new Authenticator();
		
		String result = auth.authenticate(userName, pass);
		UserProfileDetails userDetails = null;
			ReadExcel readExcelData = new ReadExcel();
			userDetails = readExcelData.readExcelData(userName, LSSUtil.getPath());
			req.setAttribute("userDetails", userDetails);
			rd = req.getRequestDispatcher("/home.jsp");
		rd.forward(req, resp);
	}
}
