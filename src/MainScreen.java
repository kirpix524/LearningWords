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

    public MainScreen() {
        state = null;
        setBackground(new Color(220, 220, 220));
    }

    private void update() {

    }

    private void render() {
        repaint();
    }

    public void changeState(States newState) {
        state = newState;
        update();
        render();
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
                break;
            case STARTREPEAT:
                break;
            case STEPREPEAT:
                break;
            default:
                break;
        }

    }
}
