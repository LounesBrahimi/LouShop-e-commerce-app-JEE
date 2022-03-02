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

DROP TABLE IF EXISTS products;
CREATE TABLE products (
  product_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(450) NOT NULL,
  category VARCHAR(450) NOT NULL,
  price DOUBLE NOT NULL,
  image VARCHAR(450) NOT NULL,
  PRIMARY KEY (product_id)
) ENGINE = INNODB;

INSERT INTO products VALUES (1,'Boucles lustre or','Boucles',798,'boucles-lustre.jpg'),
							(2,'Collier diamant','Colliers',7999,'collier-diamant.jpg'),
							(3,'Bague mariage diamant','Bague',450,'bague-mariage.jpg'),
							(4,'Boucles en diamant','Boucles',499,'boucles-diamant.jpg'),
							(5,'Bague en diamant','Bague',899,'bague-rare-diamant.jpg'),
							(6,'Collier en perles','Colliers',1999,'collier-perles.jpg'),
							(7,'Champagne of Gold','Champagne',1200,'champagne.jpg'),
							(8,'Plaque 100g of Gold','Gold',1200,'plaque.jpg');							
							