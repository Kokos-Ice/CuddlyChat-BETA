package tools.popup;

public interface Component {
    public ComponentType getType();
    public int[] getForeground();
    public void setForeground(int[] foreground);
    public int[] getBackground();
    public void setBackground(int[] background);
    public String getText();
}
