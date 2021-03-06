//Start of the animation class

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

/**
 * Created by rob on 5/4/18.
 */
public class MainHost extends JPanel implements ActionListener, KeyListener {

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
    int botlag = 1;
    int dd = 0;
    int thrust = 7;
    private int port = 5555;
    private java.util.List<PrintStream> clients;
    private Player[] players = new Player[2];
    private int[] mycoords = new int[10];
    private int[] theircoords = new int[4];
    ServerSocket server;
    Boolean connected = false;
    private Socket client;


    private boolean[] keys;


    public MainHost(int w, int h) throws IOException {

        players[0] = new Player(1200, h / 2, true, 1);
        players[1] = new Player(200, h / 2, false, 2);
        MouseX = 1200;
        MouseY = h / 2;
        setSize(w, h);
        timer = new Timer(1000 / 60, this);
        timer.start();
        addKeyListener(this);
        puck = new Puck(getWidth() / 2, getHeight() / 2, getWidth(), getHeight());
        keys = new boolean[256];
        goal1 = new Goal(1440 - 150, 150, false);
        goal2 = new Goal(-30, 150, true);
        players[0].move(1200, h / 2, puck, 0, 0);

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
            System.out.println("Server running...");
            server = new ServerSocket(port) {
                protected void finalize() throws IOException {
//                    this.close();
                }
            };
            client = server.accept();
            System.out.println("Connection from: " + server.getLocalSocketAddress());
            connected = true;

        System.out.println("loop broken");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (connected) {
            players[0].move(MouseX, MouseY, puck, playerspeedx, playerspeedy); //keep
            players[1].move(theircoords[0], theircoords[1], puck, theircoords[2], theircoords[3]);
            puck.move(players); // keep
//            if (puck.getXa() == 0 && puck.getX() < 720) {
//                puck.xa = 10;
//            }
            repaint();
            mycoords[0] = players[0].getX();
            mycoords[1] = players[0].getY();
            mycoords[2] = players[0].getPlayerspeedx();
            mycoords[3] = players[0].getPlayerspeedy();
            mycoords[4]=puck.getX();
            mycoords[5]=puck.getY();
            mycoords[6]=puck.getXa();
            mycoords[7]=puck.getYa();
            mycoords[8]=players[0].getPoints();
            mycoords[9]=players[0].getPoints1();

            if (server != null && !server.toString().equals("ServerSocket[addr=0.0.0.0/0.0.0.0,localport=5555]"))
                System.out.println("Connection from: " + server.toString());

            try {
                transfer(client);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
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

    public void transfer(Socket client) throws IOException {
        if (server != null) {
            OutputStream socketStream;
            ObjectOutputStream objectOutput;
//            System.out.println("Connection established with client: " + client.getInetAddress().getHostAddress());
            socketStream = client.getOutputStream();
            objectOutput = new ObjectOutputStream(socketStream);
            objectOutput.writeObject(mycoords);
            if (client.getInputStream() != null) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
                    theircoords=(int[]) ois.readObject();
//                    System.out.println(theircoords[0]);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        else
            System.out.println("Null server");


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

        if (players[0].getPoints() > players[0].getPoints1()) {
            g2.drawRect(1100, 100, 20, 20);
        } else if (players[0].getPoints() < players[0].getPoints1()) {
            g2.drawRect(250, 75, 20, 20);
        }

        players[0].draw(g2);
        players[1].draw(g2);
        puck.draw(g2);
        goal1.draw(g2);
        goal2.draw(g2);
        g2.setColor(new Color(0x5C6367));
        g2.setStroke(new BasicStroke(2));

//        g2.drawLine(0, puck.getY(), getWidth(), puck.getY() );


    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        JFrame window = new JFrame("Air Hockey Battle Royale HOST!");
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int w = 1440;
        int h = 800;
        window.setBounds(0, 0, w, h + 22); //(x, y, w, h) 22 due to title bar.

        JPanel panel = new MainHost(w, h);

        panel.setFocusable(true);
        panel.grabFocus();

        window.add(panel);
        window.setVisible(true);

    }

}