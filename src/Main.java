//Start of the animation class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by michael_hopps on 5/4/18.
 */
public class Main extends JPanel implements ActionListener, KeyListener{

    private Timer timer;
    private Player player;

    private int MouseX=HEIGHT/2;
    private int MouseY=WIDTH/2;

    private boolean[] keys;



    public Main(int w, int h) {
        setSize(w, h);
        timer = new Timer(1000/60, this);
        timer.start();
        addKeyListener(this);
        player = new Player(getWidth()/2, getHeight()/2);
        keys = new boolean[256];
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                MouseX=e.getX();
                MouseY=e.getY();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move(MouseX, MouseY);

        repaint();

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //left blank intentionally
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() < 256)
            keys[keyEvent.getKeyCode()] = true;
        else
            keyEvent.consume();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() < 256)
            keys[keyEvent.getKeyCode()] = false;
        else
            keyEvent.consume();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);

        g2.fillRect(0, 0, 1440, 800);

        g2.setColor(Color.ORANGE);
        player.draw(g2);
        g2.fillOval(MouseX,MouseY, 50, 50);

    }

    public static void main(String[] args) {


        JFrame window = new JFrame("Dodge!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int w = 1440;
        int h = 800;
        window.setBounds(0, 0, w, h + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Main(w, h);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);

    }

}