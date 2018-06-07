import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by student on 5/30/18.
 */
public class Goal {

    private BufferedImage pic;
    private int x, y;

    public Goal(int x, int y, boolean isLeftGoal) {
        this.x = x;
        this.y = y;
        if(isLeftGoal == true){
            setPic("GoalLeft.png");
        }
        else setPic("GoalRight.png");

    }

    public void draw(Graphics2D g2){

        g2.drawImage(pic, x,y,null);
    }


    public void setPic(String fileName) {
        try {
            pic = ImageIO.read(new File("res/" + fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean contains (int ex, int why){
        Rectangle box = new Rectangle(x, y, pic.getWidth(), pic.getHeight());
        return box.contains(ex, why);
    }


//    public boolean scored{
//
//    }


}