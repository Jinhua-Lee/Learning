create table if not exists datatype
(
    id      serial primary key auto_increment comment '主键ID',
    bit_val bit comment 'bit类型',

);

create table if not exists int_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 int,
    f2 int(5),
    f3 int(5)
);