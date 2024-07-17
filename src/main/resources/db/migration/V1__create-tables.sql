create table curso (
    id bigint not null,
    nome varchar(100) not null,
    categoria varchar(100) not null,

    primary key(id)
);

create table perfil (
    id bigint not null,
    nome varchar(100) not null,

    primary key(id)
);

create table usuario (
    id bigint not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null,

    perfil_id bigint not null,
    constraint fk_usuario_perfil_id foreign key(perfil_id) references perfil(id),

    primary key(id)
);

create table topico (
    id bigint not null,
    titulo varchar(100) not null,
    mensagem varchar(255) not null,
    data datetime not null,
    status varchar(100) not null,

    autor_id bigint not null,
    curso_id bigint not null,
    constraint fk_topico_autor_id foreign key(autor_id) references usuario(id),
    constraint fk_topico_curso_id foreign key(curso_id) references curso(id),

    primary key(id)
);

create table resposta (
    id bigint not null,
    mensagem varchar(255) not null,
    data datetime not null,
    solucao boolean not null,

    topico_id bigint not null,
    autor_id bigint not null,
    constraint fk_resposta_topico_id foreign key(topico_id) references topico(id),
    constraint fk_resposta_autor_id foreign key(autor_id) references usuario(id),

    primary key(id)
);
