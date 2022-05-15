package GuiNotes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GuiNotes {
    public static void main(String[] args) {
        new Window();
    }
}

class Window extends JFrame{
    public Window(){
        //初始化JFrame
        JFrame frame = new JFrame("注册");
        frame.setLayout(new BorderLayout());
        frame.setSize(450, 1000);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //初始化Panel
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();

        //设置字体
        Font font = new Font("微软雅黑", Font.BOLD, 15);

        //初始化控件
        JLabel lblImg = new JLabel(new ImageIcon("/Users/pers.ervinse/IdeaProjects/TestProject/class-exercise/src/exam/Gui/welcome.png"));
        lblImg.setPreferredSize(new Dimension(400, 200));
        lblImg.setBounds(0, 150, 300, 200);

        //添加label
        JLabel label1 = new JLabel("label-1");
        JLabel label2 = new JLabel("label-2");
        label1.setPreferredSize(new Dimension(150, 50));
        label2.setPreferredSize(new Dimension(150, 50));

        //text
        JTextField textField1 = new JTextField();
        JTextField textField2 = new JTextField();
        textField1.setPreferredSize(new Dimension(200, 25));
        textField2.setPreferredSize(new Dimension(200, 25));

        //添加JComboBox
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("comboBox1");
        comboBox.addItem("comboBox2");
        comboBox.setPreferredSize(new Dimension(200, 25));

        //radioButton
        JRadioButton radioButton1 = new JRadioButton("男", true);
        JRadioButton radioButton2 = new JRadioButton("女");
        radioButton1.setPreferredSize(new Dimension(100, 25));
        radioButton2.setPreferredSize(new Dimension(100, 25));

        //group
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(radioButton1);
        radioGroup.add(radioButton2);

        //checkBox
        JCheckBox checkBox1 = new JCheckBox("checkBox1");
        JCheckBox checkBox2 = new JCheckBox("checkBox2");
        JCheckBox checkBox3 = new JCheckBox("checkBox3");
        JCheckBox checkBox4 = new JCheckBox("checkBox4");
        checkBox1.setPreferredSize(new Dimension(100, 25));
        checkBox2.setPreferredSize(new Dimension(100, 25));
        checkBox3.setPreferredSize(new Dimension(100, 25));
        checkBox4.setPreferredSize(new Dimension(100, 25));

        //Button
        JButton btnOK = new JButton("确定");
        JButton btnCancel = new JButton("重置");
        btnOK.setFont(font);
        btnCancel.setFont(font);
        btnOK.setPreferredSize(new Dimension(100, 25));
        btnCancel.setPreferredSize(new Dimension(100, 25));

        //testButton
        JButton button1 = new JButton("Button1");
        JButton button2 = new JButton("Button2");
        JButton button3 = new JButton("Button3");
        JButton button4 = new JButton("Button4");
        button1.setFont(font);
        button2.setFont(font);
        button3.setFont(font);
        button4.setFont(font);
        button1.setPreferredSize(new Dimension(100,25));
        button2.setPreferredSize(new Dimension(100,25));
        button3.setPreferredSize(new Dimension(100,25));
        button4.setPreferredSize(new Dimension(100,25));

        //TextArea
        JTextArea txtInfo = new JTextArea();
        txtInfo.setPreferredSize(new Dimension(400, 200));
        JScrollPane scrollPane = new JScrollPane(txtInfo, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //p1添加图片
        panel1.add(lblImg);

        //p2添加控件
        panel2.add(label1);
        panel2.add(textField1);
        panel2.add(label2);
        panel2.add(textField2);
        panel2.add(radioButton1);
        panel2.add(radioButton2);
        panel2.add(comboBox);
        panel2.add(checkBox1);
        panel2.add(checkBox2);
        panel2.add(checkBox3);
        panel2.add(checkBox4);

        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);

        //p3添加控件
        panel3.add(btnOK);
        panel3.add(btnCancel);
        panel3.add(scrollPane);


        frame.add("North", panel1);
        frame.add("Center", panel2);
        frame.add("South", panel3);
        //窗口可见
        frame.setVisible(true);

        //动作事件
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button1ActionListener");
            }
        });

        button2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){
                    System.out.println("鼠标左键单击事件");
                }
                if(e.getButton() == MouseEvent.BUTTON2){
                    System.out.println("鼠标中键单击事件");
                }
                if(e.getButton() == MouseEvent.BUTTON3){
                    System.out.println("鼠标右键单击事件");
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("鼠标按下事件");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("鼠标释放事件");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入区域事件");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标移出区域事件");
            }
        });



    }

    //鼠标事件
    public void mouseClicked(MouseEvent e){

    }
}
