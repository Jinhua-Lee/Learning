/*
 * Copyright (c)    19-2-25 下午1:06.
 * Author:    Jinhua
 * PathName:    D:/IDEA/Learning/src/com/genetic/SpeciesIndividual.java
 * LastModified:    19-2-25 下午1:05
 */

package cn.genetic;

import java.util.Random;

/**
 * 个体类
 * 包含：
 * 1.createByRandomGenes 初始物种基因(随机) 基因直接用城市序列编码
 * 2.calFitness 计算物种适应度
 * 3.printRate 打印路径
 *
 * @author Jinhua
 */

public class SpeciesIndividual {

    /**
     * 基因序列
     */
    String[] genes;

    /**
     * 路程
     */
    double distance;

    /**
     * 适应度
     */
    double fitness;

    SpeciesIndividual next;

    double rate;

    SpeciesIndividual() {
        // 初始化
        this.genes = new String[TspData.CITY_NUM];
        this.fitness = 0.0d;
        this.distance = 0.0d;
        this.next = null;
        rate = 0.0d;
    }

    /**
     * 初始物种基因(随机)
     */
    void createByRandomGenes() {
        // 初始化基因为1-CITY_NUM序列
        for (int i = 0; i < genes.length; i++) {
            genes[i] = Integer.toString(i + 1);
        }

        // 获取随机种子
        Random rand = new Random();

        for (int j = 0; j < genes.length; j++) {
            int num = j + rand.nextInt(genes.length - j);

            // 交换
            String tmp;
            tmp = genes[num];
            genes[num] = genes[j];
            genes[j] = tmp;
        }
    }

    /**
     * 初始物种基因(贪婪)
     */
    void createByGreedyGenes() {
        Random rand = new Random();
        // 随机产生一个城市作为起点
        int i = rand.nextInt(TspData.CITY_NUM);
        genes[0] = Integer.toString(i + 1);
        int j;// 终点
        int cityNum = 0;
        do {
            cityNum++;

            // 选出单源最短城市
            double minDis = Double.MAX_VALUE;
            int minCity = 0;
            for (j = 0; j < TspData.CITY_NUM; j++) {
                if (j != i) {
                    // 判是否和已有重复
                    boolean repeat = false;
                    for (int n = 0; n < cityNum; n++) {
                        if (Integer.parseInt(genes[n]) == j + 1) {
                            // 重复
                            repeat = true;
                            break;
                        }
                    }
                    // 没重复
                    if (!repeat) {
                        // 判长度
                        if (TspData.disMap[i][j] < minDis) {
                            minDis = TspData.disMap[i][j];
                            minCity = j;
                        }
                    }
                }
            }

            // 加入到染色体
            genes[cityNum] = Integer.toString(minCity + 1);
            i = minCity;
        } while (cityNum < TspData.CITY_NUM - 1);
    }

    /**
     * 计算物种适应度
     */
    void calFitness() {
        double totalDis = 0.0f;
        for (int i = 0; i < TspData.CITY_NUM; i++) {
            int curCity = Integer.parseInt(this.genes[i]) - 1;
            int nextCity = Integer.parseInt(this.genes[(i + 1) % TspData.CITY_NUM]) - 1;

            totalDis += TspData.disMap[curCity][nextCity];
        }

        //  路径之和为总路程
        this.distance = totalDis;

        //  取倒数，路径越长，适应度越低
        this.fitness = 1.0f / totalDis;
    }

    /**
     * 深拷贝
     *
     * @return 深拷贝
     */
    @Override
    public SpeciesIndividual clone() {
        SpeciesIndividual species = new SpeciesIndividual();

        // 复制内容
        for (int i = 0; i < this.genes.length; i++) {
            species.genes[i] = this.genes[i];
        }
        species.distance = this.distance;
        species.fitness = this.fitness;

        return species;
    }

    /**
     * 打印路径
     */
    void printRate() {
        System.out.print("最短路线：");
        for (String gene : genes) {
            System.out.print(gene + "->");
        }
        System.out.print(genes[0] + "\n");
        System.out.print("最短长度：" + distance);
    }

}
