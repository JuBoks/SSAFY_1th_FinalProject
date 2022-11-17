<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- head-->
<%@ include file="../common/head.jsp" %>
<title>회원 정보 조회</title>
</head>
        <!-- script -->
        <script>
        	if("${msg}" != "") {
        		alert("${msg}");
        		location.href="${root}/user?act=detail";
        	}
        </script>
<body>
<!-- Navigation-->
<%@ include file="../common/nav.jsp" %>

<div class="container position-relative mt-3">
	<h3> 회원정보 조회 </h3>
	<form id="user-modify-form" method="post" action="${ root }/user/modify">
		<table class="table">
			<tr>
				<th class="col-3 form-label">아이디</th>
				<td class="col-9">${ userInfo.userId }</td>
			</tr>
			<tr>
				<th class="form-label">이름</th>
				<td>
					<input type="text" id="userName" name="userName" value="${ userInfo.userName }" class="form-control">
				</td>
			</tr>
			<tr>
				<th class="form-label">주소</th>
				<td>
					<input type="text" id="userAddr" name="userAddr" value="${ userInfo.userAddr }" class="form-control">
				</td>
			</tr>
			<tr>
				<th class="form-label">연락처</th>
				<td>
					<input type="text" id="userPhone" name="userPhone" value="${ userInfo.userPhone }" class="form-control">
				</td>
			</tr>
		</table>
		<div class="text-end">
			<button class="btn btn-warning" type="button" id="modifyUserBtn">수정하기</button>
			<button class="btn btn-danger" type="button" id="deleteUserBtn">회원탈퇴</button>
		</div>
	</form>
</div>

<script>
document.querySelector("#modifyUserBtn").onclick = function(e) {
	e.preventDefault();
	
	// 1. validation check
	const userName = document.querySelector("#userName");
	const userAddr = document.querySelector("#userAddr");
	const userPhone = document.querySelector("#userPhone");
	
	// 2. submit
	document.querySelector("#user-modify-form").submit();
}

document.querySelector("#deleteUserBtn").onclick = function(e) {
	e.preventDefault();
	
	if(!confirm("정말 탈퇴하시겠습니까?")) return;
	
	// 2. submit
	location.href="${ root }/user/delete?userId=${ userInfo.userId }";
}
</script>


<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<%@ include file="../common/modal.jsp" %>

</body>
</html>