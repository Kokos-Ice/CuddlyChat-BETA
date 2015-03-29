/* Banana-Chat - The first Open Source Knuddels Emulator
 * Copyright (C) 2011 - 2012 Flav <http://banana-coding.com>
 * 
 * Diese Dateien unterliegen dem Coprytight von Banana-Coding und
 * darf verändert, aber weder in andere Projekte eingefügt noch
 * reproduziert werden.
 * 
 * Der Emulator dient - sofern der Client nicht aus Eigenproduktion
 * stammt - nur zu Lernzwecken, das Hosten des originalen Knuddels Clients
 * ist auf eigene Gefahr und verstößt möglicherweise gegen Schutzrechte
 * der Knuddels.de GmbH & Co KG
 * 
 * Autoren: Flav (Grundversion), Localhost (Erweiterte Version), Kokos-Ice (Erweiterte Version)
 */



package tools;

import java.awt.Cursor;

public class KButton {

    private String text;
    private String command;
    private String textColor; //255,255,255
    private String disabledTextColor; //255,255,255
    private String iconPath; //sm_classic_yellow.gif
    private String images; //button-blue~hoverbutton-blue~button-blue.mx_1.my_1<
    private String color; //200,200,200
    private boolean enabled;
    private boolean visible;
    private boolean textBorder;
    private boolean border;
    private int width;
    private int height;
    private int textXPosition;
    private int textYPosition;
    private int disabledTimeout;
    private int cursor;

    public KButton(String text, String command) {
        if(isNullOrEmpty(command)){
            command = "/";
        }
        this.text = text;
        this.command = command;
        this.width = -1;
        this.height = -1;
        this.textXPosition = -1;
        this.textYPosition = -1;
        this.disabledTimeout = -1;
        this.visible = true;
        this.enabled = true;
        this.border = true;
        this.cursor = Cursor.DEFAULT_CURSOR;
    }

    public String getText() {
        return text;
    }

    public String getCommand() {
        return command;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getDisabledTextColor() {
        return disabledTextColor;
    }

    public String getIconPath() {
        return iconPath;
    }

    public String getImages() {
        return images;
    }

    public String getColor() {
        return color;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean hasTextBorder() {
        return textBorder;
    }

    public boolean hasBorder() {
        return border;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getTextXPosition() {
        return textXPosition;
    }

    public int getTextYPosition() {
        return textYPosition;
    }

    public int getDisabledTimeout() {
        return disabledTimeout;
    }

    public int getCursor() {
        return cursor;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTextBorder(boolean textBorder) {
        this.textBorder = textBorder;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public void setDisabledTextColor(String disabledTextColor) {
        this.disabledTextColor = disabledTextColor;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTextY(int yPosition) {
        this.textYPosition = yPosition;
    }

    public void setTextX(int xPosition) {
        this.textXPosition = xPosition;
    }

    public void setIcon(String iconPath) {
        this.iconPath = iconPath;
    }

    public void setImages(String image1, String image2, String image3) {
        this.images = String.format("%s~%s~%s", image1, image2, image3);
        if (images.length() == 2) {
            images = "";
        }
    }

    public void setColor(String color1, String color2, String color3) {
        this.color = String.format("%s~%s~%s", color1, color2, color3);
        if (color.length() == 2) {
            color = "";
        }
    }

    public void setBorder(boolean border) {
        this.border = border;
    }

    public void setDisabledTimeout(int disabledTimeout) {
        this.disabledTimeout = disabledTimeout;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    @Override
    public String toString() {
        StringBuilder buttonBuilder = new StringBuilder();
        buttonBuilder.append("°>{button} ").append(text).append("||call|_r\\").append(command);

        if (!visible) {
            buttonBuilder.append("|visible|0");
        }
        if (!enabled) {
            buttonBuilder.append("|enabled|0");
        }
        if (!border) {
            buttonBuilder.append("|noborder|1");
        }
        if (textBorder) {
            buttonBuilder.append("|textborder|1");
        }


        if (!isNullOrEmpty(textColor)) {
            buttonBuilder.append("|textcolor|").append(textColor);
        }
        if (!isNullOrEmpty(disabledTextColor)) {
            buttonBuilder.append("|disabledtextcolor|").append(disabledTextColor);
        }
        if (!isNullOrEmpty(iconPath)) {
            buttonBuilder.append("|icon|").append(iconPath);
        }
        if (!isNullOrEmpty(images)) {
            buttonBuilder.append("|images|").append(images);
        }

        if (width != -1) {
            buttonBuilder.append("|width|").append(width);
        }
        if (height != -1) {
            buttonBuilder.append("|height|").append(height);
        }
        if (textXPosition != -1) {
            buttonBuilder.append("|tx|").append(textXPosition);
        }
        if (textYPosition != -1) {
            buttonBuilder.append("|ty|").append(textYPosition);
        }
        if (disabledTimeout != -1) {
            buttonBuilder.append("|disabledTimeout|").append(disabledTimeout);
        }

        if (cursor != Cursor.DEFAULT_CURSOR) {
            buttonBuilder.append("|cursor|").append(cursor);
        }

        buttonBuilder.append("<°");
        return buttonBuilder.toString();

    }

    private boolean isNullOrEmpty(String str) {
        return (str == null || str.isEmpty());
    }

    public static String createButton(String text, String command, int width, int height) {
        KButton button = new KButton(text, command);
        button.setWidth(width);
        button.setHeight(height);
        return button.toString();
    }
}