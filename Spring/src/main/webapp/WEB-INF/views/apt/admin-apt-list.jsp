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
<title>[관리자]아파트 관리</title>
<!-- script -->
        <script>
        	if("${msg}" != "") {
        		alert("${msg}");
        		location.href="${root}/apt?act=listAptByAdmin";
        	}
        </script>
</head>
<body>
<!-- Navigation-->
<%@ include file="../common/nav.jsp" %>

	<div class="row justify-content-center">
        <div class="col-lg-8 col-md-10 col-sm-12">
          <h2 class="my-3 py-3 bg-white text-center">
            <div class="sky">아파트 목록</div>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <div class="row align-self-center mb-2">
            <div class="col-md-2 text-start">
              <button type="button" id="btn-mv-register" class="btn btn-outline-primary btn-sm" data-bs-toggle="modal" href="#registApt">
                아파트 등록
              </button>
            </div>
	      </div>
          <form id="form-search" action="${ root }/apt">
          	 <input type="hidden" name="act" value="listAptByAdmin" >
		      <div class="row align-self-center mb-2">
	     		<div class="col">
	                <select class="form-select form-sido" id="sido" name="sido">
	                    <option value="" selected>시도선택</option>
	                </select>
	            </div>
	            <div class="col">
	                <select class="form-select form-gugun" id="gugun" name="gugun">
	                    <option value="" selected>구군선택</option>
	                </select>
	            </div>
	            <div class="col">
	                <select class="form-select form-dong" id="dong" name="dong">
	                    <option value="" selected>동선택</option>
	                </select>
	            </div>
	            <div class="col">
	            	<div class="input-group input-group-sm h-100">
	                  <input type="text" class="form-control" name="aptName" placeholder="아파트 이름 검색...ex) 캐슬" />
	                  <button id="btn-search" class="btn btn-dark" type="submit">검색</button>
	                </div>
	            </div>
		      </div>
          </form>
          
          <table class="table table-hover">
            <thead>
              <tr class="text-center">
                <th scope="col">코드</th>
                <th scope="col">이름</th>
                <th scope="col">거래금액</th>
                <th scope="col">면적</th>
                <th scope="col">건축년도</th>
                <th scope="col">주소</th>
                <th scope="col">거래일자</th>
                <th scope="col">수정하기</th>
                <th scope="col">삭제하기</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach var="item" items="${ aptList }">
              <tr class="text-center">
              <form method="post" action="${ root }/apt">
              	<input type="hidden" name="act" value="modifyAptByAdmin" >
              	<input type="hidden" name="aptCode" value="${ item.aptCode }" >
                <td scope="row">${ item.aptCode }</td>
                <td><input type="text" id="aptName" name="aptName" value="${ item.aptName }" class="w-100 text-center" ></td>
                <td><input type="text" id="aptDealAmount" name="aptDealAmount" value="${ item.aptDealAmount }"  class="w-100 text-center" ></td>
                <td><input type="text" id="aptArea" name="aptArea" value="${ item.aptArea }" class="w-100 text-center" ></td>
                <td><input type="text" id="aptBuildYear" name="aptBuildYear" value="${ item.aptBuildYear }" class="w-100 text-center" ></td>
                <td><input type="text" id="aptAddr" name="aptAddr" value="${ item.aptAddr }" class="w-100 text-center"  disabled></td>
                <td><input type="text" id="aptDealDate" name="aptDealDate" value="${ item.aptDealYear }-${ item.aptDealMonth }-${ item.aptDealDay }" class="w-100 text-center" disabled></td>
                <td>
                	<button id="deleteAptBtn" class="btn-warning" type="submit" >수정하기</button>
                </td>
               </form>
                <td>
                	<button id="deleteAptBtn" class="btn-danger" type="button"
                	onclick="if(confirm('${ item.aptName } 삭제하겠습니까?')){ location.href='${ root }/apt?act=removeAptByAdmin&aptCode=${ item.aptCode }' }">삭제하기</button>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          
          <div class="row">
	          <ul class="pagination justify-content-center">
		        <li class="page-item" style="cursor: pointer; ">
	              <a class="page-link" value="${ startPage - 1 }">이전</a>
	            </li>
	            <c:forEach begin="${ startPage }" end="${ endPage }" var="i" >
	            <c:choose>
	            	<c:when test="${ activePgno == i }">
	            	<li class="page-item active" style="cursor: pointer; ">
		              <a class="page-link" >${ i }</a>
		            </li>
	            	</c:when>
	            	<c:otherwise>
	            	<li class="page-item" style="cursor: pointer; ">
	            		<a class="page-link" value=${ i } >${ i }</a>
	            	</li>
	            	</c:otherwise>
	            </c:choose>
	            </c:forEach>
	            <li class="page-item" style="cursor: pointer; ">
	              <a class="page-link" value="${ endPage + 1 }">다음</a>
	            </li>
	          </ul>
	        </div>
	        
	        <input type="hidden" id="dongCodeSearched" value="" />
	        <input type="hidden" id="aptNameSearched" value="" />
        </div>
	</div>

