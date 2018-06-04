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

    public void move(boolean[] keys){

//        double dx = 0, dy = 0;
//
////        if(x<=1440 - 100 && x>0 && y>0 && y<800-100) {
//            if (keys[KeyEvent.VK_W] && y>0)
//                dy = -1;
//            if (keys[KeyEvent.VK_S] && y<800-100)
//                dy = 1;
//            if (keys[KeyEvent.VK_A] && x>0)
//                dx = -1;
//            if (keys[KeyEvent.VK_D] && x<1440-100)
//                dx = 1;
//
//            if (dy != 0 && dx != 0) {
//                dy = dy / Math.sqrt(2);
//                dx = dx / Math.sqrt(2);
//            }
//            double speed = 20;
//            move(dx * speed, dy * speed);

        }

//    }

    public void move(int MouseX, int MouseY){

//        x += dx;
//        y += dy;

        x = MouseX;

        y = MouseY;

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
