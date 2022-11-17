var date = new Date();
var API_KEY = 'ydzP1vshtX3XU9Zu3XK5RPZ2ftkK%2FrhzsCjKiPIFTb%2BdQp5LsUFibuvueXSZ7lfZ%2BYVmMbMhs3ITHjdBhfnYjQ%3D%3D';
var map, geocoder, clusters, markers = [];
var parser = new DOMParser();
var infoList = [];

window.onload = function() {
    let yearEl = document.querySelector("#year");
    let yearOpt = `<option value="">매매년도선택</option>`;
    let year = date.getFullYear();
    for (let i = year; i > year - 20; i--) {
        yearOpt += `<option value="${i}">${i}년</option>`;
    }
    yearEl.innerHTML = yearOpt;
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
    
    //////////////////// 관심지역 추가 모달 시도/구군 이벤트 리스너 (위의 코드와 중복되므로 추후 정리요망) 시작코드 /////////////
    sendRequest("sido", "formSido", "");
    
    // 시도가 바뀌면 구군정보 얻기.
    document.querySelector("#formSido").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value;
            sendRequest("gugun", "formGugun", regcode);
            document.querySelector("#valSido").setAttribute("value", this[this.selectedIndex].innerHTML);
        } else {
            initOption("formGugun");
            initOption("formDong");
        }
    });
    
    // 구군이 바뀌면 동정보 얻기.
    document.querySelector("#formGugun").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            let regcode = this[this.selectedIndex].value;
            sendRequest("dong", "formDong", regcode);
            document.querySelector("#valGugun").setAttribute("value", this[this.selectedIndex].innerHTML);
        } else {
            initOption("formDong");
        }
    });
    
    // 동 정보 바뀌면 value 넣기
    document.querySelector("#formDong").addEventListener("change", function () {
        if (this[this.selectedIndex].value) {
            document.querySelector("#valDong").setAttribute("value", this[this.selectedIndex].innerHTML);
        }
    });
    ////////////////////관심지역 추가 모달 시도/구군 이벤트 리스너 (위의 코드와 중복되므로 추후 정리요망) 끝코드 /////////////
    
    // 추가한 관심지역 클릭 시 map 표시
    let favoriteAddr = document.querySelector("#getFavoriteAddr");
    if(favoriteAddr != null) {
    	favoriteAddr.addEventListener("click", function () {
    		let address = this.getAttribute("value").split(':');
    		let regCode = address[4].substr(0, 5);
    		let dongCode = address[5];
    		let sidoGugun = address[0] + ' ' + address[1];
    		let addr = sidoGugun + ' ' + address[2];
    		
    		findByAddress(regCode, dongCode, sidoGugun, addr, "2020", "01");
    	});
    }
    
    // 상한선/하한선으로 매물 정보 조합해서 보여줌
    document.querySelector("#combinateInfo").addEventListener("click", async function () {
    	let low = document.querySelector("#lowBind").value;
    	let high = document.querySelector("#highBind").value;
    	let units = document.querySelector("#units").value;

    	combinations = []; // 초기화
    	// 조합 생성
    	await comb(0, 0, units, 0, low, high);
    	
    	// 클라이언트에게 보낼 html 을 생성하기 위해 부모 요소를 가져온다.
    	let modalBody = document.querySelector("#combination_modal .modal-body");

    	// HTML 만들기
     	let html = "<table class='table'><thead><tr><th></th>";
 		for(let i = 0 ; i < units ; i++) {
 			html += `<th>아파트</th>`;
 			html += `<th>가격</th>`;
 		}
     	html += "</tr></thead><tbody>";
     	
     	let c = 1; // 조합의 개수를 나타내기 위한 변수 선언
     	combList.forEach(el => {
     		html += "<tr><td>" + c++ +"</td>";
     		el.forEach(i => {
     			html += "<td>" + infoList[i].aptName + "</td>";
     			html += "<td>" + infoList[i].aptDealAmount + "</td>";
     		});
     		html += "</tr>";
     	});	
     	
     	html += "</tbody></table>";
     	
     	combList=[]; // 초기화
     	
     	modalBody.innerHTML = html;
    });
    
    document.querySelector("#year").addEventListener("change", function () {
        let month = date.getMonth() + 1;
        let monthEl = document.querySelector("#month");
        let monthOpt = `<option value="">매매월선택</option>`;
        let yearSel = document.querySelector("#year");
        let m = yearSel[yearSel.selectedIndex].value == date.getFullYear() ? month : 13;
        for (let i = 1; i < m; i++) {
            monthOpt += `<option value="${i < 10 ? "0" + i : i}">${i}월</option>`;
        }
        monthEl.innerHTML = monthOpt;
    });

    // 찾아보기 버튼 누르면 아파트 정보 출력
    
    document.querySelector("#searchApt").addEventListener("click", async function () {
    	let kindSel = document.querySelector("#kind");
    	let sidoSel = document.querySelector("#sido");
		let gugunSel = document.querySelector("#gugun");
		let dongSel = document.querySelector("#dong");
		let dongCode = dongSel[dongSel.selectedIndex].value;
		let regCode = gugunSel[gugunSel.selectedIndex].value.substr(0, 5);
		let yearSel = document.querySelector("#year");
		let year = yearSel[yearSel.selectedIndex].value;
		let monthSel = document.querySelector("#month");
		let month = monthSel[monthSel.selectedIndex].value;
		let sidoGugun = sidoSel[sidoSel.selectedIndex].text + " " + gugunSel[gugunSel.selectedIndex].text;
		// 아파트 정보 가져오기 
		let aptUrl = `http://localhost/${ root }/apt/list?`;
		aptUrl += 
			encodeURIComponent("dongCode") + "=" + encodeURIComponent(dongCode);
		aptUrl += 
			"&" + encodeURIComponent("dealYear") + "=" + encodeURIComponent(year);
		aptUrl += 
			"&" + encodeURIComponent("dealMonth") + "=" + encodeURIComponent(month);
		
		// 연립 다세대 정보 가져오기
		let allianceUrl = `http://localhost/${ root }/alliance/list?`;
		allianceUrl += 
			encodeURIComponent("sigunguCode") + "=" + encodeURIComponent(regCode);
		allianceUrl += 
			"&" + encodeURIComponent("dealYear") + "=" + encodeURIComponent(year);
		allianceUrl += 
			"&" + encodeURIComponent("dealMonth") + "=" + encodeURIComponent(month);
		
		let list;
		let kind = kindSel[kindSel.selectedIndex].value;
		if(kind == "apt") {
			list = await getAsyncData(aptUrl);
			list = JSON.parse(list);
		} else if (kind == "alliance") {
			list = await getAsyncData(allianceUrl);
			list = JSON.parse(list);
			list = list.response.body.items.item;
			list = refineAllianceInfo(list, sidoGugun);
		} else { // 전부
			let temp1 = await getAsyncData(aptUrl);
			temp1 = JSON.parse(temp1);
			let temp2 = await getAsyncData(allianceUrl);
			temp2 = JSON.parse(temp2);
			temp2 = temp2.response.body.items.item;
			temp2 = refineAllianceInfo(temp2, sidoGugun);
			
			list = temp1.concat(temp2);
		}
		
		infoList = list;
		
		// 왼쪽에 리스트 보여주기
		makeListByJson(list);
		
		// 지도 보여주기
		document.querySelector("#aptListInfo").style.display = '';
		
		// 지도 가운데로 이동하기
		setCenterAddr(sidoSel[sidoSel.selectedIndex].text + " " + gugunSel[gugunSel.selectedIndex].text + " " + dongSel[dongSel.selectedIndex].text, 7);
		
		// 지도 마커 생성
		setClusterMarker(list);

    });
}

