DROP TABLE IF EXISTS book CASCADE;

CREATE TABLE book(
	id BIGINT AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	author VARCHAR(255) NOT NULL,
	published-year INT,
	publisher VARCHAR(255),
	rating INT NOT NULL,
	CHECK (rating>=1 AND rating<=5),
	PRIMARY KEY (id)
);
	