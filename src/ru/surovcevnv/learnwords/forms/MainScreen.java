package ru.surovcevnv.learnwords.forms;

import com.sun.management.VMOption;
import ru.surovcevnv.learnwords.classes.CircleTimer;
import ru.surovcevnv.learnwords.classes.Vocabulary;
import ru.surovcevnv.learnwords.classes.Word;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainScreen extends JPanel {
    private FormMain formMain;
    private static long REFRESH_TIME = 100;
    private static String DATE_TIME_FORMAT = "dd.MM HH:mm:ss";
    private static Color BACKGROUND = new Color(220, 220, 220);

    private FormMain.States state;

    public CircleTimer timer;

    private class EventGenerator extends Thread {
        JPanel form;
        EventGenerator(JPanel form) {
            this.form = form;
            start();
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        form.repaint();
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
        timer = new CircleTimer(900,100,70,70,60000, new Color(0,150,0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        g.drawString(new SimpleDateFormat(DATE_TIME_FORMAT).format(new Date()), 20, 10);
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
        g.setFont(new Font("Times New Roman", Font.PLAIN, 28));
        g.drawString(word.getWord(), x + 20, y + 50);
        g.drawString(word.getTranslation(), x + 200, y + 150);
    }
}
