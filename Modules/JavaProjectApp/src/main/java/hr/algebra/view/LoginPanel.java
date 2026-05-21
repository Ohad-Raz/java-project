/*
 * Project setup note:
 * Placeholder for final project — 2026JAVA01 Project Task requires Login form (Administrator/User roles, registration).
 * Not implemented in professor Exercise 07-08 / 09-10 / 11-12 solutions; TODO after User entity and stored procedures exist.
 */

package hr.algebra.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Placeholder for login and simple User registration UI.
 * Will gate access to {@link MainFrame} in a later step (MVC: controller opens main shell after auth).
 */
public final class LoginPanel extends JPanel {

    public LoginPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Login panel placeholder (username/password, User registration — later step)"), BorderLayout.CENTER);
    }
}
