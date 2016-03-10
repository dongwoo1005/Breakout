import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Dongwoo on 18/01/2016.
 */
public class Paddle {

    private int windowWidth = Constants.DEFAULT_WINDOW_WIDTH;
    private int windowHeight = Constants.DEFAULT_WINDOW_HEIGHT;
    private int xPos = Constants.DEFAULT_PADDLE_X, yPos = Constants.DEFAULT_PADDLE_Y;
    private int width, height;

    public int getX() {
        return xPos;
    }

    public void setX(int xPos) {
//        int max = windowWidth - width - 1;
//        if (xPos > max){
//            this.xPos = max;
//        } else {
            this.xPos = xPos;
//        }
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getY() {
        return yPos;
    }

    public void setY(int yPos) {
        this.yPos = yPos;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle2D bounds(){
        return new Rectangle2D.Double(xPos - width/2, yPos, width, height);
    }

    public void draw(Graphics2D g2){

        RoundRectangle2D paddle = new RoundRectangle2D.Double(xPos - width/2,yPos,width,height,height,height);
        g2.setPaint(Color.GRAY);
        g2.fill(paddle);
        g2.setColor(Color.WHITE);
        g2.draw(paddle);
    }

    public void resize(int w, int h){

        setX((int) (w*((double)xPos/windowWidth)));
        setY((int) (h*((double)Constants.DEFAULT_PADDLE_Y/Constants.DEFAULT_WINDOW_HEIGHT)));

        setWidth((int) (w*((double)Constants.DEFAULT_PADDLE_WIDTH/Constants.DEFAULT_WINDOW_WIDTH)));
        setHeight((int) (h*((double)Constants.DEFAULT_PADDLE_HEIGHT/Constants.DEFAULT_WINDOW_HEIGHT)));

        setWindowWidth(w);
        setWindowHeight(h);
    }
}
