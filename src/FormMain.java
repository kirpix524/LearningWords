import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormMain extends JFrame {
    private int width = 1200; //todo get against the size of the screen
    private int height = 700; //todo get against the size of the screen
    private int startX = 30;  //todo get against the size of the screen
    private int startY = 30;  //todo get against the size of the screen
    //
    private JPanel jpBottomMenu;
    private MainScreen jpMainScreen;
    //

    public FormMain() {
        setTitle("Изучение 100 иностранных слов в час");
        setBounds(startX, startY, width, height);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        //
        initMenus();
        jpMainScreen.loadVocabulary();
        showMainMenu();
    }

    private void initMenus() {
        jpBottomMenu = getBottomMenu();
        this.add(jpBottomMenu, BorderLayout.SOUTH);
        //
        JPanel jpMainMenu = getMainMenu();
        jpBottomMenu.add(jpMainMenu, "jpMainMenu");
        //
        JPanel jpStartLearnMenu = getStartLearnMenu();
        jpBottomMenu.add(jpStartLearnMenu, "jpStartLearnMenu");
        //
        JPanel jpStepLearnMenu = getStepLearnMenu();
        jpBottomMenu.add(jpStepLearnMenu, "jpStepLearnMenu");
        //
        JPanel jpStartRepeatMenu = getStartRepeatMenu();
        jpBottomMenu.add(jpStartRepeatMenu, "jpStartRepeatMenu");
        //
        JPanel jpStepRepeatMenu = getStepRepeatMenu();
        jpBottomMenu.add(jpStepRepeatMenu, "jpStepRepeatMenu");
        //
        jpMainScreen = new MainScreen();
        this.add(jpMainScreen, BorderLayout.CENTER);
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
            showStartLearn();
        });
        jpMainMenu.add(jbStartLearning);
        //Button start repeating
        JButton jbStartRepeating = new JButton("Повторение изученных");
        jbStartRepeating.addActionListener(e -> {
            showStartRepeat();
        });
        jpMainMenu.add(jbStartRepeating);
        //Button Exit
        JButton jbExitToMainMenu = new JButton("Выход");
        jbExitToMainMenu.addActionListener(e -> {
            System.exit(0);
        });
        jpMainMenu.add(jbExitToMainMenu);
        return jpMainMenu;
    }

    private JPanel getStartLearnMenu() {
        JPanel jpStartLearnMenu = new JPanel(new GridLayout());
        //Button Start
        JButton jbStartLearn = new JButton("Начать");
        jbStartLearn.addActionListener(e -> {
            showStepLearn();
        });
        jpStartLearnMenu.add(jbStartLearn);
        //Button MainMenu
        JButton jbMainMenu = new JButton("Вернуться в основное меню");
        jbMainMenu.addActionListener(e -> {
            showMainMenu();
        });
        jpStartLearnMenu.add(jbMainMenu);
        return jpStartLearnMenu;
    }

    private JPanel getStepLearnMenu() {
        JPanel jpStepLearnMenu = new JPanel(new GridLayout());
        //Button Pass
        JButton jbPass = new JButton("Пропустить");
        jbPass.addActionListener(e -> {
            jpMainScreen.passCurrentWord();
        });
        jpStepLearnMenu.add(jbPass);
        //Button Remembered
        JButton jbRemembered = new JButton("Запомнил");
        jbRemembered.addActionListener(e -> {
            jpMainScreen.studyCurrentWord();
            if (jpMainScreen.studiedEnough()) {
                showStartLearn();
            }
        });
        jpStepLearnMenu.add(jbRemembered);
        //Button StopLearning
        JButton jbStopLearning = new JButton("Закончить изучение слов");
        jbStopLearning.addActionListener(e -> {
            showStartLearn();
        });
        jpStepLearnMenu.add(jbStopLearning);
        return jpStepLearnMenu;
    }

    private JPanel getStartRepeatMenu() {
        JPanel jpStartRepeatMenu = new JPanel(new GridLayout());
        //Button MainMenu
        JButton jbMainMenu = new JButton("Вернуться в основное меню");
        jbMainMenu.addActionListener(e -> {
            showMainMenu();
        });
        jpStartRepeatMenu.add(jbMainMenu);
        return jpStartRepeatMenu;
    }

    private JPanel getStepRepeatMenu() {
        JPanel jpStepRepeatMenu = new JPanel(new GridLayout());
        //Button Remembered
        JButton jbRemembered = new JButton("Вспомнил");
        jbRemembered.addActionListener(e -> {

        });
        jpStepRepeatMenu.add(jbRemembered);
        //Button NotRemembered
        JButton jbNotRemembered = new JButton("Не вспомнил");
        jbNotRemembered.addActionListener(e -> {

        });
        jpStepRepeatMenu.add(jbNotRemembered);
        //Button FinishRepeating
        JButton jbFinishRepeating = new JButton("Закончить повторение");
        jbFinishRepeating.addActionListener(e -> {
            showStartRepeat();
        });
        return jpStepRepeatMenu;
    }

    private void showMainMenu() {
        jpMainScreen.changeState(MainScreen.States.STARTSCREEN);
        ((CardLayout) jpBottomMenu.getLayout()).show(jpBottomMenu, "jpMainMenu");
    }

    private void showStartLearn() {
        jpMainScreen.changeState(MainScreen.States.STARTLEARN);
        ((CardLayout) jpBottomMenu.getLayout()).show(jpBottomMenu, "jpStartLearnMenu");
    }

    private void showStepLearn() {
        jpMainScreen.changeState(MainScreen.States.STEPLEARN);
        jpMainScreen.startStudying(3);
        ((CardLayout) jpBottomMenu.getLayout()).show(jpBottomMenu, "jpStepLearnMenu");
    }

    private void showStartRepeat() {
        jpMainScreen.changeState(MainScreen.States.STARTREPEAT);
        ((CardLayout) jpBottomMenu.getLayout()).show(jpBottomMenu, "jpStartRepeatMenu");
    }

    private void showStepRepeat() {
        jpMainScreen.changeState(MainScreen.States.STEPREPEAT);
        ((CardLayout) jpBottomMenu.getLayout()).show(jpBottomMenu, "jpStepRepeatMenu");
    }
}
