/*
 * Copyright (c)    2019/10/8 下午3:31.
 * Author:    Jinhua-Work
 * PathName:    D:/IDEA_Projects/Learning/src/main/java/com/jedis/JavaRedisTest.java
 * LastModified:    2019/10/8 下午3:31
 */

package com.ee.jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.*;

public class JavaRedisTest {
    private static Jedis jedis;

    static {
        jedis = new Jedis("192.168.5.243",26379);
        System.out.println(jedis.ping());
    }

    /**
     * 1. 设置字符串数据
     */
    @Test
    public void testString() {
        System.out.println("______________________");
        System.out.println("1. String类型测试");
        jedis.set("str1", "string");
        System.out.println("获取存储的字符串数据： " + jedis.get("str1"));
    }

    /**
     * 2. 设置List类型数据
     */
    @Test
    public void testList() {
        System.out.println("______________________");
        System.out.println("2. List类型测试");
        jedis.lpush("sites", "google");
        jedis.rpush("sites", "taobao");
        jedis.lpush("sites", "baidu");

        List<String> sites = jedis.lrange("sites", 0, 2);
        for (String s : sites) {
            System.out.println("列表项为：" + s);
        }
    }

    /**
     * 3.key测试
     */
    @Test
    public void testKey() {
        System.out.println("______________________");
        System.out.println("3. key类型测试");
        Set<String> keys = jedis.keys("*");
        System.out.println("key的个数： " + keys.size());
        Iterator<String> it = keys.iterator();
        while (it.hasNext()) {
            String key = it.next();
            System.out.println(key);
        }
    }

    /**
     * 4. Hash 测试
     */
    @Test
    public void testHash() {
        System.out.println("______________________");
        System.out.println("4. Hash类型测试");
        Map<String, String> stu1 = new HashMap<>(2);
        stu1.put("name", "ljh");
        stu1.put("age", "23");

        jedis.hmset("student", stu1);

        List<String> stu = jedis.hmget("student", "name", "age");
        for (String s: stu) {
            System.out.println(s);
        }
    }

    @Test
    public void testRedisInvertedIndex() {
        //创建倒排索引--分类集合
        jedis.zadd("category_服装",8,"1000");
        jedis.zadd("category_服装",9,"1001");
        jedis.zadd("category_家电",7,"1002");

        //创建倒排索引--品牌集合
        jedis.zadd("brand_阿迪达斯",8,"1000");
        jedis.zadd("brand_阿迪达斯",9,"1001");
        jedis.zadd("brand_海尔",7,"1002");

        //创建倒排索引--修改时间集合，只推荐近一周新出的活动页，定期删除老数据，这里只需取时间戳的后6位
        jedis.zadd("update_time",639488,"1000");
        jedis.zadd("update_time",639588,"1001");
        jedis.zadd("update_time",639788,"1002");

        //执行交集查询,对category_服装、brand_阿迪达斯、update_time求交集即可
        jedis.zinterstore("search_result","category_服装","brand_阿迪达斯","update_time");

        Set<Tuple> first = jedis.zrangeWithScores("search_result",0,-1);
        Iterator iterator = first.iterator();
        while (iterator.hasNext()){
            Tuple temp = (Tuple)iterator.next();
            System.out.println("成员："+temp.getElement()+"--"+"分值："+temp.getScore());
        }
    }

    @Test
    public void test() {
            /*connect to redis server*/
            //连接本地的 Redis 服务
            String ip= "127.0.0.1";
            int port= 6379;
            jedis = new Jedis(ip,port);
            jedis.auth("sA123456");
            //设置当前操作的库
            jedis.select(0);
    }
}
