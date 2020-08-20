# Comic-Web-App
A web app about comic reading

Spring, Maven Project

### Description:
Web app contain 3 different web server:
- 1 server written in Spring with embedded H2 database reponsible for handler HTTP GET, POST,.. request( this repository )
- 2 image server( image server A, image server B) written in Nodejs handler get, upload image, store image
- servers used RabbitMQ as a message broker for comminucation

For testing i have deleted some images in image server B

### RabbitMQ:
- All upload image requests to image server A will be putted in rabbitMQ. When RabbitMQ distribute the image then server B will receive the image  and spring server receive image model to put into embedded h2

### Ref:
- Image server A: https://github.com/MobChar/Main-Image-Server-For-ComicApp
- Image server B: https://github.com/MobChar/Additional-Image-Server-For-Comic-App
- Cloud RabbitMQ: https://mustang.rmq.cloudamqp.com




### Demo:
https://comic-web-app.herokuapp.com/


![GitHub Logo](/comicSample.png)
