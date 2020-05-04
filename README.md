Executando o dump do mysql

```
 mysqldump -u root -p curso_spring > arquivo.sql
``` 

Adicionando ao heroku

```
heroku git:remote -a springboot-ionic-learning
```

Obtendo a url de conexao com banco de dados 

```
heroku config | grep CLEARDB_DATABASE_URL
```