<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/resources/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/plugins/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/resources/plugins/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/plugins/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/resources/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="/resources/login/css/main.css">
<!--===============================================================================================-->
<script>


</script>
<style>
  .jumbotron {
      background-color: #827ffe;
      color: #fff;
      margin-bottom: 0;
  }
  
</style>
<body class="bod">
	<div class="jumbotron text-center">
	  <h1>B-BITE</h1> 
	  <p>Book Club Community</p> 
	  <form class="form-inline">
	    <div class="input-group">
	      <input type="text" class="form-control" size="50" placeholder="keyword" name="keyword" required>
	      <div class="input-group-btn">
	        <a type="button" class="btn btn-primary" href="/board/listPage">search</a>
	      </div>
	    </div>
	  </form>
	</div>
	
	<nav class="navbar" style="background-color: #e6e6ff;margin-bottom: 0;">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="/board/listPage">B-BITE</a>
	    </div>
	    <ul class="nav navbar-nav" style="font-weight:bold">
	      <li class="active"><a href="/board/listPage">HOME</a></li>
	      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">REVIEW<span class="caret"></span></a>
	        <!-- <ul class="dropdown-menu">
	          <li><a href="#">게시판1</a></li>
	          <li><a href="#">게시판2</a></li>
	          <li><a href="#">게시판3</a></li>
	        </ul> -->
	      </li>
	      <li><a href="#">STORE</a></li>
	    </ul>
	    
	    <ul class="nav navbar-nav navbar-right" style="font-weight:bold">
	   	  <li><c:if test ="${sessionScope.login == null}">
				<a class="btn_join"  href="/join"><span class="glyphicon glyphicon-user"></span> JOIN</a>
			</c:if></li>
	      <li><c:if test ="${sessionScope.login == null}">
				<a class="btn_login" href="/newlogin"><span class="glyphicon glyphicon-log-in"></span> LOGIN</a>
			</c:if></li>
	      <li><c:if test ="${sessionScope.login != null}">
				<a class="btn_logout" href="/logout"><span class="glyphicon glyphicon-log-out"></span> LOGOUT</a>
			</c:if></li>
	    </ul>
	  </div>
	</nav>


</body>
</html>