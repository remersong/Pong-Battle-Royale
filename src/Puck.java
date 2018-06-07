import java.awt.*;
import java.awt.geom.Ellipse2D;

import javax.swing.JOptionPane;

public class Puck {
    private static final int Radius = 15;
    public int x, y, Width, Height, xa = 0, ya = 0;
    int dc;
    int air=10;
    int collisionPointX=0;
    int collisionPointY=0;
    int puckweight=70;
    int maxspeed=35;

    public Puck(int x, int y, int Width, int Height) {
        this.x = x;
        this.y = y;
        this.Width=Width;
        this.Height=Height;

    }

    public void move(Player p, int playerspeedx, int playerspeedy) {
        checkCollision(p, playerspeedx, playerspeedy);
        if (xa>maxspeed){
            xa=maxspeed;
        }
        if (xa<-maxspeed){
            xa=-maxspeed;
        }
        if (ya>maxspeed){
            ya=maxspeed;
        }
        if (ya<-maxspeed){
            ya=-maxspeed;
        }

        int a1=Math.abs(p.getX()-(x+xa));
        int b1=Math.abs(p.getY()-(y+xa));
        int distance1 = (int)Math.sqrt((a1*a1) + (b1*b1));


        if (distance1 > (Radius + p.getRadius()) ) {
            x += xa;
            y += ya;
        }
        else {
            x += xa;  //figure out how to prevent ball from going inside circle
            y += ya;
        }
        dc++;
        if (dc==air){
            if (xa>0){
                xa--;
            }
            else if(xa<0){
                xa++;
            }
            if (ya>0){
                ya--;
            }
            else if(ya<0){
                ya++;
            }
            dc=0;
        }
        if (y < Radius) {
            if (xa>0){
                xa--;
            }
            else if(xa<0){
                xa++;
            }
            if (ya>0){
                ya--;
            }
            else if(ya<0){
                ya++;
            }
            y += 5;
            ya = -ya;
        }
        else if (y > Height-Radius) {
            if (xa>0){
                xa--;
            }
            else if(xa<0){
                xa++;
            }
            if (ya>0){
                ya--;
            }
            else if(ya<0){
                ya++;
            }
            y -= 5;
            ya = -ya;
        }
        if (x < Radius) {
            if (xa>0){
                xa--;
            }
            else if(xa<0){
                xa++;
            }
            if (ya>0){
                ya--;
            }
            else if(ya<0){
                ya++;
            }
            x += 5;
            xa = -xa;
        }
        else if (x > Width-Radius) {
            if (xa>0){
                xa--;
            }
            else if(xa<0){
                xa++;
            }
            if (ya>0){
                ya--;
            }
            else if(ya<0){
                ya++;
            }
            x -= 5;
            xa = -xa;
        }


    }

    public void checkCollision(Player player, int playerspeedx, int playerspeedy) {
        if (x + Radius + player.getRadius() > player.getX()
                && x < player.getX() + Radius + player.getRadius()
                && y + Radius + player.getRadius() > player.getY()
                && y < player.getY() + Radius + player.getRadius()) {
            int a=Math.abs(player.getX()-x);
            int b=Math.abs(player.getY()-y);
            int distance = (int)Math.sqrt((a*a) + (b*b));

            if (distance < (Radius + player.getRadius()) ) {
                //collision!
                collisionPointX = ((x * player.getRadius()) + (player.getX() * Radius)) / (Radius + player.getRadius());
                collisionPointY =  ((y * player.getRadius()) + (player.getY() * Radius)) / (Radius + player.getRadius());

                xa = -(xa * (puckweight-player.getRadius()) + (2 * player.getRadius() * playerspeedx)) / (puckweight + player.getRadius()); //use player speed x for player speed
                ya = -(ya * (puckweight-player.getRadius()) + (2 * player.getRadius() * playerspeedy)) / (puckweight + player.getRadius()); //use player speed y for player speed




                System.out.println("Collision");
            }
        }
    }



    public void draw(Graphics g) {
        g.setColor(new Color(39, 149, 186));
        g.fillOval(x-Radius, y-Radius, Radius*2, Radius*2);
        g.setColor(new Color(186, 0, 180));
        g.fillOval(x, y, 5, 5);
        g.setColor(new Color(56, 186, 8));

        g.fillOval(collisionPointX, collisionPointY, 5, 5);
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;

    }

    public void setX(int x){

        this.x = x;

    }

    public void setY(int y){

        this.y = y;

    }

    public void setXA(int xa){

        this.xa = xa;

    }

    public void setYA(int ya){

        this.ya = ya;

    }
}
