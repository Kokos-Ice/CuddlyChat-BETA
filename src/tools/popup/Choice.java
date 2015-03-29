package tools.popup;

public class Choice implements Component {
    private boolean disabled;
    private String selected;
    private int selectedIndex;
    private String[] items;
    private int[] foreground;
    private int[] background;
    private int fontsize;

    public Choice(String[] items) {
        this(items, null);
    }

    public Choice(String[] items, String selected) {
        this(items, selected, new int[] { 0x00, 0x00, 0x00 }, new int[] { 0xBE, 0xBC, 0xFB }, -1, false);
    }

    public Choice(String[] items, String selected, int[] foreground, int[] background, int fontsize, boolean disabled) {
        this.items = items;
        this.selected = selected;
        this.foreground = foreground;
        this.background = background;
        this.fontsize = fontsize;
        this.disabled = disabled;
    }

    public ComponentType getType() {
        return ComponentType.CHOICE;
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
        return "Homo, no need.";
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void toggleDisabled() {
        disabled = !disabled;
    }

    public boolean useIndex() {
        return selected == null;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setSelectedIndex(int value) {
        selectedIndex = value;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String value) {
        selected = value;
    }

    public String[] getItems() {
        return items;
    }
    
    public void setItems(String[] value) {
        items = value;
    }

    public int getFontsize() {
        return fontsize;
    }

    public void setFontsize(int value) {
        fontsize = value;
    }
}  