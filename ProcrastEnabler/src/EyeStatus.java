import java.util.Date;

/**
 * Created by akarus on 20.2.16.
 */
public class EyeStatus {
    private final boolean left;
    private final boolean right;
    private final Date timestamp;

    public EyeStatus(boolean left, boolean right, Date timestamp) {
        this.left = left;
        this.right = right;
        this.timestamp = timestamp;
    }

    public boolean getLeft() {
        return left;
    }

    public boolean getRight() {
        return right;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public long getTime() {
        return timestamp.getTime();
    }


    public boolean match(EyeStatus e1) {
        return (e1.left == this.left) && (e1.right == this.right);

    }
}
