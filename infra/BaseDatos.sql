CREATE DATABASE IF NOT EXISTS bank;
USE bank;


CREATE TABLE person (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) DEFAULT NULL,
  gender VARCHAR(255) DEFAULT NULL,
  age INT(11) DEFAULT NULL,
  identification VARCHAR(255) NOT NULL,
  address VARCHAR(255) DEFAULT NULL,
  phone VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_identification (identification)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE client (
  id BIGINT(20) NOT NULL,
  client_id VARCHAR(255) NOT NULL,
  password VARCHAR(255) DEFAULT NULL,
  status BIT(1) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY UK_client_id (client_id),
  CONSTRAINT FK_client_person FOREIGN KEY (id) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE account (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  account_number VARCHAR(255) DEFAULT NULL,
  account_type VARCHAR(255) DEFAULT NULL,
  client_id BIGINT(20) DEFAULT NULL,
  initial_amount DECIMAL(38,2) DEFAULT NULL,
  status BIT(1) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_account_client FOREIGN KEY (client_id) REFERENCES client(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE transaction (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  transaction_date DATETIME DEFAULT NULL,
  transaction_type VARCHAR(255) DEFAULT NULL,
  transaction_amount DECIMAL(38,2) DEFAULT NULL,
  result_amount DECIMAL(38,2) DEFAULT NULL,
  account_id BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT FK_transaction_account FOREIGN KEY (account_id) REFERENCES account(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person (name, gender, age, identification, address, phone)
VALUES 
('Juan Perez', 'M', 30, '1234567890', 'Calle 1', '5551111'),
('Maria Lopez', 'F', 25, '0987654321', 'Calle 2', '5552222');

INSERT INTO client (id, client_id, password, status)
VALUES
(1, UUID(), 'pass123', b'1'),
(2, UUID(), 'pass456', b'1');

INSERT INTO account (account_number, account_type, client_id, initial_amount, status)
VALUES
('ACC001', 'SAVINGS', 1, 1000.00, b'1'),
('ACC002', 'CHECKING', 1, 2000.00, b'1'),
('ACC003', 'SAVINGS', 2, 1500.00, b'1');

INSERT INTO transaction (transaction_date, transaction_type, transaction_amount, result_amount, account_id)
VALUES
('2025-09-01 10:00:00', 'DEPOSIT', 500.00, 1500.00, 1),
('2025-09-02 11:30:00', 'WITHDRAW', 200.00, 1300.00, 1),
('2025-09-03 09:15:00', 'DEPOSIT', 300.00, 2300.00, 2),
('2025-09-04 14:45:00', 'WITHDRAW', 100.00, 2200.00, 2),
('2025-09-05 08:00:00', 'DEPOSIT', 200.00, 1700.00, 3);
