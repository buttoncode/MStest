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

INSERT INTO permissions (id, name) VALUES
(1, 'MANAGE_ACCOUNT'),

(3, 'MANAGE_DEPARTMENT'),

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

insert into role_permission(role_id, perm_id) values
(1,1),(1,3),(1,16),(1,17),(1,18)
;

insert into user_role(user_id, role_id) values
(1,1),(2,2),(3,4)
;

