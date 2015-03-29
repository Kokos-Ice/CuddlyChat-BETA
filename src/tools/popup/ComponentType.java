package tools.popup;

public enum ComponentType {
    BUTTON('b'),
    TEXT_FIELD('f'),
    LABEL('l'),
    TEXT_AREA('t'),
    CHECKBOX('x'),
    CHOICE('o');

    private int type;

    private ComponentType(int type) {
        this.type = type;
    }

    public int getValue() {
        return type;
    }
}