// cnt : 현재 진행된 조합 개수
// start : 조합을 구성하기 위한 start 인덱스
// units : 몇 개를 선택할 것인지
// amount : 현재 조합들의 가격 합
// lowBind : 하한가
// highBind : 상한가
var combinations = []; // 조합이 구성된 리스트
var combList = []; // 조합을 담은 리스트
var c1 = 0; // 조합의 개수
async function comb (cnt, start, units, amount, lowBind, highBind) {
	if(cnt == units) {
		if(amount < lowBind || amount > highBind) {
			return;
		}

		c1++;
		combList.push([...combinations]);
		
		return;
	}
	
	if(amount > highBind) {
		return;
	}
	
	for(let i = start ; i < infoList.length ; i++) {
		combinations[cnt] = i;
		
		let dealAmount = infoList[i].aptDealAmount;
		dealAmount += '';
		dealAmount = dealAmount.replace(",", "");
		
		comb(cnt+1, i+1, units, Number(amount)+Number(dealAmount), lowBind, highBind);
	}
}

async function findByAddress(regCode, dongCode, sidoGugun, addr, year, month) {
	// 아파트 정보 가져오기 
	let aptUrl = `http://localhost/${ root }/apt/list?`;
	aptUrl += 
		encodeURIComponent("dongCode") + "=" + encodeURIComponent(dongCode);
	aptUrl += 
		"&" + encodeURIComponent("dealYear") + "=" + encodeURIComponent(year);
	aptUrl += 
		"&" + encodeURIComponent("dealMonth") + "=" + encodeURIComponent(month);
	
	// 연립 다세대 정보 가져오기
	let allianceUrl = `http://localhost/${ root }/alliance/list?`;
	allianceUrl += 
		encodeURIComponent("sigunguCode") + "=" + encodeURIComponent(regCode);
	allianceUrl += 
		"&" + encodeURIComponent("dealYear") + "=" + encodeURIComponent(year);
	allianceUrl += 
		"&" + encodeURIComponent("dealMonth") + "=" + encodeURIComponent(month);
	
	let list;
	let kindSel = document.querySelector("#kind");
	let kind = kindSel[kindSel.selectedIndex].value;
	if(kind == "apt") {
		list = await getAsyncData(aptUrl);
		list = JSON.parse(list);
	} else if (kind == "alliance") {
		list = await getAsyncData(allianceUrl);
		list = JSON.parse(list);
		list = list.response.body.items.item;
		list = refineAllianceInfo(list, sidoGugun);
	} else { // 전부
		let temp1 = await getAsyncData(aptUrl);
		temp1 = JSON.parse(temp1);
		let temp2 = await getAsyncData(allianceUrl);
		temp2 = JSON.parse(temp2);
		temp2 = temp2.response.body.items.item;
		temp2 = refineAllianceInfo(temp2, sidoGugun);
		
		list = temp1.concat(temp2);
	}

	infoList = list;
	
	// 왼쪽에 리스트 보여주기
	makeListByJson(list);
	
	// 지도 보여주기
	document.querySelector("#aptListInfo").style.display = '';
	
	// 지도 가운데로 이동하기
	setCenterAddr(addr, 7);

	// 지도 마커 생성
	setClusterMarker(list);
}


