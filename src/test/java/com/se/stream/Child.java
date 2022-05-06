package com.se.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

/**
 * 儿子
 *
 * @author Jinhua
 * @date 2021/1/19 22:26
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {
        "toys"
})
public class Child {

    /**
     * 名字
     */
    private String name;

    /**
     * 玩具们
     */
    private List<Toy> toys;

    public Child(String name, List<Toy> toys) {
        this.name = name;
        this.toys = toys;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
