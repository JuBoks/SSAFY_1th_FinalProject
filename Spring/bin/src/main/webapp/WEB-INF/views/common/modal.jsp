<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 로그인 모달 -->
<div id="modal">
	<div class="modal fade" id="login" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form id="login-form" action="" method="post">
						<div class="mb-3">
							<label>아이디</label> <input class="form-control" type="text"
								name="userId" id="loginid" placeholder="아이디" value="ssafy">
						</div>
						<div class="mb-3">
							<label>비밀번호</label> <input class="form-control" type="password"
								name="userPwd" id="loginpwd" placeholder="비밀번호" value="1234">
						</div>
						<div class="d-grid d-sm-flex justify-content-sm-end">
							<button class="btn btn-primary" type="submit" id="login_btn">로그인</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					비밀번호를 잊었을 때는, <a data-bs-toggle="modal" href="#find_id">여기</a>를
					눌러주세요.
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="signup" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form id="join-form" method="post" action="">
						<div class="mb-3">
							<label>아이디</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="userId" id="id"
								placeholder="아이디">
						</div>
						<div class="mb-3">
							<label>비밀번호</label> <span class="text-danger">*</span> <input
								class="form-control" type="password" name="userPwd" id="pwd"
								placeholder="영문 숫자 포함 6자리 이상">
						</div>
						<div class="mb-3">
							<label>이름</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="userName" id="username"
								placeholder="User Name">
						</div>
						<div class="mb-3">
							<label>주소</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="userAddr" id="address"
								placeholder="Address">
						</div>
						<div class="mb-3">
							<label>전화번호</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="userPhone" id="number"
								placeholder="010-XXXX-XXXX">
						</div>
						<c:if test="${!empty loginUser and loginUser.userAuth == 0 }">
							<div class="mb-3">
								<label>권한</label> <span class="text-danger">*</span>
								<select name="userAuth" class="form-select" >
	                                <option value="0" selected>관리자</option>
	                                <option value="1">일반</option>
	                            </select>
							</div>
							<div class="d-grid d-sm-flex justify-content-sm-end">
								<button class="btn btn-primary" type="button" id="join_btn_admin" >회원 등록</button>
							</div>
							<script>
								document.querySelector("#join_btn_admin").onclick = function(e) {
									e.preventDefault();
									
									let form = document.querySelector("#join-form");
									form.setAttribute("action", "${root}/admin/user/add");
									form.submit();
								};
							</script>
						</c:if>
						<c:if test="${empty loginUser or !empty loginUser and loginUser.userAuth != 0 }">
							<div class="d-grid d-sm-flex justify-content-sm-end">
								<button class="btn btn-primary" type="button" id="join_btn" >회원 등록</button>
							</div>
							<script>
								document.querySelector("#join_btn").onclick = function(e) {
									e.preventDefault();
									
									let form = document.querySelector("#join-form");
									form.setAttribute("action", "${root}/user/join");
									form.submit();
								};
							</script>
						</c:if>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		document.querySelector("#login_btn").onclick = function(e) {
			e.preventDefault();
			// 1. form value 가져오기

			// 2. validation check
			if (!document.querySelector("#loginid").value) {
				alert("아이디 입력!!");
				return;
			} else if (!document.querySelector("#loginpwd").value) {
				alert("비밀번호 입력!!");
				return;
			} else {
				let form = document.querySelector("#login-form");
				form.setAttribute("action", "${root}/user/login");
				form.submit();
			}

			// 3. submit
			let loginFormId = document.querySelector("#login-form");
			loginFormId.submit();
		};
	</script>

	<!-- 회원정보확인 모달 -->

	<div class="modal fade" id="information" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">회원 정보 확인</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<div class="mb-3">
						<label>아이디</label> <span class="text-danger">*</span> <span
							class="mx-3">pjt_13</span>
					</div>
					<div class="mb-3">
						<label>비밀번호</label> <span class="text-danger">*</span> <span
							class="mx-3">pjt_13</span>
					</div>
					<div class="mb-3">
						<label>이름</label> <span class="text-danger">*</span> <span
							class="mx-3">송주영 황수빈</span>
					</div>
					<div class="mb-3">
						<label>주소</label> <span class="text-danger">*</span> <span
							class="mx-3">삼성전기 부산</span>
					</div>
					<div class="mb-3">
						<label>전화번호</label> <span class="text-danger">*</span> <span
							class="mx-3">010-XXXX-XXXX</span>
					</div>
					<div class="d-grid d-sm-flex justify-content-sm-end">
						<button class="btn btn-primary me-1" type="button">확인</button>
						<button class="btn btn-primary me-1" type="button">수정</button>
						<button class="btn btn-primary" type="button">탈퇴</button>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 비밀번호 찾기 모달 -->

	<div class="modal fade" id="find_id" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">비밀번호 찾기</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form action="">
						<div class="mb-3">
							<label>이름</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="name" id="name"
								placeholder="이름">
						</div>
						<div class="mb-3">
							<label>아이디</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="id" id="id"
								placeholder="아이디">
						</div>
						<div class="mb-3">
							<label>전화번호</label> <span class="text-danger">*</span> <input
								class="form-control" type="text" name="number" id="number"
								placeholder="010-XXXX-XXXX">
						</div>
						<div class="d-grid d-sm-flex justify-content-sm-end">
							<button class="btn btn-primary" type="button" disabled>찾기</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>


	<!-- 관심지역 추가 모달 -->

	<div class="modal fade" id="interest_area" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">관심 지역</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
					<form class="form-subscribe" id="favorite-add-form" method="post"
						action="${ root }/favorite">
						<input type="hidden" id="act" name="act" value="favoriteAdd">
						<!-- Email address input-->
						<div class="row me-10">
							<div class="col text-center">
								<label>관심 지역 설정</label> <span class="text-danger">*</span>
							</div>
							<div class="col-md-8">
								<select class="form-select form-sido" id="formSido" name="formSido">
									<option selected>도/광역시</option>
								</select> <select class="form-select mt-3 form-gugun" id="formGugun"
									name="formGugun">
									<option selected>시/구/군</option>
								</select> <select class="form-select mt-3 form-dong" id="formDong"
									name="formDong">
									<option selected>동</option>
								</select>
								<div class="row mt-3">
									<div>
										<button class="btn btn-success btn-sm" type="submit"
											id="favorite-add">등록 ✔</button>
									</div>
								</div>
							</div>
						</div>
						<input type="hidden" name="valSido" id="valSido" >
						<input type="hidden" name="valGugun" id="valGugun" >
						<input type="hidden" name="valDong" id="valDong" >
					<!-- 	<input type="hidden" name="formDong" value="" id="paramDong" > -->
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade" id="combination_modal" tabindex="-1">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">조합 리스트</h4>
					<button class="btn-close" type="button" data-bs-dismiss="modal"></button>
				</div>
				<div class="modal-body">
				</div>
			</div>
		</div>
	</div>
	
</div>