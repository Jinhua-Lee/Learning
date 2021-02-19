package cn.designpattern.decorator.glory;


import cn.designpattern.decorator.glory.buffs.BlueBuffedHero;
import cn.designpattern.decorator.glory.buffs.RedBuffedHero;
import cn.designpattern.decorator.glory.profession.assassin.李白;

/**
 * 带buff的英雄抽象类 -> 增强功能的定义
 *
 * @author Jinhua
 */
public abstract class BuffedHero implements Hero {

    /**
     * 英雄
     */
    private final Hero hero;

    public BuffedHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public int attackPower() {
        return hero.attackPower();
    }

    @Override
    public int coolingTime() {
        return hero.coolingTime();
    }

    public static void main(String[] args) {

        Hero hero = new 李白();
        System.out.println("\n初始\n------------------");
        System.out.println("攻击力：" + hero.attackPower() + "， 冷却时间：" + hero.coolingTime());
        System.out.println("------------------");

        System.out.println("加上红Buff：");
        BuffedHero redBuffedHero = new RedBuffedHero(hero);
        System.out.println("攻击力：" + redBuffedHero.attackPower() + "， 冷却时间：" + redBuffedHero.coolingTime());
        System.out.println("------------------");

        System.out.println("再加上蓝Buff：");
        BuffedHero blueBuffedHero = new BlueBuffedHero(redBuffedHero);
        System.out.println("攻击力：" + blueBuffedHero.attackPower() + "， 冷却时间：" + blueBuffedHero.coolingTime());
        System.out.println("------------------");
    }
}
