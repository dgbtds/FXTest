package sample;/**
 * @description
 * @author: WuYe
 * @vesion:1.0
 * @Data : 2020/2/10 13:40
 */

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: FXTest
 *
 * @description:
 *
 * @author: WuYe
 *
 * @create: 2020-02-10 13:40
 **/
public class test {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger time= new AtomicInteger(10);
        ThreadFactory threadFactory = r -> {
            Thread thread = new Thread(r);
            // 给线程起个名字
            AtomicInteger atomicInteger=new AtomicInteger(0);
            thread.setName("MyThread--" + atomicInteger.getAndIncrement());
            return thread;
        };
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1,threadFactory);
        executor.scheduleAtFixedRate(()->{
            int i = time.decrementAndGet();
            System.out.println(Thread.currentThread().getName()+"***time"+i);
        },0,1, TimeUnit.SECONDS);
        executor.awaitTermination(10,TimeUnit.SECONDS);
    }
}
