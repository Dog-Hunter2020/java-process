package semaphore;

/**
 * Created by kisstheraik on 16/7/22.
 * Description 用java模拟信号量操作
 */
public class MySemaphore {
    //信号量整数
    private  int s;

    public MySemaphore(int a){

        this.s=a;

    }

    public synchronized int getS(){
        return s;
    }
    //锁定代码块,使得acquire成为原子操作
    public synchronized void  acquire(){



        if(s<=0)
            try {

                //阻塞进程,直到被唤醒
                wait();

            }catch (Exception e){
                e.printStackTrace();
            }

        s--;
    }

    //锁定代码块,使得release成为原子操作,唤醒一个进程被wait的进程
    public synchronized void release(){

        s++;

        //使所有进程退出wait状态,竞争代码使用权然后执行
        notify();

    }
}
