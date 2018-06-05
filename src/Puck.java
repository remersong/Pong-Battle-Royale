import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;

public class Puck {
    private static final int Radius = 30;
    private int x, y, Width, Height, xa = 0, ya = 0;

    public Puck(int x, int y, int Width, int Height) {
        this.x = x;
        this.y = y;

    }

    public void move(Player p, int playerspeedx, int playerspeedy) {
        x += xa;
        y += ya;


//        if (y < 0 || y > Height - Radius - 29)
//            ya = -ya;
//        if (x < 0 || x > Width - Radius - 29)
//            xa = -xa;

        checkCollision(p, playerspeedx, playerspeedy);
    }

    public void checkCollision(Player player, int playerspeedx, int playerspeedy) {
        if (x + Radius + player.getRadius() > player.getX()
                && x < player.getX() + Radius + player.getRadius()
                && y + Radius + player.getRadius() > player.getY()
                && y < player.getY() + Radius + player.getRadius()) {
            int distance = (int) Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * ((y - player.getY())));

            if (distance < Radius + player.getRadius()) {
                //collision!
                int collisionPointX = ((x * player.getRadius()) + (player.getX() * Radius)) / (Radius + player.getRadius());
                int collisionPointY =  ((y * player.getRadius()) + (player.getY() * Radius)) / (Radius + player.getRadius());
                xa = (int)(xa * (Radius-player.getRadius()) + (2 * player.getRadius() * playerspeedx)) / (Radius + player.getRadius()); //use player speed x for player speed
                ya = (int)(ya * (Radius-player.getRadius()) + (2 * player.getRadius() * playerspeedy)) / (Radius + player.getRadius()); //use player speed y for player speed

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
