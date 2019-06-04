import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMain extends JFrame {
    private int width = 1200; //todo get against the size of the screen
    private int height = 700; //todo get against the size of the screen
    private int startX = 30;  //todo get against the size of the screen
    private int startY = 30;  //todo get against the size of the screen

    public FormMain() {
        setTitle("Изучение 100 иностранных слов в час");
        setBounds(startX, startY, width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void initMenus() {
        JPanel jpBottomMenu = getBottomMenu();
        this.add(jpBottomMenu);

    }

    private JPanel getBottomMenu() {
        JPanel jpBottom = new JPanel(new CardLayout());
        jpBottom.setPreferredSize(new Dimension(1, 60)); //todo make parameters
        return jpBottom;
    }

    private JPanel getMainMenu() {
        JPanel jpMainMenu = new JPanel(new GridLayout());
        //Button start learning
        JButton jbStartLearning = new JButton("Изучение новых слов");
        jbStartLearning.addActionListener(e -> {

        });
        jpMainMenu.add(jbStartLearning);
        //Button start repeating
        JButton jbStartRepeating = new JButton("Повторение изученных");
        jbStartRepeating.addActionListener(e -> {

        });
        jpMainMenu.add(jbStartRepeating);
        //Button Exit
        JButton jbExitToMainMenu = new JButton("Выйти в основное меню");
        jbExitToMainMenu.addActionListener(e -> {
            showMainMenu();
        });
        jpMainMenu.add(jbExitToMainMenu);
        return jpMainMenu;
    }

    private JPanel getStartLearnMenu() {
        JPanel jpStartLearnMenu = new JPanel(new GridLayout());
        //Button Start

        //Button MainMenu

        return jpStartLearnMenu;
    }

    private JPanel getStepLearnMenu() {
        JPanel jpStepLearnMenu = new JPanel(new GridLayout());
        //Button Pass

        //Button Remembered

        //Button StopLearning

        return jpStepLearnMenu;
    }

    private JPanel getStartRepeatMenu() {
        JPanel jpStartRepeatMenu = new JPanel(new GridLayout());
        //Button MainMenu

        return jpStartRepeatMenu;
    }

    private JPanel getStepRepeatMenu() {
        JPanel jpStepRepeatMenu = new JPanel(new GridLayout());
        //Button Remembered

        //Button NotRemembered

        //Button FinishRepeating

        return jpStepRepeatMenu;
    }

    private void showMainMenu() {

    }

}
