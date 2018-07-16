## Run Postgres database
```
docker run -d -p 5432:5432 -e POSTGRES_DB=sample -e POSTGRES_USER=user -e POSTGRES_PASSWORD=asd --name sample postgres
```