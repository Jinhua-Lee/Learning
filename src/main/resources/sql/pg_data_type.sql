create table if not exists date_time_test
(
    id           serial,
    my_timestamp timestamp,
    my_date      date
);

insert into date_time_test(my_timestamp, my_date)
values ('2022-06-07 00:00:00', '2022-06-07 00:00:00');