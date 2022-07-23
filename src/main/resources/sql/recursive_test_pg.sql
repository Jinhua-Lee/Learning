create table recursive_test
(
    id   int,
    pid  int,
    name varchar(10)
);

insert into recursive_test
values (2, 0, 'a');
insert into recursive_test
values (1, 0, 'b');
insert into recursive_test
values (3, 2, 'c');
insert into recursive_test
values (4, 2, 'd');
insert into recursive_test
values (5, 2, 'e');
insert into recursive_test
values (6, 2, 'f');
insert into recursive_test
values (7, 3, 'g');
insert into recursive_test
values (8, 3, 'h');
insert into recursive_test
values (9, 4, 'i');
insert into recursive_test
values (10, 5, 'j');
insert into recursive_test
values (11, 7, 'k');
insert into recursive_test
values (12, 2, 'l');
insert into recursive_test
values (13, 9, 'm');
insert into recursive_test
values (14, 9, 'n');
insert into recursive_test
values (15, 4, 'o');

-- 查询结点及其子孙，查询结果展示方式是广度优先
with recursive t as (
    select a.id, a.name, a.pid
    from recursive_test a
    where a.id = 2

    union all

    select k.id, k.name, k.pid
    from recursive_test k,
         t c
    where c.id = k.pid
)
select id, name, pid
from t;