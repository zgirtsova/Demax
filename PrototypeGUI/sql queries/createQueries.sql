CREATE TABLE IF NOT EXISTS users (
	id SERIAL,
	email VARCHAR(40) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(30),
	role VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS statuses(
	id SERIAL,
	status_type VARCHAR(30),
	PRIMARY KEY (id)
);
 
CREATE TABLE IF NOT EXISTS projects (
	id SERIAL,
	date_created TIMESTAMP NOT NULL,
	project_name VARCHAR(50) NOT NULL,
	company_name VARCHAR(50) NOT NULL,
	project_manager VARCHAR(60) NOT NULL,
	status_id INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (status_id) REFERENCES statuses(id)
);
 
CREATE TABLE IF NOT EXISTS products (
	id SERIAL,
	date_created TIMESTAMP NOT NULL,
	product_name VARCHAR(50) NOT NULL,
	serial_number VARCHAR(4),
	quantity INTEGER NOT NULL,
	description TEXT,
	project_id INTEGER NOT NULL,
	status_id INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (project_id) REFERENCES projects(id) ON DELETE CASCADE,
	FOREIGN KEY (status_id) REFERENCES statuses(id)
);
 
CREATE TABLE IF NOT EXISTS pictures (
	id SERIAL,
	picture BYTEA,
	product_id INTEGER NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);
 
CREATE TABLE IF NOT EXISTS actions (
	id SERIAL NOT NULL,
	occured_on TIMESTAMP NOT NULL,
	action VARCHAR(50),
	user_id INTEGER,
	PRIMARY KEY (id)
);
