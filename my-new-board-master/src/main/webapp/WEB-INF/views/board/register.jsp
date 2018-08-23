<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href="/resources/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<%@include file="../include/header2.jsp"%>
<style>
  .content{
  	margin: 0 15%;
  }
</style>
<body>
<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title" style="text-align:center;margin:30px 0;font-weight:bold;">REGISTER BOARD</h3>
				</div>
				
				<!-- /.box-header -->
				<form role="form" method="post" enctype="multipart/form-data">
					<div class="box-body">
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label> 
							<input type="text"
								name="title" class="form-control" placeholder="Enter Title">
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control"
							name="content" rows="3" placeholder="Enter ..."></textarea>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label> 
							<input type="text"
								name="writer" class="form-control" placeholder="Enter Writer"
								value="${sessionScope.login }" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">FILE</label> 
							<input type="file"
								name="file" class="form-control">
						</div>
					</div>
					<!-- /.box-body -->
					<div class="box-footer">
						<button type="submit" class="btn btn-info pull-right">Submit</button>
					</div>
				</form>

			<script src="https://cdnjs.cloudflare.com/ajax/libs/handlers.js/3.0.1/handlers.js"></script>
			<script id="template" type="text/x-handlebars-template">

			</script>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
<!-- /.content-wrapper -->
</body>
<%@include file="../include/footer2.jsp"%>