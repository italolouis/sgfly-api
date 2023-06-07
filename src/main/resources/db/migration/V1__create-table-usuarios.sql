create table sg_usuario(
    id bigint not null auto_increment,
    cpf_cnpj varchar(20) not null,
    nome varchar(200) not null,
    status char(20) not null,
    login varchar(120) not null,
    senha varchar(100) not null,
    data_cadastro date,
    primary key(id)
);

