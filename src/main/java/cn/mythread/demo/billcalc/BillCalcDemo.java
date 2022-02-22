package cn.mythread.demo.billcalc;

import lombok.SneakyThrows;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Jinhua
 * @version 1.0
 * @date 2022/2/22 9:11
 */
public class BillCalcDemo {

    /**
     * 【FeeItem的ID -> 处理线程】
     * 用于保证每个计费项只在一个线程中进行计算
     */
    public static final Map<Integer, Thread> FEE_ITEM2_HANDLE_THREAD = new ConcurrentHashMap<>();

    /**
     * 【要结算的计费项】
     */
    public static final Integer SETTLE_FEE_ITEM_ID = 3;

    /**
     * 共享资源，即是【所有待计算的计费项】
     */
    public static final List<Integer> FEE_ITEM_IDS = Arrays.asList(1, 2, 3, 4, 5);

    @SneakyThrows
    @SuppressWarnings("all")
    public static void main(String[] args) {

        Runnable timerCalcProc = new TimerCalcProc();
        Runnable reqProc = new ReqProc();
        Thread t = new Thread(timerCalcProc, "定时计算");
        Thread r = new Thread(reqProc, "请求结算");

        t.start();
        r.start();
    }
}

/**
 * 定时计算的线程
 */
class TimerCalcProc implements Runnable {

    @Override
    public void run() {
        List<Integer> obtained = acquire();
        process(obtained);
        release(obtained);
    }

    private List<Integer> acquire() {
        List<Integer> obtained = new ArrayList<>();
        try {
            for (Integer feeItemId : BillCalcDemo.FEE_ITEM_IDS) {
//                // 遍历到结算的计费项时候，释放锁三秒，保证让结算流程获取到它。
//                if (Objects.equals(BillCalcDemo.SETTLE_FEE_ITEM_ID, feeItemId)) {
//                    notifyAll();
//                    wait(3000L);
//                }
                BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.putIfAbsent(feeItemId, Thread.currentThread());
                if (Objects.equals(BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.get(feeItemId), Thread.currentThread())) {
                    obtained.add(feeItemId);
                    System.out.println(Thread.currentThread().getName() + " acquire obtained = " + feeItemId);
                }
            }
        } catch (Exception ignored) {
        }
        return obtained;
    }

    private void process(List<Integer> obtained) {
        // 处理过程
        obtained.forEach(o -> System.out.println(Thread.currentThread().getName() + " process obtained = " + o));
        try {
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException ignored) {
        }
    }

    private void release(List<Integer> obtained) {
        Optional.ofNullable(obtained).orElse(Collections.emptyList()).forEach(TimerCalcProc::releaseSingle);
    }

    public static void releaseSingle(Integer ob) {
        if (ob == null) {
            System.out.println(Thread.currentThread().getName() + " 未获取到锁！");
            return;
        }
        // 如果等于当前线程，则移除它
        if (Objects.equals(BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.get(ob), Thread.currentThread())) {
            BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.computeIfPresent(ob, (oldKey, oldThread) -> {
                // 如果是当前线程，则移除
                if (Objects.equals(oldThread, Thread.currentThread())) {
                    System.out.println(Thread.currentThread().getName() + " release obtained = " + ob);
                    return null;
                }
                // 否则不进行修改
                return oldThread;
            });
        }
    }
}

/**
 * 执行请求的线程，一次执行
 */
class ReqProc implements Runnable {

    @Override
    public void run() {
        Integer obtained = acquire();
        process(obtained);

//        try {
//            // 睡一睡，保证有线程正在获取
//            notifyAll();
//            wait(3000L);
//        } catch (InterruptedException ignored) {
//        }
        TimerCalcProc.releaseSingle(obtained);
    }

    private Integer acquire() {
        Integer obtained = null;
        BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.putIfAbsent(BillCalcDemo.SETTLE_FEE_ITEM_ID, Thread.currentThread());
        if (Objects.equals(
                BillCalcDemo.FEE_ITEM2_HANDLE_THREAD.get(BillCalcDemo.SETTLE_FEE_ITEM_ID),
                Thread.currentThread())
        ) {
            // 前提，间隔1小时执行是短时间内不会修改计费项的计算线程
            obtained = BillCalcDemo.SETTLE_FEE_ITEM_ID;
            System.out.println(Thread.currentThread().getName() + " acquire obtained = " + obtained);
        }
        return obtained;
    }

    private void process(Integer obtained) {
        if (obtained == null) {
            System.out.println(Thread.currentThread().getName() + " process obtained is null.");
        }
        System.out.println(Thread.currentThread().getName() + " process obtained = " + obtained);
    }
}