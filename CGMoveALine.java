import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CGMoveALine extends JFrame {

    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 250;
 
    private int pX = 10;
    private int pY = 50;
    private int x = 10;
    private int y = 100;
    private boolean dragging;

    private Graphics g;
    
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
		    pX -= 10;
		    x -= 10;
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
 
    class DrawCanvas extends JPanel implements MouseMotionListener {
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    setBackground(Color.WHITE);
	    g.setColor(Color.BLACK);
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
		setUpDrawingGraphics();
	    }     
	}

	private void setUpDrawingGraphics() {
	    g = getGraphics();
	    g.setColor(Color.BLACK);
	}
	
	public void mouseReleased(MouseEvent e) {
	   if (dragging == false) {
	       return;
	   }
	   dragging = false;
       }
      
	public void mouseDragged(MouseEvent e) {
	    int width = getWidth();
	    int height = getHeight();
	    int angle = 360/2;
	    double theta = Math.toRadians(angle);
	    if (dragging == false) {
		return;
	    }

	    int x = e.getX();
	    int y = e.getY();

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
		    new CGMoveALine();
		}
	    });
    }
}
