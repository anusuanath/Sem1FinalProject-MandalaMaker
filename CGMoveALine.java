import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CGMoveALine extends JFrame {

    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 250;
 
    private int x1 = 10;
    private int y1 = 50;
    private int x2 = 10;
    private int y2 = 100;
 
    private DrawCanvas canvas;

    public CGMoveALine() {

	JPanel btnPanel = new JPanel(new FlowLayout());
	JLabel lAxes = new JLabel("# Axes");
	JTextField txtAxes = new JTextField(2);
	JLabel lColor = new JLabel("Color: ");

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
	
	btnPanel.add(lAxes);
	btnPanel.add(txtAxes);
	btnPanel.add(lColor);
	btnPanel.add(colorpane);
	
	/*
	btnLeft.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		    x1 -= 10;
		    x2 -= 10;
		    canvas.repaint();
		    requestFocus();
		}
	    });
	*/

	canvas = new DrawCanvas();
	canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
 
	Container pane = getContentPane();
	pane.setLayout(new BorderLayout());
	pane.add(canvas, BorderLayout.CENTER);
	pane.add(btnPanel, BorderLayout.SOUTH);
 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Mandala Maker");
	pack();
	setVisible(true);
	requestFocus();
   }
 
    class DrawCanvas extends JPanel {
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    setBackground(Color.WHITE);
	    g.setColor(Color.BLACK);
	    g.drawLine(x1, y1, x2, y2);
	}
    }
 
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		    new CGMoveALine();
		}
	    });
    }
}
