insert into Jogador (id, nome, email) values (-1, 'Rogerio Ceni', 'rogerio.ceni@algaworks.com'), (-2, 'Cristiano Ronaldo', 'cristiano.ronaldo@algaworks.com'), (-3, 'Messi', 'messi@algaworks.com'), (-4, 'Alexandre Afonso', 'alexandre.afonso@algaworks.com'), (-5, 'Neymar Junior', 'neymar.junior@algaworks.com');
INSERT INTO usuario (id, nome, email, login, senha) VALUES (1,'Administrador','admin@mail.com','admin', '$2a$10$0yEvoqhIbxgkzMo7t9l35.nwGS53pM2zk1TLYD2MQkU7acTHx3K/.'), (2,'Tacito','tacito@mail.com','tacito', '$2a$10$0yEvoqhIbxgkzMo7t9l35.nwGS53pM2zk1TLYD2MQkU7acTHx3K/.');
INSERT INTO grupo (id, nome, descricao) VALUES (1, 'ADMINISTRADORES', ''), (2, 'USUARIOS', ''); 
INSERT INTO usuario_grupo (usuario_id, grupo_id) VALUES (1, 1), (2, 2);