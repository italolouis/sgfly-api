create table sg_plano_contas(
    id bigint not null auto_increment,
    cliente_id bigint not null,
    descricao varchar(200) not null,
    padrao char(1) not null,
    status char(20) not null,
    periodicidade varchar(30) not null,
    data_cadastro date not null,
    data_inicio date,
    data_fim date,
    primary key(id),
    constraint fk_client_id_plano_contas FOREIGN KEY (cliente_id) REFERENCES sg_usuario (id)
);
