/**
 * Created by Dongwoo on 18/01/2016.
 */
public class Constants {
    private static int FPS = 40;
    private static int Speed = 125;

    public static void setFPS(int FPS) {
        Constants.FPS = FPS;
    }

    public static void setSpeed(int speed) {
        Speed = speed;
    }

    public static int getFPS() {
        return FPS;
    }

    public static int getSpeed() {
        return Speed;
    }

    public static final int DEFAULT_WINDOW_WIDTH = 400;
    public static final int DEFAULT_WINDOW_HEIGHT = 300;

    public static final int DEFAULT_PADDLE_X = 200; //186
    public static final int DEFAULT_PADDLE_Y = 283;
    public static final int DEFAULT_PADDLE_WIDTH = 34;
    public static final int DEFAULT_PADDLE_HEIGHT = 4;

    public static final int DEFAULT_BALL_DIAMETER = 5;
    public static final int DEFAULT_BALL_X = 200;
    public static final int DEFAULT_BALL_Y = 278;

    public static final int LV1_SPEED = 125;
    public static final int LV2_SPEED = 175;
    public static final int LV3_SPEED = 225;

    public static final int DEFAULT_BLOCKS_WIDTH = 320;
    public static final int DEFAULT_BLOCKS_HEIGHT = 80;
    public static final int DEFAULT_BLOCKS_X = 40;
    public static final int DEFAULT_BLOCKS_Y = 40;
    public static final int DEFAULT_NUM_BLOCK_COL = 16;
    public static final int DEFAULT_NUM_BLOCK_ROW = 8;

    public static final int DEFAULT_NUM_LIVES = 3;
    public static final int DEFAULT_LIVES_X = 14;
    public static final int DEFAULT_LIVES_Y = 283;
    public static final int DEFAULT_LIVES_SPACE = 3;


}
