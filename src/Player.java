import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by michael_hopps on 5/29/18.
 */
public class Player {

    private int x, y, Radius=100;

    public Player(int x, int y) {

        this.x = x;

        this.y = y;

    }


    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2){

        g2.setColor(Color.ORANGE);

        g2.fillOval(x, y, Radius, Radius);

    }

    public void move(int MouseX, int MouseY){
        if(MouseX > 720 && MouseX < 1340) {

            x = MouseX;

        } else if(MouseX < 720) {

            x = 720;

        } else {

            x = 1340;

        }

        if(MouseY < 700 && MouseY>0) {

            y = MouseY;

        } else if(MouseY > 700){

            y=700;

        }

        else if(MouseY<0){

            y = 0;
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
}
