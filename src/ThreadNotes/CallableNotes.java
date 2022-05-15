package ThreadNotes;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableNotes {

    public static void main(String[] args) {

        Plus plus = new Plus();
        FutureTask ft1 = new FutureTask(plus);
        FutureTask ft2 = new FutureTask(plus);

        Thread t1 = new Thread(ft1);
        Thread t2 = new Thread(ft2);

        t1.start();
        t2.start();

        int sum1 = 0;
        int sum2 = 0;
        try {
            sum1 = (Integer) ft1.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            sum2 = (Integer) ft2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(sum1);
        System.out.println(sum2);


    }
}

class Plus implements Callable {

    int num = 100,sum = 0;
    Object lock = new Object();


    Plus(){

    }

    Plus(int num){
        this.num = num;
    }

    @Override
    public Object call() throws Exception {

        for (int i = 0; i < num; i++) {
            synchronized (lock) {
                sum += i;
//                Thread.sleep(10);
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t" +sum);
        return sum;
    }
}