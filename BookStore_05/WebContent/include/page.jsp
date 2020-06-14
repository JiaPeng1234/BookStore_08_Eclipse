<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/include/base.jsp"%>
<!-- page.url: manager/BookManagerServlet?method=page -->

<script type="text/javascript">
	$(function() {		
		$("#gotopage").click(function(){
			// 用户输入了要去第几页
			// 1.获取用户输入的值
			var pn = $("#pn_input").val();
			// 2.发送新的分页请求
			var href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/";
			window.location.href = href+"${page.url}&pn="+pn;
		});
	});
</script>

<div id="page_nav">
	<!-- first page -->
	<a href="${page.url }&pn=1">Begin</a>
	
	<c:if test="${page.hasPrev}">
		<a href="${page.url }&pn=${page.pageNo-1 }">Previous</a>
	</c:if>
	
	<!-- show pages -->
	<!-- total pages <= 5 -->
	<c:if test="${page.totalPage<=5}">
		<c:set var="begin" value="1" scope="page"></c:set>
		<c:set var="end" value="${page.totalPage }" scope="page"></c:set>
	</c:if>
	<!-- total pages > 5 -->
	<c:if test="${page.totalPage>5}">
		<c:if test="${page.pageNo<=3}">
			<c:set var="begin" value="1" scope="page"></c:set>
			<c:set var="end" value="5" scope="page"></c:set>
		</c:if>
		<c:if test="${page.pageNo>3}">
			<c:set var="begin" value="${page.pageNo-2 }" scope="page"></c:set>
			<c:set var="end" value="${page.pageNo+2 }" scope="page"></c:set>
			<c:if test="${page.pageNo+2>page.totalPage }">
				<c:set var="end" value="${page.totalPage}" scope="page"></c:set>
			</c:if>
		</c:if>
	</c:if>
	<c:forEach begin="${begin}" end="${end}" var="pnum">
		<!-- assess pnum equals currentNo or not -->
		<!-- if not, use href -->
		<c:if test="${pnum!=page.pageNo}">
			<a href="${page.url }&pn=${pnum }">${pnum }</a> 
		</c:if>
		<!-- if yes, just show current page number -->
		<c:if test="${pnum==page.pageNo}">
			<!-- current page -->
			<span style="color:blue;">【${page.pageNo }】</span>
		</c:if>
	</c:forEach>
	
	<c:if test="${page.hasNext}">
		<a href="${page.url }&pn=${page.pageNo+1 }">Next</a>
	</c:if>
	<!-- last page -->
	<a href="${page.url }&pn=${page.totalPage }">End</a> 
	${page.totalPage } Pages, ${page.totalCount } Records, to <input value="" name="pn" id="pn_input" /> Page 
	<input type="button" value="Go" id="gotopage">
</div>