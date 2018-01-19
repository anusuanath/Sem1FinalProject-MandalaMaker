import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MMaker extends JFrame {

    public static final int width = 750;
    public static final int height = 750;
    private Graphics g;
    private DrawCanvas canvas;
    
    public MMaker() {
	
	JPanel btnPanel = new JPanel(new FlowLayout());
	JLabel lAxes = new JLabel("# Axes");
	JTextField txtAxes = new JTextField(5);
	JLabel lColor = new JLabel("Color: ");
	JButton bClear = new JButton("Clear");
	
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
	colorpane.setPreferredSize(new Dimension(75, 100));

	btnPanel.setPreferredSize(new Dimension(150,750));
	btnPanel.add(lAxes);
	btnPanel.add(txtAxes);
	btnPanel.add(lColor);
	btnPanel.add(colorpane);
	btnPanel.add(bClear);
	
	canvas = new DrawCanvas();
	canvas.setPreferredSize(new Dimension(width, height));
		
	Container pane = getContentPane();
	pane.setLayout(new BorderLayout());
	pane.add(canvas, BorderLayout.CENTER);
	pane.add(btnPanel, BorderLayout.EAST);
 
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Mandala Maker");
	pack();
	setResizable(false);
	setVisible(true);
	requestFocus();
   }
 
    public static class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener {
	private int pX, pY;
	private boolean dragging;
	private Graphics g;

	DrawCanvas() {
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}
	
	private void setUp() {
	    g = getGraphics();
	    g.setColor(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int width = getWidth();
	    int height = getHeight();
	    
	    setBackground(Color.WHITE);
	    
	    g.setColor(Color.BLACK);
	    
	    g.drawLine(width/2, 0, width/2, height); //THIS IS Y-AXIS
	    g.drawLine(0, height/2, width, height/2); //THIS IS X-AXIS
	}

	public void mousePressed(MouseEvent e) {    
	    int x = e.getX();
	    int y = e.getY();

	    int width = getWidth();
	    int height = getHeight();
		
	    if (dragging == true) {
		return;
	    }
	    
	    if (x > width) {
		if (y > height) {
		    repaint();
		}
	    }

	    else if (x > 0 && x < width && y > 0 && y < height) {
		pX = x;
		pY = y;
		dragging = true;
		setUp();
	    }
	}

	public void mouseReleased(MouseEvent e) {
	   if (dragging == false) {
	       return;
	   }
	   dragging = false;
	   g.dispose();
	   g = null;
	}

	
	public void mouseDragged(MouseEvent e) {
	    int x = e.getX();
	    int y = e.getY();
	    
	    int width = getWidth();
	    int height = getHeight();
	    
	    int angle = 360/2;
	    double theta = Math.toRadians(angle);
	    
	    if (dragging == false) {
		return;
	    }

     	    g.drawLine(pX, pY, x, y);
	   
	    g.drawLine(((int)(pX * Math.cos(theta) - pY * Math.sin(theta)) + (width)),
		      ((int)(pX * Math.sin(theta) + pY * Math.cos(theta)) + (height)),
		      ((int)(x * Math.cos(theta) - y * Math.sin(theta)) + (width)),
		      ((int)(x * Math.sin(theta) + y * Math.cos(theta))) + (height));
	    pX = x;
	    pY = y;

	}
	
	public void mouseEntered(MouseEvent e) { }
	public void mouseExited(MouseEvent e) { }
	public void mouseClicked(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }
	
    }
 
    public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
		    new MMaker();
		}
	    });
    }
}
