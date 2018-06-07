import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

/**
 * Created by michael_hopps on 5/29/18.
 */
public class Player {

    private int x, y, Radius=50;

    public Player(int x, int y) {

        this.x = x;

        this.y = y;

    }


    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2){

        g2.setColor(Color.ORANGE);
        g2.fillOval(x-Radius, y-Radius, Radius*2, Radius*2);
        g2.setColor(new Color(186, 0, 180));
        g2.fillOval(x, y, 5, 5);

    }

    public void move(int MouseX, int MouseY){
        if(MouseX > 720+Radius && MouseX < 1340+Radius) {

            x = MouseX;

        } else if(MouseX < 720+Radius) {

            x = 720+Radius;
        } else {

            x = 1340+Radius;

        }

        if(MouseY < 700+Radius && MouseY>Radius) {

            y = MouseY;

        } else if(MouseY > 700+Radius){

            y=700+Radius;

        }

        else if(MouseY<Radius){

            y = Radius;
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
