<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<jsp:useBean id="userDetails" class="com.pojo.UserProfileDetails" scope="request" />  
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title> Life Savior Service</title>
<style>
html, body {
    height: 100%;
}

html {
    display: table;
    margin: auto;
}

body {
    display: table-cell;
    vertical-align: middle;
    font-family: Arial, Helvetica, sans-serif;
    font-weight: bold;
}

input[type=text], select, textarea {
    width: 100%;
    padding: 12px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    margin-top: 6px;
    margin-bottom: 16px;
    resize: vertical;
}

input[type=submit] {
    background-color: purple;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
}

input[type=submit]:hover {
    background-color: #45a049;
    
}
h2{
 font-style: italic;
 font-family: "Times New Roman", Times, serif;
 color: purple;
 font-size: 30px;
}
.container {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}

.imgcontainer {
    text-align: center;
   	margin: 24px 0 12px 0;
    border:1px solid #1ebbd7;
    background-color: #1ebbd7;
}
form {border: 3px solid purple; border-radius: 25px; padding: 20px; 
    width: 1000px;
    height: 1300px;
     background-color: lavender;
     color: purple;}
     
     #customers {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#customers td, #customers th {
    border: 1px solid #ddd;
    padding: 8px;
    font-size: 20px;
    
}

#customers tr:nth-child(even){background-color: #f2f2f2;}

#customers tr:hover {background-color: #ddd;}

#customers th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: purple;
    color: white;
}
</style>
</head>
<body>
<h2>Welcome</h2>

<div class="container">

<form action="/action_page.php">
<table id="customers">
<tr><th>Name</th><th>Details</th></tr>
   <tr> <td>  <label for="fname">First Name :</label></td><td> <% out.print(userDetails.getFirstName()); %></td>   </tr>
    <tr> <td><label for="address">Middle Name : </label> </td><td>  K</td>   </tr> 
    <tr> <td><label for="lname">Last Name :</label> </td><td><% out.print(userDetails.getLastName()); %></td>   </tr> 
    <tr> <td><label for="address">Mobile : </label></td><td> <% out.print(userDetails.getMobileNum()); %></td>   </tr>
    <tr> <td><label for="age">Age : </label> </td><td><% out.print(userDetails.getDob()); %></td>   </tr>
   <tr> <td> <label for="gender">Gender :</label>  </td><td><%  out.print(userDetails.getGender()); %></td>   </tr>
	<tr> <td><label for="address">Father Name : </label> </td><td><% out.print(userDetails.getFatherName()); %></td>   </tr>
   <tr> <td> <label for="address">Aadhar : </label> </td><td><% out.print(userDetails.getAadharNum()); %></td>   </tr>
   <tr> <td> <label for="address">Address : </label></td><td> <% out.print(userDetails.getCurrentAddress()); %></td>   </tr>
   <tr> <td> <label for="address">Emergency contact name1 : </label></td><td> <% out.print(userDetails.getEmergenyContactName1()); %></td>   </tr>
   <tr> <td> <label for="address">Emergency contact num1 : </label></td><td> <% out.print(userDetails.getEmergenyContactNum1()); %></td>   </tr>
    <tr> <td><label for="address">Emergency contact name2 : </label> </td><td><% out.print(userDetails.getEmergenyContactName2()); %></td>   </tr>
   <tr> <td> <label for="address">Emergency contact num2 : </label> </td><td><% out.print(userDetails.getEmergenyContactNum2()); %></td>   </tr>
   <tr> <td> <label for="address">Emergency contact name3 : </label></td><td> <% out.print(userDetails.getEmergenyContactName3()); %></td>   </tr>
    <tr> <td><label for="address">Emergency contact num3 : </label></td><td> <% out.print(userDetails.getEmergenyContactNum3()); %></td>   </tr>
    <tr> <td><label for="address">Family doctor name : </label> </td><td><% out.print(userDetails.getFamilyDoctorName()); %></td>   </tr>
   <tr> <td> <label for="address">Family doctor num : </label> </td><td><% out.print(userDetails.getFamilyDocNum()); %></td>   </tr>
   <tr> <td> <label for="address">Critical illness : </label> </td><td><% out.print(userDetails.getCriticalIllness()); %></td>   </tr>
    <tr> <td><label for="address">Historic Health events : </label> </td><td><% out.print(userDetails.getHistoricHealthEvents()); %></td>   </tr>
   <tr> <td> <label for="address">Family Medical Background : </label> </td><td><% out.print(userDetails.getFamilyMedicalBackground()); %></td>   </tr>
    </table>
  </form>
</div>

</body>
</html>