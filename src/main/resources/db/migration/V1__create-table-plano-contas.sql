create table sg_plano_contas(
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    padrao char(1) not null,
    status char(20) not null,
    periodicidade varchar(30) not null,
    data_inicio date,
    data_fim date,
    primary key(id)
);
