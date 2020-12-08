/*
 * Copyright (c)    19-2-25 下午1:06.
 * Author:    Jinhua
 * PathName:    D:/IDEA/Learning/src/com/genetic/MainRun.java
 * LastModified:    19-2-25 下午1:05
 */

package cn.genetic;


/**
 * 遗传算法主函数运行类
 *
 * @author Jinhua
 */

public class MainRun {

    public static void main(String[] args) {

        // 创建遗传算法驱动对象
        GeneticAlgorithm GA = new GeneticAlgorithm();

        // 创建初始种群
        SpeciesPopulation speciesPopulation = new SpeciesPopulation();

        // 开始遗传算法（选择算子、交叉算子、变异算子）
        SpeciesIndividual bestRate = GA.run(speciesPopulation);

        // 进行遍历
        speciesPopulation.traverse();

        // 打印路径与最短距离
        bestRate.printRate();

    }

}
