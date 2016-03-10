import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Dongwoo on 18/01/2016.
 */
public class View extends JPanel implements ActionListener{
    Paddle paddle = new Paddle();
    Ball ball = new Ball();

    private int row; // = Constants.DEFAULT_NUM_BLOCK_ROW;
    private int col; // = Constants.DEFAULT_NUM_BLOCK_COL;
    Block[][] blocks; // = new Block[row][col];

    private int numLives = Constants.DEFAULT_NUM_LIVES;
    Lives lives = new Lives(numLives);

    private int windowWidth = Constants.DEFAULT_WINDOW_WIDTH, windowHeight = Constants.DEFAULT_WINDOW_HEIGHT;
    private int fpsPaint = Constants.getFPS();
    private int fpsLogic = 60;
    private int Speed = Math.abs(ball.getSpeedX());
    Timer timerPaint = new Timer(1000/fpsPaint,this); // for painting
    Timer timerLogic = new Timer(1000/fpsLogic,this); // for moving ball and collision detection



    private boolean gameStarted = false;
    private boolean gameOver = false;
    private boolean gameCleared = false;

    private int score = 0;
    private int highestScore = 0;
    private boolean mouseEventEnabled = true;
    private int numDestroyed = 0;


    private int mouseX, mouseY;

    // level feature
    private int level = 1;
    private int maxLevel = 3;

    // call it when level cleared
    // or from cheat key
    private void levelUp(){
        level++;
        gameOver = false;
        gameCleared = false;
        gameStarted = false;
        mouseEventEnabled = true;

        levelInit(level);

        for (int r=0; r<row; ++r){
            for (int c=0; c<col; ++c){
                blocks[r][c].setDestroyed(false);
            }
        }

        ball.setSpeedY(-1*ball.getSpeedY());
        ball.setxPos(paddle.getX());
        ball.setyPos(paddle.getY() - ball.getDiameter());
    }
    private void levelDown(){
        level--;
    }

    private void levelInit(int level){

        if (level == 1){ // easy
            row = 1;
            col = 16;
            Speed = (int) (windowWidth * ((double)Constants.getSpeed() / Constants.DEFAULT_WINDOW_WIDTH));
        } else if (level == 2){ // normal
            row = 5;
            col = 16;
            Speed = (int) (windowWidth * ((double) Constants.LV2_SPEED / Constants.DEFAULT_WINDOW_WIDTH));
        } else if (level == 3){ // hard
            row = 8;
            col = 16;
            Speed = (int) (windowWidth * ((double) Constants.LV3_SPEED / Constants.DEFAULT_WINDOW_WIDTH));
        }

        ball.setSpeedX(Speed);
        ball.setSpeedY(Speed);

        blocks = new Block[row][col];

        for (int r=0; r<row; ++r){
            for (int c=0; c<col; ++c){
                blocks[r][c] = new Block(r,c);
                blocks[r][c].resize(windowWidth, windowHeight);
            }
        }
    }

    public View() {

        levelInit(level);

        setMinimumSize(new Dimension(Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT));
        setBorder(BorderFactory.createLineBorder(Color.black));
        setFocusable(true);

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseEventEnabled) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                    movePaddle(e.getX(), e.getY());
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                Component c = (Component)e.getSource();
                windowWidth = c.getWidth();
                windowHeight = c.getHeight();

                // Blocks
                for (int r=0; r<row; ++r){
                    for (int co=0; co<col; ++co){
                        blocks[r][co].resize(windowWidth, windowHeight);
                    }
                }
                lives.resize(windowWidth, windowHeight);
                paddle.resize(windowWidth, windowHeight);

                if (level == 1){
                    Speed = (int) (windowWidth * ((double) Constants.getSpeed() / Constants.DEFAULT_WINDOW_WIDTH));
                } else if (level == 2){
                    Speed = (int) (windowWidth * ((double) Constants.LV2_SPEED / Constants.DEFAULT_WINDOW_WIDTH));
                } else if (level == 3){
                    Speed = (int) (windowWidth * ((double) Constants.LV3_SPEED / Constants.DEFAULT_WINDOW_WIDTH));
                }
                if (ball.getSpeedX() < 0 ){
                    ball.setSpeedX(-Speed);
                } else {
                    ball.setSpeedX(Speed);
                }
                if (ball.getSpeedY() < 0){
                    ball.setSpeedY(-Speed);
                } else {
                    ball.setSpeedY(Speed);
                }

