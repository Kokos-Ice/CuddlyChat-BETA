package tools.popup;

public class TextArea implements Component {
    private int[] foreground, background;
    private String text;
    private boolean editable;
    private byte scrollbars;
    private byte rows, columns;

    public TextArea(int rows, int columns) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xFF, 0xFF, 0xFF };
        text = "";
        editable = true;
        scrollbars = 1;
        this.rows = (byte) rows;
        this.columns = (byte) columns;
    }

    public TextArea(int rows, int columns, String text) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xFF, 0xFF, 0xFF };
        this.text = text;
        editable = true;
        scrollbars = 1;
        this.rows = (byte) rows;
        this.columns = (byte) columns;
    }

    public ComponentType getType() {
        return ComponentType.TEXT_AREA;
    }

    public int[] getForeground() {
        return foreground;
    }

    public void setText(String text) {
        this.text = text;
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

    public boolean isEditable() {
        return editable;
    }

    public void disable() {
        editable = false;
    }

    public byte getScrollbars() {
        return scrollbars;
    }

    public void setScrollbars(int scrollbars) {
        this.scrollbars = (byte) scrollbars;
    }

    public byte getRows() {
        return rows;
    }

    public byte getColumns() {
        return columns;
    }
}
