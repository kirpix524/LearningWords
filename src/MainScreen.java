import com.sun.management.VMOption;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JPanel {
    public enum States {
        STARTSCREEN,
        STARTLEARN,
        STEPLEARN,
        STARTREPEAT,
        STEPREPEAT
    }

    private States state;

    private Vocabulary vocabulary;

    public MainScreen() {
        state = null;
        setBackground(new Color(220, 220, 220));
    }

    public void loadVocabulary() {
        vocabulary = new Vocabulary();
        vocabulary.addWord("word", "слово");
        vocabulary.addWord("house", "дом (здание)");
        vocabulary.addWord("love", "любовь");
    }

    public void startStudying(int wordsToLearn) {
        vocabulary.startStudying(wordsToLearn);
        changeState(States.STEPLEARN);
        vocabulary.nextWord();
        repaint();
    }


    public void changeState(States newState) {
        state = newState;
        repaint();
    }

    public void passCurrentWord() {
        vocabulary.nextWord();
        repaint();
    }

    public void studyCurrentWord() {
        vocabulary.studiedWord(vocabulary.getCurrentWord());
        vocabulary.nextWord();
        repaint();
    }

    public boolean studiedEnough() {
        return vocabulary.studiedEnough();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (state) {
            case STARTSCREEN:
                g.setFont(new Font("Times New Roman", Font.PLAIN, 48));
                g.drawString("Начальный экран", 50, 200);
                break;
            case STARTLEARN:
                break;
            case STEPLEARN:
                drawCard(vocabulary.getCurrentWord(), g);
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
