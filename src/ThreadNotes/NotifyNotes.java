package ThreadNotes;

public class NotifyNotes {

    public static void main(String[] args) {

        Counter counter = new Counter();
        Thread thread1 = new Thread(counter);
        Thread thread2 = new Thread(counter);
        Thread thread3 = new Thread(counter);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}

class Counter implements Runnable{

    static int sumNum = 0;
    int threadNum = 0;
    Object lock = new Object();


    @Override
    public void run() {

        while(sumNum < 100000){
            synchronized (lock) {//监视器

                lock.notify();//唤醒线程，需要用监视器lock来调用方法

                sumNum++;
                threadNum++;
                System.out.println(Thread.currentThread().getName() + "\t" +threadNum);

                try {
                    lock.wait();//使线程进入阻塞状态
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();


        }
//        System.out.println(Thread.currentThread().getName() + "\t总数：" +threadNum);
    }
}
