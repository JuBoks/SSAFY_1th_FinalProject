// 필요없는 파일
//회원가입
function dosignup() {
    var id = document.querySelector("#id").value;
    var pwd = document.querySelector("#pwd").value;
    var username = document.querySelector("#username").value;
    var address = document.querySelector("#address").value;
    var number = document.querySelector("#number").value;

    if (!id || !pwd || !username || !address || !number) {
        alert("회원가입을 할 수 없습니다.");
        return;
    }

    var info = {
        id: id, 
        pwd: pwd,
        username: username,
        address: address,
        number: number
    }
    var info_json = JSON.stringify(info);
    localStorage.setItem("info", info_json);

    alert("회원가입 성공!!");

    document.location.reload();
}

//로그인
function dologin() {
    var loginid = document.querySelector("#loginid").value;
    var loginpw = document.querySelector("#loginpwd").value;
    var login_info = JSON.parse(localStorage.getItem("info"));
    var id = login_info.id;
    var pwd = login_info.pwd;

    
    if ( !id || !pwd || !loginid || !loginpw || loginid != id && loginpw != pwd) {
        alert("로그인을 할 수 없습니다.");
        return;
    }
    else if (loginid != id && loginpw == pwd) {
        alert("아이디를 확인해주세요.");
        return;
    }
    else if (loginid == id && loginpw != pwd) {
        alert("비밀번호를 확인해주세요.");
        return;
    }
    else {
        console.log(loginid);
        console.log(loginpw);
        console.log(id);
        console.log(pwd);
        var log_info = {
            loginid: loginid,
            loginpw: loginpw
        }
        var log_json = JSON.stringify(log_info);
        localStorage.setItem("log_info", log_json);

        alert("로그인하였습니다.");
    
        document.querySelector("#loginsignup").style.display = "none";
        document.querySelector("#logoutinformation").style.display = "";
        document.querySelector(".btn-close").click();
    }
}

//로그아웃
function logout() {
    document.querySelector("#loginsignup").style.display = "";
    document.querySelector("#logoutinformation").style.display = "none";
}