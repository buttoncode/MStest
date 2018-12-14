-- Admin
delete from  role_permission;
delete from  user_role;
delete from  permissions;
delete from  roles;
delete from  users;


INSERT INTO permissions (id, name) VALUES
(1, 'MANAGE_ACCOUNT'),

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
(1,1),(1,16),(1,17),(1,18)
;

insert into user_role(user_id, role_id) values
(1,1),(2,2),(3,4)
;

