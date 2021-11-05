package Views;

import javax.swing.JFrame;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(int width, int height) {
        setTitle("Pharmacy.Id");
        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
    }
}
