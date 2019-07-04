package ru.surovcevnv.learnwords;

import ru.surovcevnv.learnwords.forms.FormMain;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FormMain formMain = new FormMain();
            }
        });
    }
}
