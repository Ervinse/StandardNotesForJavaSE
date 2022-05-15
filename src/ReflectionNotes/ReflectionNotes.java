package ReflectionNotes;

/*
    关于java.lang.Class类的理解
    1.类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。
    接着我们使用java.exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程就称为类的加载。
    加载到内存中的类，我们就称为运行时类，此运行时类，就作为Class的一个实例。

    2.换句话说，Class的实例就对应着一个运行时类。
    3.加载到内存中的运行时类，会缓存一定的时间。在此时间之内，我们可以通过不同的方式
    来获取此运行时类。
     */


import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;

public class ReflectionNotes {
    public static void main(String[] args) {

        //获取Class的实例的方式
        //方式一：调用运行时类的属性：.class
        Class class1 = Person.class;
        System.out.println(class1);

        //方式二：通过运行时类的对象,调用getClass()
        Person p1 = new Person();
        Class class2 = p1.getClass();
        System.out.println(class2);

        //方式三：调用Class的静态方法：forName(String classPath)
        Class class3 = null;
        try {
            class3 = Class.forName("ReflectionNotes.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(class3);

        //方式四：使用类的加载器：ClassLoader
        ClassLoader classLoader = ReflectionNotes.class.getClassLoader();
        Class class4 = null;
        try {
            class4 = classLoader.loadClass("ReflectionNotes.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(class4);

        //用不同方式获得的同一个运行时类的实例的地址相同
        boolean b1 = (class1 == class2) && (class1 == class3) && (class1 == class3) && (class1 == class4);
        System.out.println(b1);//true


        //Class实例可以是以下结构：
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();

        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);//true

        System.out.println("1********************");

        /*
        newInstance():调用此方法，创建对应的运行时类的对象。内部调用了运行时类的空参的构造器。

        要想此方法正常地创建运行时类的对象，要求：
        1.运行时类必须提供空参的构造器
        2.空参的构造器的访问权限得够。通常，设置为public。


        在javabean中要求提供一个public的空参构造器。原因：
        1.便于通过反射，创建运行时类的对象
        2.便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器

         */

        Class<Person> personClass = Person.class;
        Person person1 = null;
        try {
            person1 = personClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(person1);

        //应用举例：通过（工具类）静态方法，创建一个指定类的对象，classPath为指定类的全类名
        Person person2 = null;
        try {
            person2 = (Person) ReflectionNotes.getInstance("ReflectionNotes.Person");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(person2);

        System.out.println("2********************");

        /*
        获取运行时类的指定属性
        getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
        setAccessible(true):保证当前属性是可访问的(防止因封装性而无法读取)
        set():设置指定对象的此属性值
        get():获取指定对象的此属性值
         */
        try {
            //getDeclaredField(String fieldName):获取运行时类中指定变量名的属性
            Field privateSubField = personClass.getDeclaredField("privateSubField");
            //保证当前属性是可访问的(防止因封装性而无法读取)
            privateSubField.setAccessible(true);
            //设置指定对象的此属性值
            Person person = personClass.newInstance();
            privateSubField.set(person,1);
            //获取指定对象的此属性值
            System.out.println(privateSubField.get(person));
        } catch (NoSuchFieldException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        /*
        获取运行时类中的指定的方法
        getDeclaredMethod():获取指定的方法  参数1 ：指明获取的方法的名称  参数2：指明获取的方法的形参列表
        invoke():调用方法  参数1：方法的调用者  参数2：给方法形参赋值的实参  invoke()的返回值即为对应类中调用的方法的返回值。
         */
        try {
            //getDeclaredMethod():获取指定的方法
            // 参数1 ：指明获取的方法的名称  参数2：指明获取的方法的形参列表
            Method subMethod = personClass.getDeclaredMethod("subMethod",int.class);

            //setAccessible(true):保证当前方法是可访问的
            subMethod.setAccessible(true);

            //invoke():调用方法
            //参数1：方法的调用者  参数2：给方法形参赋值的实参
            //invoke()的返回值即为对应类中调用的方法的返回值。
            Person person = personClass.newInstance();
            int returnValue = (int)subMethod.invoke(person, 2);

            System.out.println(returnValue);//2

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用静态方法
        try {
            Method subStaticMethod = personClass.getDeclaredMethod("subStaticMethod", int.class);
            subStaticMethod.setAccessible(true);
            Object returnValue = subStaticMethod.invoke(Person.class, 3);
            //如果调用的运行时类中的方法没有返回值，则此invoke()返回null
            System.out.println(returnValue);//null

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        /*
        调用运行时类中的指定的构造器
        getDeclaredConstructor():获取指定的构造器  参数：指明构造器的参数列表
         */
        try {
            //获取指定的构造器
            //getDeclaredConstructor():参数：指明构造器的参数列表
            Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor();
            declaredConstructor.setAccessible(true);
            //通过此构造器创建运行时类调对象
            Person person = declaredConstructor.newInstance();
            System.out.println(person);//Person{publicSupperField=0, publicSubField=0, privateSubField=0}
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("3********************");


        //系统类加载器（自定义类）-->扩展类加载器-->引导类加载器（Java核心类库）
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader1 = ReflectionNotes.class.getClassLoader();
        System.out.println(classLoader1);//sun.misc.Launcher$AppClassLoader@18b4aac2
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classLoader2 = classLoader.getParent();
        System.out.println(classLoader2);//sun.misc.Launcher$ExtClassLoader@610455d6
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classLoader3 = classLoader2.getParent();
        System.out.println(classLoader3);//null

        ClassLoader classLoader4 = String.class.getClassLoader();
        System.out.println(classLoader4);//null

        System.out.println("4********************");

        //获取属性结构
        //getFields():获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] field = personClass.getFields();
        for (Field f:field){
            System.out.println(f);
            /*
            public int ReflectionNotes.Person.publicSubField
            public int ReflectionNotes.Creature.publicSupperField
             */
        }
        System.out.println();
        //getDeclaredFields():获取当前运行时类中声明的所有属性。（不包含父类中声明的属性）
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field f:declaredFields){
            System.out.println(f);
            /*
            public int ReflectionNotes.Person.publicSubField
            private int ReflectionNotes.Person.privateSubField
             */
        }
        System.out.println("5********************");

        for(Field f:declaredFields){
            //getModifiers():获取属性的权限修饰符
            int modifiers = f.getModifiers();
            System.out.println(modifiers);
            /*
            1:public
            2:private
             */
        }

        System.out.println();

        for(Field f:declaredFields){
            //getType():获取属性的数据类型
            Class type = f.getType();
            System.out.println(type.getName());
            /*
            int
            int
             */
        }

        System.out.println();

        for (Field f:declaredFields){
            //getName():获取属性的变量名
            String fName = f.getName();
            System.out.println(fName);
            /*
            publicSubField
            privateSubField
             */
        }

        System.out.println("6********************");
        //getMethods():获取当前运行时类及其所有父类中声明为public权限的方法
        Method[] methods = personClass.getMethods();
        for(Method m:methods){
            System.out.println(m);
        }
        System.out.println();
        //getDeclaredMethods():获取当前运行时类中声明的所有方法。（不包含父类中声明的方法）
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for(Method m:declaredMethods){
            System.out.println(m);
        }
        System.out.println("7********************");


        declaredMethods = personClass.getDeclaredMethods();
        for(Method m : declaredMethods){
            //1.获取方法声明的注解
            Annotation[] annos = m.getAnnotations();
            for(Annotation an : annos){
                System.out.println(an);
            }

            //2.权限修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");

            //3.返回值类型
            System.out.print(m.getReturnType().getName() + "\t");

            //4.方法名
            System.out.print(m.getName());
            System.out.print("(");
            //5.形参列表
            Class[] parameterTypes = m.getParameterTypes();
            if(!(parameterTypes == null && parameterTypes.length == 0)){
                for(int i = 0;i < parameterTypes.length;i++){

                    if(i == parameterTypes.length - 1){
                        System.out.print(parameterTypes[i].getName() + " args_" + i);
                        break;
                    }

                    System.out.print(parameterTypes[i].getName() + " args_" + i + ",");
                }
            }

            System.out.print(")");

            //6.抛出的异常
            Class[] exceptionTypes = m.getExceptionTypes();
            if(exceptionTypes.length > 0){
                System.out.print("throws ");
                for(int i = 0;i < exceptionTypes.length;i++){
                    if(i == exceptionTypes.length - 1){
                        System.out.print(exceptionTypes[i].getName());
                        break;
                    }

                    System.out.print(exceptionTypes[i].getName() + ",");
                }
            }
            System.out.println();
        }
    }
    /*
    创建一个指定类的对象。
    classPath:指定类的全类名
     */
    public static Object getInstance(String classPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class newClass = Class.forName(classPath);
        return newClass.newInstance();
    }

}