                if (!timerLogic.isRunning() && !gameStarted){
                    ball.resize(windowWidth, windowHeight);
                    ball.setyPos(paddle.getY()-ball.getDiameter());
                } else {
                    ball.resize(windowWidth, windowHeight);
                }
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // Play and Pause
                if (keyCode == KeyEvent.VK_SPACE){
                    if (timerLogic.isRunning()){
                        pauseGame();
//                        System.out.println("pause");
                    } else {
                        if (gameOver){
                            restartGame();
//                            System.out.println("restart");
                        } else if (gameCleared){
                            if (level == maxLevel){
                                restartGame();
                            } else {
                                levelUp();
                            }
//                            System.out.println("restart");
                        } else {
                            playGame();
//                            System.out.println("play");
                        }
                    }
                }
                // Quit
                if (keyCode == KeyEvent.VK_ESCAPE){
                    System.exit(0);
                }

                // Cheat
                // Force Clear
                if (keyCode == KeyEvent.VK_C){
                    clearGame();
//                    System.out.println("clear");
                }
                // Force Restart
                if (keyCode == KeyEvent.VK_R){
                    pauseGame();
                    restartGame();
                }
            }
        });
    }

    private void playGame(){
        timerLogic.start();
        gameStarted = true;
        mouseEventEnabled = true;
    }

    private void pauseGame(){
        timerLogic.stop();
        mouseEventEnabled = false;
    }

    private void restartGame(){
        numLives = Constants.DEFAULT_NUM_LIVES;
        score = 0;
        numDestroyed = 0;
        level=1;
        gameOver = false;
        gameCleared = false;
        gameStarted = false;
        mouseEventEnabled = true;
        lives.setNumLives(numLives);

//        for (int r=0; r<row; ++r){
//            for (int c=0; c<col; ++c){
//                blocks[r][c].setDestroyed(false);
//            }
//        }

        levelInit(level);
        ball.setSpeedY(-1*ball.getSpeedY());
        ball.setxPos(paddle.getX());
        ball.setyPos(paddle.getY() - ball.getDiameter());
    }

    private void clearGame(){
        gameStarted = false;
        gameCleared = true;
        timerLogic.stop();

        int clearBonus = 1000 * level;
        score += numLives * 500 + clearBonus;
        if (score > highestScore) highestScore = score;
        mouseEventEnabled = false;
    }

    private void die(){

        ball.setSpeedY(-1*ball.getSpeedY());
        ball.setxPos(paddle.getX());
        ball.setyPos(paddle.getY()-ball.getDiameter()/2);

        gameStarted = false;
        timerLogic.stop();

        numLives--;
        lives.setNumLives(numLives);
        if (numLives == 0){
            gameOver = true;
            if (score > highestScore) highestScore = score;
        }
    }

    private void movePaddle(int x, int y){

        // Current square state, stored as final variables
        // to avoid repeat invocations of the same methods.
        final int PCURR_X = paddle.getX();
        final int PCURR_Y = paddle.getY();
        final int PCURR_W = paddle.getWidth();
        final int PCURR_H = paddle.getHeight();
        final int POFFSET = 1;

        final int BCURR_X = ball.getxPos();
        final int BCURR_Y = ball.getyPos();
        final int BCURR_W = ball.getDiameter();
        final int BCURR_H = ball.getDiameter();
        final int BOFFSET = 100;

        if ((PCURR_X!=x) || (PCURR_Y!=y)) {

            // The paddle is moving, repaint background
            // over the old paddle location.
            repaint(PCURR_X-PCURR_W/2,
                    gameStarted ? PCURR_Y : PCURR_Y - BCURR_H,
                    PCURR_W+POFFSET,
                    gameStarted ? PCURR_H+POFFSET : PCURR_H + BCURR_H + POFFSET);

            // Update coordinates.
            if (x < paddle.getWidth()/2){
                x = paddle.getWidth()/2 + 3;
            } else if (x > windowWidth - paddle.getWidth()/2){
                x = windowWidth - paddle.getWidth()/2 - 3;
            }
            paddle.setX(x);
            if (!gameStarted) ball.setxPos(x);

            // Repaint the square at the new location.
            repaint(paddle.getX() - paddle.getWidth()/2,
                    gameStarted ? paddle.getY() : paddle.getY() - ball.getDiameter(),
                    paddle.getWidth()+POFFSET,
                    gameStarted ? paddle.getHeight()+POFFSET : paddle.getHeight() + ball.getDiameter() + POFFSET);
        }
    }

    private void moveBall(){

        final int CURR_X = ball.getxPos();
        final int CURR_Y = ball.getyPos();
        final int CURR_W = ball.getDiameter();
        final int CURR_H = ball.getDiameter();
        final int OFFSET = 100;

        int x = ball.getxPos() + ball.getSpeedX()/fpsLogic;
        int y = ball.getyPos() + ball.getSpeedY()/fpsLogic;

        if ((CURR_X!=x) || (CURR_Y!=y)) {

            // The square is moving, repaint background
            // over the old square location.
//            repaint(CURR_X - CURR_W/2,CURR_Y,CURR_W+OFFSET,CURR_H+OFFSET);

            // Update coordinates.
            ball.setxPos(x);
            ball.setyPos(y);

            // Repaint the square at the new location.
//            repaint(ball.getxPos() - ball.getDiameter()/2, ball.getyPos(),
//                    ball.getDiameter()+OFFSET,
//                    ball.getDiameter()+OFFSET);
        }
    }




    private void detectCollision(){

        Rectangle2D ballBounds = ball.bounds();
        Rectangle2D paddleBounds = paddle.bounds();

        // Walls
        // east and west walls
        if (ball.getxPos() > windowWidth - ball.getDiameter() || ball.getxPos() < 0){
            ball.setSpeedX(-1*ball.getSpeedX());
            score += 1;
        }
        // north and south walls
        if (ball.getyPos() < 0){
            ball.setSpeedY(-1*ball.getSpeedY());
            score += 1;
        }
        if (ball.getyPos() > windowHeight - ball.getDiameter()){
            die();
        }
        if (numDestroyed == row*col){
            clearGame();
        }

        // Paddle
        if (ballBounds.intersects(paddleBounds)){
            Rectangle2D iRect = ballBounds.createIntersection(paddleBounds);

            // If we hit on the left side, go left (negative x velocity).
            if ((ballBounds.getX()+(ballBounds.getWidth()/2))<(iRect.getX()+(iRect.getWidth()/2))){
                ball.setSpeedX(0-Math.abs(ball.getSpeedX()));
            }
            // If we hit on the right side, go right (positive x velocity).
            if ((ballBounds.getX()+(ballBounds.getWidth()/2))>(iRect.getX()+(iRect.getWidth()/2))){
                ball.setSpeedX(Math.abs(ball.getSpeedX()));
            }
            // If we hit on the top, go up.
            if ((ballBounds.getY()+(ballBounds.getHeight()/2))<(iRect.getY()+(iRect.getHeight()/2))){
                ball.setSpeedY(0-Math.abs(ball.getSpeedY()));
            }
            // If we hit on the bottom, go down.
            if ((ballBounds.getY()+(ballBounds.getHeight()/2))>(iRect.getY()+(iRect.getHeight()/2))){
                ball.setSpeedY(Math.abs(ball.getSpeedY()));
            }
        }

        // Blocks
        blocksLoop:
        for (int r=0; r<row; ++r){
            for (int c=0; c<col; ++c){
                Block block = blocks[r][c];
                if (!block.isDestroyed()){
                    Rectangle2D blockBounds = block.bounds();

                    if (ballBounds.intersects(blockBounds)){
                        Rectangle2D iRect = ballBounds.createIntersection(blockBounds);

                        // If we hit on the left side, go left (negative x velocity).
                        if ((ballBounds.getX()+(ballBounds.getWidth()/2))<(iRect.getX()+(iRect.getWidth()/2))){
                            ball.setSpeedX(0-Math.abs(ball.getSpeedX()));
                        }
                        // If we hit on the right side, go right (positive x velocity).
                        if ((ballBounds.getX()+(ballBounds.getWidth()/2))>(iRect.getX()+(iRect.getWidth()/2))){
                            ball.setSpeedX(Math.abs(ball.getSpeedX()));
                        }
                        // If we hit on the top, go up.
                        if ((ballBounds.getY()+(ballBounds.getHeight()/2))<(iRect.getY()+(iRect.getHeight()/2))){
                            ball.setSpeedY(0-Math.abs(ball.getSpeedY()));
                        }
                        // If we hit on the bottom, go down.
                        if ((ballBounds.getY()+(ballBounds.getHeight()/2))>(iRect.getY()+(iRect.getHeight()/2))){
                            ball.setSpeedY(Math.abs(ball.getSpeedY()));
                        }

                        block.setDestroyed(true);
                        numDestroyed++;

                        if (block.getType() == 1){
                            score += 110;
                        } else if (block.getType() == 2){
                            score += 90;
                        } else if (block.getType() == 3){
                            score += 70;
                        } else if (block.getType() == 4){
                            score += 50;
                        } else if (block.getType() == 5){
                            score += 30;
                        } else if (block.getType() == 6){
                            score += 10;
                        }
                        break blocksLoop;
                    }
                }
            }
        }
    }

    private void drawPressSpace(Graphics2D g2){
        String stateStr = "";
        String pressStr = "";
        if (!gameStarted){
            stateStr = "Press Space to Start";
            pressStr = "( ESC to Quit )";
            if (gameOver){
                stateStr = "GAME OVER";
                pressStr = "Press Space to Restart";
            }
            if (gameCleared){
                stateStr = "GAME CLEARED";
                if (level == maxLevel) {
                    pressStr = "Press Space to Restart";
                } else {
                    pressStr = "Press Space to Level Up";
                }
            }
        } else {
            if (!timerLogic.isRunning()){
                stateStr = "Paused";
                pressStr = "Press Space to Resume";
            }
        }

        Rectangle2D stateStrBound = g2.getFontMetrics().getStringBounds(stateStr,g2);
        int stateStrWidth = (int) stateStrBound.getWidth();
        int stateStrHeight = (int) stateStrBound.getHeight();

        int stateStrX = windowWidth/2 - stateStrWidth/2;
        int stateStrY = windowHeight/2;

        g2.setColor(Color.WHITE);
        g2.drawString(stateStr, stateStrX, stateStrY);

        int pressStrWidth = g2.getFontMetrics().stringWidth(pressStr);
        int pressStrX = windowWidth/2 - pressStrWidth/2;
        int pressStrY = windowHeight/2 + stateStrHeight;

        g2.setColor(Color.WHITE);
        g2.drawString(pressStr, pressStrX, pressStrY);

        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.DEFAULT_WINDOW_WIDTH, Constants.DEFAULT_WINDOW_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set Background
        setOpaque(true);
        setBackground(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g; // cast to get 2D drawing methods
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  // antialiasing look nicer
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw Labels
        // Score label
        String scoreLabel = "Level: " + level + ", Score: " + score;
        g2.setColor(Color.WHITE);
        g2.drawString(scoreLabel, 5, 15);

        // FPS label
        String FPSLabel = "FPS: " + fpsPaint + ", Ball Speed: " + Speed;
        g2.setColor(Color.YELLOW);
        g2.drawString(FPSLabel, 5, 30);

        // Highest Score Label
        String hiScoreLabel = "Highest Score: " + highestScore;

        Rectangle2D stateStrBound = g2.getFontMetrics().getStringBounds(hiScoreLabel,g2);
        int hiScoreWidth = (int) stateStrBound.getWidth();
        int hiScoreX = windowWidth - hiScoreWidth;

        g2.setColor(Color.WHITE);
        g2.drawString(hiScoreLabel, hiScoreX - 5, 15);

        // Panel Size Label
//        String panelSizeLabel = windowWidth + " x " + windowHeight;
//
//        stateStrBound = g2.getFontMetrics().getStringBounds(panelSizeLabel,g2);
//        int panelSizeWidth = (int) stateStrBound.getWidth();
//        int panelSizeX = windowWidth - panelSizeWidth;
//
//        g2.setColor(Color.WHITE);
//        g2.drawString(panelSizeLabel, panelSizeX - 5, windowHeight - 5);

        // Mouse position label
//        String label = "Mouse at (" + mouseX + ", " + mouseY + ")";
//        g2.setColor(Color.WHITE);
//        g2.drawString(label, 5, 45);

        // Draw Elements

        for (int r=0; r<row; ++r){
            for (int c=0; c<col; ++c){
                if (!blocks[r][c].isDestroyed()) blocks[r][c].draw(g2);
            }
        }

        lives.draw(g2);
        paddle.draw(g2);
        ball.draw(g2);

        drawPressSpace(g2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == timerPaint){
            repaint();
        } else if (e.getSource() == timerLogic){
            detectCollision();
            moveBall();
        }
    }
}
