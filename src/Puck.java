import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;

public class Puck {
    private static final int Radius = 30;
    private int x, y, Width, Height, xa = 2, ya = 2;

    public Puck(int x, int y, int Width, int Height) {
        this.x = x;
        this.y = y;

    }

    public void move(Player p, int playerspeed) {
//        x += xa;
//        y += ya;


        if (y < 0 || y > Height - Radius - 29)
            ya = -ya;
        checkCollision(p, playerspeed);
    }

    public void checkCollision(Player player, int playerspeed) {
        if (x + Radius + player.getRadius() > player.getX()
                && x < player.getX() + Radius + player.getRadius()
                && y + Radius + player.getRadius() > player.getY()
                && y < player.getY() + Radius + player.getRadius()) {
            int distance = (int) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * ((y - player.getY())));

            if (distance < Radius + player.getRadius()) {
                //balls have collided
                System.out.println("Collision");
            }
        }
    }

    public Ellipse2D getBounds() {
        return new Ellipse2D.Float(x, y, Radius, Radius);
    }

    public void draw(Graphics g) {
        g.setColor(new Color(39, 149, 186));
        g.fillOval(x, y, Radius, Radius);
    }
}
