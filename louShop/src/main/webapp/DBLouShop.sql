CREATE DATABASE loushop DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS users;
CREATE TABLE users (
  user_id INT NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  email varchar(250) NOT NULL,
  password varchar(250) NOT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY email_UNIQUE (email)
) ENGINE = INNODB;
INSERT INTO users VALUES (1,'Lounes Brahimi','Lounes@gmail.com','0000');

DROP TABLE IF EXISTS orders;
CREATE TABLE orders (
  order_id INT NOT NULL AUTO_INCREMENT,
  product_id INT NOT NULL,
  user_id INT NOT NULL,
  order_quantity INT NOT NULL,
  order_date varchar(450) NOT NULL,
  PRIMARY KEY (order_id)
) ENGINE = INNODB;
INSERT INTO orders VALUES (1,1,1,2,'2022-02-28'),(2,1,1,1,'2021-02-28');

DROP TABLE IF EXISTS products;
CREATE TABLE products (
  product_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(450) NOT NULL,
  category VARCHAR(450) NOT NULL,
  price DOUBLE NOT NULL,
  image VARCHAR(450) NOT NULL,
  PRIMARY KEY (product_id)
) ENGINE = INNODB;

INSERT INTO products VALUES (1,'Bague Lucent','Bagues',195,'bague-lucent.jpg'),(2,'Collier Somnia','Colliers',1000,'collier-somnia.jpg');