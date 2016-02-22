import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by rishabh on 20/02/16.
 */
public class Handler {
    static double xMiddle;

    /*
        public static void handleCommand(Circle Cir1, Circle Cir2) throws IOException {
            boolean left;
            boolean right;
            left = !(Cir1 == null);
            right = !(Cir2 == null);
            if (Cir1 != null && Cir2 != null) {
                xMiddle = (Cir1.getCenterX() + Cir2.getCenterX()) / 2;
            }
    */
    //public static void handleCommand() throws IOException {
    public static void main(String[] args) throws IOException {
        Date date = new Date();
        PatternDatabase.initialize();
/*
        EyeStatus eye = new EyeStatus(true, true, new Date());
        PatternParser.addEyeStatus(eye);

        Date time = new Date();
        time.setTime(time.getTime() + 100);
        eye = new EyeStatus(true, false, time);
        PatternParser.addEyeStatus(eye);

        time.setTime(time.getTime() + 900);
        eye = new EyeStatus(true, true, time);

        PatternParser.addEyeStatus(eye);
*/
        //DEBUG testing
        List<EyeStatus> eyes = new ArrayList<>();
        eyes.add(new EyeStatus(true, true, new Date()));
        eyes.add(new EyeStatus(true, false, new Date(new Date().getTime()+500)));
        eyes.add(new EyeStatus(true, true, new Date(new Date().getTime()+900)));
        /*
        PatternDatabase.writeNewPattern(eyes, "echo 'hello'");

        for (int i = 0; i < eyes.size(); i++)
        {
            PatternParser.addEyeStatus(eyes.get(i));
        }
        */
        Random random = new Random();

        for (int i = 0; i < 100; i++)
        {
            boolean l = random.nextBoolean();
            boolean r = random.nextBoolean();
            System.out.println(l +", "+ r + ", " + new Date().getTime());
            PatternParser.addEyeStatus(new EyeStatus(l, r, new Date()));
        }

    }
}
