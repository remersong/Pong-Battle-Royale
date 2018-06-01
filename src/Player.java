import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by michael_hopps on 5/29/18.
 */
public class Player {

    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //TODO: implement using an image instead of this rect
    public void draw(Graphics2D g2){

        if(x > 1440 - 100){

            x = 1340;
        }

        if(x < 0){

            x = 0;
        }

        if(y < 0){

            y = 0;
        }

        if(y > 800 - 100){

            y = 700;
        }

        g2.setColor(Color.black);

        g2.fillRect(0, 0, 1440, 800);

        g2.setColor(Color.ORANGE);

        g2.fillOval(x, y, 100, 100);

    }

    public void move(boolean[] keys){

        double dx = 0, dy = 0;
        if(keys[KeyEvent.VK_W])
            dy = -1;
        if(keys[KeyEvent.VK_S])
            dy = 1;
        if(keys[KeyEvent.VK_A])
            dx = -1;
        if(keys[KeyEvent.VK_D])
            dx = 1;

        if(dy != 0 && dx != 0){
            dy = dy / Math.sqrt(2);
            dx = dx / Math.sqrt(2);
        }
        double speed = 20;
        move(dx*speed, dy*speed);

    }

    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
