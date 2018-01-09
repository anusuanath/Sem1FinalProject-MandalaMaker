import java.awt.*;
import javax.swing.*;
  
public class PanelTest
{
    public static void main(String[] args)
    {
        CustomComponent cc = new CustomComponent();
        cc.setBackground(Color.blue);
        JPanel lo = new JPanel();
        System.out.println("panel isOpaque = " + lo.isOpaque() + "\n" +
                           "background color = " + lo.getBackground() + "\n" +
                           "layout = " + lo.getLayout());
        lo.setBackground(Color.red);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = gbc.BOTH;
        gbc.gridwidth = gbc.REMAINDER;
        panel.add(cc, gbc);
        panel.add(lo, gbc);
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(panel);
        f.setSize(400,400);
        f.setLocation(200,200);
        f.setVisible(true);
    }
}
