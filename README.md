# Small parking management Project
##### 소단지 경비원을 위한 주차 관리 웹 애플리케이션

🙋🏻‍♂️ 2022 개인 프로젝트

## 소단지 경비원을 위한 주차 관리 웹 애플리케이션 : 소경관 프로젝트

### | 프로젝트 제안 배경 🔎
- 자동 주차 관리 시스템을 통해 무단 주차 차량과 주차 체크 근무를 대신 하지만 소단지 아파트 경우 세대 수 대비 효율이 떨어져 자동 주차 관리 시스템을 사용하지 않는 경우가 많다.
- 이 경우 경비원이 수기로 체크하는 곳이 많아 날씨가 좋지 않은 날에는 많은 경비원 분들이 고생하고 있다.
- 이런 문제를 개선하기 위해 소경관 프로젝트를 기획하게 되었다.

### | 프로젝트 설명 👨🏻‍🏫
- 사용자 경우 복잡하지 않은 CRUD 작업 밖에 없으므로 생산성과 오류를 최대한 줄이기 위해 `JPA`를 사용
- 주민 데이터 경우 복잡한 쿼리가 필요하지 않았고, 수정이나 삭제 보다는 조회 작업이 많았기 때문에 장점을 따라 `MongoDB`를 메인으로 사용
- 근무자, 주민의 지역 선택을 위해 `Daum 우편번호 서비스 API` 사용
- 주민에게 공지사항 전파와 방문자에게 방문자 양식을 전달하기 위한 방법으로 연령대 상관 없이 접근이 쉬운 문자를 선택함에 따라 `CoolSmsAPI`를 사용
- 차량 번호판 인식을 위해 Kakao에서 제공하는 `Kakao Vision API`를 사용(구글에서 제공하는 `Tesseract OCR`을 먼저 적용해 봤으나 매우 인식률이 떨어져서 `Kakao Vision OCR` 사용)

### | 프로젝트 기대 효과 🌱
- 경비원 근무 환경 개선
- 공지사항전파, 방문자 양식 전달 등의 기능을 통한 단지 주민과 경비원의 커뮤니케이션 활성화
- 무단 주차 차량 관리로 주차 자리 부족 문제 해소

### | 사용 스킬✨

|**구분**|**스킬**|
|-----|---|
|Language|`Java` `HTML` `CSS`|
|Framework & Spring|`Spring Boot` `Gradle` `JPA` `Thymeleaf`|
|Server|`Tomcat` `AWS Ec2 Ubuntu`|
|DataBase|`MongoDB` `MariaDB`|
|Tool|`Git Hub` `IntelliJ` `Data Grip`|
|API|`Kakao Vision API` `CoolSms API` `Daum 우편번호 서비스 API`|

### | 시연 영상
- 개인 정보가 있어 따로 유튜브에 업로드 하진 않았습니다.
- 개인 노션 이력서 페이지 제일 하단 부분에서 시연 영상을 확인하실 수 있습니다.<br>
[시연 영상 보기로 이동(페이지 하단 참고)](https://ohju96.notion.site/eaa8820c7aad4e6a9d697bd2285c6c72)
<div>

### | 프로젝트 회고
- [링크](https://velog.io/@ohju96/%EC%86%8C%EB%8B%A8%EC%A7%80-%EA%B2%BD%EB%B9%84%EC%9B%90%EC%9D%84-%EC%9C%84%ED%95%9C-%EC%A3%BC%EC%B0%A8-%EA%B4%80%EB%A6%AC-%EC%9B%B9-%EC%95%A0%ED%94%8C%EB%A6%AC%EC%BC%80%EC%9D%B4%EC%85%98-%ED%9A%8C%EA%B3%A0)
