import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class MMaker extends JFrame implements ActionListener, ChangeListener {

    public static final int width = 750;
    public static final int height = 750;

    private String sN = "1";
    private int sn = Integer.parseInt(sN);
    // private int angle = 360 / (2 * sn);
    
    private DrawCanvas canvas;
    
    public void actionPerformed(ActionEvent e) {
	String s = e.getActionCommand();
	System.out.println(s);
	if(s.equals("Clear")) {
	    repaint();
	}
	else {
	    sendHelp(s);
	    //System.out.println("sN: " + sN + "\tsn: " + sn + "\tangle: " + angle);
	}
    }

    private void sendHelp (String s) {
	sN = s;
	sn = Integer.parseInt(sN);
	//angle = 360 / (2 * sn);
    }
    
    public MMaker() {
	JPanel btnPanel = new JPanel(new FlowLayout());
	JLabel lAxes = new JLabel("# Axes");
	JTextField txtAxes = new JTextField(sN,5);
	JLabel lColor = new JLabel("Color: ");
	JButton bClear = new JButton("Clear");

	JColorChooser jcc = new JColorChooser();
	ColorSelectionModel model = jcc.getSelectionModel();
	
	txtAxes.addActionListener(this);
	bClear.addActionListener(this);
	model.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
		    System.out.println("Color: " + jcc.getColor());
		}
	    });
	
	btnPanel.setPreferredSize(new Dimension(150,750));
	btnPanel.add(lAxes);
	btnPanel.add(txtAxes);
	btnPanel.add(bClear);
	btnPanel.add(jcc);
	
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
	    g.setColor(Color.BLACK);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int width = getWidth();
	    int height = getHeight();
	    
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
	    
	    // double theta = Math.toRadians(angle);
	    
	    if (dragging == false) {
		return;
	    }
	    
	    g.drawLine(pX, pY, x, y);

	    if (sn == 1){
		g.drawLine(width/2, 0, width/2, height);
		    
		g.drawLine(width - pX, pY, width - x, y);
	    }
	    if (sn == 2){
		g.drawLine(width/2, 0, width/2, height);
		g.drawLine(0, height/2, width, height/2);
		    
		g.drawLine(pX, pY, x, y);
		g.drawLine(pX, height - pY, x, height - y);
		g.drawLine(width - pX, pY, width - x, y);
		g.drawLine(width - pX, height - pY, width - x, height - y);
	    }
	    if (sn == 4){
		g.drawLine(width/2, 0, width/2, height);
		g.drawLine(0, height/2, width, height/2);
		g.drawLine(0, 0, width, height);
		g.drawLine(0, height, width, 0);
		
		g.drawLine(pX, pY, x, y);
		g.drawLine(pX, height - pY, x, height - y);
		g.drawLine(width - pX, pY, width - x, y);
		g.drawLine(width - pX, height - pY, width - x, height - y);
		
		g.drawLine(pY, pX, y, x);
		g.drawLine(height - pY, pX, height - y, x);
		g.drawLine(pY, width - pX, y, width - x);
		g.drawLine(height - pY, width - pX, height - y, width - x);
	    }
	    /*
	    int x1 = pX - width/2;
	    int y1 = height/2 - pY;
	    
	    int x2 = x - width/2;
	    int y2 = height/2 - y;


	    System.out.println("#: " + 1 + ", " + 0 + "\n\tx1: " + x1 + "\n\twidth/2: " + (x1 + width/2) + "\n\ty1: " + y1 + "\n\theight/2: " + (y1 + height/2) +  "\n\tx2: " + x2 + "\n\twidth/2: " + (x2 + width/2) + "\n\ty2: " + y2 + "\n\theight/2: " + (y2 + height/2));
	    
	    for (int i = 1; i < (2 * sn); i++) {
		int x3 = ((int)(x1 * i * Math.cos(theta) - y1 * i * Math.sin(theta)));
		int y3 = ((int)(x1 * i * Math.sin(theta) + y1 * i * Math.cos(theta)));
		int x4 = ((int)(x2 * i * Math.cos(theta) - y2 * i * Math.sin(theta)));
		int y4 = ((int)(x2 * i * Math.sin(theta) + y2 * i * Math.cos(theta)));
		
		x1 = x3;
		y1 = y3;
		x2 = x4;
		y2 = y4;
		
		g.drawLine(x3 + width/2, y3 + height/2, x4 + width/2, y4 + height/2);

		System.out.println("#: " + (i + 1) + ", " + (i * angle) + "\n\tx3: " + x3 + "\n\twidth/2: " + (x3 + width/2) + "\n\ty3: " + y3 + "\n\theight/2: " + (y3 + height/2) +  "\n\tx4: " + x4 + "\n\twidth/2: " + (x4 + width/2) + "\n\ty4: " + y4 + "\n\theight/2: " + (y4 + height/2));

	    }
	    */
	    
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
