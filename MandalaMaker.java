import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/* http://math.hws.edu/eck/cs124/javanotes6/source/SimplePaint.java  */

public class MandalaMaker extends JApplet {
   
    public static void main(String[] args) {
	JFrame window = new JFrame("Mandala Maker");
	panel content = new panel();
	window.setContentPane(content);
	window.setSize(556,500);
	window.setLocation(100,100);
	window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	window.setVisible(true);
    }
   
    public void init() {
	setContentPane(new panel());
    }

    public static class panel extends JPanel implements MouseListener, MouseMotionListener {
	private final static int BLACK = 0,
	    RED = 1,     
	    GREEN = 2,   
	    BLUE = 3, 
	    CYAN = 4,   
	    MAGENTA = 5,
	    YELLOW = 6;
	private int currentColor = BLACK;      
	private int pX, pY;      
	private boolean dragging;
	private Graphics 
	   pX = x;
	   pY = y;
	}
       
	public void mouseEntered(MouseEvent evt) { }
	public void mouseExited(MouseEvent evt) { }
	public void mouseClicked(MouseEvent evt) { }
	public void mouseMoved(MouseEvent evt) { }
    }
}
