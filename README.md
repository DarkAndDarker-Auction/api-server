# 🎯 DarkAndDarker Auction

> **🎮 하드코어 게임 DarkAndDarker의 실시간 아이템 거래 중개 플랫폼**

**개발 기간**: 2023.10.16 ~ 2023.11.26 (6주)

DarkAndDarker 게임의 불편한 대면거래 구조를 해결하기 위해 개발된 **실시간 경매 시스템**입니다. WebSocket 기반 실시간 통신과 복잡한 게임 아이템 속성을 고려한 동적 다중 조건 검색을 구현했습니다.

## 🎯 프로젝트 배경

### 🎮 게임의 특성과 문제점
DarkAndDarker는 **하드코어 RPG 게임**으로 캐릭터가 죽으면 모든 아이템을 잃게 되는 특성상 **아이템 거래 빈도가 매우 높습니다**. 하지만 게임 내 거래 시스템은 다음과 같은 문제점이 있습니다:

- **실시간 대면거래만 가능**: 수백-수천명이 접속하는 채팅 채널에서만 거래 가능
- **게임 플레이 중 거래 불가**: 던전 진행 중에는 아이템 거래가 완전히 차단
- **비효율적인 검색**: 아이템 링크에 마우스를 올려 일일이 옵션 확인 필요
- **카테고리 제한**: 거래소마다 특정 아이템만 거래 가능

### 💡 해결 방안
**비동기 경매 시스템**을 통해 거래소 상주 시간을 획기적으로 단축하고, **고급 검색 기능**으로 원하는 아이템을 빠르게 찾을 수 있는 플랫폼을 구현했습니다.

## 🌟 주요 기능

### ⚡ 실시간 경매 시스템
- **WebSocket & STOMP** 기반 양방향 실시간 통신
- **4가지 거래 상태**: ACTIVE → HAS_OFFER → TRADING → COMPLETED
- **다중 화폐 지원**: 골드, 황금 주괴, 황금 열쇠, 이벤트 주화
- **유연한 경매 기간**: 3, 6, 12, 24시간 중 선택 가능

🎬 **시연 영상**

- **아이템 등록**  
[![아이템 등록](https://img.youtube.com/vi/1zarSa-CUdQ/0.jpg)](https://youtu.be/1zarSa-CUdQ)

- **즉시 구매**  
[![즉시 구매](https://img.youtube.com/vi/AEV6CVoWJrk/0.jpg)](https://youtu.be/AEV6CVoWJrk)

<br/>

### 🔍 고급 검색 시스템
- **동적 다중 조건 검색**: 아이템명, 카테고리, 복수 옵션 필터링
- **슬롯 기반 카테고리**: legs, chest, primary weapon 등
- **AND 조건 검색**: 복수 옵션 중복 선택 가능

🎬 시연 영상  

- **아이템 검색 데모**  
[![아이템 검색](https://img.youtube.com/vi/5vZ3yJUy4So/0.jpg)](https://www.youtube.com/watch?v=5vZ3yJUy4So)

- **위시리스트**  
[![위시리스트](https://img.youtube.com/vi/u5GICgspXRI/0.jpg)](https://youtu.be/u5GICgspXRI)

<br/>

### 💰 OFFER 시스템
- **가격 제안 기능**: 구매자가 희망 가격 제시 가능
- **선택적 활성화**: 판매자가 OFFER 기능 on/off 선택
- **1:N 구조**: 판매자는 복수 OFFER 수신 가능, 구매자는 아이템당 1개만 전송
    
🎬 시연 영상  

- **거래 제안 (OFFER)**  
[![거래 제안](https://img.youtube.com/vi/AYe06CfJlXo/0.jpg)](https://youtu.be/AYe06CfJlXo)

- **거래 제안 수락**  
[![제안 수락](https://img.youtube.com/vi/A4cN5lt2lsM/0.jpg)](https://youtu.be/A4cN5lt2lsM)

<br/>

### 📱 실시간 알림 시스템
- **FCM** 기반 푸시 알림 (포그라운드/백그라운드)
- **Service Worker** 연동으로 앱 비활성 상태에서도 안정적 수신
- **알림 분류**: 필터링 기능과 전체 보기 옵션
- **영구 저장**: 사용자 삭제 전까지 알림 내역 보존

🎬 시연 영상  

- **실시간 알림**  
[![실시간 알림](https://img.youtube.com/vi/tLW7j50vkm8/0.jpg)](https://youtu.be/tLW7j50vkm8)

<br/>

### 💬 실시간 채팅
- **거래별 채팅방**: 물품 거래 건마다 자동 생성
- **아이템 정보 표시**: 채팅방 상단에 거래 아이템 정보 제공
- **WebSocket** 기반 실시간 메시징

🎬 시연 영상  

- **실시간 채팅**  
[![실시간 채팅](https://img.youtube.com/vi/nCjSX9aAlzc/0.jpg)](https://youtu.be/nCjSX9aAlzc)

<br/>

### 🔐 사용자 인증
- **JWT** 기반 토큰 인증 (AccessToken 30분, RefreshToken 1주)
- **자동 토큰 갱신**: AccessToken 만료 시 자동 리프레시

🎬 시연 영상  

- **회원가입 및 로그인**
[![회원가입/로그인](https://img.youtube.com/vi/8likOHpfL1c/0.jpg)](https://youtu.be/8likOHpfL1c)

## 🏗️ 아키텍처

<img width="900" alt="Cretive_Portfolio" src="https://github.com/user-attachments/assets/02f042e2-4239-4788-91d7-1b55d36fe27b" />


## 🛠️ 기술 스택

### Frontend
![React](https://img.shields.io/badge/React-20232A?style=flat&logo=react&logoColor=61DAFB)
![Material UI](https://img.shields.io/badge/Material--UI-007FFF?style=flat&logo=mui&logoColor=white)
![CSS Modules](https://img.shields.io/badge/CSS_Modules-000000?style=flat&logo=cssmodules&logoColor=white)
![React Router](https://img.shields.io/badge/React_Router-CA4245?style=flat&logo=react-router&logoColor=white)
![WebSocket](https://img.shields.io/badge/WebSocket-010101?style=flat&logo=socket.io&logoColor=white)
![STOMP](https://img.shields.io/badge/STOMP-FF6B6B?style=flat)

### Backend
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=flat&logo=spring&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=flat&logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=flat&logo=gradle&logoColor=white)
![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)

### Database
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat&logo=redis&logoColor=white)

### Infrastructure & Tools
![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=firebase&logoColor=black)
![FCM](https://img.shields.io/badge/FCM-FFCA28?style=flat&logo=firebase&logoColor=black)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black)
![SMTP](https://img.shields.io/badge/SMTP-0078D4?style=flat&logo=microsoft-outlook&logoColor=white)

## 🗂️ 프로젝트 문서

- **🎨 UI/UX 설계**: [Figma 디자인](https://www.figma.com/file/CCmejjh2vW9bWEKTeznaeJ/Dark-And-Darker-Auction)
- **🗃️ ERD**: [ERD Cloud](https://www.erdcloud.com/d/utv25ytEihieq2B9f)

---

<p align="center">
  <em>🎮 하드코어 게임의 아이템 거래에 혁신을 가져다준 DarkAndDarker Auction</em><br/>
  <em>실시간 기술과 게이머 중심 UX의 완벽한 조화</em>
</p>
