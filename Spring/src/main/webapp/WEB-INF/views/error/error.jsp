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
<title>[타이틀]</title>
</head>
<body>
<!-- Navigation-->
<%@ include file="../common/nav.jsp" %>

<div class="container">

	<h1>에러페이지 입니다.</h1>
	<h3>${ msg }</h3>
</div>



<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<%@ include file="../common/modal.jsp" %>

</body>
</html>