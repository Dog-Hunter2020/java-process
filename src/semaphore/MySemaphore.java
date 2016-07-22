package semaphore;

/**
 * Created by kisstheraik on 16/7/22.
 * Description 用java模拟信号量操作
 */
public class MySemaphore {
    //信号量整数
    private  int s;

    public MySemaphore(int a){


        if(a<0)return;

        this.s=a;

    }

    public void  acquire(){

        s--;
        if(s<=0)
            try {

                wait();

            }catch (Exception e){
                e.printStackTrace();
            }


    }

    public void release(){

        s++;

        if(s>0)

            notify();


    }
}
