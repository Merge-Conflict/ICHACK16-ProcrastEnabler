import java.io.File;
import java.io.IOException;
import java.util.Date;

public class CommandHandler {
    public static Date date = new Date();
    public static void main(String[] args) throws IOException {
        if (new Date().getTime() - date.getTime() < 5000)
        {
            return;
        }
        try {
            ProcessBuilder pb = new ProcessBuilder(args);
            pb.directory(new File(CommandHandler.class.getClassLoader().getResource("").getPath()));
            Process p = pb.start();
            p.waitFor();
            StringBuilder command = new StringBuilder();
            for (int i = 0; i < args.length; i++)
            {
                command.append(args[i]);
                command.append(" ");
            }
            System.out.println("Command executed: " + command);
            date.setTime(new Date().getTime());
            } catch (IOException e) {
                System.out.println("Error processing command.");
            } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
