package ru.surovcevnv.learnwords.classes;

import java.awt.*;

public class Card {


    public static final int WIDTH_DEFAULT = 400;
    public static final int HEIGHT_DEFAULT = 250;
    public static final int ARC_WIDTH_DEFAULT = 10;
    public static final int ARC_HEIGHT_DEFAULT = 10;
    public static final int WORD_X_INDENT_DEFAULT = 20;
    public static final int WORD_Y_INDENT_DEFAULT = 50;
    public static final int TRANSLATION_X_INDENT_DEFAULT = 200;
    public static final int TRANSLATION_Y_INDENT_DEFAULT = 150;
    public static final Color BORDER_COLOR_DEFAULT = new Color(0,0,0);
    public static final float BORDER_WIDTH_DEFAULT = 4.0f;
    //
    private int startX;
    private int startY;

    //
    private Word word;
    public static Font CARD_FONT = new Font("Times New Roman", Font.PLAIN, 28);
    private int width;
    private int height;
    private int arcWidth;
    private int arcHeight;
    private int wordXIndent;
    private int wordYIndent;
    private int translationXIndent;
    private int translationYIndent;
    private Color borderColor;

    public Card(int startX, int startY) {
        init(startX, startY, WIDTH_DEFAULT, HEIGHT_DEFAULT, ARC_WIDTH_DEFAULT, ARC_HEIGHT_DEFAULT, WORD_X_INDENT_DEFAULT, WORD_Y_INDENT_DEFAULT, TRANSLATION_X_INDENT_DEFAULT, TRANSLATION_Y_INDENT_DEFAULT,BORDER_COLOR_DEFAULT,  null);
    }

    private void init(int startX, int startY, int width, int height, int arcWidth, int arcHeight, int wordXIndent, int wordYIndent, int translationXIndent, int translationYIndent,Color borderColor, Word word) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.wordXIndent = wordXIndent;
        this.wordYIndent = wordYIndent;
        this.translationXIndent = translationXIndent;
        this.translationYIndent = translationYIndent;
        this.borderColor = borderColor;
        this.word = word;
    }

    private void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        //
        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();
        Font oldFont = g2d.getFont();
        //
        g2d.setColor(borderColor);

        g.drawRoundRect(startX, startY, width, height, arcWidth, arcHeight);
        g.setFont(CARD_FONT);
        g.drawString(word.getWord(), startX + wordXIndent, startY + wordYIndent);
        g.drawString(word.getTranslation(), startX + translationXIndent, startY + translationYIndent);
    }
}
