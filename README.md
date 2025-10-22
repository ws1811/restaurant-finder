# 🍽️ Restaurant Finder

AI 기반 맥락 인식 식당 추천 서비스

## 📋 프로젝트 소개

위치, 날씨, 인원 수, 메뉴 선호도 등 다양한 맥락 정보를 종합하여 AI가 최적의 식당을 추천해주는 웹 애플리케이션입니다.

**배포 URL:** https://dh16eh1zyeeii.cloudfront.net

---

## 🏗️ 아키텍처
```
┌─────────────────────────────────────────────────────────────┐
│                         사용자                               │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│              프론트엔드 (React + Vite)                       │
│         https://dh16eh1zyeeii.cloudfront.net                │
│                                                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │ 위치 정보    │  │ 사용자 입력  │  │ 추천 결과    │     │
│  │ 수집         │  │ (인원/선호도)│  │ 표시         │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└──────────────────────────┬──────────────────────────────────┘
                           │ HTTPS
                           ▼
┌─────────────────────────────────────────────────────────────┐
│          AWS CloudFront (CDN + HTTPS)                       │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│          AWS S3 (정적 웹 호스팅)                            │
└─────────────────────────────────────────────────────────────┘

                           │
                           │ HTTPS API Request
                           ▼
┌─────────────────────────────────────────────────────────────┐
│       백엔드 API (Spring Boot)                              │
│    https://restaurant-finder.duckdns.org                    │
│                                                              │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐     │
│  │ REST API     │  │ CORS 설정    │  │ 캐싱         │     │
│  │ Controller   │  │ WebConfig    │  │ (Spring)     │     │
│  └──────────────┘  └──────────────┘  └──────────────┘     │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────────┐
│          AWS EC2 (Ubuntu 22.04)                             │
│                                                              │
│  ┌──────────────────────────────────────────────────┐      │
│  │  Nginx (리버스 프록시 + SSL/TLS)                 │      │
│  │  - Let's Encrypt 인증서                          │      │
│  │  - HTTP → HTTPS 리다이렉트                       │      │
│  └────────────────┬─────────────────────────────────┘      │
│                   │                                         │
│                   ▼                                         │
│  ┌──────────────────────────────────────────────────┐      │
│  │  Spring Boot Application (Port 8080)             │      │
│  │  - Java 17                                        │      │
│  │  - Gradle 빌드                                    │      │
│  └──────────────────────────────────────────────────┘      │
└──────────────────────────┬──────────────────────────────────┘
                           │
        ┌──────────────────┼──────────────────┐
        │                  │                  │
        ▼                  ▼                  ▼
┌──────────────┐  ┌──────────────┐  ┌──────────────┐
│ 카카오 로컬  │  │ OpenWeather  │  │ Google       │
│ API          │  │ API          │  │ Gemini AI    │
│              │  │              │  │              │
│ • 주변 식당  │  │ • 현재 날씨  │  │ • AI 추천    │
│   검색       │  │ • 온도 정보  │  │   로직       │
└──────────────┘  └──────────────┘  └──────────────┘
```

---

## 🛠️ 기술 스택

### **프론트엔드**
- **React 18** - UI 라이브러리
- **Vite** - 빌드 도구
- **CSS3** - 스타일링

### **백엔드**
- **Spring Boot 3.x** - 프레임워크
- **Java 17** - 프로그래밍 언어
- **Gradle** - 빌드 도구
- **Spring Cache** - 캐싱 (Caffeine)

### **인프라 (AWS)**
- **EC2 (t3.micro)** - 백엔드 서버
- **S3** - 정적 파일 호스팅
- **CloudFront** - CDN 및 HTTPS
- **VPC** - 네트워크 격리
- **Security Group** - 방화벽

### **DevOps**
- **Nginx** - 리버스 프록시
- **Let's Encrypt (Certbot)** - SSL/TLS 인증서
- **DuckDNS** - 무료 도메인
- **Git/GitHub** - 버전 관리

### **외부 API**
- **Kakao Local API** - 주변 식당 검색
- **OpenWeather API** - 날씨 정보
- **Google Gemini AI** - 맥락 기반 추천

---

## ✨ 주요 기능

### 1. **위치 기반 식당 검색**
- 브라우저 Geolocation API로 현재 위치 획득
- 카카오 로컬 API로 반경 1km 내 식당 검색

### 2. **날씨 정보 수집**
- OpenWeather API로 현재 날씨 및 온도 확인
- 30분 캐싱으로 API 호출 최적화

### 3. **AI 기반 추천**
- Google Gemini AI를 활용한 맥락 인식 추천
- 인원, 날씨, 메뉴 선호도를 종합 분석
- 추천 이유와 함께 3곳의 식당 제안