// APT 에 맞게 맞춰줌
function refineAllianceInfo(list, sidoGugun) {
	let newList = [];
	
	list.forEach(el => {
		let newObj = {
			aptAddr: sidoGugun+ " " + el["법정동"] + el["지번"],
			aptArea: el["대지권면적"],
			aptBuildYear: el["건축년도"],
			aptDealAmount: Number(el["거래금액"].replace(",","")),
			aptDealYear: el["년"],
			aptDealMonth: el["월"],
			aptDealDay: el["일"],
			aptName: el["연립다세대"],
		}
		newList.push(newObj);
	});
	
	return newList;
}

async function getAsyncData(url) {
	let response = await fetch(url);
	let data = await response.text();
	return data;
}

function setClusterMarker(lists) {
	let map = getMapInstance();
	let geocoder = getGeoInstance();

	// 마커 클러스터러를 생성합니다 
	let clusters = getClusterInstance();

	// 지도 위에 모든 마커 지우기
	clearAllMarkers();
	clusters.clear();

	lists.forEach(el => {
		geocoder.addressSearch(el.aptAddr, function (result, status) {
			if (status === kakao.maps.services.Status.OK) {
				// 마커가 표시될 위치입니다 
				var markerPosition  = new kakao.maps.LatLng(result[0].y, result[0].x); 

				// 마커를 생성합니다
				var marker = new kakao.maps.Marker({
						position: markerPosition
				});

				clusters.addMarker(marker);
				markers.push(marker);

				// 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
				let aptName = el.aptName;
				let money = el.aptDealAmount.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
				var iwContent = `
						<div style="padding:5px; font-size:0.7rem; text-align:center;">
								[${aptName}]
								<div>${money} 만원</div>
						</div>
				`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
				iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
				
				// 인포윈도우를 생성합니다
				var infowindow = new kakao.maps.InfoWindow({
						content : iwContent,
						removable : iwRemoveable
				});

				// 마커에 클릭이벤트를 등록합니다
				kakao.maps.event.addListener(marker, 'click', function() {
					// 마커 위에 인포윈도우를 표시합니다
					infowindow.open(map, marker);
					
					// 해당 건축물 정보를 왼쪽 리스트에 표현
					let aptLists = [];
					lists.forEach(apt => {
						if (apt.aptName.includes(el.aptName)) {
							aptLists.push(apt);
						}
					});
					makeListByJson(aptLists);

					// 해당 위치가 중심으로 가도록 이동
					map.setCenter(marker.getPosition());
				});
			}
		});
	});
}

