## 소개
<img src="https://user-images.githubusercontent.com/85449777/131453809-74d303d6-66c7-4a7a-81ee-7552ed045e10.png" width="300px" height=auto>

네이버,카카오 웹툰 플랫폼을 이어주는 개인화된 웹툰 추천 사이트 서버입니다.<br>
여러 사이트에 흩어져있는 웹툰 랭킹을 한 곳에 모아 보여주고, 개인의 취향에 맞는 개인화된 추천을 제공함으로써 웹툰 유저들만의 커뮤니티를 제공하려합니다.<br>
Business Rule, 기술적인 문제에 대한 해결 방법은 WIKI에서 확인할 수 있습니다.

## 프로젝트 구조
![image](https://user-images.githubusercontent.com/50096655/130104899-9ec32925-af77-4796-838e-bbbb510204e2.png)

## 프로젝트 기술 스택
- BackEnd: Java 8, Spring Boot, JPA, Querydsl
- DB: Redis, MariaDB
- AWS: EC2, S3, jenkins, Nginx
- Github, github action


## 프로젝트의 주요 관심사

### 공통사항

- 나쁜 냄새가 나는 코드를 지속적으로 리팩토링합니다.
- 지속적인 성능 개선을 위해 노력합니다.
- 더 나은 프로젝트 구조를 가져가기 위해 지속적으로 학습합니다.

### 코드 컨벤션
- Google Code style 

### 성능 최적화
- 자세한 내용은 [Wiki](https://github.com/yeonnwoo/Webtooni_server/wiki)를 참고해주세요

## 브랜치 관리 전략

Git Flow를 사용하여 브랜치를 관리합니다. 모든 브랜치는 Pull Request에 리뷰를 진행한 후 merge를 진행합니다.

<img src="https://user-images.githubusercontent.com/50096655/128598662-8bc629d9-0c35-415b-90ca-317f07f17b29.png" width="400px" height=auto>



- Main : 배포시 사용합니다. 아직 배포단계에 이르지 않아 Main 브랜치에 내용이 없습니다.
- Develop : 완전히 개발이 끝난 부분에 대해서만 Merge를 진행합니다.
- Feature : 기능 개발을 진행할 때 사용합니다.
    - feature/#이슈번호 와 같은 형태로 브랜치를 관리합니다.

- Release : 배포를 준비할 때 사용합니다.
- Hot-Fix : 배포를 진행한 후 발생한 버그를 수정해야 할 때 사용합니다.
    - 배포한 버전에서 긴급하게 수정할 필요가 있을 때 master 브랜치에서 분리하는 브랜치를 말합니다.

#### 브랜치 관리 전략 참고 문헌

- [우아한 형제들 기술 블로그](http://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html)
- [협업을 위한 Gitflow](https://overcome-the-limits.tistory.com/entry/%ED%98%91%EC%97%85-%ED%98%91%EC%97%85%EC%9D%84-%EC%9C%84%ED%95%9C-Git-Flow-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)

### 테스트

### 성능 테스트

### Wiki

## 사용 기술 및 환경

## DB ERD
![RDS_20210727_16_03](https://user-images.githubusercontent.com/50096655/128598705-e5638fba-6b30-4709-8a31-d8a2e1caeb00.png)

## Diagram

<img src="https://user-images.githubusercontent.com/50096655/129123676-c567f6f6-99af-422b-8436-aa2b0866c6dd.png" width="500px" height=auto>
<img src="https://user-images.githubusercontent.com/85449777/131452871-dab93ac9-547f-49e2-9846-7b852c814a2b.png" width="500px" height=auto>

