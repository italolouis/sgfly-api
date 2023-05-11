create table sg_contas_pagar(
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    plano_id bigint not null,
    data_lancamento date,
    categoria varchar(120) not null,
    observacao varchar(500),
    data_vencimento date not null,
    pago varchar(1),
    primary key(id)
);

