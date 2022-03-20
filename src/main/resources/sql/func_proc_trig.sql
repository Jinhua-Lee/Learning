-- 1. 创建存储过程
create procedure select_student_innodb_limit_10()
begin
    select *
    from student_innodb
    limit 10;
end;

-- 2. 存储过程调用_call
call select_student_innodb_limit_10();

-- 3. 创建函数
-- 加密函数封装

delimiter $
create function my_aes_base64_enc(src text) returns text
    deterministic
    contains sql
    reads sql data
begin
    declare ivBase64 text default 'GTRXcpCrze8SZBR4kKyuRQ==';
    declare my_key text default '8914534290ABCDEF1264147890ACAB55';
    declare enc_method text default 'aes-cbc/pad:pkcs';
    declare aes text;
    declare iv text;

    set iv = decode(ivBase64, 'base64');
    set aes = asymmetric_encrypt(enc_method, src, my_key);
    return encode(aes, 'base64');
end $;
delimiter ;

-- 4. 调用函数
select paged_max_age(0, 10);

-- 5. 非空字段测试表，测试异常处理程序
create table if not exists nil_exception_test
(
    id             serial primary key auto_increment comment '主键ID',
    non_null_field varchar(50) not null
);

insert into nil_exception_test(non_null_field)
values ('abc');

delimiter $
create procedure update_null_exception()
begin
    set @x = 1;
-- 程序出错，不会再继续执行
    update nil_exception_test set non_null_field = null where id = 1;
    set @x = 2;
    update nil_exception_test set non_null_field = 'ljh' where id = 1;
    set @x = 3;
end $;
delimiter ;
call update_null_exception();
select @x;


delimiter $
create procedure update_null_exception_continue()
begin
    -- 方式一：使用MYSQL_ERROR_CODE
    declare continue handler for 1048 set @prc_value = -1;
    -- 方式二：使用sql_state_value
-- declare continue handler for sqlstate '23000' set @prc_value = -1;

    set @x = 1;
-- 程序出错，不会再继续执行
    update nil_exception_test set non_null_field = null where id = 1;
    set @x = 2;
    update nil_exception_test set non_null_field = 'ljh' where id = 1;
    set @x = 3;
end $;
delimiter ;

call update_null_exception_continue();
select @x;

-- 6. 触发器

create table if not exists trigger_test
(
    id     serial primary key auto_increment comment '主键ID',
    t_note varchar(50)
);

create table if not exists trigger_log_test
(
    id    serial primary key auto_increment comment '主键ID',
    t_log varchar(50)
);

-- 触发器作用：插入表a，同时向表b写日志
delimiter $
create trigger before_insert_trigger_test_tri
    before insert
    on trigger_test
    for each row
begin
    insert into trigger_log_test(t_log) values ('before insert...');
end $
delimiter ;

-- 模拟插入
insert into trigger_test(t_note)
values ('天动万象');