package ThreadNotes;

public class Thread1 {
    public static void main(String[] args) {
        new TicketWindow1().start();
        new TicketWindow1().start();
        new TicketWindow1().start();
    }
}

class TicketWindow1 extends Thread{
    static int staticNum = 100;
    int num = 100;
    //继承Thread方式实现多线程，是不同对象的多线程，对象属性的值在不同线程中不共享
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