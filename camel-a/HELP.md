# camel-a

-----
docker pull rmohr/activemq
docker volume create acctivemq-vol
docker run --name activemq-8161 -p 61616:61616 -p 8161:8161 -v acctivemq-vol:/opt/activemq rmohr/activemq
http://localhost:8161/admin/
admin
admin


----
docker pull bitnamilegacy/kafka:4.0.0-debian-12-r10