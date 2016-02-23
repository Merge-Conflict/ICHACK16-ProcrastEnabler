import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rishabh on 20/02/16.
 */
public class PatternParser {
    static List<EyeStatus> currentList = new ArrayList<>();

    public static void addEyeStatus(EyeStatus e1) throws IOException {
        boolean add = false;
        if (currentList.size() == 0) {
            add = true;
        } else {
            long threshold = 500;
            if (e1.match(currentList.get(currentList.size() - 1)))  {
                //currentList.remove(currentList.size() - 1);
                //currentList.add(e1);||
                    //e1.getTime() - currentList.get(currentList.size()-1).getTime() < threshold)
            } else if (e1.getTime() - currentList.get(currentList.size()-1).getTime() > threshold){
                add = true;
            }
        }

        if (add) {
            currentList.add(e1);
            if (currentList.size() > PatternDatabase.getMaxLength()) {
                currentList = currentList.subList(currentList.size() - PatternDatabase.getMaxLength(), currentList.size());
            }
            System.out.println(e1.getLeft() + ", " + e1.getRight() + ", " + e1.getTime());
            callMe();
        }
    }


    public static void callMe() throws IOException {
        int i;

        for (i = 0; i < currentList.size(); i++) {
            List<EyeStatus> subCur = currentList.subList(i, currentList.size());
            for (int y = 0; y < PatternDatabase.getSize(); y++) {
                if (PatternDatabase.getPatternList().get(y).compare(subCur)) {
                    CommandHandler.main(PatternDatabase.getPatternList().get(y).getCmd());
                }
            }
        }
    }

}
