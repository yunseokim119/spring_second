# ❇️ JPA를 활용한 upgrade 일정 관리 앱 서버 만들기
## ✅ API 명세서

<img width="747" alt="스크린샷 2024-08-27 오후 8 25 28" src="https://github.com/user-attachments/assets/944435f9-fb74-4870-962a-5a493f98f014">

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

201 Created: 일정이 성공적으로 생성됨

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

### 3. 일정 전체 조회 API
전체 일정을 조회한다.

경로: /api/schedules

메서드: GET

Response Body

    {
      "id": 1,                 // 일정 ID
      "userName": "string",    // 작성자명
      "title": "string",       // 할일 제목
      "content": "string",     // 할일 내용
      "createdDate": "2024-08-22T10:00:00",  // 작성일
      "modifiedDate": "2024-08-22T10:00:00"  // 수정일
    },
    {
      "id": 2,                 
      "userName": "string",    
      "title": "string",      
      "content": "string",    
      "createdDate": "2024-08-23T10:00:00",  
      "modifiedDate": "2024-08-23T10:00:00" 
    }

Query Parameters

page: 페이지 번호 (기본값 0)

size: 페이지 크기 (기본값 10)

상태코드

200 OK: 일정이 성공적으로 조회됨

### 4. 일정 수정
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

### 5. 일정 삭제
특정 ID를 가진 일정을 삭제한다.

경로: /api/schedules/{id}

메서드: DELETE

상태코드

204 OK: 일정이 성공적으로 삭제됨

404 Not Found: 지정된 ID의 일정이 없음

---
<img width="748" alt="스크린샷 2024-08-27 오후 8 25 43" src="https://github.com/user-attachments/assets/06af97ed-a1cc-47bd-9bc2-6d2d12da6dbd">


### 1. 유저 생성
새로운 유저를 등록한다.

경로: /api/users

메서드: POST

Request Body

    {
      "username": "kim",
      "email": "kim@email.com"
    }

Response Body

    {
        "id": "1",
        "username": "kim",
        "email": "kim@email.com",
        "createdDate": "2024-08-22T10:00:00",
        "modifiedDate": "2024-08-22T10:00:00"
    }

상태코드

200 Created: 유저가 성공적으로 생성됨

### 2. 유저 단건 조회 API
특정 ID를 가진 유저를 조회한다.

경로: /api/users/{id}

메서드: GET

Response Body

    {
      "id": "1",
      "username": "kim",
      "email": "kim@email.com",
      "createdDate": "2024-08-22T10:00:00",
      "modifiedDate": "2024-08-22T10:00:00"
    }

상태코드

200 OK: 유저가 성공적으로 조회됨

404 Not Found: 지정된 ID의 유저가 없음

### 3. 유저 전체 조회 API
전체 유저를 조회한다.

경로: /api/users

메서드: GET

Response Body

    {
      "id": "1",
      "username": "kim",
      "email": "kim@email.com",
      "createdDate": "2024-08-22T10:00:00",
      "modifiedDate": "2024-08-22T10:00:00"
    },
    {
      "id": "2",
      "username": "Lee",
      "email": "Lee@email.com",
      "createdDate": "2024-08-24T12:00:00",
      "modifiedDate": "2024-08-24T12:00:00"
    }


상태코드

200 OK: 유저가 성공적으로 조회됨

### 4. 유저 수정
특정 ID를 가진 유저를 수정한다.

경로: api/users/{id}

메서드: PUT

Request Body

    {
      "username": "Lee",
      "email": "Lee@email.com"
    }

Response Body

    {
      "id": "2",
      "username": "Park",
      "email": "Park@email.com",
      "createdDate": "2024-08-24T12:00:00",
      "modifiedDate": "2024-08-25T12:00:00"
    }
    
상태코드

200 OK: 유저가 성공적으로 수정됨

404 Not Found: 지정된 ID의 유저가 없음

### 4. 유저 삭제
특정 ID를 가진 유저를 삭제한다.

경로: api/users/{id}

메서드: DELETE

상태코드

204 OK: 유저가 성공적으로 삭제됨

