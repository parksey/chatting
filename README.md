## 채팅 정리
```
1. Polling, Long Polling ,SSE, Socket 구현
2. Socket을 사용한 인프라 구축
```

### MySQL 설정 - Docker

[컨테이너 생성 및 사용]
```bash
docker run --name chat -e MYSQL_ROOT_PASSWORD=qwe123 -d -p 3306:3306 mysql:latest
```

[MYSQL 컨테이너 접속]
```bash
docker exec -it chat /bin/bash

myql -u root -p
(qwe123)

create database chat;

show databases;
```

### 테스트 방법
```
http 폴더 내부의 테스트 진행
```

[private 테스트 환경 추가]
```
{
  "default": {
    "my-login-id": "tester1",
    "my-password": "test"
  },
  "local": {
    "my-login-id": "tester1",
    "my-password": "test"
  }
}
```
- 회원 가입 후 자신의 id, password추가 후 login 테스트 진행