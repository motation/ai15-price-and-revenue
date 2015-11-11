FROM ubuntu
RUN apt-get update
RUN apt-get -y install mysql-server
RUN sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf
RUN /usr/sbin/mysqld & sleep 10s && echo "GRANT ALL ON *.* TO admin@'%' IDENTIFIED BY 'root' WITH GRANT OPTION; FLUSH PRIVILEGES; CREATE DATABASE pricerevenue" | mysql

EXPOSE 3306
CMD ["mysqld"]
