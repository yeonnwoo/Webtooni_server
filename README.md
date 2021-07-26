## 소개

## 프로젝트 구조

## 프로젝트의 주요 관심사

### 공통사항

### 코드 컨벤션

### 성능 최적화

## 브랜치 관리 전략
Git Flow를 사용하여 브랜치를 관리합니다.
모든 브랜치는 Pull Request에 리뷰를 진행한 후 merge를 진행합니다.

<img src="https://user-images.githubusercontent.com/50096655/126958427-df332279-3365-4d60-84c5-16f44bcacdec.png" width="400px" height=auto>


- Master : 배포시 사용합니다. 아직 배포단계에 이르지 않아 Master 브랜치에 내용이 없습니다.
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

## DB ERD

## 사용 기술 및 환경
