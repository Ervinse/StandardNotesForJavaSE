package ThreadNotes;

public class RunnableNotes {

    public static void main(String[] args) {



        Operation pro = new Productor();
        Operation cum = new Customer();

        Thread proT = new Thread(pro);
        Thread cumT = new Thread(cum);

        proT.setName("生产者");
        cumT.setName("消费者");


        proT.start();
        cumT.start();


    }
}

abstract class Operation implements Runnable{

    static int goods = 0,num = 0;

    static Object lock = new Object();
}

class Productor extends Operation{

    @Override
    public void run() {
        while(goods < 10000){
            synchronized (lock) {
                if(goods <500) {
                    lock.notify();
                    goods++;
                    num++;
                    System.out.println(num + "" + Thread.currentThread().getName() + "\t当前货物数量为：" + goods);
                }else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}

class Customer extends Operation{

    @Override
    public void run() {
        while(goods > 0){

            synchronized (lock) {
                if(goods > 50){
                    lock.notify();
                    goods--;
                    num++;
                    System.out.println(num + "" + Thread.currentThread().getName() + "\t当前货物数量为：" + goods);
                }else{
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}


