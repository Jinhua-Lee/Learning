/*
 * Copyright (c)    19-2-25 下午1:06.
 * Author:    Jinhua
 * PathName:    D:/IDEA/Learning/src/com/genetic/SpeciesPopulation.java
 * LastModified:    19-2-25 下午1:05
 */

package cn.genetic;

/**
 * 种群类
 * 包含：
 * 		1.add 添加物种
 * 		2.traverse 遍历
 * @author Jinhua
 */

public class SpeciesPopulation {

	/**
	 * 头节点
	 */
	SpeciesIndividual head;

	/**
	 * 物种数量
	 */
	int speciesNum;

	SpeciesPopulation() {
		head = new SpeciesIndividual();
		speciesNum = TSPData.SPECIES_NUM;
	}

	/**
	 * 添加物种
	 * @param species 物种
	 */
	void add(SpeciesIndividual species) {
		// 游标
		SpeciesIndividual point = head;
		// 寻找表尾结点
		while(point.next != null) {
			point = point.next;
		}
		point.next = species;
	}

	/**
	 * 遍历
	 */
	void traverse() {
		// 游标
		SpeciesIndividual point = head.next;
		// 寻找表尾结点
		while(point != null) {
			for(int i = 0; i < TSPData.CITY_NUM; i++) {
				System.out.print(point.genes[i] + " ");
			}
			System.out.println(point.distance);
			point = point.next;
		}
		System.out.println("_______________________");
	}

}