<script>
window.onload = function() {
	
	// 브라우저가 열리면 시/도 얻기
    sendRequest("sido", "sido", "");
    // 시도가 바뀌면 구군정보 얻기.
    document.querySelector("#sido").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value;
            sendRequest("gugun", "gugun", regcode);
        } else {
            initOption("gugun");
            initOption("dong");
        }
    });
    
    // 구군이 바뀌면 동정보 얻기.
    document.querySelector("#gugun").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value;
            sendRequest("dong", "dong", regcode);
        } else {
            initOption("dong");
        }
    });
    
    // 페이지 클릭
    document.querySelectorAll(".page-link").forEach(el => {
    	el.addEventListener("click", function() {
    		let url = "${ root }/apt?act=listAptByAdmin&pgno=" + el.getAttribute("value");
    		url += "&dong=${ dongCode }";
    		url += "&aptName=${ aptName }";
    		
    		location.href = url;
    	});
    });

}

function sendRequest(act, selid, regcode) {
    const url = `http://localhost:8080${ root }/area`;
    let params = "act=" + act;
    switch (act) {
    case "sido":
    	break;
    case "gugun":
    	params += "&sidoCode=" + regcode;
    	break;
    case "dong":
    	params += "&gugunCode=" + regcode;
    	break;
    }
    console.log(url + "?" + params)
    fetch(url + "?" + params)
        .then((response) => response.json())
        .then((data) => addOption(selid, data));
}

function initOption(selid) {
    let options = document.querySelector("#" + selid);
    options.length = 0;
}
function addOption(selid, data) {
    let opt = ``;
    initOption(selid);

    switch (selid) {
    case "formSido":
    case "sido":
        opt += `<option value="">시도선택</option>`;
        for(let key in data) {
        	opt += "<option value='" + key + "' >" + data[key] + "</option>";
        }
        break;
    case "formGugun":
    case "gugun":
        opt += `<option value="">구군선택</option>`;
        for(let key in data) {
        	opt += "<option value='" + key + "' >" + data[key] + "</option>";
        }
        break;
    case "formDong":
    case "dong":
        opt += `<option value="">동선택</option>`;
        
        for(let key in data) {
        	opt += "<option value='" + key + "' >" + data[key] + "</option>";
        }
        break;
    }
    document.querySelector("#" + selid).innerHTML = opt;
}
</script>


<!-- Footer-->
<%@ include file="../common/footer.jsp" %>

<!-- Modal -->
<div class="modal fade" id="registApt" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">아파트 추가</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form id="registApt-form" method="post" action="${ root }/apt">
						<input type="hidden" name="act" value="registAptByAdmin">
						<div class="mb-3">
							<label>아파트 코드</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="aptCode" id="aptCode" value="212"
								placeholder="코드 입력 ... ">
						</div>
						<div class="mb-3">
							<label>아파트 동 코드</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="aptDongCode" id="aptDongCode" value="1111010100"
								placeholder="동 코드 입력 ... ">
						</div>
						<div class="mb-3">
							<label>아파트 이름</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="aptName" id="aptName" value="청운동 아파트 테스트용"
								placeholder="아파트 이름 입력 ... ">
						</div>
						<div class="mb-3">
							<label>아파트 거래금액</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptDealAmount" id="aptDealAmount" value="10000"
								placeholder="0 ">
						</div>
						<div class="mb-3">
							<label>아파트 면적</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptArea" id="aptArea" value="45.23"
								placeholder="0.0 ">
						</div>
						<div class="mb-3">
							<label>아파트 건축년도</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptBuildYear" id="aptBuildYear" value="2021"
								placeholder="2022">
						</div>
						<div class="mb-3">
							<label>아파트 거래년도</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptDealYear" id="aptDealYear" value="2021"
								placeholder="2022">
						</div>
						<div class="mb-3">
							<label>아파트 거래월</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptDealMonth" id="aptDealMonth" value="12"
								placeholder="1">
						</div>
						<div class="mb-3">
							<label>아파트 거래일</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptDealDay" id="aptDealDay" value="25"
								placeholder="1">
						</div>
						<div class="mb-3">
							<label>아파트 시군구코드</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptSiGunguCode" id="aptSiGunguCode" value="11110"
								placeholder="11110">
						</div>
						<div class="mb-3">
							<label>아파트 도로명</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="aptRoadName" id="aptRoadName" value="자하문로33길"
								placeholder="사직로 8길">
						</div>
						<div class="mb-3">
							<label>아파트 지번</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="aptJibun" id="aptJibun" value="56-45"
								placeholder="9">
						</div>
						<div class="mb-3">
							<label>아파트 위도</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptLat" id="aptLat" value="37.5861486417138"
								placeholder="37.5746540320628">
						</div>
						<div class="mb-3">
							<label>아파트 경도</label> <span class="text-danger">*</span> <input
								class="form-control" type="number" name="aptLng" id="aptLng" value="126.966930414705"
								placeholder="126.968575235313">
						</div>
						
						
						<div class="d-grid d-sm-flex justify-content-sm-end">
							<button class="btn btn-primary" type="submit" >아파트 등록</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


</body>
</html>