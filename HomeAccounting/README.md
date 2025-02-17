# Home Accounting

**Всем привет! Представляю вашему вниманию небольшое приложение 
для ведения домашней бухгалтерии.**

![фото](https://i.pinimg.com/736x/11/82/98/11829859fcf93ec48b9d558a1390d341.jpg)

## API Documentation

### -----------------------------------------
### <u>*Аутентификация и авторизация*</u>
### -----------------------------------------

---
### Endpoint ```/auth/register```
---
**Метод** ```POST```
- **Описание:** регистрация нового пользователя
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
    - Тело запроса (JSON):
        
        ```json
        {
            "username": "Vasya",
            "password": "pass123"
        }
        ```
- **Ответ:**

    - Код ответа: ``201 Created``

---
### Endpoint ```/auth/login```
---
**Метод** ```POST```
- **Описание:** аутентификация пользователя
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
    - Тело запроса (JSON):
        
        ```json
        {
            "username": "Admin",
            "password": "pass123"
        }
        ```
- **Ответ:**

    - Тело ответа:
        ```json
        {
            "token": "Bearer ${token}"
        }

### -----------------------------------------
### <u>*Счёт(MoneyAccount)*</u>
### -----------------------------------------

---
### Endpoint ```/money-account```
---
**Метод** ```POST```
- **Описание:**  создание счёта
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "name": "Налик",
            "amount": 354.50
        }
        ```
- **Ответ:**

    - Код ответа: ``201 Created``

---
### Endpoint ```/money-account```
---
**Метод** ```GET```
- **Описание:**  вывод информации о счетах
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Тело ответа:
        ```json
        [
            {
                "id": 2,
                "name": "Карта Сбербанка",
                "amount": 1470.50
            },
            {
                "id": 3,
                "name": "Налик",
                "amount": 354.50
            }
        ]
        ```

---
### Endpoint ```/money-account/{id}```
---
**Метод** ```PATCH```
- **Описание:**  изменение счёта
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "name": "Налик",
            "amount": 50.50
        }
        ```
- **Ответ:**

    - Код ответа: ``200 OK``

---
### Endpoint ```/money-account/{id}```
---
**Метод** ```DELETE```
- **Описание:**  удаление счёта
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Код ответа: ``200 OK``

### -----------------------------------------
### <u>*Категории расходов(Category)*</u>
### -----------------------------------------

---
### Endpoint ```/category```
---
**Метод** ```POST```
- **Описание:**  создание категории
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "name": "Продукты"
        }
        ```
- **Ответ:**

    - Код ответа: ``201 Created``

---
### Endpoint ```/category```
---
**Метод** ```GET```
- **Описание:**  вывод информации о категориях
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Тело ответа:
        ```json
        [
            {
                "id": 2,
                "name": "Аренда чайника"
            },
            {
                "id": 3,
                "name": "Продукты"
            }
        ]
        ```

---
### Endpoint ```/category/{id}```
---
**Метод** ```PATCH```
- **Описание:**  изменение категории
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "name": "Неправильный мёд"
        }
        ```
- **Ответ:**

    - Код ответа: ``200 OK``

---
### Endpoint ```/category/{id}```
---
**Метод** ```DELETE```
- **Описание:**  удаление категории
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Код ответа: ``200 OK``

### -----------------------------------------
### <u>*Записи расходов(ExpenseRecord)*</u>
### -----------------------------------------

---
### Endpoint ```/expense-record```
---
**Метод** ```POST```
- **Описание:**  создание записи о расходах
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "sum": 500,
            "nameAccount": "Карта Сбербанка",
            "nameCategory": "Неправильный мёд"
        }
        ```
- **Ответ:**

    - Код ответа: ``201 Created``

---
### Endpoint ```/expense-record```
---
**Метод** ```GET```
- **Описание:**  вывод записей о расходах
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Тело ответа:
        ```json
        [
            {
                "id": 5,
                "sum": 500,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T01:02:53.57292"
            },
            {
                "id": 9,
                "sum": 1452,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T08:02:53.57292"
            }
        ]
        ```

---
### Endpoint ```/expense-record/{id}```
---
**Метод** ```PATCH```
- **Описание:**  изменение записи о расходах
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    - Тело запроса (JSON):
        
        ```json
        {
            "sum": 600,
            "nameAccount": "Карта Сбербанка",
            "nameCategory": "Неправильный мёд"
        }
        ```
- **Ответ:**

    - Код ответа: ``200 OK``

---
### Endpoint ```/expense-record/{id}```
---
**Метод** ```DELETE```
- **Описание:**  удаление записи о расходах
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Код ответа: ``200 OK``
---
### Endpoint ```/expense-record/account/{id}```
---
**Метод** ```GET```
- **Описание:**  вывод записей о расходах по определённому счёту
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**
  
    - Код ответа: ``200 OK``

    - Тело ответа:
        ```json
        [
            {
                "id": 5,
                "sum": 500,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T01:02:53.57292"
            },
            {
                "id": 9,
                "sum": 1452,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T08:02:53.57292"
            }
        ]
        ```

---
### Endpoint ```/expense-record/category/{id}```
---
**Метод** ```GET```
- **Описание:**  вывод записей о расходах по определённой категории
- **Запрос:** 

    - Заголовки
  
        - ``content-type: application/json``
        - ``Athorization: Bearer ${token}``
    
- **Ответ:**

    - Код ответа: ``200 OK``

    - Тело ответа:
        ```json
        [
            {
                "id": 5,
                "sum": 500,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T01:02:53.57292"
            },
            {
                "id": 9,
                "sum": 1452,
                "nameAccount": "Карта Сбербанка",
                "nameCategory": "Неправильный мёд",
                "dateOperation": "2025-01-19T08:02:53.57292"
            }
        ]
        ```

