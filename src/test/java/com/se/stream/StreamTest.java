package com.se.stream;

import lombok.SneakyThrows;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流测试
 *
 * @author Jinhua
 */
public class StreamTest {

    /**
     * 测试长单词统计
     */
    @Test
    @SneakyThrows
    public void testCountLongWords() {
        String dir = "E:/IOTest/";
        String filename = "chars.txt";
        String contents = new String(Files.readAllBytes(Paths.get(dir + filename)), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for (String s : words) {
            if (s.length() > 4) {
                count++;
            }
        }
        System.out.println(count);

        count = words.stream().filter(s -> s.length() > 4).count();
        System.out.println(count);

        count = words.parallelStream().filter(s -> s.length() > 4).count();
        System.out.println(count);
    }

    /**
     * 测试嵌套对象从内层属性映射到外层本身，测试成功
     */
    @Test
    public void testNestedObjStream() {
        List<Toy> toys = Arrays.asList(
                new Toy("迪迦"), new Toy("戴拿"),
                new Toy("盖亚"), new Toy("阿古茹"),
                new Toy("赛罗"), new Toy("梦比优斯"),
                new Toy("希卡利"), new Toy("高斯"));
        List<Child> children = Arrays.asList(
                new Child("小明", new ArrayList<>(toys.subList(0, 2))
                ),
                new Child("小强", new ArrayList<>(toys.subList(2, 4))
                ),
                new Child("小红", new ArrayList<>(toys.subList(4, 6))
                ),
                new Child("小兰", new ArrayList<>(toys.subList(6, 8))
                )
        );
        List<Father> fathers = Arrays.asList(
                new Father("大明", new ArrayList<>(children.subList(0, 2))
                ),
                new Father("二明", new ArrayList<>(children.subList(2, 4))
                )
        );
        // 尝试构造 toy.name -> Father 的映射
        Map<String, List<Father>> fatherNameMapFather = fathers.stream().collect(Collectors.groupingBy(Father::getName));

        Map<String, Child> childNameMapChild =
                fathers.stream().flatMap(father -> father.getChildren().stream())
                        .collect(Collectors.toMap(Child::getName, Function.identity()));
        Map<String, Toy> toyNameMapToy =
                childNameMapChild.values().stream().flatMap(child -> child.getToys().stream())
                        .collect(Collectors.toMap(Toy::getName, Function.identity()));
        final int mapInitSize = 16;
        Map<String, Father> toyNameMapFather = new HashMap<>(mapInitSize);
        toyNameMapToy.forEach((toyName, toy) -> {
            childNameMapChild.forEach((childName, child) -> {
                if (child.getToys().contains(toy)) {
                    fatherNameMapFather.forEach((fatherName, fatherList) -> {
                        Father anyFather = fatherList.stream().filter(Objects::nonNull).findAny().orElse(null);
                        if (Objects.nonNull(anyFather) && anyFather.getChildren().contains(child)) {
                            toyNameMapFather.put(toyName, anyFather);
                        }
                    });
                }
            });
        });
        toyNameMapFather.forEach((tName, father) -> {
            System.out.println(tName + " -> " + father);
        });
    }

    @Test
    public void testStream() {
        List<String> words = new ArrayList<>();
        words.add("your");
        words.add("name");

        Stream<Stream<Character>> result = words.stream().map(StreamTest::characterStream);
        Stream<Character> letters = words.stream().flatMap(StreamTest::characterStream);
    }

    public static Stream<Character> characterStream(String s) {
        List<Character> result = new ArrayList<>();
        for (char c : s.toCharArray()) {
            result.add(c);
        }
        return result.stream();
    }

    @Test
    public void testPeek() {
        List<Child> children = new ArrayList<>();;
        children.add(new Child(" hello ", null));
        children.add(new Child(" world   ", null));
        List<Child> childList = children.stream().peek(
                child -> child.setName(child.getName().trim())
        ).collect(Collectors.toList());
        childList.forEach(System.out::println);
    }
}
