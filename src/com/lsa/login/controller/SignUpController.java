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

public class SignUpController extends HttpServlet{

	public SignUpController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RequestDispatcher rd = req.getRequestDispatcher("/personal.html");
		rd.forward(req, resp);
	}
}
