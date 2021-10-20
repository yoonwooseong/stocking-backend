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

# arm64 기반 oracle 클라우드에 배포 시
# (1) jar 빌드 (뒤의 -x test는 테스트를 생략하겠다는 의미이다. 크게 바쁘지 않다면 테스트까지 수행하고 빌드하도록 하자)
$ ./gradlew build -x test
# (2) buildx로 다른 플랫폼 용 이미지를 만들고, 이 때 Dockerfile은 arm.~ 을 쓰겠다는 의미
$ docker buildx build --platform linux/arm64 -t s4ng/stocking-back:arm . -f arm.Dockerfile
# (3) 배포할 때 mysql과 redis가 같은 환경에 컨테이너로 배포되고 있기 때문에 --link속성을 이용하여 연결시켜 준다.
$ docker run --name api -d -p 8080:8080 --link redis --link mysql s4ng/stocking-back:arm
```
