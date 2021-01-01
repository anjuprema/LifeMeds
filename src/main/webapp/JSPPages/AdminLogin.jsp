<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../../css/styles.css">
<title>Admin - Login</title>
</head>
<body>
  <div class="container" >
  	<div class="parentContainer">
  		<form action="" method="post">
  			<h2>Login</h2>
	  		<label for="uname"><b>Username</b></label>
		    <input type="text" placeholder="Enter Username" name="userName" required>
		
		    <label for="psw"><b>Password</b></label>
		    <input type="password" placeholder="Enter Password" name="userPassword" required>
		    
		    <div style="color:red;">${errorMsg}</div>
		    
		    <button type="submit">Login</button>
	    </form>
  	</div>    
  </div>
</body>
</html>