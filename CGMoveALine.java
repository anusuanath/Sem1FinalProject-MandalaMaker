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
    private int y2 = 60;
 
    private DrawCanvas canvas;

    public CGMoveALine() {

	JPanel btnPanel = new JPanel(new FlowLayout());
	JButton btnLeft = new JButton("Move Left ");
	btnPanel.add(btnLeft);
	btnLeft.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		    x1 -= 10;
		    x2 -= 10;
		    canvas.repaint();
		    requestFocus();
		}
	    });
	JButton btnRight = new JButton("Move Right");
	btnPanel.add(btnRight);
	btnRight.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
		    x1 += 10;
		    x2 += 10;
		    canvas.repaint();
		    requestFocus();
		}
	    });

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
	    setBackground(Color.BLACK);
	    g.setColor(Color.WHITE);
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
