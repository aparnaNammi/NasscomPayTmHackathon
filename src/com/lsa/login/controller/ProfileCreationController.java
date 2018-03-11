package com.lsa.login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excel.ReadExcel;
import com.excel.WriteToExcel;
import com.lsa.util.LSSUtil;
import com.pojo.UserProfileDetails;

public class ProfileCreationController extends HttpServlet{

	public ProfileCreationController() {
		super();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserProfileDetails userDetails = new UserProfileDetails();
		userDetails.setFirstName(req.getParameter("fname"));
		userDetails.setLastName(req.getParameter("lname"));
		userDetails.setMiddleName(req.getParameter("mname"));
		userDetails.setFatherName(req.getParameter("fathername"));
		userDetails.setMobileNum(req.getParameter("mobNum"));
		userDetails.setDob(req.getParameter("age"));
		userDetails.setGender(req.getParameter("gender"));
		userDetails.setAadharNum(req.getParameter("aadharNum"));
		userDetails.setCurrentAddress(req.getParameter("address"));
		userDetails.setEmergenyContactName1(req.getParameter("contactName1"));
		userDetails.setEmergenyContactNum1(req.getParameter("contactNum1"));
		userDetails.setEmergenyContactName2(req.getParameter("contactName2"));
		userDetails.setEmergenyContactNum2(req.getParameter("contactNum2"));
		userDetails.setEmergenyContactName3(req.getParameter("contactName3"));
		userDetails.setEmergenyContactNum3(req.getParameter("contactNum3"));
		userDetails.setFamilyDoctorName(req.getParameter("familyDoctorName"));
		userDetails.setFamilyDocNum(req.getParameter("familyDoctorNum"));
		userDetails.setCriticalIllness(req.getParameter("crtiticalIllness"));
		userDetails.setHistoricHealthEvents(req.getParameter("historicLifeEvents"));
		userDetails.setFamilyMedicalBackground(req.getParameter("familyMedBackground"));
		WriteToExcel writeToExcel = new WriteToExcel();
		writeToExcel.writeUserProfileToExcel(LSSUtil.getPath(), userDetails);
		req.setAttribute("userDetails", userDetails);
		RequestDispatcher rd = req.getRequestDispatcher("/home.jsp");
		rd.forward(req, resp);
	}
}
