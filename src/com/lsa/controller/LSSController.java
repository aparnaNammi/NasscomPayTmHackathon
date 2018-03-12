package com.lsa.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.email.SendEmail;
import com.lsa.util.LSSUtil;

public class LSSController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LSSController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SendEmail email = new SendEmail();

		String result = "failure";
		try {
			result = email.sendEmailTo108(LSSUtil.getPath());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = null;

		if (result.equals("success")) {
			rd = req.getRequestDispatcher("/lifesavior108.html");
		} else {
			rd = req.getRequestDispatcher("/signup.html");
		}
		rd.forward(req, resp);
	}
	
	

}