404 Not Found: 지정된 ID의 유저가 없음

---
<img width="875" alt="스크린샷 2024-08-27 오후 8 25 59" src="https://github.com/user-attachments/assets/bb7e12aa-9e6a-450d-b6d3-0f9b9a408663">


### 1. 댓글 생성
새로운 댓글을 등록한다.

경로: /api/schedules/{scheduleId}/comments

메서드: POST

Request Body

    {
      "content": "string",
      "userId": "number"
    }

Response Body

    {
      "id": "number",
      "content": "string",
      "createdDate": "datetime",
      "modifiedDate": "datetime",
      "userId": "number"
    }

상태코드

201 Created: 댓글이 성공적으로 생성됨

### 2. 댓글 단건 조회 API
특정 ID를 가진 댓글을 조회한다.

경로: /api/comments/{id}

메서드: GET

Response Body

    {
      "id": "number",
      "content": "string",
      "createdDate": "datetime",
      "modifiedDate": "datetime",
      "userId": "number"
    }

상태코드

200 OK: 댓글이 성공적으로 조회됨

404 Not Found: 지정된 ID의 댓글이 없음

### 3. 댓글 전체 조회 API
전체 댓글을 조회한다.

경로: /api/schedules/{scheduleId}/comments

메서드: GET

Response Body

    {
        "id": "number",
        "content": "string",
        "createdDate": "datetime",
        "modifiedDate": "datetime",
        "userId": "number"
    },
    {
        "id": "number",
        "content": "string",
        "createdDate": "datetime",
        "modifiedDate": "datetime",
        "userId": "number"
    }


상태코드

200 OK: 댓글이 성공적으로 조회됨

### 4. 댓글 수정
특정 ID를 가진 댓글을 수정한다.

경로: /api/comments/{id}

메서드: PUT

Request Body

    {
      "content": "string"
    }

Response Body

    {
      "id": "number",
      "content": "string",
      "createdDate": "datetime",
      "modifiedDate": "datetime",
      "userId": "number"
    }
    
상태코드

200 OK: 댓글이 성공적으로 수정됨

404 Not Found: 지정된 ID의 댓글이 없음

### 4. 댓글 삭제
특정 ID를 가진 댓글을 삭제한다.

경로: /api/comments/{id}

메서드: DELETE

상태코드

204 OK: 댓글이 성공적으로 삭제됨

404 Not Found: 지정된 ID의 댓글이 없음


## ✅ ERD
### ERD 설계
### 엔터티 (Entity)
<img width="717" alt="스크린샷 2024-08-27 오후 8 35 37" src="https://github.com/user-attachments/assets/93f7ffac-4130-451d-a7b6-608fd2748658">

<img width="718" alt="스크린샷 2024-08-27 오후 8 35 45" src="https://github.com/user-attachments/assets/a0e8c096-586f-43dd-9cb6-0418f4fa37ab">

<img width="719" alt="스크린샷 2024-08-27 오후 8 35 54" src="https://github.com/user-attachments/assets/7791079c-fb55-4ba1-a7c1-552a7257bf89">

<img width="720" alt="스크린샷 2024-08-27 오후 8 36 03" src="https://github.com/user-attachments/assets/6f0f6858-17f4-435c-8d45-42096eebf816">

### ERD 다이어그램
    +-------------------+      +------------------+        +------------------+       +-------------------+
    |      User         |      |     Schedule      |      |     Comment       |       |   AssignedUser    |
    +-------------------+      +------------------+        +------------------+       +-------------------+
    | id (PK)           |<-----| id (PK)           |<-----| id (PK)           |       | id (PK)           |
    | username          |      | title             |      | content           |       | user_id (FK)      |
    | email             |      | content           |      | createdDate       |       | schedule_id (FK)  |
    | createdDate       |      | createdDate       |      | modifiedDate      |       +-------------------+
    | modifiedDate      |      | modifiedDate      |      | user_id (FK)      |
    +-------------------+      | owner_id (FK) ----+      | schedule_id (FK)  |
                               +------------------+        +------------------+

