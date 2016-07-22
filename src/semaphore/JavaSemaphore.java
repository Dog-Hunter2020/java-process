package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by kisstheraik on 16/7/22.
 * Description java信号量操作
 */
public class JavaSemaphore {


    public void  sum(){

        //线程池子
        ExecutorService executorService= Executors.newCachedThreadPool();

        //信号量
        Semaphore semaphore=new Semaphore(5);

        for(int i=0;i<20;i++){

            final int index=i;

            Runnable task=new Runnable() {
                @Override
                public void run() {


                    try {
                        semaphore.acquire();
                        System.out.println("Acessing:"+index);
                        Thread.sleep((long) (Math.random() * 6000));
                        semaphore.release();
                        System.out.println("------------"+semaphore.availablePermits());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            };

            executorService.execute(task);
        }
        executorService.shutdown();

    }
    public static void main(String[] args){

        JavaSemaphore javaSemaphore=new JavaSemaphore();
        javaSemaphore.sum();

    }
}