function getClusterInstance() {
	if (clusters == null) {
		clusters = new kakao.maps.MarkerClusterer({
			map: map, // 마커들을 클러스터로 관리하고 표시할 지도 객체 
			averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정 
			minLevel: 3 // 클러스터 할 최소 지도 레벨 
		});
	}

	return clusters;
}

function getMapInstance() {
    if(map == null) {
        const mapContainer = document.getElementById('map'), // 지도를 표시할 div 
            mapOption = { 
                center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 6 // 지도의 확대 레벨
            };
        
        map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    }

    return map;
}
function getGeoInstance() {
    if(geocoder == null) {
        geocoder = new kakao.maps.services.Geocoder();
    }

    return geocoder;
}

function clearAllMarkers() {
    markers.forEach(el => {
        el.setMap(null);
    });

    markers = [];
}

function setCenterAddr(addr, zoom) {
    let map = getMapInstance();
    let geocoder = getGeoInstance();
    
    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(addr, function(result, status) {

        // 정상적으로 검색이 완료됐으면 
        if (status === kakao.maps.services.Status.OK) {

			// 마커가 표시될 위치입니다 
			var coord  = new kakao.maps.LatLng(result[0].y, result[0].x); 

			map.setCenter(coord);
			map.setLevel(zoom, {anchor: coord});
        } 
    });  
}

function sendRequest(act, selid, regcode) {
    let url = `http://localhost/${ root }/area`;
    let params = "";
    switch (act) {
    case "sido":
    	url += '/sido';
    	break;
    case "gugun":
    	url += '/gugun';
    	params += "sidoCode=" + regcode;
    	break;
    case "dong":
    	url += '/dong';
    	params += "gugunCode=" + regcode;
    	break;
    }
    fetch(`${url}?${params}`)
        .then((response) => response.json())
        .then((data) => addOption(selid, data));
}

function initOption(selid) {
    let options = document.querySelector(`#${selid}`);
    options.length = 0;
}

function addOption(selid, data) {
    let opt = ``;
    initOption(selid);
    switch (selid) {
    case "formSido":
    case "sido":
        opt += `<option value="">시도선택</option>`;
        for(let key of data) {
        	opt += `
                <option value="${key['dongCode']}">${key['sidoName']}</option>
            `;
        }
        break;
    case "formGugun":
    case "gugun":
        opt += `<option value="">구군선택</option>`;
        for(let key of data) {
        	
        	opt += `
                <option value="${key['dongCode']}">${key['gugunName']}</option>
            `;
        }
        break;
    case "formDong":
    case "dong":
        opt += `<option value="">동선택</option>`;
        
        for(let key of data) {
        	opt += `
                <option value="${key['dongCode']}">${key['dongName']}</option>
            `;
        }
        break;
    }
    document.querySelector(`#${selid}`).innerHTML = opt;
}

function makeListByJson(data) {
	let aptList = document.querySelector("#aptList");
	let div = '';

	if(data.length == 0) {
		div += `
			<div class='h4'>등록 정보가 없습니다.</div>
		`
	} else {
		data.forEach(el => {
			div += `
				<li class="list-group-item pl-0"><div class="h4 apt-name">${el.aptName}</div>
				<div style="display: none;" class="addr" value="${el.aptAddr}"></div>
				<div class="h6">거래금액: ${el.aptDealAmount}만원</div>
				<div class="h6">면적:${el.aptArea}</div>
				<div>건축년도: ${el.aptBuildYear}</div></li>
				`
		});
	}

	aptList.innerHTML = div;

	// 이벤트 달기
	document.querySelectorAll("#aptList .apt-name").forEach(child => {
		child.addEventListener("click", function () {
			// 해당 위치로 이동
			setCenterAddr(child.parentElement.querySelector(".addr").getAttribute("value"), 2);
		});
	})

}
