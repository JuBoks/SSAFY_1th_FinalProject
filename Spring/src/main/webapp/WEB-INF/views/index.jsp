<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>구해줘 홈즈!</title>
        
        <!-- head-->
        <%@ include file="./common/head.jsp" %>
        
        <!-- kakao map -->
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ca952765a8b85b240017701b0a1c3e4b&libraries=services,clusterer,drawing"></script>
        <style>
            .apt-name {
                cursor: pointer;
            }
            .apt-name:hover {
                color: #4581D0;
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <!-- Navigation-->
        <%@ include file="./common/nav.jsp" %>
        
        <!-- Masthead-->
        <header class="masthead">
            <div class="container position-relative">
                <div class="row justify-content-center">
                    <div class="col">
                        <div class="text-center text-white">
                            <!-- Page heading-->
                            <h1 class="mb-5"><img src="${ root }/assets/img/house_icon.png" style="height: 60px; filter: invert();" alt=""> 구해줘 홈즈 <img src="./assets/img/house_icon.png" style="height: 60px; filter: invert();" alt=""></h1>
                            <form class="form-subscribe">
                                <!-- Email address input-->
                                <div class="row">
                                	<div class="col">
                                        <select class="form-select" id="kind">
                                            <option value="" selected>종류선택(All)</option>
                                            <option value="apt">아파트</option>
                                            <option value="alliance">다세대주택</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select class="form-select form-sido" id="sido">
                                            <option selected>시도선택</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select class="form-select form-gugun" id="gugun">
                                            <option selected>구군선택</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select class="form-select form-dong" id="dong">
                                            <option value="" selected>동선택</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select class="form-select" id="year">
                                            <option selected>매매년도선택</option>
                                        </select>
                                    </div>
                                    <div class="col">
                                        <select class="form-select" id="month">
                                            <option value="" selected>매매월선택</option>
                                        </select>
                                    </div>
                                </div>
                            </form>
                            <button type="button" class="mt-3 btn btn-primary" id="searchApt">찾아보기</button>
                        </div>
                    </div>
                </div>
            </div>
        </header>
        
        <!-- 아파트 정보 -->
        <div class="container my-3" style="display: none;" id="aptListInfo">
			<div class="accordion mb-3" id="accordionFlushExample">
				<div class="accordion-item">
					<h2 class="accordion-header" id="flush-headingOne">
						<button class="accordion-button collapsed fs-3" type="button"
							data-bs-toggle="collapse" data-bs-target="#flush-collapseOne"
							aria-expanded="false" aria-controls="flush-collapseOne">
							거래 정보</button>
					</h2>
					<div id="flush-collapseOne" class="accordion-collapse collapse"
						aria-labelledby="flush-headingOne"
						data-bs-parent="#accordionFlushExample">
						<div class="accordion-body">
							<div class="input-group mb-3">
							  <!-- <input type="text" class="form-control" placeholder="Username" aria-label="Username"> -->
			        		  <input type="number" class="form-control" id="lowBind" placeholder="하한선" value="5000">
							  <span class="input-group-text">~</span>
							  <input type="number" class="form-control" id="highBind" placeholder="상한선" value="10000">
							  <span class="input-group-text">개수</span>
							  <input type="number" class="form-control" id="units" placeholder="2~5개만 가능합니다." min="2" max="5">
							  <button type="button" id="combinateInfo" class="btn btn-outline-secondary" data-bs-toggle="modal" href="#combination_modal" >조합검색</button>
							</div>
			        	</div>
			        </div>
				</div>
			</div>

            <div class="container justify-content-between h-50">
            	<div class="row">
	                <!-- 아파트 정보 리스트 -->
	                <div class="col-4" style="overflow-y:scroll; height: 80vh; ">
	                    <ul class="list-group list-group-flush" id="aptList">
	                    </ul>
	                </div>
	                <!-- 지도 -->
	                <div class="col-8">
	                    <div id="map" style="width:100%;height:500px;"></div>
	                </div>
                </div>
            </div>
        </div>

        <!-- Footer-->
        <%@ include file="./common/footer.jsp" %>

        <!-- Modal -->
        <%@ include file="./common/modal.jsp" %>

        <!-- Core theme JS-->
        <script src="${root }/assets/js/scripts.js"></script>
        <script src="${root }/assets/js/header.js"></script>
        
        <script>
        
        </script>
    </body>
</html>

