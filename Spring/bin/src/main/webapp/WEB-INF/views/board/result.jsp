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


      <div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 bg-white text-center">
            <div class="sky">글쓰기 결과</div>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-primary pt-3">등록 완료!!!</h2>
            <div class="card-body">
              <p class="card-text">글작성이 완료되었습니다.</p>
              <button type="button" id="btn-list" class="btn btn-outline-primary">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
          <div class="card text-center bg-light">
            <h2 class="fw-bold text-danger pt-3">등록 실패T.T</h2>
            <div class="card-body">
              <p class="card-text">
                글작성에 문제가 있습니다. <br />
                잠시 후 시도해주세요.
              </p>
              <button type="button" id="btn-list" class="btn btn-outline-danger">
                글목록 페이지 이동...
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "";
      });
    </script>



<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<%@ include file="../common/modal.jsp" %>

</body>
</html>
