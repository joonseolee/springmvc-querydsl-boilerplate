# springmvc-querydsl-boilerplate

![build badge](https://github.com/joonseolee/springmvc-querydsl-boilerplate/actions/workflows/gradle.yml/badge.svg)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=joonseolee_springmvc-querydsl-boilerplate&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=joonseolee_springmvc-querydsl-boilerplate)

springMVC + queryDSL 보일러플레이트

## 스택

1. Spring Boot
2. JPA + QueryDSL
3. MySQL (replication)
4. Flyway
5. Swagger

## 사용

서버환결별 구성은 __로컬,알파,리얼__ 로 정의해두었고  
로컬같은경우 디비를 `docker-compose`로 이미지 구성해두었다.  

그래서 로컬에서 사용하기위해선 해당 프로젝트의 루트에서  
아래 명령어 실행해주고 프로젝트를 실행해주면 된다.
```bash
docker-compose up -d
```