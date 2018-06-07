//Start of the animation class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 5/4/18.
 */
public class Main extends JPanel implements ActionListener, KeyListener{

    private Timer timer;
    private Player player;
    private Puck puck;
    private int MouseX=HEIGHT/2;
    private int MouseY=WIDTH/2;
    private ArrayList<Point> points= new ArrayList();
    private int playerspeedx=0;
    private int playerspeedy=0;
    private int puckweight=6;
    private int time=20;
    private Goal goal1, goal2;



    private boolean[] keys;


    public Main(int w, int h) {
        setSize(w, h);
        timer = new Timer(1000/60, this);
        timer.start();
        addKeyListener(this);
        player = new Player(getWidth()/2, getHeight()/2);
        puck = new Puck(getWidth()/2, getHeight()/2, getWidth(), getHeight());
        keys = new boolean[256];
        goal1 = new Goal(1440-150, 150, false);
        goal2 = new Goal(-30, 150, true);


        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                    MouseX = e.getX();
                    MouseY = e.getY();
                    points.add(new Point(MouseX, MouseY));
                    if(points.size()>time)
                        points.remove(0);
                    playerspeedx=(int)(points.get(0).getX() - points.get(points.size()-1).getX()) / puckweight;
                    playerspeedy=(int)(points.get(0).getY() - points.get(points.size()-1).getY()) / puckweight;
                    if (playerspeedy==0){
                        playerspeedy--;
                    }
                    if (playerspeedx==0){
                        playerspeedx--;
                    }

                System.out.println(playerspeedx+" "+playerspeedy);
                }
        });

}

    @Override
    public void actionPerformed(ActionEvent e) {

        player.move(MouseX, MouseY);
        puck.move(player, playerspeedx, playerspeedy);

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
        puck.x=getWidth()/2+100;
        puck.y=getHeight()/2;
        puck.xa=2;
        puck.ya=2;
        if(keyEvent.getKeyCode() < 256)
            keys[keyEvent.getKeyCode()] = false;
        else
            keyEvent.consume();
    }
//
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.black);

        g2.fillRect(0, 0, 1440, 800);

        g2.setColor(Color.WHITE);

        g2.setStroke(new BasicStroke(6));

        g2.drawLine(720, 0, 720, 800);

        g2.setColor(Color.ORANGE);
        player.draw(g2);
        puck.draw(g2);
        goal1.draw(g2);
        goal2.draw(g2);


    }

    public static void main(String[] args) {


        JFrame window = new JFrame("Air Hockey Battle Royale!");
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