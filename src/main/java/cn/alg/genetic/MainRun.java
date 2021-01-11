/*
 * Copyright (c)    2021 Jinhua, Inc. All rights reserved.
 * Author:    Jinhua
 * PathName:    D:/IdeaProjects/Learning/src/main/java/cn/alg/genetic/MainRun.java
 * LastModified:    2020/11/7 下午2:08
 */

package cn.alg.genetic;


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
