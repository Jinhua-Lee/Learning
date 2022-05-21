package cn.mythread.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 【原子操作】单元测试
 *
 * @author Jinhua-Lee
 * @version 1.0
 * @date 2022/5/21 11:37
 */
@Slf4j
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
public class AtomicOperationsTest {

    @Test
    @DisplayName(value = "测试原子字段更新器")
    public void testAtomicFieldUpdater() {
        // 原子字段更新器可以先独立存在
        AtomicIntegerFieldUpdater<Person> personCountUpdater = AtomicIntegerFieldUpdater
                .newUpdater(Person.class, "count");

        Person person = new Person("ljh", 26, 1);

        int countAdded = personCountUpdater.incrementAndGet(person);
        Assertions.assertEquals(2, countAdded, "count值不相等");
    }

    @Test
    @DisplayName(value = "测试原子引用")
    public void testAtomicReference() {
        Person person = new Person("ljh", 26, 1);
        Person updatePerson = new Person("lwk", 23, 1);
        AtomicReference<Person> personAtomic = new AtomicReference<>(person);

        boolean set = personAtomic.compareAndSet(person, updatePerson);

        // 预期设置成功
        Assertions.assertTrue(set);

        // 预期是新的对象
        Assertions.assertEquals("lwk", personAtomic.get().name);
        Assertions.assertEquals(23, personAtomic.get().age);
    }

    @Data
    @AllArgsConstructor
    @EqualsAndHashCode
    private static class Person {
        private String name;
        private int age;

        public volatile int count;
    }
}
