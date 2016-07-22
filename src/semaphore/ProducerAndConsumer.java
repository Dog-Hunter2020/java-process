package semaphore;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kisstheraik on 16/7/23.
 * Description java模拟生产者消费者问题
 */
public class ProducerAndConsumer {

    //用于模拟的缓冲区
    public ArrayList<Integer> buffer=new ArrayList<>();
    public int currentSize=-1;
    public MySemaphore locksemaphore=new MySemaphore(1);
    public MySemaphore usesemaphore=new MySemaphore(1);

    private void produce(){


        while(true) {
            locksemaphore.acquire();
            currentSize++;
            buffer.add(new Integer((int) Math.random() * 100));
            locksemaphore.release();
            usesemaphore.release();
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void consume(){

        while(true) {

            usesemaphore.acquire();
            locksemaphore.acquire();
            buffer.remove(currentSize);
            currentSize--;
            locksemaphore.release();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    /*
     * @param pnum int 生产者的数目
     * @param snum int 消费者的数目
     */
    public void run(int pnum,int snum){

        ExecutorService executorService= Executors.newCachedThreadPool();

        for(int i=0;i<pnum;i++){

            Runnable task=new Runnable() {
                @Override
                public void run() {

                    produce();
                }
            };

            executorService.execute(task);
        }

        for(int i=0;i<snum;i++){

            Runnable task=new Runnable() {
                @Override
                public void run() {

                    consume();
                }
            };

            executorService.execute(task);
        }

        executorService.shutdown();


    }

    public static void main(String[] args){

        ProducerAndConsumer producerAndConsumer=new ProducerAndConsumer();

        producerAndConsumer.run(4,4);

    }
}
