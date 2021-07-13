package cn.mythread.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2021/7/13 18:43
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference<Person> ref = new AtomicReference<>();
        Person person = new Person("SnailClimb", 22);
        ref.set(person);
        Person updatePerson = new Person("Daisy", 20);
        ref.compareAndSet(person, updatePerson);

        System.out.println(ref.get().getName());
        System.out.println(ref.get().getAge());
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private int age;
}
