insert into statuses (status_type)
values
('New'),
('In Progress'),
('Finished');

insert into projects (date_created, project_name, company_name, project_manager, status_id)
values 
('2018-11-13 11:10:25-07', 'First Project', 'Demax', 'Ivan', '1'),
('2018-11-13 19:10:25-07', 'Second Project', 'Demax', 'Alex', '2'),
('2018-10-22 10:10:10-07', 'Third Project', 'Demax', 'Kamen', '3'),
('2018-11-13 11:10:25-07', 'Fourth Project', 'Demax', 'Ivan', '3'),
('2018-11-13 19:10:25-07', 'Fifth Project', 'Demax', 'Alex', '2'),
('2018-10-22 10:10:10-07', '6th Project', 'Demax', 'Kamen', '3');

insert into products (date_created, product_name, serial_number, quantity, description, project_id, status_id)
values
('2018-11-13 11:10:25-07', 'Product 1', 'A002', '2', 'This is product 1', '1', '2'),
('2018-11-13 19:10:25-07', 'Product 2', '001W', '6', 'This is product 2', '2', '2'),
('2018-10-22 10:10:10-07', 'Product 3', '5324', '4', 'This is product 3', '3', '3'),
('2018-11-13 11:10:25-07', 'Product 4', 'A002', '2', 'This is product 4', '1', '1'),
('2018-11-13 19:10:25-07', 'Product 5', '001W', '6', 'This is product 5', '2', '2'),
('2018-10-22 10:10:10-07', 'Product 6', '5324', '4', 'This is product 6', '3', '3');

insert into pictures (product_id)
values
('1'),
('2'),
('3');

insert into actions (occured_on, action, user_id)
values
('2018-11-13 11:10:25-07', 'created new Project', '1'),
('2018-11-13 19:10:25-07', 'created new Product', '2'),
('2018-10-22 10:10:10-07', 'logged in', '2');

insert into users (email, password, first_name, last_name, role)
values
('ivan@demax.bg', '123', 'Ivan', 'Dedinski', 'Manager'),
('kamen@demax.bg', '456', 'Kamen', 'Nestorov', 'Owner');