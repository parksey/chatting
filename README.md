## Polling, Long Polling ,SSE 구현


### Docker

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

