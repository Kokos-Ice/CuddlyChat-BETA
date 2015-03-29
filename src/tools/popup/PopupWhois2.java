
    package tools.popup;
     
    import java.util.*;
    import starlight.*;
    import tools.*;
     
     
    public class PopupWhois2 {
            private String title, subtitle, message;
            private int width, height;
            private List<Panel> panels;
            private String opcode, parameter;
     
            public PopupWhois2(String title, String subtitle, String message, int width,
                            int height) {
                    this.title = title;
                    this.subtitle = subtitle;
                    this.message = message;
                    this.width = width;
                    this.height = height;
                    panels = new ArrayList<Panel>();
            }
     
            public void addPanel(Panel panel) {
                    panels.add(panel);
            }
     
            public void setOpcode(String opcode, String parameter) {
                    this.opcode = opcode;
                    this.parameter = parameter;
            }
     
            @Override
            public String toString() {
                    PacketBuilder buffer = new PacketBuilder(SendOpcode.POPUP.getValue());
     
                    buffer.writeByte(0x00);
                    addString(buffer, title);
    //message=message.replace("\n", "#");  
                    if (opcode != null) {
                            buffer.writeByte('s');
                            addString(buffer, opcode);
                            addString(buffer, parameter);
                    }
     
                    addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
                    addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                    buffer.writeByte(0xE3);
     
                    // border right
                    buffer.writeByte('E');
                    buffer.writeByte('l');
                    addString(buffer, "         ");
                    addFontStyle(buffer, 'p', 5);
                    addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                    buffer.writeByte(0xE3);
     
                    // border left
                    buffer.writeByte('W');
                    buffer.writeByte('l');
                    addString(buffer, "         ");
                    addFontStyle(buffer, 'p', 5);
                    addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                    buffer.writeByte(0xE3);
     
                    buffer.writeByte('C');
                    addLayout(buffer, 'B');
     
                    buffer.writeByte('N');
                    addLayout(buffer, 'B');
     
                    // border top
                    buffer.writeByte('N');
                    buffer.writeByte('l');
                    addString(buffer, " ");
                    addFontStyle(buffer, 'p', 5);
                    addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                    buffer.writeByte(0xE3);
     
                    if (subtitle != null) {
                            buffer.writeByte('C');
                            buffer.writeByte('l');
                            addString(buffer, subtitle);
                            addFontStyle(buffer, 'b', 16);
                            addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
                            addBackground(buffer, new int[] { 0xE5, 0xE5, 0xFF });
                            buffer.writeByte(0xE3);
     
                            buffer.writeByte('S');
                            buffer.writeByte('l');
                            addString(buffer, " ");
                            addFontStyle(buffer, 'p', 5);
                            addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                            buffer.writeByte(0xE3);
                    }
     
                    buffer.writeByte(0xE3);
     
                    if (message != null) {
                            buffer.writeByte('C');
                            buffer.writeByte('c');
                            addString(buffer, message);
                            addFrameSize(buffer, width, height);
                            addForeground(buffer, new int[] { 0x00, 0x00, 0x00 });
                            addBackground(buffer, new int[] { 0xBE, 0xBC, 0xFB });
                            addBackgroundImage(buffer, "pics/cloudsblue.gif", 17);
                            buffer.writeByte(0xE3);
                    }
     
                    boolean useBorderLayouts = panels.size() > 1;
                    int borderLayouts = 0;
     
                    for (Panel panel : panels) {
                            buffer.writeByte('S');
     
                            if (useBorderLayouts) {
                                    borderLayouts++;
                                    addLayout(buffer, 'B');
                                    buffer.writeByte('N');
                            }
     
                            addLayout(buffer, 'F');
     
                            for (Component component : panel.getComponents()) {
                                    ComponentType type = component.getType();
                                    buffer.writeByte(type.getValue());
     
                                    if (type == ComponentType.CHECKBOX) {
                                            if (component.getText() != null) {
                                                    buffer.writeByte('l');
                                                    addString(buffer, component.getText());
                                            }
     
                                            addFontStyle(buffer, 'p', 16);
                                            Checkbox checkbox = (Checkbox) component;
     
                                            if (checkbox.isDisabled()) {
                                                    buffer.writeByte('d');
                                            }
     
                                            if (checkbox.isChecked()) {
                                                    buffer.writeByte('s');
                                                    buffer.writeByte('t');
                                            }
     
                                            if (checkbox.getGroup() != 0) {
                                                    buffer.writeByte('r');
                                                    addSize(buffer, checkbox.getGroup());
                                            }
                                    } else {
                                            addString(buffer, component.getText());
     
                                            if (type == ComponentType.BUTTON) {
                                                    addFontStyle(buffer, 'p', 16);
                                                    Button button = (Button) component;
     
                                                    if (button.isStyled()) {
                                                            buffer.writeByte('c');
     
                                                            if (button.isColored()) {
                                                                    buffer.writeByte('e');
                                                            }
                                                    }
     
                                                    if (button.isClose()) {
                                                            buffer.writeByte('d');
                                                    }
     
                                                    if (button.isAction()) {
                                                            buffer.writeByte('s');
                                                    }
     
                                                    if (button.getCommand() != null) {
                                                            buffer.writeByte('u');
                                                            addString(buffer, button.getCommand());
                                                    }
                                            } else if (type == ComponentType.TEXT_FIELD) {
                                                    addSize(buffer, ((TextField) component).getWidth());
                                            } else if (type == ComponentType.LABEL) {
                                                    addFontStyle(buffer, 'p', 16);
                                            } else if (type == ComponentType.TEXT_AREA) {
                                                    TextArea textarea = (TextArea) component;
                                                    addSize(buffer, textarea.getRows());
                                                    addSize(buffer, textarea.getColumns());
     
                                                    switch (textarea.getScrollbars()) {
                                                    case 0:
                                                            buffer.writeByte('b');
                                                            break;
                                                    case 1:
                                                            buffer.writeByte('s');
                                                            break;
                                                    case 2:
                                                            buffer.writeByte('w');
                                                            break;
                                                    }
     
                                                    if (textarea.isEditable()) {
                                                            buffer.writeByte('e');
                                                    }
                                            }
                                    }
     
                                    addForeground(buffer, component.getForeground());
                                    addBackground(buffer, component.getBackground());
                                    buffer.writeByte(0xE3);
                            }
     
                            buffer.writeByte(0xE3);
                    }
     
                    for (int i = 0; i < borderLayouts; i++) {
                            buffer.writeByte(0xE3);
                    }
     
                    buffer.writeByte(0xE3);
                    buffer.writeByte(0xE3);
    PacketBuilder buffer2 = new PacketBuilder(SendOpcode.POPUP.getValue());
    height-=30;
    buffer2.writeByte('s');
    buffer2.writeShort(width);
    buffer2.writeShort(height);
   
    // Fehler beheben  paketfake
    
    String action = PacketCreator.paketfake("k\0"+title+"õf\0\0\0hÿÿÿãCp~õBNp~õU-õU\0\0\nBãSp~õbëëÿU-õU\0\0\nBãCp~õBSp~õbëëÿGBBBBp~õbëëÿFp~õGABBBp~õbëëÿBCb     OK     õcedbgOhÿÿÿãEp~õbëëÿU-õU\0\0\0BãWp~õbëëÿU-õU\0\0\0BãããããCp~õBNpBNpBWpUõbgUf\0\0\0hÞÞÿããããCp~õBCp~õBNp~õU-õU\0\0BãSp~õbëëÿU-õU\0\0BãCp~õBCc°R>{linkhovercolor}<r°"+message+"°>{linkhovercolorreset}<°õ~tpõ"+buffer2+"f\0\0\0hÿÿÿipics/layout/bg_trend.pngõÿÿããEp~õUpics/layout/bg_trend.pngõU\0\0BãWp~õUpics/layout/bg_trend.pngõU\0\0BãããããEp~õUpics/layout/bg_trend.pngõU\0\n\0BãWp~õUpics/layout/bg_trend.pngõU\0\n\0Bããã");
                    return action.toString();
            }
     
            private static void addSize(PacketBuilder buffer, int size) {
                    buffer.writeByte('A' + size);
            }
     
            private static void addString(PacketBuilder buffer, String str) {
                    buffer.writeString(str);
                    buffer.writeByte(0xF5);
            }
     
            private static void addFontStyle(PacketBuilder buffer, char weight, int size) {
                    if (weight != 'p') {
                            buffer.writeByte(weight);
                    }
     
                    buffer.writeByte('g');
                    addSize(buffer, size);
            }
     
            private static void addLayout(PacketBuilder buffer, char layout) {
                    buffer.writeByte('p');
                    buffer.writeByte(layout);
            }
     
            private static void addFrameSize(PacketBuilder buffer, int width, int height) {
                    buffer.writeByte('s');
                    buffer.writeShort(width);
                    buffer.writeShort(height);
            }
     
            private static void addForeground(PacketBuilder buffer, int[] color) {
                    buffer.writeByte('f');
                    buffer.write(color);
            }
     
            private static void addBackground(PacketBuilder buffer, int[] color) {
                    buffer.writeByte('h');
                    buffer.write(color);
            }
     
            private static void addBackgroundImage(PacketBuilder buffer, String image,
                            int position) {
                    buffer.writeByte('i');
                    addString(buffer, image);
                    buffer.writeShort(position);
            }
     
            public static String create(String title, String subtitle, String message,
                            int width, int height) {
                    Popup popup = new Popup(title, subtitle, message, width, height);
                    Panel panel = new Panel();
                    panel.addComponent(new Button("   OK   "));
                    popup.addPanel(panel);
                    return popup.toString();
            }
    }

