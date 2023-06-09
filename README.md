# Sortware_Project1


## 🖥️ 프로젝트 소개

<br>
내가 본 영화와 유사한 영화 추천 프로그램

### 🕰️ 개발 기간
* 23.03.06 - 23.06.12

### 🧑‍🤝‍🧑 맴버구성
 - 팀원1 : 김규민 - 머신러닝 및 플라스크
 - 팀원2 : 박준형 - Frontend(nextJs)
 - 팀원3 : 오현식 - Backend(Spring)

### ⚙️ 개발 환경
- **Framework** : Springboot(2.x)
- **Database** : Maria DB(11xe)

### 📌 주요 기능
#### 로그인
- Spring Security
- DB값 검증
- 로그인 시 JWT 토큰 생성 및 쿠키(Cookie)로 전달
- 토큰 유효시간 만료 시, API 접근 제한 (401 에러)
#### 회원가입
- ID, Nickname, Email 중복 체크
- 협업필터링 가상데이터 때문에, Userid가 315번부터 시작
#### 게시판 및 평점
- 댓글 및 평점 불러오기
- 댓글 및 평점 받아서 DB에 저장
#### 영화추천
- 한 영화의 내용, 키워드 기반 추천 MovieId값을 플라스크와 통신
- 한 사용자가 본 영화데이터(평점) 기반으로 플라스크와 협업필터링 MovieId값 통신

