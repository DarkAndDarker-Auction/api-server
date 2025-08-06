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

### 🔍 고급 검색 시스템
- **동적 다중 조건 검색**: 아이템명, 카테고리, 복수 옵션 필터링
- **슬롯 기반 카테고리**: legs, chest, primary weapon 등
- **AND 조건 검색**: 복수 옵션 중복 선택 불가능한 정확한 검색

🎬 시연 영상  

- **아이템 검색 데모**  
[![아이템 검색](https://img.youtube.com/vi/5vZ3yJUy4So/0.jpg)](https://www.youtube.com/watch?v=5vZ3yJUy4So)

- **위시리스트**  
[![위시리스트](https://img.youtube.com/vi/u5GICgspXRI/0.jpg)](https://youtu.be/u5GICgspXRI)

### 💰 OFFER 시스템
- **가격 제안 기능**: 구매자가 희망 가격 제시 가능
- **선택적 활성화**: 판매자가 OFFER 기능 on/off 선택
- **1:N 구조**: 판매자는 복수 OFFER 수신 가능, 구매자는 아이템당 1개만 전송
    
🎬 시연 영상  

- **거래 제안 (OFFER)**  
[![거래 제안](https://img.youtube.com/vi/AYe06CfJlXo/0.jpg)](https://youtu.be/AYe06CfJlXo)

- **거래 제안 수락**  
[![제안 수락](https://img.youtube.com/vi/A4cN5lt2lsM/0.jpg)](https://youtu.be/A4cN5lt2lsM)

### 📱 실시간 알림 시스템
- **FCM** 기반 푸시 알림 (포그라운드/백그라운드)
- **Service Worker** 연동으로 앱 비활성 상태에서도 안정적 수신
- **알림 분류**: 필터링 기능과 전체 보기 옵션
- **영구 저장**: 사용자 삭제 전까지 알림 내역 보존

🎬 시연 영상  

- **실시간 알림**  
[![실시간 알림](https://img.youtube.com/vi/tLW7j50vkm8/0.jpg)](https://youtu.be/tLW7j50vkm8)

### 💬 실시간 채팅
- **거래별 채팅방**: 물품 거래 건마다 자동 생성
- **아이템 정보 표시**: 채팅방 상단에 거래 아이템 정보 제공
- **WebSocket** 기반 실시간 메시징

🎬 시연 영상  

- **실시간 채팅**  
[![실시간 채팅](https://img.youtube.com/vi/nCjSX9aAlzc/0.jpg)](https://youtu.be/nCjSX9aAlzc)

### 🔐 사용자 인증
- **JWT** 기반 토큰 인증 (AccessToken 30분, RefreshToken 1주)
- **자동 토큰 갱신**: AccessToken 만료 시 자동 리프레시
- **Redis 세션 관리**: 빠른 인증 처리

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

## 🚀 핵심 기술 구현

### 🔐 JWT 인증 시스템
- **이중 토큰 구조**: AccessToken(30분) + RefreshToken(1주)
- **자동 토큰 갱신**: AccessToken 만료 시 자동 리프레시
- **Redis 캐시**: RefreshToken을 Redis에 저장하여 빠른 인증 처리

### 🔍 동적 쿼리 시스템
- **JPA Criteria API** 활용한 런타임 쿼리 생성
- **복합 조건 검색**: 아이템명 + 카테고리 + 다중 옵션 필터
- **성능 최적화**: 인덱스 설계와 쿼리 최적화로 빠른 검색 응답

### ⚡ 실시간 통신 최적화
- **WebSocket + STOMP**: 표준 프로토콜 기반 안정적 통신
- **메시지 큐잉**: 연결 끊김 시 메시지 손실 방지
- **부하 분산**: 다중 클라이언트 동시 접속 최적화

### 📲 크로스 플랫폼 알림
- **FCM 통합**: 웹/모바일 통합 푸시 알림 시스템
- **Service Worker**: PWA 기반 백그라운드 알림 지원
- **알림 상태 관리**: 읽음/미읽음 상태와 영구 보관 기능

## 💡 주요 구현 성과

### ✅ 사용자 경험 개선
- **거래 시간 단축**: 대면거래 대기시간을 경매 시스템으로 대폭 단축
- **검색 효율성**: 복잡한 아이템 속성을 직관적인 UI로 쉽게 검색
- **실시간 피드백**: 모든 거래 활동에 즉시 알림 제공

### ✅ 기술적 도전 해결
- **복잡한 비즈니스 로직**: 게임 아이템의 다양한 속성과 거래 규칙 구현
- **실시간 동시성**: 다중 사용자의 동시 경매 참여 처리
- **안정적인 알림**: 네트워크 불안정 환경에서도 안정적인 알림 전송

## 📱 주요 화면

### 🏪 메인 마켓플레이스
- 등록된 아이템 목록과 실시간 경매 상태 표시
- 카테고리별 분류와 즐겨찾기 기능

### 🔍 고급 검색
- 다중 조건 필터링과 실시간 검색 결과
- 저장된 검색 조건과 알림 설정

### 💰 경매 참여
- 실시간 입찰 시스템과 OFFER 기능
- 남은 시간 카운트다운과 참여자 현황

### 📨 알림 센터
- 거래 관련 모든 알림 통합 관리
- 필터링과 상태별 분류 기능

### 💬 거래 채팅
- 거래별 전용 채팅방
- 아이템 정보 표시와 빠른 이동

## 🗂️ 프로젝트 문서

- **📋 상세 기획서**: [Notion 문서](https://ribbon-sound-6fd.notion.site/DarkAndDarker-Autction-e5a83564d7334c9bb1e8075ef01e1043?pvs=74)
- **🎨 UI/UX 설계**: [Figma 디자인](https://www.figma.com/file/CCmejjh2vW9bWEKTeznaeJ/Dark-And-Darker-Auction)
- **📊 API 문서**: Swagger 기반 API 명세서
- **🗃️ ERD**: MySQL 데이터베이스 스키마 설계도

## 🔗 관련 링크

- **💻 GitHub Organization**: [DarkAndDarker-Auction](https://github.com/orgs/DarkAndDarker-Auction/repositories)
- **🎮 DarkAndDarker 게임**: [공식 사이트](https://www.darkanddarker.com/)
- **📱 Firebase 프로젝트**: FCM 알림 서비스 구성

## 🏆 프로젝트 성과

### 📈 기술적 성취
- **실시간 시스템**: WebSocket 기반 안정적인 실시간 통신 구현
- **복잡한 검색 로직**: 게임 아이템의 다양한 속성을 고려한 동적 쿼리 시스템
- **크로스 플랫폼**: 웹/모바일 통합 알림 시스템 구축
- **확장 가능한 구조**: 마이크로서비스 지향적 모듈 설계

### 🎯 사용자 가치
- **거래 효율성**: 기존 대면거래 대비 시간 단축
- **사용자 편의성**: 직관적인 UI/UX로 게임 플레이어들의 높은 만족도
- **실시간 소통**: 안정적인 채팅과 알림 시스템으로 원활한 거래 지원

---

<p align="center">
  <em>🎮 하드코어 게임의 아이템 거래에 혁신을 가져다준 DarkAndDarker Auction</em><br/>
  <em>실시간 기술과 게이머 중심 UX의 완벽한 조화</em>
</p>
