package FileNotes;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class FileNotes {
    public static void main(String[] args) {

        /*
         * File类的使用
         *
         * 1. File类的一个对象，代表一个文件或一个文件目录(俗称：文件夹)
         * 2. File类声明在java.io包下
         * 3. File类中涉及到关于文件或文件目录的创建、删除、重命名、修改时间、文件大小等方法，
         *    并未涉及到写入或读取文件内容的操作。如果需要读取或写入文件内容，必须使用IO流来完成。
         */

        /*
        创建File类的实例
        File(String filePath):创建路径对应的文件
        File(String parentPath,String childPath):创建父级路径下的文件
        File(File parentFile,String childPath):创建指定文件下的文件
         */

        //File(String filePath)
        File f1 = new File("TestFile1.txt");//相对路径，在当前工程下创建
        System.out.println(f1);//TestFile1.txt
        f1 = new File("D:\\WorkSpace\\WorkSpace_idea\\TestFile1.txt");//绝对路径
        System.out.println(f1);//D:\WorkSpace\WorkSpace_idea\TestFile1.txt

        //File(String parentPath,String childPath)
        f1 = new File("D:\\WorkSpace\\WorkSpace_idea","TestFile1.txt");
        System.out.println(f1);//D:\WorkSpace\WorkSpace_idea\TestFile1.txt

        //File(File parentFile,String childPath)
        f1 = new File("D:\\WorkSpace","WorkSpace_idea");//此时f1相当于文件夹
        File f2 = new File(f1,"TestFile1.txt");
        System.out.println(f2);//D:\WorkSpace\WorkSpace_idea\TestFile1.txt

        System.out.println("1*******************************");
        /*
        public String getAbsolutePath()：获取绝对路径
        public String getPath() ：获取路径
        public String getName() ：获取名称
        public String getParent()：获取上层文件目录路径。若无，返回null
        public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
        public long lastModified() ：获取最后一次的修改时间，毫秒值

        如下的两个方法适用于文件目录：
        public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
        public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
         */

        f1 = new File("TestFile1.txt");
        System.out.println(f1.getPath());//TestFile1.txt
        System.out.println(f1.getAbsoluteFile());//D:\WorkSpace\WorkSpace_idea\TestFile1.txt
        System.out.println(f1.getName());//TestFile1.txt
        System.out.println(f1.getParent());//null
        f1 = new File("D:\\WorkSpace\\WorkSpace_idea\\File\\TestFile1.txt");
        System.out.println(f1.getPath());//D:\WorkSpace\WorkSpace_idea\File\TestFile1.txt
        System.out.println(f1.getAbsoluteFile());//D:\WorkSpace\WorkSpace_idea\File\TestFile1.txt
        System.out.println(f1.getName());//TestFile1.txt
        System.out.println(f1.getParent());//D:\WorkSpace\WorkSpace_idea\File


        System.out.println("2*******************************");
        /*
         创建硬盘中对应的文件或文件目录
         public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
         public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
         public boolean mkdirs() ：创建文j件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建
         注意：不返回true
         删除磁盘中的文件或文件目录
         public boolean delete()：删除文件或者文件夹
         删除注意事项：Java中的删除不走回收站。
         */

        //public boolean createNewFile() ：创建文件。若文件存在，则不创建，返回false
        boolean fileCreatSuccessFlag = true;
        try {
            fileCreatSuccessFlag = f1.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(fileCreatSuccessFlag){
            System.out.println("文件创建成功");
        }else{
            System.out.println("文件创建失败");
        }

        //public boolean mkdir() ：创建文件目录。如果此文件目录存在，就不创建了。如果此文件目录的上层目录不存在，也不创建。
        //public boolean mkdirs() ：创建文件目录。如果此文件目录存在，就不创建了。如果上层文件目录不存在，一并创建
        f2  = new File("D:\\WorkSpace\\WorkSpace_idea\\File\\SubFile");
        boolean dirCreatSuccessFlag = true;
        dirCreatSuccessFlag = f2.mkdir();
        if(dirCreatSuccessFlag){
            System.out.println("文件夹创建成功");
        }else{
            System.out.println("文件夹创建失败");
        }

        //public boolean delete()：删除文件或者文件夹
        //要想删除成功，文件目录下不能有子目录或文件
        boolean fileDeleteSuccessFlag = true;
        fileDeleteSuccessFlag = f2.delete();
        if(fileDeleteSuccessFlag){
            System.out.println("文件删除成功");
        }else{
            System.out.println("文件删除失败");
        }


        System.out.println("3*******************************");
        /*
        public long length() ：获取文件长度（即：字节数）。不能获取目录的长度。
        public long lastModified() ：获取最后一次的修改时间，毫秒值
         */

        System.out.println(f1.length());//默认值为0
        Date fileDate1 = new Date(f1.lastModified());
        System.out.println(fileDate1);//Sat Aug 21 17:48:09 CST 2021

        System.out.println("4*******************************");
        /*
        如下的两个方法适用于文件目录：
        public String[] list() ：获取指定目录下的所有文件或者文件目录的名称数组
        public File[] listFiles() ：获取指定目录下的所有文件或者文件目录的File数组
         */
        f2  = new File("D:\\WorkSpace\\WorkSpace_idea\\File\\SubFile\\SubFile2\\SubFile3");
        f2.mkdirs();
        f2  = new File("D:\\WorkSpace\\WorkSpace_idea\\File");

        String[] sArr1 = f2.list();
        for(String fileName : sArr1){
            System.out.println(fileName);
            /*
            SubFile
            TestFile1.txt
             */
        }
        System.out.println();

        File[] fileList = f2.listFiles();
        for(File file : fileList){
            System.out.println(file);
            /*
            D:\WorkSpace\WorkSpace_idea\File\SubFile
            D:\WorkSpace\WorkSpace_idea\File\TestFile1.txt
             */
        }

        System.out.println("5*******************************");
        /*
        public boolean isDirectory()：判断是否是文件目录
        public boolean isFile() ：判断是否是文件
        public boolean exists() ：判断是否存在
        public boolean canRead() ：判断是否可读
        public boolean canWrite() ：判断是否可写
        public boolean isHidden() ：判断是否隐藏
         */

        System.out.println(f1.isDirectory());//false
        System.out.println(f1.isFile());//true
        System.out.println(f1.exists());//true
        System.out.println(f1.canRead());//true
        System.out.println(f1.canWrite());//true
        System.out.println(f1.isHidden());//false

    }
}
