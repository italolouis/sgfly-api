create table sg_receita(
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    valor numeric not null,
    plano_id bigint not null,
    data_cadastro date,
    categoria varchar(120) not null,
    repeticao varchar(20),
    observacao varchar(500),
    data_recebimento date not null,
    recebido varchar(1),
    primary key(id)
);

