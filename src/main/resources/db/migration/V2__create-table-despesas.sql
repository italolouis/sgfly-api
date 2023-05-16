create table sg_despesa(
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    valor numeric not null,
    plano_id bigint not null,
    data_cadastro date,
    categoria varchar(120) not null,
    repeticao varchar(20),
    observacao varchar(500),
    data_vencimento date not null,
    pago varchar(1),
    primary key(id)
);

