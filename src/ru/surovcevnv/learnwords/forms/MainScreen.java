package ru.surovcevnv.learnwords.forms;

import com.sun.management.VMOption;
import ru.surovcevnv.learnwords.classes.Vocabulary;
import ru.surovcevnv.learnwords.classes.Word;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {
    private FormMain formMain;

    private FormMain.States state;



    public MainScreen(FormMain formMain) {
        this.formMain = formMain;
        setBackground(new Color(220, 220, 220));
    }

    private void update() {

    }

    private void render() {

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
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
