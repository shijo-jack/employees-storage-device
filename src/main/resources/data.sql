DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS employee;
 
CREATE TABLE company (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE employee (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  surname VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  address VARCHAR(250) NOT NULL,
  salary NUMBER NOT NULL,
  company_id INT NOT NULL,
  foreign key (company_id) references company(id)
);

 
INSERT INTO company (name) VALUES
  ('Cognizant'),
  ('Tata Consultancy Services'),
  ('Infosys'),
  ('Wipro');

INSERT INTO employee (name, surname, email, address, salary, company_id) VALUES
   ('Albert','Aron','albert@tcs.com','tcs chennai', 50000, (select id from company where name='Tata Consultancy Services')),
   ('Shijo','Chacko','shijo@cts.com','cts chennai', 50000, (select id from company where name='Cognizant')),
   ('Krithiga','Santhosh','Krithiga@cts.com','cts chennai', 70000, (select id from company where name='Cognizant')),
   ('Venkat','Raj','Venkat@cts.com','cts chennai', 60000, (select id from company where name='Cognizant')),
   ('Renga','Krishna','Renga@infosys.com','infosys chennai', 25000, (select id from company where name='Infosys')),
   ('Mahesh','sasi','Mahesh@infosys.com','infosys chennai', 45000, (select id from company where name='Infosys')),
   ('Peer','Vignesh','Peer@infosys.com','infosys chennai', 45000, (select id from company where name='Infosys')),
   ('Karthick','Balaji','Karthick@wipro.com','wipro chennai', 45000, (select id from company where name='Wipro')),
   ('Subbu','Ratnam','Subbu@wipro.com','wipro chennai', 45000, (select id from company where name='Wipro')),
   ('Devi','Selvam','Devi@wipro.com','wipro chennai', 45000, (select id from company where name='Wipro')),
   ('Arjun','Rajini','Arjun@wipro.com','wipro chennai', 45000, (select id from company where name='Wipro'));



