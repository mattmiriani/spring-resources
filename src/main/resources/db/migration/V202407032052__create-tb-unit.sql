create table if not exists tb_unit (
    id uuid not null
    constraint "unit_pkey" primary key,
    name varchar(50) not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    active boolean not null,
    network_id uuid not null
    constraint "unit_network_id_fkey"
    references tb_network(id)
);