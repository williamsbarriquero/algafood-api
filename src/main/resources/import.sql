insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Thai Gourmet', 10, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Thai Delivery', 9.50, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Tuk Tuk Comida Indiana', 15, 2);

insert into estado (id, nome) values (1, 'Minas Gerais');
insert into estado (id, nome) values (2, 'S?o Paulo');
insert into estado (id, nome) values (3, 'Cear?');

insert into cidade (id, nome, estado_id) values (1, 'Uberl?ndia', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 1);
insert into cidade (id, nome, estado_id) values (3, 'S?o Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Fortaleza', 3);

insert into forma_pagamento (id, descricao) values (1, 'Cart?o de cr?dito');
insert into forma_pagamento (id, descricao) values (2, 'Cart?o de d?bito');
insert into forma_pagamento (id, descricao) values (3, 'Dinheiro');

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');