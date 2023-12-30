# NewsApp
뉴스 앱
  - Retrofit
  - Material Design (CardView, Chip)
  - Jsoup
  - Glide
  - Lottie

## [Tikxml](https://github.com/Tickaroo/tikxml)
  - Retrofit과 함께 사용할 수 있는 XML Parser
  - Retrofit에서 support 하고 있는 XML Parser 들은 'Simple XML', 'JAXB'가 있지만 'Simple XML'은 Deprecated 되었고, JAXB는 Android를 지원하지 않는다
  - XML Parsing이 필요한 상황이라면 Tikxml을 차선책으로 사용하는 것이 좋지만 가급적이면 XML 데이터 대신 Json 이용 권장
  - 0.8.15가 최신이지만 안정성 이슈로 0.8.13을 권장

## [Material Design](https://m2.material.io/design)

## [Jsoup](https://jsoup.org/)
  - Java HTML Parser
  - HTML을 Parsing 해야할 때 주로 사용. 크롤링과 관련한 프로젝트를 수행해야하는 경우 유용
  - 하지만 HTML 데이터를 직접 크롤링하기보다는 서버통신을 통한 JSON 형식의 데이터를 받는게 더 일반적이므로 자주 사용하는 라이브러리는 아님

## [Glide](https://bumptech.github.io/glide/)
  - 안드로이드 이미지 로딩 라이브러리
  - 네트워크를 통해 이미지를 받아올 수 있도록 도와주는 라이브러리
  - 이미지 캐싱 처리가 가능하기 때문에 네트워크 이미지를 불러올 때 뿐만 아니라 로컬 이미지를 불러올 때에도 용이
  - gif 이미지를 불러올 때에도 유용

## [Lottie](https://airbnb.io/lottie/#/)
  - Airbnb에서 만든 애니메이션 처리 라이브러리
  - json 파일 형식으로 데이터를 불러와 간편하게 개발자가 디자이너에게 전달받은 애니메이션을 그려줄 수 있다
