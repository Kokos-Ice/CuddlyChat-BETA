package tools.popup;

public class Button implements Component {
    private int[] foreground, background;
    private String text;
    private boolean styled, colored;
    private boolean close;
    private boolean action;
    private String command;

    public Button(String text) {
        foreground = new int[] { 0x00, 0x00, 0x00 };
        background = new int[] { 0xBE, 0xBC, 0xFB };
        this.text = text;
        styled = false;
        close = true;
        action = false;
        command = null;
    }

    public ComponentType getType() {
        return ComponentType.BUTTON;
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

    public boolean isStyled() {
        return styled;
    }

    public boolean isColored() {
        return colored;
    }

    public void setStyled(boolean colored) {
        styled = true;
        this.colored = colored;
    }

    public boolean isClose() {
        return close;
    }

    public void disableClose() {
        close = false;
    }

    public boolean isAction() {
        return action;
    }

    public void enableAction() {
        action = true;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
