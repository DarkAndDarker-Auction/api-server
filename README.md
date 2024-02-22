# [DarkAndDarker Autcion]

# 진행 상황

[프로젝트 진행 상황](https://www.notion.so/459ee5b2e1f2404281bad1a833c70d10?pvs=21)

# 프로젝트 소개

### 소개 및 배경

- 프로젝트 명 : 다크앤다커 옥션 (Dark And Darker Auction)
- 기간 : 6주 (23. 10. 16 ~ 11. 26)
- 배경
    
    ### 게임의 특성
    
    - **아이템 거래 빈도**
        - 본 게임은 하드코어 게임. 즉, 캐릭터가 한 번 죽으면 모든 아이템을 잃게되는 게임이다. 또한, 적게는 1번 많아야 10번 이내에 캐릭터가 죽을 수 밖에 없는 구조이기 때문에 플레이어는 항상 새로운 아이템을 구비해야한다. 따라서, 다른 게임에 비해서 거래 빈도수가 높다.
    - **거래 구조의 특수성**
        
        다크앤다커 게임의 거래 구조는 경매장 시스템이 구현되어 있는 여타 RPG 게임과는 많이 다르다. 대다수의 경매장 시스템은 게임을 플레이하는 도중에도 아이템을 사고팔 수 있는 구조이다. 하지만, 본 게임은 플레이어가 수백-수천명이 동시에 접속할 수 있는 채팅 채널(거래소)에서 직접 아이템을 사고 팔아야한다. 즉, 실시간 대면 거래가 이루어진다. 그렇기에 게임 플레이중에는 아이템 거래가 불가능한 매우 불편한 거래 구조를 가지고 있다.
        
    
    ![                                                              <거래소 내부>](https://prod-files-secure.s3.us-west-2.amazonaws.com/90b6d907-f249-4d6f-8e3b-1f47c4f98b01/fe0d9b4a-2d60-4b8d-94d4-9e79ee4b2f5a/KakaoTalk_Photo_2023-10-17-00-04-12_001.png)
    
                                                                  <거래소 내부>
    
    ### 프로젝트의 필요성
    
    - 판매 시간 및 거래소 상주 시간 단축
        
        위에서 언급한 것처럼 아이템 거래 빈도수는 높지만 특수한 거래 구조 때문에 많은 플레이어가 아이템을 구하는데 많은 피로를 느낀다. 매 번 거래소에서 대면으로 아이템을 거래해야 하기 때문에 비싼 아이템은 수 시간 ~ 수 일이 걸리기도 한다. 경매장에 아이템을 등록하고 비동기적으로 구매자를 찾아 거래를 함으로써 거래소에 상주하는 시간을 획기적으로 줄일 수 있다.
        
    - 구매 시간 단축
        
        아이템 링크에 마우스를 올려 일일이 옵션을 확인하고 거래를 신청해야 하는 구조로 인해 구매 또한 쉽지 않다. 뿐만 아니라, 거래소 마다 아이템 카테고리 제한이 있기 때문에 여러 아이템을 구해야 하는 경우 동시에 확인할 수 없다. 가장 큰 문제는 구매하고자 하는 아이템을 동시간 동일 거래소에 판매자를 만나야 한다는 것이다. 경매장 시스템이 있다면 아이템 옵션 및 카테고리 검색과 더불어 훨씬 많은 매물을 찾을 수 있어 구매시간을 단축할 수 있다.
        
- 설명
    - 다크앤 다커 개임 내 아이템 거래를 보조하는 웹 어플리케이션으로 유저간 실시간 거래 중개 플랫폼이다. 게임 내 불친절하고 불편한 거래시스템에서 언제든지 아이템을 등록하여 거래가 이루어질 수 있도록 만드는 것이 본 프로젝트의 목적이다.

### 주요 기능

1. **경매장 기능**
- 아이템 등록 (판매)
- 아이템 구매
- 가격 제안
- 아이템 검색 (옵션 필터, 카테고리 등)
- 위시 리스트
- 거래 내역

1. 알림 기능
- 아이템 관련 이벤트 발생시 알림 전송

1. 채팅 기능
- 아이템 거래 시 필요한 채팅 기능

### 세부 기능

[JWT 인증 방식 도입기](https://www.notion.so/JWT-69174501a07d4cc0b4d13c7b647fc33e?pvs=21)

[세부 기능](https://www.notion.so/30e2efdd43474756a52efe46c457c51b?pvs=21)

# 프로젝트 설명

### 사용 기술

| 분야 | 사용 기술명 |
| --- | --- |
| 프론트엔드 | React React hook material UI moduled css firebase React Router DOM Web socket Toast STOMP js |
| 백엔드 | Spring Boot  Spring Data JPA Gradle jwt Spring Security SMTP FCM web socket STOMP |
| 데이터베이스 | Mysql Redis  |
| API | Swagger |

---

### 프로젝트 구조

![Cretive Portfolio.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/90b6d907-f249-4d6f-8e3b-1f47c4f98b01/be5aabf0-a571-4487-9784-b2006979bb3d/Cretive_Portfolio.png)

---

### 화면 구성

https://www.figma.com/file/CCmejjh2vW9bWEKTeznaeJ/Dark-And-Darker-Auction?type=design&node-id=0-1&mode=design&t=Fc8l9zY0SCZEfwox-0

---

### ERD

![Travellery_ERD.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/90b6d907-f249-4d6f-8e3b-1f47c4f98b01/b484e98b-0e5e-40b2-8f49-4ded5bd9dee8/Travellery_ERD.png)

[auction api](https://www.erdcloud.com/d/utv25ytEihieq2B9f)

---

### API 명세서

![DaDAuction_API_SWAGGER_page-0001.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/90b6d907-f249-4d6f-8e3b-1f47c4f98b01/046d05ed-509f-417b-8f53-c736657260e5/DaDAuction_API_SWAGGER_page-0001.jpg)

[DaDAuction_API_SWAGGER.pdf](https://prod-files-secure.s3.us-west-2.amazonaws.com/90b6d907-f249-4d6f-8e3b-1f47c4f98b01/9e9db758-692b-4c69-a6e0-5a52ef18d862/DaDAuction_API_SWAGGER.pdf)

---

### 구현 내용

[알림 기능](https://www.notion.so/1c08eb054c324bbf86836c8bca04a480?pvs=21)

---

### Git

[DarkAndDarker-Auction](https://github.com/orgs/DarkAndDarker-Auction/repositories)

# 프로젝트 시연

### 1) 회원가입 및 로그인

https://youtu.be/8likOHpfL1c

### 2) 물품 검색

https://www.youtube.com/watch?v=5vZ3yJUy4So

 

### 3) 물품 등록

https://youtu.be/1zarSa-CUdQ

### 4) 거래 제안

https://youtu.be/AYe06CfJlXo

### 5) 거래제안 수락

https://youtu.be/A4cN5lt2lsM

### 6) 즉시 구매

https://youtu.be/AEV6CVoWJrk

### 7) 위시리스트

https://youtu.be/u5GICgspXRI

### 8) 알림

https://youtu.be/tLW7j50vkm8

### 9) 채팅
