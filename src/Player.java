import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by michael_hopps on 5/29/18.
 */
public class Player {

    private int x, y, WIDTH=50, HEIGHT=50;

    public Player(int x, int y) {

        this.x = x;

        this.y = y;

    }

    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2){

        g2.setColor(Color.ORANGE);

        g2.fillOval(x, y, 100, 100);

    }

    public void move(int MouseX, int MouseY){

        if(MouseX > 720 && MouseX < 1340) {

            x = MouseX;

        }

        if(MouseY < 700) {

            y = MouseY;

        }

    }

    public Ellipse2D getBounds() {

        return new Ellipse2D.Float(x, y, WIDTH, HEIGHT) {

        };

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
