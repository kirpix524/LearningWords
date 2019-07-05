package ru.surovcevnv.learnwords.forms;

import ru.surovcevnv.learnwords.classes.Card;
import ru.surovcevnv.learnwords.classes.CircleTimer;
import ru.surovcevnv.learnwords.classes.Word;
import ru.surovcevnv.learnwords.propertyclases.Fonts;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreen extends JPanel {
    public static final int TIMER_X = 900;
    public static final int TIMER_Y = 100;
    public static final int TIMER_WIDTH = 70;
    public static final int TIMER_HEIGHT = 70;
    public static final int TIMER_DEFAULT_DURATION = 60000;
    public static final Font FONT_DATE_TIME = new Font("Times New Roman", Font.PLAIN, 12);
    public static final int X_DATE_TIME = 20;
    public static final int Y_DATE_TIME = 10;
    private FormMain formMain;
    private static long REFRESH_TIME = 100;
    private static String FORMAT_DATE_TIME = "dd.MM HH:mm:ss";
    private static Color BACKGROUND = new Color(220, 220, 220);

    public CircleTimer timer;

    private class EventGenerator extends Thread {
        JPanel me;
        EventGenerator(JPanel me) {
            this.me = me;
            start();
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        me.repaint();
                    }
                });
                try {
                    sleep(REFRESH_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public MainScreen(FormMain formMain) {
        this.formMain = formMain;
        setBackground(BACKGROUND);
        new EventGenerator(this);
        timer = new CircleTimer(TIMER_X, TIMER_Y, TIMER_WIDTH, TIMER_HEIGHT, TIMER_DEFAULT_DURATION);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(FONT_DATE_TIME); //TODO extract to Fonts and return default value afterwards
        g.drawString(new SimpleDateFormat(FORMAT_DATE_TIME).format(new Date()), X_DATE_TIME, Y_DATE_TIME);
        if (timer.isVisible()) {
            timer.draw(g);
        }

        FormMain.States state = formMain.getCurrentState();
        switch (state) {
            case STARTSCREEN:
                g.setFont(new Font("Times New Roman", Font.PLAIN, 48));
                g.drawString("Начальный экран", 50, 200);
                break;
            case STARTLEARN:
                break;
            case STEPLEARN:
                drawCard(formMain.vocabulary.getCurrentWord(), g);
                break;
            case STARTREPEAT:
                break;
            case STEPREPEAT:
                break;
            default:
                break;
        }

    }

    private void drawCard(Word word, Graphics g) {
        int x = 100, y = 200;
        g.drawRoundRect(x, y, 400, 250, 10, 10);
        g.setFont(Card.CARD_FONT);
        g.drawString(word.getWord(), x + 20, y + 50);
        g.drawString(word.getTranslation(), x + 200, y + 150);
    }
}
