package WebNotes;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPNotes {
    //通过局域网发送信息
    //客户端
    @Test
    public void Client(){
        Socket socket = null;
        OutputStream outputStream = null;

        try {
            //1.创建Socket对象，指明服务器端的ip和端口号
            InetAddress inetAddress = InetAddress.getLocalHost();//服务器IP
            socket = new Socket(inetAddress, 9000);

            //2.获取一个输出流，用于输出数据
            outputStream = socket.getOutputStream();

            //3.写出数据的操作
            outputStream.write("测试数据".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }
    }

    //服务器端
    @Test
    public void Server(){
        ServerSocket serverSocket = null;
        Socket customSocket = null;
        InputStreamReader inputStreamReader = null;
        try {

            //1.创建服务器端的ServerSocket，指明自己的端口号
            serverSocket = new ServerSocket(9000);

            //2.调用accept()表示接收来自于客户端的socket
            customSocket = serverSocket.accept();

            //3.获取输入流，通过转换流将文件流转换成字符流
            InputStream inputStream = customSocket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);

            //4.读取输入流中的数据
            char[] cBuf = new char[2];
            int len;
            while((len = inputStreamReader.read(cBuf)) != -1){
                System.out.print(cBuf);
                System.out.println("收到了来自于" + customSocket.getInetAddress().getHostAddress() + "的信息");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            //5.关闭资源
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (customSocket != null) {
                try {
                    customSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //通过局域网传输文件
    //客户端
    @Test
    public void ImageClient(){
        Socket socket = null;
        OutputStream socketOutputStream= null;
        FileInputStream fileInputStream = null;

        try {
            //创建socket对象和输出文件
            File imageFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/WebNotes/File/OutputTestImage.jpg");
            InetAddress inetAddress = InetAddress.getLocalHost();
            socket = new Socket(inetAddress,9001);

            //创建用于网络传输的输出流
            socketOutputStream = socket.getOutputStream();

            //创建用于读取文件的文件输入流
            fileInputStream = new FileInputStream(imageFile);

            //读取文件并将其通过网络传输出去
            byte[] bBuff = new byte[1024];
            int len;
            while((len = fileInputStream.read(bBuff)) != -1){
                socketOutputStream.write(bBuff,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socketOutputStream != null) {
                try {
                    socketOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    //服务器端
    @Test
    public void ImageServer() {
        ServerSocket serverSocket = null;
        Socket cumSocket = null;
        InputStream socketInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            //创建服务器端的ServerSocket，指明自己的端口号，调用accept()表示接收来自于客户端的socket
            serverSocket = new ServerSocket(9001);
            cumSocket = serverSocket.accept();

            //获取网络输入流
            socketInputStream = cumSocket.getInputStream();

            //声明本地存储文件
            File imageFile = new File("/Users/pers.ervinse/IdeaProjects/TestProject/Notes/src/WebNotes/File/OutputTestImage2.jpg");

            //获取文件输出流写入本地文件
            fileOutputStream = new FileOutputStream(imageFile);

            //通过网络输入流读取数据，然后写入本地
            byte[] bBuff = new byte[1024];
            int len;
            while((len = socketInputStream.read(bBuff)) != -1){
                fileOutputStream.write(bBuff,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (cumSocket != null) {
                try {
                    cumSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (serverSocket != null) {
                try {
                    socketInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }


}
