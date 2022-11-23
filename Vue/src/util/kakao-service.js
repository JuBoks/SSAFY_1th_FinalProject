/* global kakao */

export function Service() {
  this.ps = new kakao.maps.services.Places(window.map);
  this.markers = [];
  this.placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 });
  this.contentNode = document.createElement("div");
  this.contentNode.className = "placeinfo_wrap";
  // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
  // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
  // addEventHandle(
  //   this.contentNode,
  //   "mousedown",
  //   kakao.maps.event.preventMap
  // );
  // addEventHandle(
  //   this.contentNode,
  //   "touchstart",
  //   kakao.maps.event.preventMap
  // );

  this.searchPlaces = function (category) {
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
  this.displayPlaces = (places) => {
    for (let i = 0; i < places.length; i++) {
      // 마커를 생성하고 지도에 표시합니다
      const marker = this.addMarker(
        new kakao.maps.LatLng(places[i].y, places[i].x)
      );
      // 마커와 검색결과 항목을 클릭 했을 때
      // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
      ((marker, place) => {
        kakao.maps.event.addListener(marker, "click", () => {
          const currContent = this.placeOverlay.getContent();
          if (currContent == null) {
            this.displayPlaceInfo(place);
          } else {
            this.placeOverlay.setContent(null);
            this.placeOverlay.setMap(null);
          }
        });
      })(marker, places[i]);
    }
  };
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
    this.placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
    this.placeOverlay.setMap(window.map);
    // 커스텀 오버레이 컨텐츠를 설정합니다
    this.placeOverlay.setContent(this.contentNode);
  };
  this.addMarker = function (position) {
    const imageSrc =
        "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_category.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
      imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
      imgOptions = {
        spriteSize: new kakao.maps.Size(72, 208), // 스프라이트 이미지의 크기
        spriteOrigin: new kakao.maps.Point(46, 5 * 36), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new kakao.maps.Point(11, 28), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      },
      markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
      marker = new kakao.maps.Marker({
        position: position, // 마커의 위치
        image: markerImage,
      });

    marker.setMap(window.map); // 지도 위에 마커를 표출합니다
    this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

    return marker;
  };
  this.removeMarker = function () {
    for (let i = 0; i < this.markers.length; i++) {
      this.markers[i].setMap(null);
    }
    this.markers = [];
  };
}
