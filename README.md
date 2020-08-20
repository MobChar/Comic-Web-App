# Comic-Web-App
A web app about comic reading

Spring, Maven Project

### Description:
Web app contain 3 different web server:
- 1 server written in Spring with embedded H2 data base reponsible for handler HTTP GET, POST,.. request( this repository )
- 2 image server written in Nodejs handler get, upload image, store image
- servers used RabbitMQ as a message broker for comminucation

### Ref:
- Image server A: https://github.com/MobChar/Main-Image-Server-For-ComicApp
- Image server B: https://github.com/MobChar/Additional-Image-Server-For-Comic-App
- Cloud RabbitMQ: https://mustang.rmq.cloudamqp.com


### Demo:
https://comic-web-app.herokuapp.com/
