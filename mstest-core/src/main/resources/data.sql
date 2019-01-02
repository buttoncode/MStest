delete from  employee_incident;
delete from  task_books;
delete from  compliance_code_of_ethic;
delete from  compliance_anticorruption_policy;
delete from  waiting_time;

delete from  employees_data;
delete from  employees;
delete from  status_employee;
delete from  status_compliance;

delete from  cost_position_department;
delete from  user_department_permission;

delete from  cost_position;

delete from  departments where tree = 'bottom';
delete from  departments;
delete from  sections;
delete from  companies;

-- Admin
delete from  role_permission;
delete from  user_role;
delete from  permissions;
delete from  roles;
delete from  users;

INSERT INTO employee_incident (id, name, description) VALUES
(1, 'B9', 'Zwolniony'),
(2, 'BA', 'Zwolniony emeryt'),
(3, 'B1', 'Zatrudniony'),
(4, 'B2', 'Transfer pracownika'),
(5, 'B7', 'Zawieszenie pracownika'),
(6, 'B8', 'Powrot z zawieszenia'),
(7, 'BM', 'Przywrocenie pracownika ten sam numer os.')
;

INSERT INTO waiting_time (id, time) VALUES
(1, '3'),
(2, '5'),
(3, '2')
;

INSERT INTO status_employee (id, name) VALUES
(1, 'Aktywny'),
(2, 'Zawieszony'),
(3, 'Św.rehabilitacyjne'),
(4, 'Nieaktywny'),
(5, 'Zwolniony')
;

INSERT INTO status_compliance (id, name) VALUES
(1, 'Aktywny'),
(2, 'Nieaktywny')
;

INSERT INTO companies (id, name, works, enterprise) VALUES
(1, 'MS t.e.s.t', 'X10', '1X0')
;

INSERT INTO sections (id, name, short_name, company_id, tree) VALUES
(1, 'Prezesa', 'P' ,1,'top'),
(2, 'Wiceprezes 1', 'R' ,1,'top'),
(3, 'Wiceprezes 2', 'O' ,1,'top')
;

-- ółą  -- Ą ą Ć ć Ę ę Ł ł Ń ń Ó ó Ś ś Ź ź Ż ż
INSERT INTO departments (id, name, short_name, section_id, department_id, tree) VALUES
(1, 'Biuro 110', 'P1',1,null,'middle'),
(2, 'Biuro 120', 'P2',1,null,'middle'),
(3, 'Biuro 130', 'P3',1,null,'middle'),
(4, 'Biuro 140', 'P4',1,null,'middle'),
(5, 'Biuro 150', 'P5',1,null,'middle'),
(6, 'Biuro 160', 'P6',1,null,'middle'),
(7, 'Biuro 170', 'P7',1,null,'middle'),
(8, 'Biuro 180', 'P8',1,null,'middle'),
(9, 'Biuro 190', 'P9',1,null,'middle'),
(10, 'Dzial 210', 'O1',2,null,'middle'),
(11, 'Dział 220', 'O2',2,null,'middle'),
(12, 'Dział 230', 'O3',2,null,'middle'),
(13, 'Dział 240', 'O4',2,null,'middle'),
(14, 'Dział 310', 'R1',3,null,'middle'),
(15, 'Dział 320', 'R2',3,null,'middle'),
(16, 'Dział 330', 'R3',3,null,'middle'),
(17, 'Dział 340', 'R4',3,null,'middle'),
(18, 'Dział 350', 'R4',3,null,'middle'),
(19, 'Dział 131', 'P31',null,3,'bottom'),
(20, 'Dział 132', 'P32',null,3,'bottom'),
(21, 'Dział 141', 'P41',null,4,'bottom'),
(22, 'Dział 211', 'O11',null,10,'bottom'),
(23, 'Dział 212', 'O12',null,10,'bottom'),
(24, 'Dział 213', 'O13',null,10,'bottom'),
(25, 'Dział 214', 'O14',null,10,'bottom'),
(26, 'Dział 215', 'O15',null,10,'bottom'),
(27, 'Dział 241', 'O41',null,13,'bottom'),
(28, 'Dział 242', 'O42',null,13,'bottom'),
(29, 'Dział 243', 'O43',null,13,'bottom'),
(30, 'Dział 244', 'O44',null,13,'bottom')
;

