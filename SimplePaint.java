import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* http://math.hws.edu/eck/cs124/javanotes6/source/SimplePaint.java  */

public class SimplePaint extends JApplet {
   
    public static void main(String[] args) {
	JFrame window = new JFrame("Simple Paint");
	SimplePaintPanel content = new SimplePaintPanel();
	window.setContentPane(content);
	window.setSize(600,480);
	window.setLocation(100,100);
	window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	window.setVisible(true);
    }
   
    public void init() {
	setContentPane(new SimplePaintPanel());
    }

    public static class SimplePaintPanel extends JPanel implements MouseListener, MouseMotionListener {
	private final static int BLACK = 0,
	    RED = 1,     
	    GREEN = 2,   
	    BLUE = 3, 
	    CYAN = 4,   
	    MAGENTA = 5,
	    YELLOW = 6;
	private int currentColor = BLACK;      
	private int prevX, prevY;      
	private boolean dragging;
	private Graphics graphicsForDrawing;
	
	SimplePaintPanel() {
	    setBackground(Color.WHITE);
	    addMouseListener(this);
	    addMouseMotionListener(this);
	}

	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    int width = getWidth();
	    int height = getHeight();
	    int colorSpacing = (height - 56) / 7;
         
	    g.setColor(Color.GRAY);
	    g.drawRect(0, 0, width-1, height-1);
	    g.drawRect(1, 1, width-3, height-3);
	    g.drawRect(2, 2, width-5, height-5);
	    
	    g.fillRect(width - 56, 0, 56, height);
         
	    g.setColor(Color.WHITE);
	    g.fillRect(width-53,  height-53, 50, 50);
	    g.setColor(Color.BLACK);
	    g.drawRect(width-53, height-53, 49, 49);
	    g.drawString("CLEAR", width-48, height-23);
	    
	    g.setColor(Color.BLACK);
	    g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.RED);
	    g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.GREEN);
	    g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.BLUE);
	    g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.CYAN);
	    g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.MAGENTA);
	    g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
	    g.setColor(Color.YELLOW);
	    g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);
         
	    g.setColor(Color.WHITE);
	    g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	    g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);    
	}

	private void changeColor(int y) {   
	    int width = getWidth();
	    int height = getHeight();
	    int colorSpacing = (height - 56) / 7;
	    int newColor = y / colorSpacing;
	    
	    if (newColor < 0 || newColor > 6) {
		return;
	    }
	    
	    Graphics g = getGraphics();
	    g.setColor(Color.GRAY);
	    g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	    g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
	    currentColor = newColor;
	    g.setColor(Color.WHITE);
	    g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
	    g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
	    g.dispose();      
	}
	
	private void setUpDrawingGraphics() {
	    graphicsForDrawing = getGraphics();
	    switch (currentColor) {
	    case BLACK:
		graphicsForDrawing.setColor(Color.BLACK);
		break;
	    case RED:
		graphicsForDrawing.setColor(Color.RED);
		break;
	    case GREEN:
		graphicsForDrawing.setColor(Color.GREEN);
		break;
	    case BLUE:
		graphicsForDrawing.setColor(Color.BLUE);
		break;
	    case CYAN:
		graphicsForDrawing.setColor(Color.CYAN);
		break;
	    case MAGENTA:
		graphicsForDrawing.setColor(Color.MAGENTA);
		break;
	    case YELLOW:
		graphicsForDrawing.setColor(Color.YELLOW);
		break;
	    }
	}
	
	public void mousePressed(MouseEvent e) {    
	    int x = e.getX();
	    int y = e.getY();

	    int width = getWidth();
	    int height = getHeight();
	    
	    if (dragging == true) {
		return;
	    }	 

	    if (x > width - 53) {
		if (y > height - 53) {
		    repaint();
		}
		else {
		    changeColor(y);
		}
	    }

	    else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
		prevX = x;
		prevY = y;
		dragging = true;
		setUpDrawingGraphics();
	    }     
	}      

       public void mouseReleased(MouseEvent evt) {
	   if (dragging == false) {
	       return;
	   }
	   dragging = false;
	   graphicsForDrawing.dispose();
	   graphicsForDrawing = null;
       }
      
	public void mouseDragged(MouseEvent e) {     
	   if (dragging == false) {
	       return;
	   }

	   int x = e.getX();
	   int y = e.getY();
         
	   if (x < 3) {
	       x = 3;
	   }
	
	   if (x > getWidth() - 57) {
	       x = getWidth() - 57;
	   }
	
	   if (y < 3) {
	       y = 3;
	   }

	   if (y > getHeight() - 4) {
	       y = getHeight() - 4;
	   }

	   graphicsForDrawing.drawLine(prevX, prevY, x, y); 
         
	   prevX = x;
	   prevY = y;
	}
       
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseMoved(MouseEvent evt) { }
    }
}
