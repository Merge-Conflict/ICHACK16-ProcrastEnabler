import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarus on 20.2.16.
 */
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akarus on 20.2.16.
 */
public class Pattern {
    private List<EyeStatus> pattern = new ArrayList<>();
    private String[] cmd;

    public Pattern(List<EyeStatus> pattern, String[] cmd) {
        this.pattern = pattern;
        this.cmd = cmd;
    }
    //String[] args= new String[]{"/usr/bin/open", "-a", "/Applications/Google Chrome.app", "http://google.com/"};


    public List<EyeStatus> getPattern() {
        return pattern;
    }

    public int length() {
        return pattern.size();
    }

    public boolean compare(List<EyeStatus> sublst) {
        if (sublst.size() != pattern.size()) {
            return false;
        } else {
            for (int i = 0; i < sublst.size(); i++) {
                if (!sublst.get(i).match(pattern.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    public String[] getCmd() {
        return cmd;
    }
}
/*
public class Pattern {
    private List<EyeStatus> pattern = new ArrayList<>();
    private String[] cmd;
    private static final double perError = 1.4;

    public Pattern(List<EyeStatus> pattern, String[] cmd) {
        this.pattern = pattern;
        this.cmd = cmd;
    }
    //String[] args= new String[]{"/usr/bin/open", "-a", "/Applications/Google Chrome.app", "http://google.com/"};


    public List<EyeStatus> getPattern() {
        return pattern;
    }

    public int length() {
        return pattern.size();
    }

    public boolean compare(List<EyeStatus> sublst) {
        if (sublst.size() != pattern.size()) {
            return false;
        } else {
            long tdiffC;
            long tdiffE;
            for (int i = 0; i < sublst.size(); i++) {
                if(i>0) {
                    tdiffC = sublst.get(i).getTime() - sublst.get(i - 1).getTime();
                    tdiffE = pattern.get(i).getTime() - pattern.get(i - 1).getTime();

                    boolean tdiff = compareTimes(tdiffC, tdiffE);
                    if ((!sublst.get(i).match(pattern.get(i))) || !tdiff) {
                        return false;
                    }
                }
                else {
                    if (!sublst.get(i).match(pattern.get(i))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public String[] getCmd() {
        return cmd;
    }

    static boolean compareTimes(Long lC, Long lE) {

        boolean b = lC>=lE*(1-perError) && lC<=lE*(1+perError);
        return b;
    }
}*/