INSERT INTO cost_position (id, name_mpk) VALUE
(1,'1X001'),
(2,'1X002'),
(3,'1X003'),
(4,'1X004'),
(5,'1X005'),
(6,'1X006'),
(7,'1X007'),
(8,'1X008'),
(9,'1X009'),
(10,'1X010'),
(11,'1X011'),
(12,'1X012'),
(13,'1X013'),
(14,'1X014'),
(15,'1X015'),
(16,'1X016'),
(17,'1X017'),
(18,'1X018'),
(19,'1X019'),
(20,'1X020'),
(21,'1X021'),
(22,'1X022'),
(23,'1X023'),
(24,'1X024'),
(25,'1X025'),
(26,'1X026'),
(27,'1X027'),
(28,'1X028'),
(29,'1X029'),
(30,'1X030'),
(31,'1X031'),
(32,'1X032'),
(33,'1X033'),
(34,'1X034'),
(35,'1X035'),
(36,'1X036'),
(37,'1X037'),
(38,'1X038'),
(39,'1X039'),
(40,'1X040'),
(41,'1X041'),
(42,'1X042'),
(43,'1X043'),
(44,'1X044'),
(45,'1X045'),
(46,'1X046'),
(47,'1X047'),
(48,'1X048'),
(49,'1X049'),
(50,'1X050'),
(51,'1X051'),
(52,'1X052')
;

INSERT INTO employees (id, first_name, last_name, number_sap, status_employee_id,created_on) VALUES
(1, 'Jan1', 'Kowalski1', '12301', 1,now()),
(2, 'Jan2', 'Kowalski2', '12302', 1,now()),
(3, 'Jan3', 'Kowalski3', '12303', 1,now()),
(4, 'Jan4', 'Kowalski4', '12304', 5,now()),
(5, 'Jan5', 'Kowalski5', '12305', 5,now()),
(6, 'Jan6', 'Kowalski6', '12306', 1,now()),
(7, 'Jan7', 'Kowalski7', '12307', 5,now()),
(8, 'Jan8', 'Kowalski8', '12308', 1,now()),
(9, 'Jan9', 'Kowalski9', '12309', 1,now())
;

INSERT INTO employees_data (id, employee_id, work_position, beginning_of_validity, group_designation, department_id, created_on) VALUES
(1, 2, 'Kierownik Biura','2015-12-02', 'Umyslowi',1, now()),
(2, 3, 'Specjalista','2000-12-01', 'Umyslowi',1,now()),
(3, 4, 'Sprzataczka','2000-12-01', 'Umyslowi',1,now()),
(4, 8, 'Sprzataczka','2011-11-11', 'Umyslowi',1,now()),
(5, 5, 'Sprzataczka','2010-10-01', 'Umyslowi',1,now()),
(6, 6, 'Sprzataczka','2010-10-01', 'Umyslowi',1,now()),
(7, 7, 'Sprzataczka','2000-03-24', 'Umyslowi',1,now()),
(8, 8, 'Sprzataczka','2000-03-24', 'Umyslowi',1,now()),
(9, 9, 'Sprzataczka','2000-03-24', 'Umyslowi',1,now()),
(10, 1, 'Technik','2000-12-01', 'Umyslowi',1,now()),
(11, 1, 'Technik','2000-12-01', 'Umyslowi',1,now()),
(12, 1, 'Technik','2000-12-09', 'Umyslowi',2,now()),
(13, 1, 'Sprzataczka','2003-12-01', 'Umyslowi',1,now()),
(14, 1, 'Sprzataczka','2012-12-01', 'Umyslowi',2,now()),
(15, 1, 'Specjalista', '2014-05-19', 'Umyslowi',1, now()),
(16, 1, 'Specjalista', '2015-05-19', 'Umyslowi',2,now()),
(17, 1, 'Specjalista', '2016-05-19', 'Umyslowi',1,now())
;

INSERT INTO task_books (id, employee_id, name_file, task_book_date, created_on) VALUES
(1, 1, '1_12301_TaskBook_12102004.pdf','2000-10-06', now()),
(2, 1, '1_12301_TaskBook_12102001.pdf','2001-10-12', now()),
(3, 1, '1_12301_TaskBook_12102003.pdf','2002-10-12', now()),
(4, 1, '1_12301_TaskBook_12102002.pdf','2003-10-12', now()),
(5, 1, '1_12301_TaskBook_12102004.pdf','2004-10-12', now()),
(6, 1, '1_12301_TaskBook_12102004.pdf','2014-10-12', now()),
(7, 1, '1_12301_TaskBook_12102004.pdf','2015-10-12', now()),
(8, 2, '1_12302_TaskBook_12102003.pdf','2003-10-12', now()),
(9, 2, '1_12302_TaskBook_12102004.pdf','2004-10-12', now())
;

INSERT INTO permissions (id, name) VALUES
(1, 'MANAGE_ACCOUNT'),

(3, 'MANAGE_DEPARTMENT'),

(7, 'MANAGE_EMPLOYEES'),
(8, 'MANAGE_EMPLOYEES_WITHOUT'),
(9, 'MANAGE_EMPLOYEES_ADDEMPLOYEE'),
(10, 'MANAGE_EMPLOYEES_EPLOADEMPLOYEE'),
(11, 'MANAGE_EMPLOYEES_TASKBOOK'),
(12, 'MANAGE_EMPLOYEES_EDITEMPLOYEE'),

