package cn.designpattern.facade;

import cn.designpattern.facade.sub.BrickLayer;
import cn.designpattern.facade.sub.BrickWorker;
import cn.designpattern.facade.sub.Mason;

/**
 * 包工头要做的事
 *
 * @author Jinhua
 * @version 1.0
 * @date 2021/8/5 23:42
 */
public class LabourContractor {

    private final Mason p1 = new Mason();

    private final BrickWorker p2 = new BrickWorker();

    private final BrickLayer p3 = new BrickLayer();

    /**
     * 提供给外部的方法
     */
    public void buildHouse() {
        p1.mix();
        p2.carry();
        p3.neat();
    }

    public static void main(String[] args) {
        // 告诉包工头，我要建房子
        LabourContractor contractor = new LabourContractor();
        contractor.buildHouse();
    }

}
