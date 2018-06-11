//Start of the animation class

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by michael_hopps on 5/4/18.
 */
public class Main extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Puck puck;
    private int MouseX;
    private int MouseY;
    private ArrayList<Point> points = new ArrayList();
    private int playerspeedx = 0;
    private int playerspeedy = 0;
    private int puckweight = 6;
    private int time = 20;
    private Goal goal1, goal2;
    int botlag=3;
    int dd=0;
    int thrust=7;


    private Player[] players=new Player[2];


    private boolean[] keys;


    public Main(int w, int h) {

        players[0]=new Player(1200, h/2, true);
        players[1]=new Player(200, h/2, false);
        MouseX=1200;
        MouseY=h/2;
        setSize(w, h);
        timer = new Timer(1000 / 60, this);
        timer.start();
        addKeyListener(this);
        puck = new Puck(getWidth() / 2, getHeight() / 2, getWidth(), getHeight());
        keys = new boolean[256];
        goal1 = new Goal(1440 - 150, 150, false);
        goal2 = new Goal(-30, 150, true);
        players[0].move(1200, h/2, puck, 0, 0);



        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

//                System.out.println(e.getX() + " " + e.getY());

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                MouseX = e.getX();
                MouseY = e.getY();
                points.add(new Point(MouseX, MouseY));
                if (points.size() > time)
                    points.remove(0);
                playerspeedx = (int) (points.get(0).getX() - points.get(points.size() - 1).getX()) / puckweight;
                playerspeedy = (int) (points.get(0).getY() - points.get(points.size() - 1).getY()) / puckweight;
                if (playerspeedy > 0 && playerspeedy < 5) {
                    playerspeedy = thrust;
                }
                if (playerspeedy < 0 && playerspeedy > -5) {
                    playerspeedy = -thrust;
                }
                if (playerspeedx > 0 && playerspeedx < 5) {
                    playerspeedx = thrust;
                }
                if (playerspeedx < 0 && playerspeedx > -5) {
                    playerspeedx = -thrust;
                }


//                System.out.println(playerspeedx+" "+playerspeedy);


            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        players[0].move(MouseX, MouseY, puck, playerspeedx, playerspeedy); //keep
        if (dd==botlag) {
            players[1].move(200, puck.getY(), puck, -30, -5);
            dd=0;
        }
        dd++;
        puck.move(players); // keep
        if (puck.getXa()==0 && puck.getX()<720){
            puck.xa=10;
        }

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //left blank intentionally
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {


        if (keyEvent.getKeyCode() < 256)
            keys[keyEvent.getKeyCode()] = true;
        else
            keyEvent.consume();

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        puck.x = getWidth() / 2 + 100;
        puck.y = getHeight() / 2;
        puck.xa = 2;
        puck.ya = 2;
        if (keyEvent.getKeyCode() < 256)
            keys[keyEvent.getKeyCode()] = false;
        else
            keyEvent.consume();
    }

    //
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 188, 102));

        g2.fillRect(0, 0, 1440, 800);


        g2.setColor(Color.WHITE);

        g2.fillOval(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);

        g2.setStroke(new BasicStroke(8));

        g2.drawOval(getWidth() / 2 - 150, getHeight() / 2 - 150, 300, 300);

        g2.setStroke(new BasicStroke(12));

        g2.drawLine(720, 0, 720, 800);

        g2.setColor(Color.ORANGE);

        g2.setFont(new Font("Times new roman", Font.PLAIN, 44));

        g2.drawString("Points " + players[0].getPoints(), 1200, 100);

        g2.drawString("Points " + players[0].getPoints1(), 100, 100);

        g2.setColor(Color.BLUE);

        if (players[0].getPoints() > players[0].getPoints1()){
            g2.drawRect(1100, 100, 20, 20);
        }
        else if (players[0].getPoints() < players[0].getPoints1()){
            g2.drawRect(250, 75, 20, 20);
        }

        players[0].draw(g2);
        players[1].draw(g2);
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