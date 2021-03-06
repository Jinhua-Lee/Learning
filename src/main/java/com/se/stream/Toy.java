package com.se.stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * 玩具
 *
 * @author Jinhua
 * @date 2021/1/19 22:27
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Toy {

    /**
     * 名字
     */
    private String name;

    public Toy(String name) {
        this.name = name;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this);
    }
}
