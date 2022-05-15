package ProxyNotes;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    /**
     * Person类为PersonImpl类的接口
     * 本文档通过newProxyInstance()方法，获取了真实类PersonImpl的代理类对象personProxy，
     * 并通过在invoke()方法中区别不同的原方法，实现了增强了actionA()方法，而没有增强actionB()方法
     * 最后通过代理类对象personProxy，调用了actionA()方法和actionB()方法，获取且打印返回值，展示增强方法的效果
     * @param args
     */

    public static void main(String[] args) {
        //实例化真实类，即要增强的类
        PersonImpl personImpl = new PersonImpl();

        /*
            通过Proxy类的静态方法newProxyInstance()获取代理类
            其中参数含义如下：
                personImpl.getClass().getClassLoader()：通过真实类获取其类加载器
                personImpl.getClass().getInterfaces()：通过真实类获取其所有接口的数组
                new InvocationHandler()：创建一个InvocationHandler类型的匿名类，重写其invoke()方法
         */
        Person personProxy = (Person)Proxy.newProxyInstance(
                personImpl.getClass().getClassLoader(),
                personImpl.getClass().getInterfaces(),
                new InvocationHandler() {

                    /*
                        invoke()方法含义：
                            当程序通过 代理类对象(personProxy) 运行 真实类 的任意方法时（personProxy.actionA(1)），
                            jvm虚拟机会调用此方法（原方法不执行），并将原本应该执行的方法和其参数通过反射的方式封装到参数中
                        invoke()方法参数含义：
                            Object proxy：代理对象
                            Method method：原本执行的方法
                            Object[] args：原本执行的方法的参数组

                        因此，从宏观上来看，通过代理对象调用任意方法相当于调用此方法
                        在此方法中根据原方法来决定，是否更改其逻辑，亦或是直接调用原方法
                        此方法中的返回值即为代理对象调用原方法的返回值
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //判断原本执行的方法名是否为actionA，即执行体中对actionA()方法进行增强
                        if(method.getName().equals("actionA")){

                            //获取原actionA()方法传入的参数，并进行修改
                            int num = (Integer) args[0];
                            num++;

                            //增强actionA()方法调用前逻辑
                            System.out.println("actionA()方法调用前逻辑");

                            //传入经过修改的参数，通过反射的方式调用原方法，并获取返回值
                            Object returnNum = method.invoke(personImpl, num);

                            //增强actionA()方法调用后逻辑
                            System.out.println("actionA()方法调用后逻辑");
                            return returnNum;
                        }else {
                            Object returnNum = method.invoke(personImpl, args);
                            return returnNum;
                        }
                    }
                });

        //通过代理对象调用原方法，实际上调用InvocationHandler类中的invoke()方法
        int a = personProxy.actionA(1);
        int b = personProxy.actionB(1);

        System.out.println("actionA()方法的返回值为：" + a);
        System.out.println("actionB()方法的返回值为：" + b);
    }
}
