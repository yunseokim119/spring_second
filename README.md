# ❇️ JPA를 활용한 upgrade 일정 관리 앱 서버 만들기
## ✅ API 명세서
<img width="763" alt="스크린샷 2024-08-23 오후 1 20 35" src="https://github.com/user-attachments/assets/a91a2a63-132a-4646-9920-6f159cdf5a45">


### 1. 일정 생성
새로운 일정을 등록한다.

경로: /api/schedules

메서드: POST

Request Body

    {
      "userName": "string",    // 작성자명
      "title": "string",       // 할일 제목
      "content": "string"      // 할일 내용
    }

Response Body

    {
      "id": 1,                 // 일정 ID
      "userName": "string",    // 작성자명
      "title": "string",       // 할일 제목
      "content": "string",     // 할일 내용
      "createdDate": "2024-08-22T10:00:00",  // 작성일
      "modifiedDate": "2024-08-22T10:00:00"  // 수정일
    }

상태코드

200 Created: 일정이 성공적으로 생성됨

### 2. 일정 단건 조회 API
특정 ID를 가진 일정을 조회한다.

경로: /api/schedules/{id}

메서드: GET

Response Body

    {
      "id": 1,                 // 일정 ID
      "userName": "string",    // 작성자명
      "title": "string",       // 할일 제목
      "content": "string",     // 할일 내용
      "createdDate": "2024-08-22T10:00:00",  // 작성일
      "modifiedDate": "2024-08-22T10:00:00"  // 수정일
    }

상태코드

200 OK: 일정이 성공적으로 조회됨

404 Not Found: 지정된 ID의 일정이 없음

### 3. 일정 수정
특정 ID를 가진 일정을 수정한다.

경로: /api/schedules/{id}

메서드: PUT

Request Body

    {
      "userName": "string",    // 작성자명 (optional, 필요에 따라 생략 가능)
      "title": "string",       // 할일 제목
      "content": "string"      // 할일 내용
    }

Response Body

    {
      "id": 1,                 // 일정 ID
      "userName": "string",    // 작성자명
      "title": "string",       // 할일 제목
      "content": "string",     // 할일 내용
      "createdDate": "2024-08-22T10:00:00",  // 작성일
      "modifiedDate": "2024-08-22T11:00:00"  // 수정일
    }
    
상태코드

200 OK: 일정이 성공적으로 수정됨

404 Not Found: 지정된 ID의 일정이 없음

### 4. 일정 삭제
특정 ID를 가진 일정을 삭제한다.

경로: /api/schedules/{id}

메서드: DELETE

상태코드

204 OK: 일정이 성공적으로 삭제됨

404 Not Found: 지정된 ID의 일정이 없음

## ✅ ERD
### ERD 설계
### 엔터티 (Entity)
<img width="719" alt="스크린샷 2024-08-23 오후 1 32 41" src="https://github.com/user-attachments/assets/4545fc1f-19e3-4033-8639-78ac04b839fe">

- id: Primary Key로 각 일정의 고유한 식별자입니다.
- userName: 일정을 작성한 사용자의 이름입니다.
- title: 일정의 제목입니다.
- content: 일정의 상세 내용입니다.
- createdDate: 일정이 처음 생성된 날짜와 시간입니다.
- modifiedDate: 일정이 마지막으로 수정된 날짜와 시간입니다.


### ERD 다이어그램
    +-----------------------+
    |       Schedule        |
    +-----------------------+
    | id          (PK)      |
    | userName              |
    | title                 |
    | content               |
    | createdDate           |
    | modifiedDate          |
    +-----------------------+
