<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="true"%>
<%@include file="../include/header2.jsp"%>
<style>
  .content{
  	margin: 0 10%;
  }
</style>
<script>
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
	 
/* 	
	$(".btn_logout").click(function(){
		location.href = '/logout';
	});  */
	
	$('.btn_writer').click(function(){
		location.href = '/board/register';
	});
});
</script>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			
			<div class="box">
					<div class="box-header with-border">
						<h3 class="box-title" style="text-align:center;margin:30px 0;font-weight:bold;">REVIEW BOARD</h3>
					</div>

					<div class="box-body">
				
				<table class="table table-bordered">
					<tr>
						<th style="width: 10px">BNO</th>
						<th>TITLE</th>
						<th>WRITER</th>
						<th>REGDATE</th>
						<th style="width: 40px">VIEWCNT</th>
					</tr>
				
				<c:forEach items="${list}" var="boardVO">
				
					<tr>
						<td>${boardVO.bno}</td>
						<td>
							<a href='/board/readPage${pageMaker.makeQuery(pageMaker.cri.page)
							}&bno=${boardVO.bno}'>
							${boardVO.title}</a>
						</td>
						<td>${boardVO.writer}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
								value="${boardVO.regdate}" /></td>
						<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
					</tr>
				</c:forEach>
				</table>
				
				<div class="text-center">
					<ul class="pagination">
						<!-- 이전 페이지로 가는 링크가 있어야 하는지 boolean 값으로 if 조건식을 통해 처리 -->
						<c:if test="${pageMaker.prev }">
							<li><a href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1 )}">&laquo;</a></li>
						</c:if>
						<!-- 각 페이지 번호의 출력 -->
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="idx">
							<li
								<c:out value="${pageMaker.cri.page == idx?'class = active':'' }"/>>
								<a href="listPage${pageMaker.makeQuery(idx)}">${idx}</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
							<li><a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1 )}">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
				
				<div class="pull-right">								<!-- session 정보 확인 후 글쓰기 사용 -->
					<c:if test ="${sessionScope.login != null}">
						<button class="btn btn_writer btn-danger">WRITE</button>
					</c:if>
				</div>
				<%-- <form id="jobForm">
					<input type="hidden" name="page" value="${pageMaker.cri.perPageNum}"/>
					<input type="hidden" name="perPageNum" value="${pageMaker.cri.page}"/>
				</form> --%>
				</div>
				<!-- /.box-body -->
				<!-- <div class="box-footer">Footer</div> -->
				<!-- /.box-footer-->
			</div>
		</div>
		<!--/.col (left) -->

	</div>
	<!-- /.row -->
</section>
<!-- /.content -->
</div>
<!-- /.content-wrapper -->
<%@include file="../include/footer2.jsp"%>
<script>
  	/* $(document).ready(function(){
	    $(".pagination li a").on("click", function(event){
	    	event.preventDefault();
	    	var targetPage = $(this).attr("href");
	    	
	    	var jobForm = $("#jobForm");
	    	jobForm.find("[name='page']").val(targetPage);
	    	jobForm.attr("action","/board/listPage").attr("method", "get");
	    	jobForm.submit();
	    });
    }); */
    </script>