### 4. **보안 및 성능**
- HTTPS 통신 (CloudFront + Let's Encrypt)
- CORS 설정으로 안전한 API 통신
- CloudFront CDN으로 빠른 콘텐츠 전송
- Spring Cache로 반복 요청 최적화

---

## 📂 프로젝트 구조
```
restaurant-finder/
├── backend/                    # Spring Boot 백엔드
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/restaurant/finder/
│   │   │   │   ├── controller/         # REST API 컨트롤러
│   │   │   │   ├── service/            # 비즈니스 로직
│   │   │   │   ├── dto/                # 데이터 전송 객체
│   │   │   │   └── config/             # 설정 (CORS, Cache)
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── build.gradle
│   └── gradlew
│
└── frontend/                   # React 프론트엔드
    ├── src/
    │   ├── App.jsx             # 메인 컴포넌트
    │   ├── App.css             # 스타일
    │   └── main.jsx            # 진입점
    ├── .env.production         # 프로덕션 환경변수
    ├── package.json
    └── vite.config.js
```

---

## 🚀 배포 과정

### **백엔드 배포 (EC2)**

1. **EC2 인스턴스 생성**
   - Ubuntu 22.04, t3.micro
   - 보안 그룹: 22, 80, 443, 8080 포트 개방

2. **환경 구성**
```bash
   sudo apt update && sudo apt upgrade -y
   sudo apt install openjdk-17-jdk git -y
```

3. **프로젝트 배포**
```bash
   git clone https://github.com/ws1811/restaurant-finder.git
   cd restaurant-finder/backend
   ./gradlew clean build -x test
   nohup java -jar build/libs/backend-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
```

4. **Nginx 설정**
```bash
   sudo apt install nginx -y
   sudo nano /etc/nginx/sites-available/restaurant-backend
```

5. **SSL 인증서 발급**
```bash
   sudo apt install certbot python3-certbot-nginx -y
   sudo certbot --nginx -d restaurant-finder.duckdns.org
```

### **프론트엔드 배포 (S3 + CloudFront)**

1. **빌드**
```bash
   cd frontend
   npm install
   npm run build
```

2. **S3 버킷 생성 및 업로드**
   - 정적 웹 호스팅 활성화
   - 퍼블릭 액세스 허용
   - dist 폴더 내용 업로드

3. **CloudFront 배포 생성**
   - S3 원본 연결
   - HTTPS 자동 제공
   - 캐시 무효화 설정

---

## 🔧 로컬 개발 환경 설정

### **백엔드**
```bash
cd backend

# application.properties 설정
cp src/main/resources/application.properties.example src/main/resources/application.properties
# API 키 입력

# 실행
./gradlew bootRun
```

**필요한 API 키:**
- Kakao REST API: https://developers.kakao.com
- OpenWeather API: https://openweathermap.org
- Google Gemini API: https://ai.google.dev

### **프론트엔드**
```bash
cd frontend

# 설치
npm install

# 개발 서버 실행
npm run dev

# 빌드
npm run build
```

---

## 🌐 API 엔드포인트

### **GET** `/api/restaurants/test`
서버 상태 확인

### **GET** `/api/restaurants/nearby`
주변 식당 검색
- **Query Parameters:**
  - `latitude` (required): 위도
  - `longitude` (required): 경도
  - `radius` (optional, default: 1000): 검색 반경 (미터)

### **GET** `/api/restaurants/weather`
날씨 정보 조회
- **Query Parameters:**
  - `latitude` (required): 위도
  - `longitude` (required): 경도

### **POST** `/api/restaurants/recommend`
AI 식당 추천
- **Request Body:**
```json
  {
    "latitude": 37.5665,
    "longitude": 126.9780,
    "peopleCount": 4,
    "menuPreference": "매운 음식"
  }
```

## 🔄 CI/CD

GitHub Actions를 통한 자동 배포

- **프론트엔드:** `frontend/` 디렉토리 변경 시 자동으로 S3 업로드 및 CloudFront 무효화
- **백엔드:** `backend/` 디렉토리 변경 시 자동으로 빌드 후 EC2 배포

---

## 📊 주요 의사결정

### 1. **모노레포 vs 멀티레포**
- **선택:** 모노레포
- **이유:** 토이 프로젝트 특성상 통합 관리가 효율적

### 2. **백엔드 프레임워크**
- **선택:** Spring Boot
- **이유:** 안정성, 풍부한 생태계, 기업 표준

### 3. **프론트엔드 빌드 도구**
- **선택:** Vite
- **이유:** 빠른 개발 서버, 최신 기술 스택

### 4. **배포 아키텍처**
- **선택:** 프론트/백 분리 배포
- **이유:** 프로페셔널한 구조, 확장성, 각 계층의 독립적 관리

### 5. **HTTPS 구현**
- **선택:** Let's Encrypt + Nginx
- **이유:** 무료, 자동 갱신, 업계 표준

### 6. **캐싱 전략**
- **선택:** Spring Cache (Caffeine)
- **이유:** 날씨 API 호출 최적화 (30분 TTL)

---

## 🔐 보안

- HTTPS 통신 (TLS 1.2+)
- CORS 정책으로 허용된 도메인만 API 접근
- 환경변수로 API 키 관리 (.gitignore)
- AWS Security Group으로 포트 제한

---

## 📈 향후 개선 사항

- [ ] 사용자 계정 시스템 (회원가입/로그인)
- [ ] 찜한 식당 저장 기능
- [ ] 사용자 리뷰 및 평점 시스템
- [ ] 식당 방문 기록 및 통계
- [ ] 카카오맵 연동 (지도 표시)
- [ ] PWA 지원 (오프라인 모드)
- [ ] Docker 컨테이너화
- [ ] 모니터링 (CloudWatch, Prometheus)
- [ ] 로드 밸런서 추가 (고가용성)

---

## 📝 라이선스

이 프로젝트는 개인 포트폴리오 목적으로 제작되었습니다.

---

## 👨‍💻 개발자

**이름:** wonsun 
**GitHub:** [@ws1811](https://github.com/ws1811)  
**이메일:** rnsjtm0811@gmail.com

---

## 🙏 Reference

- [Kakao Developers](https://developers.kakao.com) - 로컬 API 제공
- [OpenWeather](https://openweathermap.org) - 날씨 API 제공
- [Google AI](https://ai.google.dev) - Gemini AI 제공
- [Let's Encrypt](https://letsencrypt.org) - 무료 SSL 인증서
- [DuckDNS](https://www.duckdns.org) - 무료 도메인
