<!DOCTYPE html>
<html lang="ko">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <%@include file="./include/header2.jsp"%>
<script>
$(document).ready(function () {
    var result = '${msg}';
    
    if(result == "id")
    	alert("Sorry, the id already exist.");
    
    if(result == "try")
    	alert("try again");
});

</script>
<head>
	<title>Join</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="resources/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/plugins/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="resources/plugins/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/plugins/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="resources/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="resources/login/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	<div class="limiter" style="padding-top:0;margin-top:0;">
		<div class="container-login100" style="padding-top:0;margin-top:0;">
			<div class="wrap-login100 p-t-50 p-b-90" style="padding-top:0;margin-top:0;">
				<form class="login100-form validate-form flex-sb flex-w" role="form" method="post">
					<span class="login100-form-title p-b-51">
						JOIN
					</span>
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "UserID is required">
						<input class="input100" type="text" name="userid" placeholder="USER ID">
						<span class="focus-input100"></span>
					</div>					
					
					<div class="wrap-input100 validate-input m-b-16" data-validate = "Password is required">
						<input class="input100" type="password" name="userpwd" placeholder="PASSWORD">
						<span class="focus-input100"></span>
					</div>
					
	 				<div class="wrap-input100 validate-input m-b-16" data-validate = "Username is required">
						<input class="input100" type="text" name="userphone" placeholder="PHONE">
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input m-b-16" data-validate = "Usermail is required">
						<input class="input100" type="text" name="usermail" placeholder="EMAIL">
						<span class="focus-input100"></span>
					</div>
					
					<div class="container-login100-form-btn m-t-17">
						<button  type="submit" class="login100-form-btn">
							Join
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
<%@include file="./include/footer2.jsp"%>
</html>