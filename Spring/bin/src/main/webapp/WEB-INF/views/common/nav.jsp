<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Navigation-->
<nav class="navbar navbar-expand-sm navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="${ root }/"><img
			src="${root}/assets/img/logo.png" style="height: 60px;" alt=""></a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>

		<ul class="navbar-nav justify-content-start" id="logoutinformation">
			<li class="nav-item"><a class="nav-link"
				href="${ root }/board/list">공지사항</a></li>
		</ul>
		<div class="collapse navbar-collapse justify-content-end" id="navb">

			<c:if test="${ !empty loginUser}">
				<c:if test="${loginUser.userAuth ==1 }">

					<ul class="navbar-nav" id="logoutinformation">

						<li class="nav-item"><a class="nav-link"
							style="pointer: none;">어서오세요 ${ loginUser.userName} 님.</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${ root }/user/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${ root }/user/view">회원정보</a></li>
						<li class="nav-item"><a class="nav-link"
							data-bs-toggle="modal" id="interestArea"
							onclick="sendRequest('formSido', '*00000000');"
							href="#interest_area">관심지역 추가</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-bs-toggle="dropdown"
							href="#">관심지역 보기</a>
							<div class="dropdown-menu">
							<c:set var="address" value="${ cookie.addr.value.split(':') }"></c:set>
								<a class="dropdown-item" id="getFavoriteAddr" style="cursor:pointer; " value="${ cookie.addr.value }" >${address[0]}&nbsp;${address[1]}&nbsp;${address[2]}</a>
							</div></li>
					</ul>
				</c:if>


				<c:if test="${loginUser.userAuth == 0 }">
					<ul class="navbar-nav" id="logoutinformation">
						<li class="nav-item"><a class="nav-link"
							style="pointer: none;">어서오세요 [ADMIN]${ loginUser.userName} 님.</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${ root }/user/logout">로그아웃</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${ root }/admin/user/list">회원관리</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${ root }/admin/apt/list">아파트관리</a></li>
					</ul>
				</c:if>
			</c:if>

			<c:if test="${ empty loginUser}">
				<ul class="navbar-nav" id="loginsignup">
					<li class="nav-item"><a class="nav-link"
						data-bs-toggle="modal" href="#login">로그인</a></li>
					<li class="nav-item"><a class="nav-link"
						data-bs-toggle="modal" href="#signup">회원가입</a></li>
				</ul>
			</c:if>

		</div>
	</div>
</nav>