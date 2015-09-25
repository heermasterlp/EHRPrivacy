<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="trackerlogin" method="post" >
				<div class="form-signin">
				<h2 class="form-signin-heading">PLEASE SIGN IN</h2>
				<label for="inputEmail" class="sr-only">User Name</label>
        		<input id="inputUserName" class="form-control" name="trackerid" placeholder="User Name" required autofocus>
       			<label for="inputPassword" class="sr-only">Password</label>
        		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
				<input type="submit" class="btn btn-lg btn-primary btn-block" value="Sign in">
			</div>
	</form>
</body>
</html>