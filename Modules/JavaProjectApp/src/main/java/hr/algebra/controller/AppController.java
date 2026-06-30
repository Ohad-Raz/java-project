package hr.algebra.controller;

import hr.algebra.view.LoginFrame;
import hr.algebra.view.util.LookAndFeelUtils;

// Application startup: LAF first, then login on the EDT (Exercise 09-10 pattern).
public final class AppController {

    private AppController() {
    }

    // Installs look-and-feel and opens the login screen.
    public static void start() {
        LookAndFeelUtils.installDefaultLookAndFeel();
        LoginFrame.launch();
    }
}
