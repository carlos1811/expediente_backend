


//cliente
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-02', 'carlos1811@gmail.com','carlos','rs');
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-01', 'juan@gmail.com','juan','r1');
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-01', 'pedro@gmail.com','pedro','r2');
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-01', 'marcos@gmail.com','marcos','r3');
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-01', 'javier@gmail.com','javier','r4');
INSERT INTO cliente (create_at,email,nombre,apellido) VALUES ('2020-02-01', 'julian@gmail.com','julian','r5');


INSERT INTO mediador (create_at,email,nombre,apellido) VALUES ('2020-02-07', 'mediador1@gmail.com','mediador1','r5');
INSERT INTO mediador (create_at,email,nombre,apellido) VALUES ('2020-02-07', 'mediador1@gmail.com','mediador2','r5');
INSERT INTO mediador (create_at,email,nombre,apellido) VALUES ('2020-02-07', 'mediador1@gmail.com','mediador3','r5');
INSERT INTO mediador (create_at,email,nombre,apellido) VALUES ('2020-02-07', 'mediador1@hotmail.es','mediador4','r5');



INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('carlos','$2a$10$lWQ2o63av3cA2NFJ/dv/bO40kB7PnjpPlLqEM6flRqXlzQ8gQmY6O',1, 'Carlos', 'R S','carlos1811@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('demo','$2a$10$lWQ2o63av3cA2NFJ/dv/bO40kB7PnjpPlLqEM6flRqXlzQ8gQmY6O',1, 'demo', 'demo','demo@gmail.com');
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email) VALUES ('admin','$2a$10$lWQ2o63av3cA2NFJ/dv/bO40kB7PnjpPlLqEM6flRqXlzQ8gQmY6O',1, 'John', 'lewis','jhon.doe@mail.com');

INSERT INTO `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO `roles` (nombre) VALUES ('ROLE_ADMIN');
INSERT INTO `roles` (nombre) VALUES ('ROLE_USER2');

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 1);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (3, 1);

INSERT INTO `provincia` (provincia) VALUES ('Albacete');
INSERT INTO `provincia` (provincia) VALUES ('Alicante/Alacant');
INSERT INTO `provincia` (provincia) VALUES ('Asturias');
INSERT INTO `provincia` (provincia) VALUES ('Badajoz');
INSERT INTO `provincia` (provincia) VALUES ('Barcelona');
INSERT INTO `provincia` (provincia) VALUES ('Bizkaia');
INSERT INTO `provincia` (provincia) VALUES ('Burgos');
INSERT INTO `provincia` (provincia) VALUES ('Cáceres');
INSERT INTO `provincia` (provincia) VALUES ('Cádiz');
INSERT INTO `provincia` (provincia) VALUES ('Cantabria');
INSERT INTO `provincia` (provincia) VALUES ('Ceuta');
INSERT INTO `provincia` (provincia) VALUES ('Cuenca');
INSERT INTO `provincia` (provincia) VALUES ('Granada');
INSERT INTO `provincia` (provincia) VALUES ('Huelva');
INSERT INTO `provincia` (provincia) VALUES ('Lugo');
INSERT INTO `provincia` (provincia) VALUES ('Madrid');
INSERT INTO `provincia` (provincia) VALUES ('Murcia');
INSERT INTO `provincia` (provincia) VALUES ('Palencia');
INSERT INTO `provincia` (provincia) VALUES ('Segovia');
INSERT INTO `provincia` (provincia) VALUES ('Sevilla');






