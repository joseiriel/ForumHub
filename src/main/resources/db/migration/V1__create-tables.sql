create table usuarios(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,

    primary key (id)
);

create table cursos(
    id bigint not null auto_increment,
    nome varchar(100) not null,
    categoria varchar(100),

    primary key (id)
);

create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem text not null,
    data datetime not null,
    status varchar(100),

    usuario_id bigint not null,
    curso_id bigint not null,

    foreign key (usuario_id) references usuarios(id),
    foreign key (curso_id) references cursos(id),

    primary key (id)
);

create table respostas(
    id bigint not null auto_increment,
    mensagem text not null,
    data datetime not null,
    solucao tinyint not null,

    topico_id bigint not null,
    usuario_id bigint not null,

    foreign key (topico_id) references topicos(id),
    foreign key (usuario_id) references usuarios(id),

    primary key (id)
)