# 🔐 방탈출 어플리케이션
> 방탈출 카페의 예약 관리를 위한 **관리자 전용** 웹 어플리케이션입니다.

# 📌 Reservation API 명세서

---

## 1️⃣ GET /reservations

- **Description:** 모든 예약 정보를 조회합니다.
- **URL:** `/reservations`
- **Method:** `GET`
- **Response Status:** `200 OK`
- **Response Body:**  

    ```json
    [
      {
        "id": 1,
        "name": "브라운",
        "date": "2023-01-01",
        "time": "10:00"
      },
      {
        "id": 2,
        "name": "브라운",
        "date": "2023-01-02",
        "time": "11:00"
      },
      {
        "id": 3,
        "name": "브라운",
        "date": "2023-01-03",
        "time": "12:00"
      }
    ]
    ```

---

## 2️⃣ POST /reservations

- **Description:** 새로운 예약 정보를 생성합니다.
- **URL:** `/reservations`
- **Method:** `POST`
- **Request Body:**  

    ```json
    {
      "name": "브라운",
      "date": "2023-08-05",
      "time": "15:40"
    }
    ```

    | 필드명 | 타입       | 설명                     | 필수 여부 |
    |---------|-----------|--------------------------|-----------|
    | name    | `String` | 예약자 이름              | ✅         |
    | date    | `String` | 예약 날짜 (YYYY-MM-DD)   | ✅         |
    | time    | `String` | 예약 시간 (HH:mm)        | ✅         |

- **Response Status:** `201 Created`
- **Response Headers:**  
    - `Location`: 생성된 예약의 URI  

- **Response Body:**  

    ```json
    {
      "id": 4,
      "name": "브라운",
      "date": "2023-08-05",
      "time": "15:40"
    }
    ```

- **에러 응답:**  
    - 잘못된 요청 데이터:
        - **Status Code:** `400 Bad Request`
        - **Response Body:**  

        ```json
        {
          "message": "필수 정보가 누락되었습니다."
        }
        ```

---

## 3️⃣ DELETE /reservations/{id}

- **Description:** 지정된 ID의 예약을 삭제합니다.
- **URL:** `/reservations/{id}`
- **Method:** `DELETE`
- **Path Parameter:**  

    | 필드명 | 타입     | 설명          |
    |---------|----------|---------------|
    | id      | `Long`  | 삭제할 예약의 ID |

- **Response Status:** `204 No Content`

- **에러 응답:**  
    - 예약이 존재하지 않을 때:
        - **Status Code:** `404 Not Found`
        - **Response Body:**  

        ```json
        {
          "message": "Reservation not found"
        }
        ```

---

## 4️⃣ 페이지 이동

- **Description:** 템플릿 렌더링을 위한 페이지 이동  
- **PageController**  
    - `/` → `home.html` 렌더링  
    - `/reservation` → `reservation.html` 렌더링  
- **Method:** `GET`
- **Response Status:** `200 OK`

