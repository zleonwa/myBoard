<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<style>
  footer {
      background-color: #2d2d30;
      color: #f5f5f5;
      margin-top: 70px;
      padding: 5px;
  }
  footer a {
      color: #f5f5f5;
  }
  footer a:hover {
      color: #777;
      text-decoration: none;
  }  
</style>
<script>
	 $(document).ready(function(){
		 $('#foo').append('<a class="up-arrow" href="'+window.location.href+'" data-toggle="tooltip" title="TO TOP"> <span class="glyphicon glyphicon-chevron-up"></span></a><br><br>');
		 $('#foo').append('<p>Bootstrap Theme Made By <a href="/board/listPage" data-toggle="tooltip" title="Visit B-BITE">B-BITE</a></p>'); 
	 }); 	
	 
</script>
<body>
	<!-- Footer -->
	<footer class="text-center" id="foo">
	 <!--  <a class="up-arrow" href="" data-toggle="tooltip" title="TO TOP">
	    <span class="glyphicon glyphicon-chevron-up"></span>
	  </a><br><br>
	  <p>Bootstrap Theme Made By <a href="/board/listPage" data-toggle="tooltip" title="Visit B-BITE">B-BITE</a></p>  -->
	</footer>
</body>
<script>
$(document).ready(function(){
    // Initialize Tooltip
    $('[data-toggle="tooltip"]').tooltip(); 
})
</script>
</html>