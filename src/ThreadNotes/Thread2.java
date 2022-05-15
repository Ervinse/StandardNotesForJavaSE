package ThreadNotes;

public class Thread2 {
    public static void main(String[] args) {
        TicketWindow2 ticketWindow2 = new TicketWindow2();
        new Thread(ticketWindow2,"窗口1").start();
        new Thread(ticketWindow2,"窗口2").start();
        new Thread(ticketWindow2,"窗口3").start();

    }
}

class TicketWindow2 implements Runnable{
    static int staticNum = 100;
    int num = 100;
    //实现Runnable方式实现多线程，是同一个对象的多线程，对象属性的值在不同线程中共享
    static Object lock = new Object();

    @Override
    public void run() {
        while (true){
            synchronized (lock) {
                staticNum = staticNum - 1;
                num = num - 1;
                if (staticNum > 0){
                    System.out.println(Thread.currentThread().getName() + "-staticNum:" + staticNum);
                    System.out.println(Thread.currentThread().getName() + "-num      :" + num);

                }else {
                    break;
                }
            }
        }
    }
}