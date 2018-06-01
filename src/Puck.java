import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Puck {
    private static final int WIDTH = 30, HEIGHT = 30;
    private int x, y, Width, Height, xa = 2, ya = 2;

    public Puck(int x, int y, int Width, int Height) {
        this.x=x;
        this.y=y;

    }

    public void move() {
        x += xa;
        y += ya;


        if (y < 0 || y > Height - HEIGHT - 29)
            ya = -ya;
        checkCollision();
    }

    public void checkCollision() {
        //if (game.getPanel().getPlayer(1).getBounds().intersects(getBounds()) || game.getPanel().getPlayer(2).getBounds().intersects(getBounds()))
            //xa = -xa;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void draw(Graphics g) {
        g.fillOval(x, y, WIDTH, HEIGHT);
    }
}