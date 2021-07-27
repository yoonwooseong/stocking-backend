# Stocking Backend

## 🔗 Link

 - [Stocking RS](http://52.78.111.36:8080/)


<br>

## 📃 개요

Stocking 프로젝트 Backend 레포지토리

<br>

## 📋 문서

 - __API 인터페이스 정의서__ : [Stocking 인터페이스.xml](https://docs.google.com/spreadsheets/d/182aLbTaK65A3b54N6PWPdKt8OSbjQdrZvL_DBAccawo/edit?usp=sharing)  

 - __ER 다이어그램__ : [Stocking ERD](https://www.erdcloud.com/d/ZQjY97KMQrEthMPyn)  

 - __스토리보드__ : [카카오오븐](https://ovenapp.io/view/DOhZ6TnDKWFjINtQKjnj2RAulxojOZCb#3QyvB)

 - __API 문서__ : [Swagger](http://52.78.111.36:8080/swagger-ui.html)

<br>

## ✏ 요구사항 분석 (Backend)  

- 로그인 기능 구현 (JWT + Spring Security, redis)  

- 주식 API 개발 (JPA + mysql)
  - 주식 상세보기
  - 보유 주식 조회
  - 주식 매수
  - 주식 매도  

- 자산 API 개발 (JPA + mysql)  
  - 수입(또는 지출) 조회
  - 수입(또는 지출) 등록
  - 수입(또는 지출) 수정
  - 수입(또는 지출) 삭제
 
- 서버 구축 (AWS EC2, Docker)  

<br>

## 🔨 구조

```
bis.stock.back
├── domain
│   ├── user
│   │   ├── User.java
│   │   ├── UserController.java
│   │   ├── UserService.java
│   │   └── UserRepository.java
│   ├── stock
│   │   ├── Stock.java
│   │   ├── StockController.java
│   │   ├── StockService.java
│   │   └── StockRepository.java
│   └── ...
│       └── ...
└── global
    ├── config
    │   ├── WebSecurityConfig.java
    │   └── ...
    └── exception
        └── SomeCustomException.java
```

<br>

## 💻 명령어

```bash
$ git pull https://github.com/Beauty-inside/stocking-backend.git

$ ./gradlew
```
