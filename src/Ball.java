import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Dongwoo on 18/01/2016.
 */
public class Ball {

    private int windowWidth=Constants.DEFAULT_WINDOW_WIDTH;
    private int windowHeight=Constants.DEFAULT_WINDOW_HEIGHT;
    private int xPos = Constants.DEFAULT_BALL_X, yPos = Constants.DEFAULT_BALL_Y;
    private int diameter = Constants.DEFAULT_BALL_DIAMETER;
    private int speedX = Constants.getSpeed(), speedY = Constants.getSpeed() * -1;

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

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public Rectangle2D bounds(){
        return new Rectangle2D.Double(xPos-diameter/2, yPos, diameter, diameter);
    }

    public void draw(Graphics2D g2){
        Ellipse2D ball = new Ellipse2D.Double(xPos-diameter/2 , yPos, diameter, diameter);
        g2.setColor(Color.WHITE);
        g2.fill(ball);
        g2.setColor(Color.BLACK);
        g2.draw(ball);
    }

    public void resize(int w, int h) {
        setxPos((int) (w*((double) xPos/windowWidth)));
        setyPos((int) (h*((double) yPos/windowHeight)));

        setDiameter((int) (w*((double)Constants.DEFAULT_BALL_DIAMETER/Constants.DEFAULT_WINDOW_WIDTH)));

        setWindowWidth(w);
        setWindowHeight(h);
    }
}
