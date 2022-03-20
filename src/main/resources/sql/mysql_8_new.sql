# 窗口函数

-- 1. 地区销量表
create table if not exists sales
(
    id          serial primary key auto_increment comment '主键ID',
    city        varchar(15),
    county      varchar(15),
    sales_value decimal
);

-- 2. 插入数据
insert into sales(city, county, sales_value)
values ('北京', '海淀', 10.00),
       ('北京', '朝阳', 20.00),
       ('上海', '黄埔', 30.00),
       ('上海', '长宁', 10.00);

-- 需求：每个城市的销售总额(分组求和)、每个区的销售额占所在城市销售额的比率

select city as 城市,county as 区,
       sales_value as 区销售额,

       sum(sales_value) over (partition by city) as 市销售额,
       sales_value / sum(sales_value) over (partition by city) as 市销售比率
from sales
order by city, county;
