/* global kakao */
const KAKAO_MAP_API_KEY = "fb37ee426ff3d4073d98482866d2228b";
const KAKAO_MAP_URL = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${KAKAO_MAP_API_KEY}&libraries=services,clusterer,drawing`;
const APT_MARKER_IMG = require("@/assets/img/apartment_marker.png");
const MARKER_WIDTH = 40;
const MARKER_HEIGHT = 40;
const OVER_MARKER_WIDTH = 38;
const OVER_MARKER_HEIGHT = 50;

export class Map {
  constructor() {
    window.aptMarkers = [];
    window.allianceMarkers = [];
    window.mapInstance = this;

    // 맵 인스턴스 생성
    const script = document.createElement("script");
    script.onload = () => {
      kakao.maps.load(this.$initMap);
      // geoCoder 인스턴스 생성
      kakao.maps.load(this.$initGeocoder);
    };
    script.src = KAKAO_MAP_URL;
    document.head.appendChild(script);
  }

  $initMap() {
    const container = document.getElementById("map");
    const options = {
      center: new kakao.maps.LatLng(33.450701, 126.570667),
      level: 5,
    };

    //지도 객체를 등록합니다.
    //지도 객체는 반응형 관리 대상이 아니므로 initMap에서 선언합니다.
    window.map = new kakao.maps.Map(container, options);

    // 클러스터 인스턴스 생성
    window.clusters = new kakao.maps.MarkerClusterer({
      map: window.map, // 마커들을 클러스터로 관리하고 표시할 지도 객체
      averageCenter: true, // 클러스터에 포함된 마커들의 평균 위치를 클러스터 마커 위치로 설정
      minLevel: 5, // 클러스터 할 최소 지도 레벨
    });
  }

  $initGeocoder() {
    window.geocoder = new kakao.maps.services.Geocoder();
  }

  // 주소로 맵 이동시키기
  setCenterAddr(addr, zoom) {
    const map = window.map;
    const geocoder = window.geocoder;

    // 주소로 좌표를 검색합니다
    geocoder.addressSearch(addr, function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        // 마커가 표시될 위치입니다
        var coord = new kakao.maps.LatLng(result[0].y, result[0].x);

        map.setCenter(coord);
        map.setLevel(zoom, { anchor: coord });
      }
    });
  }

  clearAptMarkers() {
    window.aptMarkers.forEach((el) => {
      el.setMap(null);
    });
    window.aptMarkers = [];
  }

  hideAptMarkers() {
    window.aptMarkers.forEach((el) => {
      el.setMap(null);
    });
  }

  showAptMarkers() {
    window.aptMarkers.forEach((el) => {
      el.setMap(window.map);
    });
  }

  // clearAllianceMarkers() {
  //   window.allianceMarkers.forEach((el) => {
  //     el.setMap(null);
  //   });
  //   window.allianceMarkers = [];
  // }

  // 맵에 표시된 모든 마커 제거
  clearAllMarkers() {
    this.clearAptMarkers();
    //this.clearAllianceMarkers();
    window.clusters.clear();
  }

  // addMarker(marker) {
  //   window.markers.push(marker);
  //   marker.setMap(window.map);
  // }

  addAptCluster(markerList) {
    window.aptMarkers.push(...markerList);
    window.clusters.addMarkers(markerList);
  }

  // addAllianceCluster(markerList) {
  //   window.allianceMarkers.push(...markerList);
  //   window.clusters.addMarkers(markerList);
  // }
  createRoadView(roadviewContainer, position) {
    const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
    const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

    // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
    roadviewClient.getNearestPanoId(position, 50, function (panoId) {
      panoId && roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
    });
  }
}

// export function createRoadView(roadviewContainer) {
//   console.log("roadviewContainer", roadviewContainer);
//   const roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
//   const roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

//   const position = new kakao.maps.LatLng(33.450701, 126.570667);

//   // 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
//   roadviewClient.getNearestPanoId(position, 50, function (panoId) {
//     roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
//   });
// }

async function getLatLngByAddr(addr) {
  return new Promise((resolve, reject) => {
    window.geocoder.addressSearch(addr, function (result, status) {
      // 정상적으로 검색이 완료됐으면
      if (status === kakao.maps.services.Status.OK) {
        // 마커가 표시될 위치입니다
        const coord = new kakao.maps.LatLng(result[0].y, result[0].x);
        resolve(coord);
      } else {
        reject();
      }
    });
  });
}

async function AMark(el, bounds, markers) {
  // 마커 이미지 지정
  const image = createMarkerImage(
    APT_MARKER_IMG,
    MARKER_WIDTH,
    MARKER_HEIGHT
  );
  const overImage = createMarkerImage(
    APT_MARKER_IMG,
    OVER_MARKER_WIDTH,
    OVER_MARKER_HEIGHT
  );

  // 마커 위치 생성
  const addr = `${el.sidoName} ${el.gugunName} ${el.dongName} ${el.bunji}`;
  const position = await getLatLngByAddr(addr).catch(() => { });

  if (!position) return;

  // 마커 생성
  let marker = new kakao.maps.Marker({
    position,
    image,
  });

  // 클릭 이벤트 추가
  kakao.maps.event.addListener(marker, "click", function () {
    // 1. 클릭 시 해당 매물로 지도 이동
    window.map.panTo(position);
    // 2. 상세 거래내역 왼쪽 리스트에 조회
  });

  // 마커에 mouseover 이벤트를 등록합니다
  kakao.maps.event.addListener(marker, "mouseover", function () {
    // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 오버 이미지로 변경합니다
    marker.setImage(overImage);
  });
  kakao.maps.event.addListener(marker, "mouseout", function () {
    // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
    // 마커의 이미지를 오버 이미지로 변경합니다
    marker.setImage(image);
  });

  // 화면에 모든 마커가 보이도록 bound 설정
  bounds.extend(position);
  window.map.setBounds(bounds);

  // table 에서 필요한 변수 및 함수들 선언
  el.position = position;
  el.onHover = () => {
    marker.setImage(overImage);
  };
  el.onHoverout = () => {
    marker.setImage(image);
  };

  // markers 에 push
  markers.push(marker);
}

export async function AptMarkers(list) {
  let bounds = new kakao.maps.LatLngBounds();
  let markers = [];
  let promiseArr = [];
  for (let el of list) {
    promiseArr.push(AMark(el, bounds, markers));
  }

  await Promise.all(promiseArr);

  return markers;
}

// MakrerImage 객체를 생성하여 반환하는 함수입니다
function createMarkerImage(markerImg, width, height) {
  const markerImage = new kakao.maps.MarkerImage(
    markerImg, // 마커 이미지 URL
    new kakao.maps.Size(width, height) // 마커의 크기
  );

  return markerImage;
}
