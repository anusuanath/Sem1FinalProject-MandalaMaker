import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MMaker extends JFrame implements ActionListener, ChangeListener {

    public static final int width = 750;
    public static final int height = 750;

    private String sN = "1";
    private int sn = Integer.parseInt(sN);
    private int angle = 360 / (2 * sn);

    private Color color = Color.BLACK;
    
    private DrawCanvas canvas;

    public void actionPerformed(ActionEvent e) {
	String s = e.getActionCommand();
	System.out.println(s);
	if(s.equals("Clear")) {
	    repaint();
	}
	else {
	    sendHelp(s);
	    System.out.println("sN: " + sN + "\tsn: " + sn + "\tangle: " + angle);
	}
    }

    public void stateChanged(ChangeEvent e) {
	color = colors.getColor();
    }

    private void sendHelp (String s) {
	sN = s;
	sn = Integer.parseInt(sN);
	angle = 360 / (2 * sn);
    }
    
    public MMaker() {
	
	JPanel btnPanel = new JPanel(new FlowLayout());
	JLabel lAxes = new JLabel("# Axes");
	JTextField txtAxes = new JTextField(sN,5);
	JLabel lColor = new JLabel("Color: ");
	JButton bClear = new JButton("Clear");

	JColorChooser colors = new JColorChooser();
	
	txtAxes.addActionListener(this);
	bClear.addActionListener(this);
	colors.addChangeListener(this);
	
	btnPanel.setPreferredSize(new Dimension(150,750));
	btnPanel.add(lAxes);
	btnPanel.add(txtAxes);
	btnPanel.add(bClear);
	btnPanel.add(colors);
	
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

    public class DrawCanvas extends JPanel implements MouseListener, MouseMotionListener {
	private int pX, pY;
	private boolean dragging;
	private Graphics g;

	DrawCanvas() {
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}
	
	private void setUp() {
	    g = getGraphics();
	    g.setColor(color);
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
	    
	    double theta = Math.toRadians(angle);
	    
	    if (dragging == false) {
		return;
	    }
	    
	    g.drawLine(pX, pY, x, y);
	    
	    int x1 = pX - width/2;
	    int y1 = pY - height/2;
	    
	    int x2 = x - width/2;
	    int y2 = y - height/2;
	    
	    for (int i = 1; i < (2 * sn); i++) {
		int x3 = ((int)(x1 * i * Math.cos(theta) - y1 * i * Math.sin(theta)));
		int y3 = ((int)(x1 * i * Math.sin(theta) + y1 * i * Math.cos(theta)));
		int x4 = ((int)(x2 * i * Math.cos(theta) - y2 * i * Math.sin(theta)));
		int y4 = ((int)(x2 * i * Math.sin(theta) + y2 * i * Math.cos(theta)));
		
		x1 = x3;
		y1 = y3;
		x2 = x4;
		y2 = y4;
		
		g.drawLine(x3 + width/2, y3 + width/2, x4 + width/2, y4 + width/2);
	    }
	    
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
