//Start of the animation class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by michael_hopps on 5/4/18.
 */
public class Main extends JPanel implements ActionListener, KeyListener{

    private Timer timer;
    private Player player;

    private boolean[] keys;

    public Main(int w, int h) {
        setSize(w, h);
        timer = new Timer(1000/60, this);
        timer.start();
        addKeyListener(this);
        player = new Player(getWidth()/2, getHeight()/2);
        keys = new boolean[256];
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move(keys);


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

        player.draw(g2);
    }

    public static void main(String[] args) {


        JFrame window = new JFrame("Dodge!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int w = 800;
        int h = 800;
        window.setBounds(0, 0, w, h + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new Main(w, h);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);
    }
}