/* global kakao */
const KAKAO_MAP_API_KEY = "fb37ee426ff3d4073d98482866d2228b";
const KAKAO_MAP_URL = `//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${KAKAO_MAP_API_KEY}&libraries=services,clusterer,drawing`;
const APT_MARKER_IMG = require('@/assets/img/apartment_marker.png');

export class Map {
  constructor() {
    window.aptMarkers = [];
    window.allianceMarkers = [];

    // 맵 인스턴스 생성
    if (window.kakao && window.kakao.maps) {
      this.$initMap();
    } else {
      const script = document.createElement("script");
      script.onload = () => {
        kakao.maps.load(this.$initMap);
        // geoCoder 인스턴스 생성
        kakao.maps.load(this.$initGeocoder);
      };
      script.src = KAKAO_MAP_URL;
      document.head.appendChild(script);
    }
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
      minLevel: 3, // 클러스터 할 최소 지도 레벨
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
}

export async function AptMarkers(list) {
  let bounds = new kakao.maps.LatLngBounds();
  let markers = [];
  list.forEach((el) => {
    // 마커 이미지 지정
    const image = new kakao.maps.MarkerImage(APT_MARKER_IMG, new kakao.maps.Size(40, 40));

    // 마커 위치 생성
    const position = new kakao.maps.LatLng(el.lat, el.lng);

    // 마커 생성
    const marker = new kakao.maps.Marker({
      position,
      image,
    });

    // 화면에 모든 마커가 보이도록 bound 설정
    bounds.extend(position);
    window.map.setBounds(bounds);
    // 클릭 이벤트 추가

    // markers 에 push
    markers.push(marker);
  });

  // const markers = await Promise.all(promises);

  return markers;
}

// export async function AllianceMarkers(list) {
//   const promises = [];
//   list.forEach((el) => {
//     promises.push(MApt(el.aptLat, el.aptLng));
//   });

//   const markers = await Promise.all(promises);

//   return markers;
// }

export async function MApt(lat, lng) {
  // 마커 이미지 지정
  // const image = new kakao.maps.MarkerImage(APT_MARKER_IMG, new kakao.maps.Size(64, 69), {
  //   offset: new kakao.maps.Point(27, 69)
  // });

  //const position = await getPositionByAddr(addr);
  console.log(lat, lng);
  const position = new kakao.maps.LatLng(lat, lng);

  // 마커를 생성합니다
  const marker = new kakao.maps.Marker({
    position,
  });

  // 화면에 모든 마커가 보이도록 bound 설정
  


  // 클릭 이벤트 달기

  return marker;
}

// async function getPositionByAddr(addr) {
//   return new Promise(function (resolve, reject) {
//     window.geocoder.addressSearch(addr, function (result, status) {
//       if (status === kakao.maps.services.Status.OK) {
//         resolve(new kakao.maps.LatLng(result[0].y, result[0].x));
//       } else {
//         reject(null);
//       }
//     });
//   })
// }
