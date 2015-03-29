package tools.popup;

public class Label implements Component {
    private int[] foreground, background;
    private String text;

    public Label(String text) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xBE, 0xBC, 0xFB };
        this.text = text;
    }

    public ComponentType getType() {
        return ComponentType.LABEL;
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
}
