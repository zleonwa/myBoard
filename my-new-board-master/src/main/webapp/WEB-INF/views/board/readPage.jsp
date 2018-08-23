<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/header2.jsp"%>

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
	<li class="replyLi" data-rno={{rno}}>
	<i class="fa fa-comments bg-blue"></i>
		<div class="timeline-item">
			<span class="time">
				<i class="fa fa-clock-o"></i>{{prettifyDate regdate}}
			</span>
			<h3 class="timeline-header"><strong>{{rno}}</strong> -{{replyer}}</h3>
			<div class="timeline-body">{{replytext}} </div>
			<div class="timeline-footer">
				<a class="btn btn-success btn-xs" data-toggle="modal" data-target="#modifyModal">Modify</a>
			</div>
		</div>
	</li>
	{{/each}}
</script>

<script>
	Handlebars.registerHelper("prettifyDate", function(timevalue){
		var dateObj = new Date(timevalue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year+"/"+month+"/"+date;
	});
	var printData = function(replyArr, target, templateObject){
		var template = Handlers.compile(templateObject.html());
		var html = template(replyArr);
		$('.replyLi').remove();
		target.after(html);
	}
</script>

<script type="text/javascript">

$(document).ready(function () {
	if ('${error}' == 'wrong_bno') {
		alert('잘못된 요청입니다.');
		location.href='/board/listPage';
	}
	
    var result = '${msg}';
	
    if(result == 'SUCCESS'){
    	alert("처리가 완료되었습니다.");
    }
    
    if(result == 'logout'){
    	alert("로그아웃이 완료되었습니다.");
    }
	 
	$('.btn_login').click(function(){
		location.href = '/newlogin';
	});
	
	$(".btn_logout").click(function(){
		location.href = '/logout';
	}); 
	
	var formObj = $("form[role='form']");
	
	$(".goListBtn").on("click", function(){
		formObj.attr("method", "get");
		formObj.attr("action", "/board/listPage");
		formObj.submit();
	});
	$(".removeBtn").on("click", function(){
		formObj.attr("method", "post");
		formObj.attr("action", "/board/remove");
		formObj.submit();
	});
	$(".modifyBtn").on("click", function(){
		formObj.attr("action", "/board/modify");
		formObj.attr("method", "get");		
		formObj.submit();
	});
	
	$("#filelink").on("click", function(){
		formObj.attr("action", "/board/filedown");
		formObj.attr("method", "get");		
		formObj.submit();
	});
});
</script>

<style>
  .content{
  	margin: 0 15%;
  }
</style>
<body>
<!-- Main content -->
<section class="content">
	<div class="row" >
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box box-primary">
				<div class="box-header">
					<h3 class="box-title" style="text-align:center;margin:30px 0;font-weight:bold;">READ BOARD</h3>
				</div>
				<!-- /.box-header -->

				<form role="form" method="post">
					<input type='hidden' name='bno' value="${boardVO.bno}">
					<input type="hidden" name="page" value="${cri.page }"/>
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }"/>
				</form>
				
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label> <input type="text"
							name='title' class="form-control" value="${boardVO.title}"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Content</label>
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content}</textarea>
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label> <input type="text"
							name="writer" class="form-control" value="${boardVO.writer}"
							readonly="readonly">
					</div>
					<div class="form-group">
							<label for="exampleInputEmail1">FILE</label> 
							<a id="filelink">${boardVO.oldname }</a>
							<input type="file" name="file" class="form-control" disabled>
					</div>
				</div>
				<!-- /.box-body -->
				
				<div class="box-footer pull-right">
					<c:if test="${sessionScope.login eq boardVO.writer}">
						<button type="submit" class="btn btn-warning modifyBtn">MODIFY</button>
						<button type="submit" class="btn btn-danger removeBtn">REMOVE</button>
					</c:if>
					<button type="submit" class="btn btn-info goListBtn">LIST ALL</button>
				</div>
			</div>
			<!-- /.box -->
		</div>
		<!--/.col (left) -->
	</div>
	
	<div class="row" style="margin-top:5%;padding:5% 15%;">
		<div class="col-md-12">
			<div class="box box-success">
				<div class="box-header">
					<h3 class="box-title" style="font-weight:bold;padding-bottom:10px">ADD NEW REPLY</h3>
				</div>
				<div class="box-body">
					<div class="form-group">
						<label for="newReplyWriter">Writer</label>
						<input class="form-control" type="text" placeholder="USER ID" id="newReplyWrtier" value="${sessionScope.login}" readonly>
					</div>
					<div class="form-group">
						<label for="newReplyText">ReplyText</label>
						<textarea class="form-control" type="text" placeholder="REPLY TEXT" id="newReplyText"></textarea>
					</div>
				</div>
			</div>
			
			<div class="box-footer">
				<button class="btn btn-success pull-right" type="submit" id="replyAddBtn">ADD REPLY</button>
			</div>		
		</div>
	</div>
	<ul class="timeline" style="padding:0 15%;">
		<li class="time-label" id="repliesDiv"><span class="bg-green">Replies List</span></li>
	</ul>
	<div class="text-center">
		<ul id="pagination" class="pagination patination-sm no-margin">
		</ul>
	</div>
	

	<!-- /.row -->
</section>
<!-- /.content -->

</body>

<%@include file="../include/footer2.jsp"%>