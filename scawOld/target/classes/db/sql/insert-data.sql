INSERT INTO person (FIRSTNAME, LASTNAME, EMAIL) VALUES ('Cintia', 'Gioia', 'cgioia@unlam.edu.ar'); 
INSERT INTO person (FIRSTNAME, LASTNAME, EMAIL) VALUES ('Walter', 'Ureta', 'wureta@unlam.edu.ar');
INSERT INTO person (FIRSTNAME, LASTNAME, EMAIL) VALUES ('Juan', 'Monteagudo', 'jmonteagudo@unlam.edu.ar');

INSERT INTO usuario(NOMBRE,APELLIDO,CONTRASENA,TIPO,ESTAAPROBADO,NICKNAME) VALUES  ('admin','molaro','12345678','administrador',1,'admin');
INSERT INTO usuario(NOMBRE,APELLIDO,CONTRASENA,TIPO,ESTAAPROBADO,NICKNAME) VALUES  ('normal','normal','12345678','normal',1,'normal');
INSERT INTO usuario(NOMBRE,APELLIDO,CONTRASENA,TIPO,ESTAAPROBADO,NICKNAME) VALUES  ('normal2','normal2','12345678','normal',1,'normal2');

INSERT INTO tarea(NOMBRE,DESCRIPCION,ACCESO,ESTADO,CREADOR) VALUES ('Limpieza','barrer la cocina','privada','pendiente',1);
INSERT INTO tarea(NOMBRE,DESCRIPCION,ACCESO,ESTADO,CREADOR) VALUES ('tareaNoPendiente','barrer la cocina','privada','completa',1);
INSERT INTO tarea(NOMBRE,DESCRIPCION,ACCESO,ESTADO,CREADOR) VALUES ('Tarea 2 publica','limpiar el baño','publica','pendiente',1);

INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('escritura','1','2');
INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','2','2');
INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','2','3');
INSERT INTO accedetarea(MODO,IDTAREA,IDUSUARIO) VALUES ('lectura','3','2');



--COMMIT;

