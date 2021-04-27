package com.se.collection;

import org.junit.Test;

import java.util.*;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/4/27 9:26
 */
public class MapTest {

    @Test
    public void testCompute() {
        Map<String, List<Integer>> map1 = new HashMap<>(16);
        map1.put("a", new ArrayList<>(Arrays.asList(1, 2)));
        map1.put("b", new ArrayList<>(Arrays.asList(4, 5)));
        int temp = 7;
        // 向已有a映射值数组中加入记录，若a不存在，新建立一个
        map1.compute("c", (k, v) -> {
           if (v == null) {
               return new ArrayList<>(Collections.singletonList(temp));
           } else {
               v.add(temp);
           }
           return v;
        });
        map1.get("c").forEach(System.out::println);
        // computeIfAbsent
        // computeIfPresent
    }
}
