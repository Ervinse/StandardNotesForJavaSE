import javax.swing.*;
import java.awt.*;

public class GUINotes {
    public static void main(String[] args) {
//        new GUIFrame();
//        new Border();
        new Grid();
    }
}
class GUIFrame extends JFrame{

    public GUIFrame(){
        //设置布局
        setLayout(new FlowLayout());
        //设置标题
        setTitle("JFrame窗口");
        //设置大小
        setSize(500,500);
        //设置可见性
        setVisible(true);
        //设置窗口位置
        setLocation(400,200);
        //设置默认关闭选项
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置能否调整尺寸
//        setResizable(false);

        this.add(new Button("+"));
    }
}
class Border extends JFrame{

    public Border(){
        setLayout(new BorderLayout());
        setTitle("边界布局");
        setSize(500,500);
        setVisible(true);
        setLocation(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanelNorth = new JPanel();
        JPanel jPanelSouth = new JPanel();
        jPanelNorth.add(new Button("Button1"));
        jPanelNorth.add(new Button("Button2"));
        jPanelNorth.add(new Button("Button3"));
        jPanelSouth.add(new Button("Button4"));
        jPanelSouth.add(new Button("Button5"));
        jPanelSouth.add(new Button("Button6"));

        add(jPanelNorth,BorderLayout.NORTH);
        add(jPanelSouth,BorderLayout.SOUTH);
        add(new Button("Button7"),BorderLayout.CENTER);
    }
}

class Grid extends JFrame{
    public Grid(){
        setTitle("边界布局");
        setSize(400,400);
        setLocation(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponent();

        setVisible(true);
    }

    private void addComponent(){
        JPanel jPanelText = new JPanel();//文字面板
        JPanel jPanelBtn = new JPanel(new GridLayout(4, 4));//按钮面板，设置4x4的网格布局

        //将组件添加到面板中
        jPanelText.add(new JTextField(30));
        jPanelBtn.add(new Button("1"));
        jPanelBtn.add(new Button("2"));
        jPanelBtn.add(new Button("3"));
        jPanelBtn.add(new Button("+"));
        jPanelBtn.add(new Button("4"));
        jPanelBtn.add(new Button("5"));
        jPanelBtn.add(new Button("6"));
        jPanelBtn.add(new Button("-"));
        jPanelBtn.add(new Button("7"));
        jPanelBtn.add(new Button("8"));
        jPanelBtn.add(new Button("9"));
        jPanelBtn.add(new Button("*"));
        jPanelBtn.add(new Button("0"));
        jPanelBtn.add(new Button("."));
        jPanelBtn.add(new Button("="));
        jPanelBtn.add(new Button("/"));

        //将面板添加到框架中
        add(jPanelText,BorderLayout.NORTH);
        add(jPanelBtn);

    }

}