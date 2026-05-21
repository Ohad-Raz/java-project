/*
 * Project setup note:
 * Based on Exercise 09-10, application startup orchestration (Look and Feel + EDT). MVC: no SQL in controller.
 */

package hr.algebra.controller;

import hr.algebra.view.MainFrame;
import hr.algebra.view.util.LookAndFeelUtils;

/**
 * Application entry orchestration (MVC: controller starts view, no SQL/GUI mixing).
 */
public final class AppController {

    private AppController() {
    }

    public static void start() {
        LookAndFeelUtils.installDefaultLookAndFeel();
        MainFrame.launch();
    }
}
