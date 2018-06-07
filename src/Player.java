import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by michael_hopps on 5/29/18.
 */
public class Player {

    private int x, y, Radius = 50, points1, points, playerspeedx=0,playerspeedy=0;

    public Player(int x, int y) {

        this.x = x;

        this.y = y;

    }


    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2) {

        g2.setColor(Color.ORANGE);
        g2.fillOval(x - Radius, y - Radius, Radius * 2, Radius * 2);
        g2.setColor(new Color(186, 0, 180));
        g2.fillOval(x, y, 5, 5);

    }

    public void move(int MouseX, int MouseY, Puck puck, int psx, int psy) {
        playerspeedx=psx;
        playerspeedy=psy;
        if (MouseX > 720 + Radius && MouseX < 1340 + Radius) {

            x = MouseX;

        } else if (MouseX < 720 + Radius + 1) {

            x = 720 + Radius;
        } else {

            x = 1340 + Radius;

        }

        if (MouseY < 700 + Radius && MouseY > Radius) {

            y = MouseY;

        } else if (MouseY > 700 + Radius) {

            y = 700 + Radius;

        } else if (MouseY < Radius) {

            y = Radius;
        }

        if (puck.getY() > 162 && puck.getY() < 640 && puck.getX() > 1297) {

            points1++;

            puck.setX(720);

            puck.setY(400);

            puck.setYA(0);

            puck.setXA(0);

        }

        if (puck.getY() > 162 && puck.getY() < 640 && puck.getX() < 139) {

            points++;

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
