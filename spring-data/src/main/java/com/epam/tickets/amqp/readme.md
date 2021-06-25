docker run -d --name some-rabbit -p 5672:5672 -p 5673:5673 -p 15672:15672 rabbitmq:3-management

port 5672 is used for the RabbitMQ client connections
port 15672 is for the RabbitMQ management website
http://localhost:15672 [guest:guest]
