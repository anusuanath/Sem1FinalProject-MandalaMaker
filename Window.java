import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class Window extends JFrame {

    public Window() {

    }
    
    public static void main (String[] args) {
	Window pane = new Window();
	
	pane.setTitle("Mandala Maker");
	pane.setSize(700,700);
	pane.setLocation(0,0);
	pane.setDefaultCloseOperation(EXIT_ON_CLOSE);
	pane.setLayout(new GridBagLayout());

	MandalaMaker paint = new MandalaMaker();
	JLabel colorl = new JLabel("Color: ");
	JLabel axe = new JLabel("Axes: ");
	JTextField axes = new JTextField(10);
	JButton clear = new JButton("Clear");

	DefaultListModel colors = new DefaultListModel();
	colors.addElement("black");
	colors.addElement("white"); 
	colors.addElement("red");
	colors.addElement("yellow");
	colors.addElement("green");
	colors.addElement("blue");
	
	JList color = new JList(colors);
	color.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	color.setLayoutOrientation(JList.VERTICAL);
	color.setVisibleRowCount(-1);
	JScrollPane colorpane = new JScrollPane(color);
	colorpane.setPreferredSize(new Dimension(150, 50));

	pane.add(paint);
	pane.add(axe);
	pane.add(axes);
	pane.add(colorl);
	pane.add(colorpane);
	pane.add(clear);

	pane.setVisible(true);
    }
}
