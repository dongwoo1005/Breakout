import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Dongwoo on 18/01/2016.
 */
public class Block {
    private int windowWidth=Constants.DEFAULT_WINDOW_WIDTH;
    private int windowHeight=Constants.DEFAULT_WINDOW_HEIGHT;

    private int blocksX=Constants.DEFAULT_BLOCKS_X;
    private int blocksY=Constants.DEFAULT_BLOCKS_Y;
    private int blocksWidth=Constants.DEFAULT_BLOCKS_WIDTH;
    private int blocksHeight=Constants.DEFAULT_BLOCKS_HEIGHT;
    private int numBlockRow=Constants.DEFAULT_NUM_BLOCK_ROW;
    private int numBlockCol=Constants.DEFAULT_NUM_BLOCK_COL;

    private int row, col;
    private int xPos, yPos, width, height;
    private boolean destroyed;
    private int type;

    public Block(int row, int col) {
        this.row = row;
        this.col = col;

        if (row == 0) {
            type = 1;
        } else if (row >= 1 && row <= 2){
            type = 2;
        } else if (row == 3){
            type = 3;
        } else if (row == 4){
            type = 4;
        } else if (row == 5){
            type = 5;
        } else if (row >= 6){
            type = 6;
        }

        width = blocksWidth/numBlockCol;
        height = blocksHeight/numBlockRow;
        xPos = blocksX + col*width;
        yPos = blocksY + row*height;
        destroyed = false;
    }

    public void setBlocksX(int blocksX) {
        this.blocksX = blocksX;
    }

    public void setBlocksY(int blocksY) {
        this.blocksY = blocksY;
    }

    public void setBlocksWidth(int blocksWidth) {
        this.blocksWidth = blocksWidth;
    }

    public void setBlocksHeight(int blocksHeight) {
        this.blocksHeight = blocksHeight;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Rectangle2D bounds(){
        return new Rectangle2D.Double(xPos,yPos,width,height);
    }

    public void draw(Graphics2D g2){
        Rectangle2D block = new Rectangle2D.Double(xPos,yPos,width,height);
        if (type == 1) {
            g2.setPaint(new Color(255,0,0));
        } else if (type == 2){
            g2.setPaint(new Color(255,130,64));
        } else if (type == 3){
            g2.setPaint(new Color(255,255,37));
        } else if (type == 4){
            g2.setPaint(new Color(0,198,0));
        } else if (type == 5){
            g2.setPaint(new Color(107,181,255));
        } else if (type == 6){
            g2.setPaint(new Color(249,0,249));
        }
        g2.fill(block);
        g2.setColor(Color.BLACK);
        g2.draw(block);
    }

    public void resize(int w, int h){

        setBlocksWidth(w*Constants.DEFAULT_BLOCKS_WIDTH/Constants.DEFAULT_WINDOW_WIDTH);
        setBlocksHeight(h*Constants.DEFAULT_BLOCKS_HEIGHT/Constants.DEFAULT_WINDOW_HEIGHT);
        setBlocksX(w*Constants.DEFAULT_BLOCKS_X/Constants.DEFAULT_WINDOW_WIDTH);
        setBlocksY(h*Constants.DEFAULT_BLOCKS_Y/Constants.DEFAULT_WINDOW_HEIGHT);

        setWidth(blocksWidth/numBlockCol);
        setHeight(blocksHeight/numBlockRow);

        setxPos(blocksX + col*width);
        setyPos(blocksY + row*height);

        setWindowWidth(w);
        setWindowHeight(h);
    }
}
