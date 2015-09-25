<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	
	<!-- Bootstrap core css  -->
	<link href="css/bootstrap.css" rel="stylesheet" />
	<link href="css/signin.css" rel="stylesheet" />
	<script type="text/javascript" src = "js/jquery-1.11.3.min.js" ></script>
	<script>
	
		function btnLoginClicked() {
			
			var url = 'http://10.119.180.45:8080/authdoctor?username=' + $('#inputUserName').val() + '&password=' + $('#inputPassword').val();
			
			$.get(url,function(data){
				$.post('login1',
						{tokens: data.token},
						function(response){
							alert(response);
							$('#subViewDiv').html(response);
						});
			});
		}
	</script>
	
</head>
	<body class="container">
			<!-- <form action="login" method="post" > -->
			<div id="subViewDiv">
				<div class="form-signin">
				<h2 class="form-signin-heading">PLEASE SIGN IN</h2>
				<label for="inputEmail" class="sr-only">User Name</label>
        		<input id="inputUserName" class="form-control" name="username" placeholder="User Name" required autofocus>
       			<label for="inputPassword" class="sr-only">Password</label>
        		<input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
				<button class="btn btn-lg btn-primary btn-block" onClick="btnLoginClicked()" >Sign in</button>
			</div>
			</div>
			<!-- </form> -->
	</body>
</html>
