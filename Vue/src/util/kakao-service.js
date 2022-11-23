/* global kakao */

export function Service() {
  this.ps = new kakao.maps.services.Places(window.map);
  this.markers = [];
  //this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.contentNode = document.createElement("div");
  this.contentNode.className = "placeinfo_wrap";
  this.markerObj = null;

  this.searchPlaces = function (markerObj) {
    this.markerObj = markerObj;
    const category = markerObj.category;
    if (!category) return;
    this.ps.categorySearch(category, this.placesSearchCB, {
      useMapBounds: true,
    });
  };
  this.placesSearchCB = (data, status) => {
    if (status === kakao.maps.services.Status.OK) {
      // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
      this.displayPlaces(data);
    } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
      // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
    } else if (status === kakao.maps.services.Status.ERROR) {
      // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
    }
  };
  this.displayPlaces = async (places) => {
    let promiseArr = [];
    for (let i = 0; i < places.length; i++) {
      promiseArr.push(this.createMarker(places[i]));
    }
    await Promise.all(promiseArr);
  };
  this.createMarker = async (p) => {
    // 마커를 생성하고 지도에 표시합니다
    const marker = this.addMarker(
      new kakao.maps.LatLng(p.y, p.x)
    );
    // 마커와 검색결과 항목을 클릭 했을 때
    // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
    ((marker, place) => {
      kakao.maps.event.addListener(marker, "click", () => {
        const currContent = this.markerObj.placeOverlay.getContent();
        if (currContent == null) {
          this.displayPlaceInfo(place);
        } else {
          this.markerObj.placeOverlay.setContent(null);
          this.markerObj.placeOverlay.setMap(null);
        }
      });
    })(marker, p);
  }
  this.displayPlaceInfo = function (place) {
    let content = `
    <div class="placeinfo">
      <a class="title" href="${place.place_url}" target="_blank" title="${place.place_name}">${place.place_name}</a>
    `;

    if (place.road_address_name) {
      content += `
      <span title="${place.road_address_name}">${place.road_address_name}</span>
        <span class="jibun" title="${place.address_name}">(지번 : ${place.address_name})</span>
      `;
    } else {
      content += `<span title="${place.address_nam}">${place.address_nam}</span>`;
    }

    content += `
    <span class="tel">${place.phone}</span>
    </div>
    <div class="after"></div>
    `;

    this.contentNode.innerHTML = content;
    this.markerObj.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    this.markerObj.placeOverlay.setMap(window.map);
    // 커스텀 오버레이 컨텐츠를 설정합니다
    this.markerObj.placeOverlay.setContent(this.contentNode);
  };
  this.addMarker = function (position) {
    // const imageSrc =
    //     "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
    //   imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
    //   imgOptions = {
    //     spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
    //     spriteOrigin: new kakao.maps.Point(46, 5 * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
    //     offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
    //   },
    const imageSrc = this.markerObj.image,
      imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
      imgOptions = {
        offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(window.map); // 지도 위에 마커를 표출합니다
    this.markerObj.markers.push(marker);
    //this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  };
  this.removeMarker = function (markerObj) {
    for (let i = 0; i < markerObj.markers.length; i++) {
      markerObj.markers[i].setMap(null);
    }
    markerObj.markers = [];
  };
}

export function Mart() { // 대형마트
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/mart_marker.png");
  this.category = "MT1";
  this.markers = [];
} 
export function Hospital() { // 병원
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/hospital_marker.png");
  this.category = "HP8";
  this.markers = [];
}
export function ConvStore() { // 편의점
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/convenience_marker.png");
  this.category = "CS2";
  this.markers = [];
}
export function School() { // 학교
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/school_marker.png");
  this.category = "SC4";
  this.markers = [];
} 
export function ParkingLot() { // 주차장
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/parkinglot_marker.png");
  this.category = "PK6";
  this.markers = [];
} 
export function GasStation() { // 주유소
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/gasstation_marker.png");
  this.category = "OL7";
  this.markers = [];
} 
export function Subway() { // 지하철역
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/subway_marker.png");
  this.category = "SW8";
  this.markers = [];
} 
export function Bank() { // 은행
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/bank_marker.png");
  this.category = "PK6";
  this.markers = [];
} 
export function Pharmacy() { // 약국
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.image = require("@/assets/img/pharmacy_marker.png");
  this.category = "PM9";
  this.markers = [];
} 