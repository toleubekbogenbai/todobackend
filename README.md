# todo backend



### database: Postgres
#### scripts:
- create table task
(
    id bigint
        constraint task_pk
            primary key,
    title varchar(100),
    completed int,
    date date,
    priority_id bigint,
    category_id bigint
);


- create table category
(
    id bigint
        constraint category_pk
            primary key,
    title varchar(45),
    completed_count bigint,
    uncompleted_count bigint
);

- create table priority
(
    id bigint
        constraint priority_pk
            primary key,
    title varchar(45),
    color varchar(45)
);



- alter table task
    add constraint task_priority_id_fk
        foreign key (priority_id) references priority;

- alter table task
    add constraint task_category_id_fk
        foreign key (category_id) references category;
