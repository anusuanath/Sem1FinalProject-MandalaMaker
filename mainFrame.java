import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mainFrame extends JFrame implements MouseListener,MouseWheelListener,MouseMotionListener{
    private mainDraw draw;
    
    public void mouseEntered(MouseEvent e) {
	System.out.println("mouseEntered");
    }
    
    public void mouseExited(MouseEvent e) {
	System.out.println("mouseExited");
    }
    
    public void mouseMoved(MouseEvent e) {
	System.out.println("mouseMoved");
    }
    
    public void mouseDragged(MouseEvent e) {
	System.out.println("mouseDragged");

	if (e.getX() >= draw.x && e.getY() <= draw.y) {
	    draw.moveRight();
	    draw.moveUp();
	}
	else if (e.getX() >= draw.x && e.getY() > draw.y) {
	    draw.moveRight();
	    draw.moveDown();
	}
	else if (e.getX() < draw.x && e.getY() <= draw.y) {
	    draw.moveLeft();
	    draw.moveUp();
	}
	else if (e.getX() < draw.x && e.getY() > draw.y) {
	    draw.moveLeft();
	    draw.moveDown();
	}
    }
    
    public void mousePressed(MouseEvent e) {
	System.out.println("mousePressed");
	if (e.getX() >= draw.x && e.getY() <= draw.y) {
	    draw.moveRight();
	    draw.moveUp();
	}
	else if (e.getX() >= draw.x && e.getY() > draw.y) {
	    draw.moveRight();
	    draw.moveDown();
	}
	else if (e.getX() < draw.x && e.getY() <= draw.y) {
	    draw.moveLeft();
	    draw.moveUp();
	}
	else if (e.getX() < draw.x && e.getY() > draw.y) {
	    draw.moveLeft();
	    draw.moveDown();
	}
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    public void mouseClicked(MouseEvent e) {
	System.out.println("mouseClicked");
    }
    
    public void mouseWheelMoved(MouseWheelEvent e) {
	System.out.println("mouseWheel");

	if (e.getWheelRotation() < 0) {
	    draw.moveDown();
	}
	else {
	    draw.moveUp();
	}
    }

    public mainFrame(){
        this.draw=new mainDraw();
        addMouseListener(this);
	addMouseWheelListener(this);
	addMouseMotionListener(this);
    }

    public static void main(String[] args) {
	mainFrame frame = new mainFrame();
	frame.setTitle("mouse listener: draggable");
	frame.setResizable(false);
	frame.setSize(600, 600);
	frame.setMinimumSize(new Dimension(600, 600));
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//dont understand these 2 lines
	frame.getContentPane().add(frame.draw);
	frame.pack();
	frame.setVisible(true);
    }


}


