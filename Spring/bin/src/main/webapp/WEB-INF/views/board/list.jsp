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
<!-- script -->
        <script>
        	if("${msg}" != "") {
        		alert("${msg}");
        		location.href="${root}/board?act=list";
        	}
        </script>

<c:if test="${articles == null}">
	<script type="text/javascript">
		alert("정상적인 URL 접근이 아닙니다.");
		location.href = "${root}/board?act=list&pgno=1&key=&word=";
	</script>
</c:if>
      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 bg-white text-center">
            <div class="sky">공지사항</div>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
            <c:if test="${!empty loginUser and loginUser.userAuth ==0 }">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm">
                글쓰기
              </button>
            </c:if>
            </div>
            <div class="col-md-7 offset-3">
              <form class="d-flex" id="form-search" action="" method="get">
                <select
                  class="form-select form-select-sm ms-5 me-1 w-50"
                  name="key"
                  aria-label="검색조건"
                >
                  <option value="" >검색조건</option>
                  <option value="subject" selected>제목</option>
                  <option value="userid">작성자</option>
                  <option value="precise">정확도</option>
                </select>
                <div class="input-group input-group-sm">
                  <input type="text" class="form-control" name="word" placeholder="검색어..." />
                  <button id="btn-search" class="btn btn-dark" type="submit">검색</button>
                </div>
              </form>
            </div>
          </div>
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">글번호</th>
                <th scope="col">제목</th>
                <th scope="col">작성자</th>
                <th scope="col">조회수</th>
                <th scope="col">작성일</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${ articles }">
              <tr class="text-center">
                <th scope="row">${ article.articleNo }</th>
                <td class="text-start">
                  <a
                    href="#"
                    class="article-title link-dark"
                    data-no="${ article.articleNo }"
                    style="text-decoration: none"
                  >
                    	${ article.subject }
                  </a>
                </td>
                <td>${ article.userId }</td>
                <td>${ article.hit }</td>
                <td>${ article.registerTime }</td>
              </tr>
            </c:forEach>

            </tbody>
          </table>
        </div>
        <div class="m-3 row">${navigation.navigator}</div>
      </div>
    </div>
    <form id="form-no-param" method="get" action="${ root }/board/view">
      <input type="hidden" id="pgno" name="pgno" value="${ pgno }">
      <input type="hidden" id="key" name="key" value="${ key }">
      <input type="hidden" id="word" name="word" value="${ word }">
      <input type="hidden" id="articleno" name="articleNo" value="${ article.articleNo }">
    </form>
    <script>
      let titles = document.querySelectorAll(".article-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          document.querySelector("#articleno").value = this.getAttribute("data-no");
          document.querySelector("#form-no-param").submit();
        });
      });

      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board/write";
      });
      
      document.querySelector("#btn-search").addEventListener("click", function () {
    	  let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/board/list");
          form.submit();
      });

    </script>


<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<%@ include file="../common/modal.jsp" %>

</body>
</html>
