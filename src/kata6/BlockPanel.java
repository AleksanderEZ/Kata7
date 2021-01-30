package kata6;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata6.view.BlockDisplay;

public class BlockPanel extends JPanel implements BlockDisplay{

    private static final int SIZE = 100;
    private int x = 0;
    private int y = 0;
    private int bounds;
    private Moved moved;

    public BlockPanel(int max) {
        this.bounds = max;
        MouseHandler handler = new MouseHandler();
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        
        g.setColor(Color.black);
        
        int max = bounds*SIZE;
        for(int i = 0; i <= max; i+=SIZE){
            g.drawLine(i, 0, i, max);
            g.drawLine(0, i, max, i);
        }
        
        g.setColor(Color.green);
        g.fillRect(x * SIZE, y * SIZE, SIZE, SIZE);
    }

    @Override
    public void show(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void register(Moved moved) {
        this.moved = moved;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener{

        private boolean grabbed = true;
        private boolean exit = false;
        
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getX()/SIZE < x || e.getY()/SIZE < y ||
                    e.getX()/SIZE > x || e.getY()/SIZE > y) return;
            grabbed = true;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            grabbed = false;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (exit == true) exit = false; grabbed = true;
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (e.getX()/SIZE >= bounds || e.getY()/SIZE >= bounds ||
                    e.getY()/SIZE < 0 || e.getX()/SIZE < 0) grabbed = false; exit = true;
            if (grabbed && moved != null) moved.to(e.getX()/SIZE, e.getY()/SIZE);
            System.out.println(e.getX()/SIZE + "," + e.getY()/SIZE);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }
    }
}
