import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Dongwoo on 24/01/2016.
 */
public class Lives extends Ball {

    private int windowWidth=Constants.DEFAULT_WINDOW_WIDTH;
    private int windowHeight=Constants.DEFAULT_WINDOW_HEIGHT;

    private int xPos = Constants.DEFAULT_LIVES_X, yPos = Constants.DEFAULT_LIVES_Y;
    private int diameter = Constants.DEFAULT_BALL_DIAMETER;
    private int space = Constants.DEFAULT_LIVES_SPACE;

    private int numLives;

    public Lives(int numLives) {
        this.numLives = numLives;
    }

    public int getNumLives() {
        return numLives;
    }

    public void setNumLives(int numLives) {
        this.numLives = numLives;
    }

    @Override
    public int getWindowWidth() {
        return windowWidth;
    }

    @Override
    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    @Override
    public int getWindowHeight() {
        return windowHeight;
    }

    @Override
    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }

    @Override
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    @Override
    public int getDiameter() {
        return diameter;
    }

    @Override
    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public void draw(Graphics2D g2){

        for (int i=0; i<numLives; ++i){
            Ellipse2D ball = new Ellipse2D.Double(xPos + i* (diameter + space), yPos, diameter, diameter);
            g2.setColor(Color.WHITE);
            g2.fill(ball);
            g2.setColor(Color.BLACK);
            g2.draw(ball);
        }
    }

    public void resize(int w, int h){
        setxPos(w*Constants.DEFAULT_LIVES_X/Constants.DEFAULT_WINDOW_WIDTH);
        setyPos(h*Constants.DEFAULT_LIVES_Y/Constants.DEFAULT_WINDOW_HEIGHT);

        setDiameter(w * Constants.DEFAULT_BALL_DIAMETER / Constants.DEFAULT_WINDOW_WIDTH);
        setSpace(w * Constants.DEFAULT_LIVES_SPACE/Constants.DEFAULT_WINDOW_WIDTH);

        setWindowWidth(w);
        setWindowHeight(h);
    }
}
