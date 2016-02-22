
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by akarus on 20.2.16.
 */
public class PatternDatabase {
    private static List<Pattern> patternList = new ArrayList<>();
    private static int maxLength;
    final static String filename = "patterns.txt";


    public static int getSize() {
        return patternList.size();
    }

    public static void initialize()
    {
        try {
            patternList.clear();
            loadPatterns();
            calculateMaxLength();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String writeNewScriptFile(String command)
    {
        File folder = new File(PatternDatabase.class.getClassLoader().getResource("").getPath()+"/scripts");
        File[] listOfFiles = folder.listFiles();

        String prefix = "script";
        String suffix = ".sh";
        String name = "";
        int index = 0;
        boolean found;
        do {
            found = true;
            String candidate = prefix + index + suffix;
            for (File f : listOfFiles) {
                if (f.getName().equals(candidate)) {
                    found = false;
                    index++;
                    break;
                }
            }
            if (found) { name = candidate; }
        }
        while (!found);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(folder.getPath()+"/"+name, "UTF-8");
            writer.println(command);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static void writeNewPattern(List<EyeStatus> eyes, String cmd)
    {
        //long lastTime = 0; //TODO decide
        if (eyes.size()==0) { return; }
        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(PatternDatabase.class.
                    getResource(filename).getPath()), true));
            String script = writeNewScriptFile(cmd);
            String command = "sh scripts/"+script;
            pw.append("command: "+"\""+command+"\"\n");
            //long time = eyes.get(0).getTimestamp().getTime();
            for (int i = 0; i < eyes.size(); i++) {
                if (i > 0 && eyes.get(i-1).getLeft() == eyes.get(i).getLeft() && eyes.get(i-1).getRight() == eyes.get(i).getRight() )
                {
                    continue;
                }
                String pat = "";
                if (eyes.get(i).getLeft()) { pat+="1"; } else { pat+="0"; }
                if (eyes.get(i).getRight()) { pat+=" 1 "; } else { pat+=" 0 "; }
                //if (i < eyes.size()-1) {
                    //long nextTime = eyes.get(i+1).getTimestamp().getTime();
                    //pat += nextTime - time;
                    //time = eyes.get(i+1).getTimestamp().getTime();
                //}
                /*else
                {
                    pat += lastTime;
                }
                */
                pat+=new Date().getTime();
                pw.write(pat+"\n");
            }
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void loadPatterns() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(PatternDatabase.class.getClassLoader().getResource(filename).getPath()));
        try {
            String line = br.readLine();
            String currentCommand = "";
            List<EyeStatus> currentStatuses = new ArrayList<>();
            long currentDuration = 0;
            while (line != null) {
                if (line.equals(""))
                {
                    line = br.readLine();
                    continue;
                }
                if (line.startsWith("command: ")) {
                    if (!currentCommand.equals("")) {
                        Pattern pattern = new Pattern(currentStatuses, currentCommand.split(" "));
                        patternList.add(pattern);
                        currentStatuses = new ArrayList<>();
                        currentCommand = "";
                    }
                    String[] strs = line.split("command: ");
                    if (strs.length==2)
                        currentCommand = strs[1].substring(1, strs[1].length() - 1);
                } else {
                    String[] strs = line.split(" ");
                    if (strs.length != 3) {
                        continue;
                    }
                    boolean left = Integer.parseInt(strs[0]) == 1;
                    boolean right = Integer.parseInt(strs[1]) == 1;
                    long duration = Long.parseLong(strs[2]) - currentDuration;
                    Date date = new Date();
                    date.setTime(duration);
                    EyeStatus status = new EyeStatus(left, right, date);
                    currentStatuses.add(status);
                    currentDuration = duration;
                }
                line = br.readLine();
            }
            if (!currentCommand.equals("")) {
                Pattern pattern = new Pattern(currentStatuses, currentCommand.split(" "));
                patternList.add(pattern);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculateMaxLength() {
        if (patternList.size() == 0) {
            return;
        }

        maxLength = patternList.get(0).length();
        for (int i = 1; i < patternList.size(); i++) {
            int length = patternList.get(i).length();
            maxLength = length > maxLength ? length : maxLength;
        }
    }


    public static List<Pattern> getPatternList() {
        return patternList;
    }

    public static int getMaxLength() {
        return maxLength;
    }
}
