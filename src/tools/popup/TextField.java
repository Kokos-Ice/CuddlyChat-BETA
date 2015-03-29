package tools.popup;

public class TextField implements Component {
    private int[] foreground, background;
    private String text;
    private byte width;

    public TextField(int width) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xFF, 0xFF, 0xFF };
        text = "";
        this.width = (byte) width;
    }

    public TextField(int width, String text) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xFF, 0xFF, 0xFF };
        this.text = text;
        this.width = (byte) width;
    }

    public ComponentType getType() {
        return ComponentType.TEXT_FIELD;
    }

    public int[] getForeground() {
        return foreground;
    }

    public void setForeground(int[] foreground) {
        this.foreground = foreground;
    }

    public int[] getBackground() {
        return background;
    }

    public void setBackground(int[] background) {
        this.background = background;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte getWidth() {
        return width;
    }
}