(13, 'MANAGE_COMPLIANCES'),
(14, 'MANAGE_COMPLIANCES_CODE_OF_ETHIC'),
(15, 'MANAGE_COMPLIANCES_ANTICORRUPTION_POLICY'),

(16, 'MANAGE_USERS'),
(17, 'MANAGE_ROLES'),
(18, 'MANAGE_PERMISSIONS')
;

INSERT INTO roles (id, name) VALUES 
(1, 'ROLE_SUPER_ADMIN'),
(2, 'ROLE_KIEROWNIK'),
(3, 'ROLE_PLANISTKA'),
(4, 'ROLE_PRACOWNIK'),
(5, 'ROLE_STREAM')
;

INSERT INTO users (id, email, password, first_name, last_name) VALUES
(1, 'stefan.krupa@gmail.pl', '$2a$10$p3PHnpoBAnzZiL8mr3gMieMhVVSb585ajC2ZsBB0kwb4KvZKFSdNi', 'Stefan','Krupa'),
(2, 'grazyna.kowalska@gmail.pl', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Grazyna','Kowalska'),
(3, 'jan.kowalski@gmail.pl', '$2a$10$ByIUiNaRfBKSV6urZoBBxe4UbJ/sS6u1ZaPORHF9AtNWAuVPVz1by', 'Jan','Kowalski')
;

INSERT INTO compliance_code_of_ethic (id, date_of_training, waiting_time_id, expiry_date_of_training, employee_id, status_compliance_id, file_name, created_on) VALUES
(1,'2018-01-12',1 ,'2021-01-12', 1,1, 'CeE',now()),
(2,'2013-04-22',1 ,'2018-04-22', 2,1, 'CeE',now()),
(3,'2014-12-12',2 ,'2017-12-12', 1,2, 'CeE',now()),
(4,'2014-11-12',1 ,'2019-11-12', 2,1, 'CeE',now()),
(5,'2013-03-12',1 ,'2018-03-12', 3,1, 'CeE',now()),
(6,'2013-04-12',1 ,'2018-04-12', 5,1, 'CeE',now()),
(7,'2012-11-12',1 ,'2017-11-12', 6,1, 'CeE',now()),
(8,'2013-03-12',1 ,'2018-03-12', 4,1, 'CeE',now()),
(9,'2013-03-12',1 ,'2030-03-12', 1,1, 'CeE',now()),
(10,'2013-03-12',1 ,'2018-03-12', 8,2, 'CeE',now()),
(11,'2013-03-12',1 ,'2018-03-12', 1,1, 'CeE',now())
;

INSERT INTO compliance_anticorruption_policy (id, date_of_training, waiting_time_id, expiry_date_of_training, employee_id, status_compliance_id, file_name, created_on) VALUES
(1,'2018-01-12',1 ,'2021-01-12', 1,1, 'AP',now()),
(2,'2013-04-22',1 ,'2018-04-22', 2,1, 'AP',now()),
(3,'2014-12-12',2 ,'2017-12-12', 1,2, 'AP',now()),
(4,'2014-11-12',1 ,'2019-11-12', 2,1, 'AP',now()),
(5,'2013-03-12',1 ,'2018-03-12', 3,1, 'AP',now()),
(6,'2013-04-12',1 ,'2018-04-12', 5,1, 'AP',now()),
(7,'2012-11-12',1 ,'2017-11-12', 6,1, 'AP',now()),
(8,'2013-03-12',1 ,'2018-03-12', 4,1, 'AP',now()),
(9,'2013-03-12',1 ,'2030-03-12', 1,1, 'AP',now()),
(10,'2013-03-12',1 ,'2018-03-12', 8,2, 'AP',now()),
(11,'2013-03-12',1 ,'2018-03-12', 1,1, 'AP',now())
;

insert into role_permission(role_id, perm_id) values
(1,1),(1,3),
(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),
(1,16),(1,17),(1,18)
;

insert into user_role(user_id, role_id) values
(1,1),(2,2),(3,4)
;

INSERT INTO user_department_permission (user_id, department_id) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),
(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26),(1,27),(1,28),(1,29),(1,30)
;

INSERT INTO cost_position_department (cost_position_id, department_id) VALUES
(3,1),(4,8),(5,3),(5,21),(6,4),(7,14),(8,2),(8,19),(8,20),(9,6),(10,5),(11,7),(12,9),(13,22),(15,22),(16,22),(14,23),(17,23),(22,22),(23,26),(19,25),(18,24),(20,24),
(21,24),(24,11),(25,11),(26,11),(27,11),(28,12),(29,12),(30,12),(31,27),(32,28),(33,27),(34,30),(35,29),(36,30),(37,30),(38,15),(39,16),(40,17),(41,18),(42,10),(43,13)
;
