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
        <!-- script -->
        <script>
        	if("${msg}" != "") {
        		alert("${msg}");
        		location.href="${root}/user?act=listUserByAdmin";
        	}
        </script>
</head>
<body>
<!-- Navigation-->
<%@ include file="../common/nav.jsp" %>

      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 bg-white text-center">
            <div class="sky">사용자 목록</div>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
          	<c:if test="${!empty loginUser and loginUser.userAuth == 0 }">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" href="#signup">
                사용자 등록
              </button>
            </c:if>
            </div>
            <div class="col-md-7 offset-3">
            
               <form class="d-flex" id="form-search" action="${ root }/admin/user/list">
<!--                 <select
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  name="key"
                  aria-label="검색조건"
                >
                  <option value="" >검색조건</option>
                  <option value="subject" selected>제목</option>
                  <option value="userid">작성자</option>
                </select> -->
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" name="userId" placeholder="아이디 검색..." />
                  <button id="btn-search" class="btn btn-dark" type="submit">검색</button>
                </div>
              </form>
              
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">아이디</th>
                <th scope="col">이름</th>
                <th scope="col">주소</th>
                <th scope="col">연락처</th>
                <th scope="col">수정하기</th>
                <th scope="col">삭제하기</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach var="item" items="${ userList }">
              <tr class="text-center">
              <form method="post" action="${ root }/admin/user/modify">
              	<input type="hidden" name="pgno" value="${pgno }">
              	<input type="hidden" name="key" value="${key }">
              	<input type="hidden" name="word" value="${word }">
              	<input type="hidden" name="userId" value="${ item.userId }" >
                <td scope="row">${ item.userId }</td>
<%--                 <td class="text-start">
                  <a
                    href="#"
                    class="article-title link-dark"
                    data-no="${ article.articleNo }"
                    style="text-decoration: none"
                  >
                    	${ item.userName }
                  </a>
                </td> --%>
                <td><input type="text" id="userName" name="userName" value="${ item.userName }" class="w-100 text-center" ></td>
                <td><input type="text" id="userAddr" name="userAddr" value="${ item.userAddr }"  class="w-100 text-center" ></td>
                <td><input type="text" id="userPhone" name="userPhone" value="${ item.userPhone }" class="w-100 text-center" ></td>
                <td>
                	<button id="deleteUserBtn" class="btn-warning" type="submit">수정하기</button>
                </td>
                </form>
                <td>
                	<button id="deleteUserBtn" class="btn-danger" type="button"
                	onclick="if(confirm('${ item.userId } 삭제하겠습니까?')){ location.href='${ root }/admin/user/remove?userId=${ item.userId }' }">삭제하기</button>
                </td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
        
        <div class="m-3 row">${navigation.navigator}</div>
<%--         <div class="row">
          <ul class="pagination justify-content-center">
            <c:forEach begin="${ startPage }" end="${ endPage }" var="i" >
            <c:choose>
            	<c:when test="${ activePgno == i }">
            	<li class="page-item active">
	              <a class="page-link" href="${ root }/user?act=listUserByAdmin&pgno=${i}">${ i }</a>
	            </li>
            	</c:when>
            	<c:otherwise>
            	<li class="page-item"><a class="page-link" href="${ root }/user?act=listUserByAdmin&pgno=${i}">${ i }</a></li>
            	</c:otherwise>
            </c:choose>
            </c:forEach>
          </ul>
        </div>  --%>
        
      </div>

<script>
function modifyUser(userId) {
	location.href = "${ root }/user?act=removeUserByAdmin&userId=" + userId;
}
</script>

<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<%@ include file="../common/modal.jsp" %>

</body>
</html>