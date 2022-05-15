package StreamNotes;

import java.io.*;

public class StreamNotes {
    public static void main(String[] args) {

//        File file = new File("/Users/pers.ervinse/IdeaProjects/TestProject/FileTest/file2.txt");
//        try {
//            file.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /*
         * 一、流的分类：
         * 1.操作数据单位：字节流、字符流
         * 2.数据的流向：输入流、输出流
         * 3.流的角色：节点流、处理流
         *
         * 二、流的体系结构
         * 抽象基类         节点流（或文件流）                                缓冲流（处理流的一种）
         * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
         * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
         * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
         * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()
         */


        System.out.println("1**********************");
        /*
        将FileTest下的file1.txt文件内容读入程序中，并输出到控制台

        说明点：
        1. read()的理解：返回读入的一个字符。如果达到文件末尾，返回-1
        2. 异常的处理：为了保证流资源一定可以执行关闭操作。需要使用try-catch-finally处理
        3. 读入的文件一定要存在，否则就会报FileNotFoundException。
         */

        //此节点流的null赋值为try-catch-finally所创建
        FileReader fileReader1 = null;

        try {
            //1.实例化File类的对象，指明要操作的文件
            File file1 = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt");

            //2.提供具体的流
            //如果文件不存在，在报FileNotFoundException，并且fileReader1的值为null
            fileReader1 = new FileReader(file1);

            //3.数据的读入
            //read():返回读入的一个字符。如果达到文件末尾，返回-1
            int buf;//将每一个读到的字符赋值给buf，在输出的时候转我为字符型
            while((buf = fileReader1.read()) != -1)
            {
                System.out.print((char)buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //4.流的关闭操作
            if (fileReader1 != null) {//如果文件不存在导致节点流没有正确创建，则不需要关闭fileReader流
                try {
                    fileReader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println();
        System.out.println("2**********************");

        //对read()操作升级：使用read的重载方法
        FileReader fileReader2 = null;
        try {
            //1.实例化File类的对象，指明要操作的文件
            File file2 = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt");

            //2.提供具体的流
            //如果文件不存在，在报FileNotFoundException，并且fileReader1的值为null
            fileReader2 = new FileReader(file2);

            //3.数据的读入
            //read(char[] cBuf):返回每次读入cBuf数组中的字符的个数。如果达到文件末尾，返回-1
            char[] cBuf = new char[5];
            int len;//记录每次读入到cbuf数组中的字符的个数
            while((len = fileReader2.read(cBuf)) != -1){
                //方式1
    //            for(int i = 1;i < len;i++){
    //                System.out.println(cBuf[i]);
    //            }
                //方式2
                String str = new String (cBuf,0,len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.流的关闭操作
            if (fileReader2 != null) {
                try {
                    fileReader2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        System.out.println();
        System.out.println("3**********************");

        /*
        从内存中写出数据到硬盘的文件里。

        说明：
        1. 输出操作，对应的File可以不存在的。并不会报异常
        2.
            File对应的硬盘中的文件如果不存在，在输出的过程中，会自动创建此文件。
            File对应的硬盘中的文件如果存在：
                如果流使用的构造器是：FileWriter(file,false) / FileWriter(file):对原有文件的覆盖
                如果流使用的构造器是：FileWriter(file,true):不会对原有文件覆盖，而是在原有文件基础上追加内容

                每一个流的write方法之间都是追加内容

         */
        FileWriter fileWriter3 = null;

        try {
            //1.提供File类的对象，指明写出到的文件
            File file3 = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file2.txt");

            //2.提供FileWriter的对象，用于数据的写出
            fileWriter3 = new FileWriter(file3);

            //3.写出的操作
            fileWriter3.write("WriteTest\n");
            fileWriter3.write("WriteTest");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fileWriter3 != null) {
                try {
                    //4.流资源的关闭
                    fileWriter3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        System.out.println();
        System.out.println("4**********************");
        //复制文件
        //public void write(char cbuf[], int off, int len) throws IOException
        //cbuf[]:需要写入的字符数组,off:字符数组起始写入的偏移量,len:写入的个数

        FileReader fileReader4 = null;
        FileWriter fileWriter5 = null;
        try {
            //1.创建File类的对象，指明读入和写出的文件
            File readFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/readFile.txt");
            File writeFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/writeFile.txt");

            //2.创建输入流和输出流的对象
            fileReader4 = new FileReader(readFile);
            fileWriter5 = new FileWriter(writeFile);

            //3.数据的读入和写出操作
            char[] cBuf = new char[8];
            int len;
            while((len = fileReader4.read(cBuf)) != -1){
                //每次写出len个字符
                fileWriter5.write(cBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //4.关闭流资源
            if (fileReader4 != null) {
                try {
                    fileReader4.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileWriter5 != null) {
                try {
                    fileWriter5.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }




        System.out.println();
        System.out.println("5**********************");

        /*
         * 测试FileInputStream和FileOutputStream的使用
         *
         * 结论：
         * 1. 对于文本文件(.txt,.java,.c,.cpp)，使用字符流处理
         * 2. 对于非文本文件(.jpg,.mp3,.mp4,.avi,.doc,.ppt,...)，使用字节流处理
         *
         * 提示:使用字节流FileInputStream处理文本文件，可能出现乱码。
         */


        //对图片的复制操作

        FileInputStream fileInputStream1 = null;
        FileOutputStream fileOutputStream1 = null;

        try {
            //1.实例化File类的对象，指明要操作的文件
            File readImage = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/InputTestImage.jpg");
            File writeImage = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/OutputTestImage.jpg");

            //2.提供具体的流
            fileInputStream1 = new FileInputStream(readImage);
            fileOutputStream1 = new FileOutputStream(writeImage);

            //3.读取数据
            byte[] bBuf = new byte[1024];
            int len;
            while((len = fileInputStream1.read(bBuf)) != -1){
                fileOutputStream1.write(bBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //4.关闭资源
            if (fileInputStream1 != null) {
                try {
                    fileInputStream1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream1 != null) {
                try {
                    fileOutputStream1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }


        System.out.println();
        System.out.println("6**********************");


        /*
         **
         * 处理流之一：缓冲流的使用
         *
         * 1.缓冲流：
         * BufferedInputStream
         * BufferedOutputStream
         * BufferedReader
         * BufferedWriter
         *
         * 2.作用：提供流的读取、写入的速度
         *   提高读写速度的原因：内部提供了一个缓冲区
         *
         * 3. 处理流，就是“套接”在已有的流的基础上。
         *
         */

        /*
        缓冲流的创建方式：

        1.
        File file = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        2.
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt"));

        3.
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt")));

         */


        //通过缓冲流复制图片
        BufferedInputStream bufferedInputStream = null;
        BufferedOutputStream bufferedOutputStream = null;

        try {
            //实例化file类对象
            File readImage = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/InputTestImage.jpg");
            File writeImage = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/OutputTestImage_Buffered.jpg");

            //创建节点流
            FileInputStream fileInputStream = new FileInputStream(readImage);
            FileOutputStream fileIOutputStream = new FileOutputStream(writeImage);

            //创建缓冲流
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedOutputStream = new BufferedOutputStream(fileIOutputStream);

            //复制数据
            byte[] bBuf = new byte[1024];
            int len;
            while((len = bufferedInputStream.read(bBuf)) != -1){
                bufferedOutputStream.write(bBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭资源:
            //说明：关闭外层流的同时，内层流也会自动地进行关闭。
            if (bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedOutputStream != null) {
                try {
                    bufferedOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println();
        System.out.println("7**********************");

        //通过缓冲流实现文本复制

        BufferedReader bufferedReader1 = null;
        BufferedWriter bufferedWriter1 = null;
        try {
            //创建缓冲流
            bufferedReader1 = new BufferedReader(new FileReader("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/readFile.txt"));
            bufferedWriter1 = new BufferedWriter(new FileWriter("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/writeFile_Buffered.txt"));

            //复制文本
            char[] cBuf = new char[10];
            int len;
            while((len = bufferedReader1.read(cBuf)) != -1){
                bufferedWriter1.write(cBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //关闭资源
            if (bufferedReader1 != null) {
                try {
                    bufferedReader1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedWriter1 != null) {
                try {
                    bufferedWriter1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        /*
        * 处理流之二：转换流的使用
        * 1.转换流：属于字符流
        *   InputStreamReader：将一个字节的输入流转换为字符的输入流
        *   OutputStreamWriter：将一个字符的输出流转换为字节的输出流
        *
        * 2.作用：提供字节流与字符流之间的转换
        *
        * 3. 解码：字节、字节数组  --->字符数组、字符串
        *    编码：字符数组、字符串 ---> 字节、字节数组
        *
        *
        * 4.字符集
        *ASCII：美国标准信息交换码。用一个字节的7位可以表示。
        *ISO8859-1：拉丁码表。欧洲码表。用一个字节的8位表示。
        * GB2312：中国的中文编码表。最多两个字节编码所有字符
        * GBK：中国的中文编码表升级，融合了更多的中文文字符号。最多两个字节编码
        * Unicode：国际标准码，融合了目前人类使用的所有字符。为每个字符分配唯一的字符码。所有的文字都用两个字节来表示。
        * UTF-8：变长的编码方式，可用1-4个字节来表示一个字符。
        *
         */


        //实现字节的输入流到字符的输入流的转换
        InputStreamReader inputStreamReader = null;
        try {
            File file = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt");
            inputStreamReader = new InputStreamReader(new FileInputStream(file),"UTF-8");
            char[] cBuf = new char[10];
            int len;
            while((len = inputStreamReader.read(cBuf)) != -1){
                for(int i = 1;i < len;i++){
                    System.out.print(cBuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println();
        System.out.println("8**********************");

        //通过转换流改变文本文件的编码集格式

        InputStreamReader inputStreamReader2 = null;
        OutputStreamWriter outputStreamWriter2 = null;
        try {
            File readFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/file1.txt");
            File writeFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/StreamConversion.txt");

            inputStreamReader2 = new InputStreamReader(new FileInputStream(readFile),"UTF-8");
            outputStreamWriter2 = new OutputStreamWriter(new FileOutputStream(writeFile),"GBK");

            char[] cBuf = new char[10];
            int len;
            while((len = inputStreamReader2.read(cBuf)) != -1){
                outputStreamWriter2.write(cBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (inputStreamReader2 != null) {
                try {
                    inputStreamReader2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStreamWriter2 != null) {
                try {
                    outputStreamWriter2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        System.out.println();
        System.out.println("9**********************");


        /*
         * 对象流的使用
         * 1.ObjectInputStream 和 ObjectOutputStream
         * 2.作用：用于存储和读取基本数据类型数据或对象的处理流。它的强大之处就是可以把Java中的对象写入到数据源中，也能把对象从数据源中还原回来。
         *
         * 3.要想一个java对象是可序列化的，需要满足相应的要求。见Person.java
         *
         * 4.序列化机制：
         * 对象序列化机制允许把内存中的Java对象转换成平台无关的二进制流，从而允许把这种
         * 二进制流持久地保存在磁盘上，或通过网络将这种二进制流传输到另一个网络节点。
         * 当其它程序获取了这种二进制流，就可以恢复成原来的Java对象。
         */

        /*
        序列化过程：将内存中的java对象保存到磁盘中或通过网络传输出去
        使用ObjectOutputStream实现
         */

        ObjectOutputStream objectOutputStream = null;

        try {
            //创建对象流
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/Object.data"));

            //写入对象
            objectOutputStream.writeObject(new String("ObjectTest"));
            objectOutputStream.writeObject(new Person());
            objectOutputStream.writeObject(new Person("张三",20,1000));
            objectOutputStream.writeObject(new Person("李四",22,2000));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        ObjectInputStream objectInputStream = null;
        try {
            //创建对象流
            objectInputStream = new ObjectInputStream(new FileInputStream("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/Object.data"));

            //通过readObject()来读取data中的对象，一次读取一个对象
            Object obj = objectInputStream.readObject();
            String str = (String) obj;

            Person p1 = (Person)objectInputStream.readObject();
            Person p2 = (Person) objectInputStream.readObject();
            Person p3 = (Person) objectInputStream.readObject();

            System.out.println(str);
            System.out.println(p1);
            System.out.println(p2);
            System.out.println(p3);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println();
        System.out.println("10**********************");

        /*
         * RandomAccessFile的使用
         * 1.RandomAccessFile直接继承于java.lang.Object类，实现了DataInput和DataOutput接口
         * 2.RandomAccessFile既可以作为一个输入流，又可以作为一个输出流
         *
         * 3.如果RandomAccessFile作为输出流时，写出到的文件如果不存在，则在执行过程中自动创建。
         *   如果写出到的文件存在，则会对原有文件内容进行覆盖。（默认情况下，从头覆盖）
         *
         * 4. 可以通过相关的操作，实现RandomAccessFile“插入”数据的效果
         */

        /*
        创建 RandomAccessFile 类实例需要指定一个 mode 参数，该参数指 定 RandomAccessFile 的访问模式:
        r: 以只读方式打开
        rw:打开以便读取和写入
        rwd:打开以便读取和写入;同步文件内容的更新
        rws:打开以便读取和写入;同步文件内容和元数据的更新
         */

        RandomAccessFile randomAccessFile1 = null;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile1 = new RandomAccessFile(new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/InputTestImage.jpg"),"r");
            randomAccessFile2 = new RandomAccessFile(new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/randomAccessImage.jpg"),"rw");

            byte[] bBuf = new byte[10];
            int len;
            while((len = randomAccessFile1.read(bBuf)) != -1){
                randomAccessFile2.write(bBuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomAccessFile1 != null) {
                try {
                    randomAccessFile1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println();
        System.out.println("11**********************");

        //通过RandomAccessFile对文件原有内容进行覆盖写入
        //注意：此代码存在问题
        FileWriter fileWriter = null;
        FileReader fileReader = null;
        RandomAccessFile randomAccessFile3 = null;

        try {

            File file = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/StreamNotes/FileTest/RandomAccessFile.txt");
//            file.createNewFile();
            fileWriter = new FileWriter(file);
//            fileWriter.write("ABCDEFG");
            randomAccessFile3 = new RandomAccessFile(file,"rw");
            randomAccessFile3.write("xxx".getBytes());

            fileReader = new FileReader(file);
            char[] cBuf = new char[2];
            int len;
            while((len = fileReader.read(cBuf)) != -1){
                String str = new String (cBuf,0,len);
                System.out.print(str);
            }
            randomAccessFile3.seek(5);
            randomAccessFile3.write("x".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (randomAccessFile3 != null) {
                try {
                    randomAccessFile3.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
/*
 * Person需要满足如下的要求，方可序列化
 * 1.需要实现接口：Serializable
 * 2.当前类提供一个全局常量：serialVersionUID
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性
 *   也必须是可序列化的。（默认情况下，基本数据类型可序列化）
 *
 * 补充：ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
 *
 */

class Person implements Serializable{

    static final long serialVersionUID = 4217235L;

    private String name;
    private int age;
    private Account account;

    public Person() {

    }

    public Person(String name, int age, int balance) {

        Account account = new Account(balance);

        this.name = name;
        this.age = age;
        this.account = account;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", account=" + account +
                '}';
    }
}

class Account implements Serializable{

    static final long serialVersionUID = 4217236L;

    private int balance;

    public Account() {

    }

    public Account(int balance) {
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}

