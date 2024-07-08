create table if not exists tb_network (
    id uuid not null
    constraint "network_pkey" primary key,
    name varchar(50) not null unique,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    expired_at timestamp with time zone,
    active boolean not null,
    user_id serial,
    foreign key (user_id) references tb_users(id_user)
);