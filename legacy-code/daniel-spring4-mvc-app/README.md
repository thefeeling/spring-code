## Spring MVC App ##
Spring-MVC-Showcase 탬플릿을 기준으로 기능을 확장한 형태입니다..<br>
**(Spring 4.0 기반으로 작성)**

## 소스  ##
    $ git clone https://github.com/thefeeling/daniel-spring4-mvc-app


## 실행 / 개발환경 ##
- Spring Tool Suite 3.7.0.RELEASE
- JDK 1.8.0_60
- in Windows 7 x64


## 소스 구성 ##
- Front : AngularJS(boardApp)(분리됨)
- Back  : Datasource & Logging & Transaction
- DB    : MySql / H2(콘솔용으로 추후 추가)

## Complete ##
- 앵귤러 프런트 셋팅(기본 게시판 샘플 src/main/angular/boardApp 참고)
  - **AngularJS(Grunt / Bower) [angular_boardApp] 참고**
- log4j 셋팅**(인터셉터 설정, 쿼리로그 완료/2015-11-19)(완료/2015-11-19)**
- Message Bean 추가**(AbstractController.java getMessage() 만들기)(완료/2015-11-20)**
- Properties Bean 추가**(디비 및 설정 정보)(완료/2015-11-20)**
- 유틸리티 jar 추가(String, Date, File)**(commons-lang 추가 / 2015-12-08)**
- WebJar**(완료 / 2015-12-31)**

## To do ##
- Transaction
- Spring Security
- JPA / Hibernate


[angular_boardApp]: https://github.com/thefeeling/angularjs_boardsample_study/
