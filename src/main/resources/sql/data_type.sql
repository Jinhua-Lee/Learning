-- 1. int类型测试
create table if not exists int_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 int,
    f2 int(5),
    f3 int(5)
);

-- 显示宽度
insert into int_test(f1, f2)
values(123, 123456);

insert into int_test(f2, f3)
values(123, 12);

-- 2. 浮点类型float和double测试
create table if not exists double_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 double,
    f2 double(5, 2)
);

-- 四舍
insert into double_test(f1, f2)
values(5.5, 999.994);

-- 五入，超出位数，失败
insert into double_test(f1, f2)
values(5.5, 999.995);

-- 3. 定点类型decimal测试
create table if not exists decimal_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 decimal,
    f2 decimal(5, 2)
);

-- 默认(标度, 精度) = (10, 0)
desc decimal_test;

insert into decimal_test(f1)
values (123), (123.4), (123.5);

-- 五入
insert into decimal_test(f2)
values (67.567);

-- 超出位数，失败
insert into decimal_test(f2)
values (1267.567);

-- 五入，超出位数，失败
insert into decimal_test(f2)
values (999.995);

-- 4. 位类型bit测试
create table if not exists bit_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 bit,
    f2 bit(5),
    f3 bit(64)
);

-- 默认1bit
insert into bit_test(f1)
values (0), (1);

select * from bit_test;

insert into bit_test(f2)
values (31);

-- 超出位数，报错
insert into bit_test(f2)
values (32);

-- 5. 时间类型测试
create table if not exists date_time_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 datetime,
    f2 timestamp
);

insert into date_time_test(f1, f2)
values ('2022-03-20 00:00:00', '2022-03-20 00:00:00');

-- value = SYSTEM
show variables like 'time_zone%';

-- 会话中临时修改时区+9
set time_zone = '+09:00';

select * from date_time_test;

-- 6. 字符类型测试_char和varchar
create table if not exists character_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 char(4),
    f2 varchar(4)
);

insert into character_test(f1)
values ('你好你好');

-- 超出4个字符，插入失败
insert into character_test(f1)
values ('你好你好昂');

-- 超出4个字符，插入失败
insert into character_test(f1)
values ('abcde');

-- 超出4个字符，插入失败
insert into character_test(f2)
values ('你好你好昂');

-- 超出4个字符，插入失败
insert into character_test(f2)
values ('abcde');

-- 长度，char不包含空格，varchar包含空格
insert into character_test(f1, f2)
values ('ab ', 'ab ');
select char_length(f1), char_length(f2)
from character_test;

-- 7. 文本text类型
create table if not exists text_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 text(4)
);

-- 长度加了也没用
insert into text_test(f1)
values ('abcde');

-- 字符长度，包含空格
insert into text_test(f1)
values ('ab     ');
select char_length(f1)
from text_test;

-- 8. json类型
create table json_test
(
    id serial primary key auto_increment comment '主键ID',
    f1 json
);

-- 存储时格式校验
insert into json_test(f1)
values ('{ "name": "ljh", "age" : 26}');

-- 通过json表达式，获取json内部信息
select f1 -> '$.name' as name
from json_test;