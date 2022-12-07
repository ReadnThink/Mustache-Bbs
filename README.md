# Mustache-Bbs

# 프로젝트
1. 병원 리뷰 사이트 - 다녀왔던 병원의 리뷰를 적어서 정보를 공유하는 사이트   <br>
<br>
2. 전국 모든 병원 정보 파서 - 11만 9000개   <br>
<br>
3. 지역과 진료 과목 검색 기능 ex) 서울 강남 성형외과
실제 사용자 리뷰 기능

## End-Point
http://localhost:8080
### 게시판
1. /articles,  /articles/list : 게시판의 목록을 보여줌
2. /new : 새로운 글을 쓰는 기능 -> /posts 로연결됩니다.
3. /{id} : 해당 id값의 게시물을 찾는 기능
4. /{id}/edit : 게시물을 수정하는기능 -> /update로 연결됩니다.

## 엔티티 다이어그램
![img_2.png](img_2.png)
