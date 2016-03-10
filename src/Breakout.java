import javax.swing.*;
import java.awt.*;

/**
 * Created by Dongwoo on 18/01/2016.
 */
public class Breakout {

    public static void main(String[] args){
        int firstArg; // FPS
        int secondArg; // Speed
        if (args.length > 0){
            try {
                firstArg = Integer.parseInt(args[0]);
                secondArg = Integer.parseInt(args[1]);
                Constants.setFPS(firstArg);
                Constants.setSpeed(secondArg);
            } catch (NumberFormatException e) {
                System.err.println("Arguments must be integers: FPS and Speed");
                System.exit(1);
            }
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI(){
        JFrame f = new JFrame("Breakout!");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Splash Screen
        final SplashScreen splash = SplashScreen.getSplashScreen();
        if (splash == null) {
            System.out.println("Ensure that you have images/BreakoutSplash.png file in the appropriate directory.");
            System.out.println("Pass the VM Option -splash:images/BreakoutSplash.png to the command line argument.");
//            return;
        } else {
            Graphics2D g = splash.createGraphics();
            if (g == null) {
                System.out.println("g is null");
                return;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            splash.close();
        }
        // Game Screen
        f.add(new View());
        f.pack();
        f.setMinimumSize(f.getSize());
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}
