/*
 * Project setup note:
 * Application entry point (Exercise 07-08 ArticleManager main pattern). Delegates to controller.
 */

package hr.algebra;

import hr.algebra.controller.AppController;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        AppController.start();
    }
}
