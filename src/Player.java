import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * Created by rob on 5/29/18.
 */
public class Player {

    private int x, y, startx, starty, Radius = 60, points1, points, playerspeedx=-10,playerspeedy=0;
    private int player;
    private boolean you;

    public Player(int x, int y, boolean you, int player) {
        this.you=you;
        if (you==true) {
            try {
                Robot r = new Robot();
                r.mouseMove(x, y);

            } catch (AWTException e) {
            }
        }
        this.x = x;
        this.player=player;
        this.y = y;
        startx = x;

        starty = y;

    }




    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2) {
        if (you)
            g2.setColor(new Color(14,0, 230));
        else
            g2.setColor(new Color(255,0, 44));

        g2.fillOval(x - Radius, y - Radius, Radius * 2, Radius * 2);
        g2.setColor(new Color(186, 0, 180));
        g2.fillOval(x, y, 5, 5);

    }

    public void move(int MouseX, int MouseY, Puck puck, int psx, int psy) {
        playerspeedx=psx;
        playerspeedy=psy;
        if (player==1) {
            if (MouseX > 720 + Radius && MouseX < 1340 + Radius) {

                x = MouseX;
            } else if (MouseX < 720 + Radius + 1) {

                x = 720 + Radius;
            } else {

                x = 1340 + Radius;

            }
        }
        if (player==2) {

            if (MouseX < 720 - Radius && MouseX > 0) {
                x = MouseX;
            } else if (MouseX > 720 - Radius - 1) {

                x = 720 - Radius;
            } else {

                x = 0;

            }
        }

        if (MouseY < 700 + Radius && MouseY > Radius) {

            y = MouseY;

        } else if (MouseY > 700 + Radius) {

            y = 700 + Radius;

        } else if (MouseY < Radius) {

            y = Radius;
        }
        if (!you){
            x=MouseX;
        }




        if (puck.getY() > 162 && puck.getY() < 640 && puck.getX() > 1297) {

            points1++;
            x=startx;
            y=starty;
//            if (you == true) {

                try {
                    Robot r = new Robot();
                    r.mouseMove(startx, starty);

                } catch (AWTException e) {
                }
//            }
            // no robots when testing
            x=startx;
            y=starty;

            puck.setX(720);

            puck.setY(400);

            puck.setYA(0);

            puck.setXA(0);

        }

        if (puck.getY() > 162 && puck.getY() < 640 && puck.getX() < 139) {

            points++;

            x = startx;
            y = starty;
//            if (you == true){
                try {
                    Robot r = new Robot();
                    r.mouseMove(startx, starty);

                } catch (AWTException e) {
                }
//        }
            x=startx;
            y=starty;

            puck.setX(720);

            puck.setY(400);

            puck.setYA(0);

            puck.setXA(0);



        }

    }

    public Ellipse2D getBounds() {

        return new Ellipse2D.Float(x, y, Radius, Radius) {

        };

    }

    public boolean contains(double x, double y){
        double dx = x - this.x;
        double dy = y - this.y;
        if( dx*dx + dy*dy < getRadius()*getRadius()){
            return true;
        }
        return false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return Radius;
    }

    public int getPoints1() {
        return points1;
    }

    public int getPoints() {
        return points;
    }

    public int getPlayerspeedx() {
        return playerspeedx;
    }

    public int getPlayerspeedy() {
        return playerspeedy;
    }
}
