/*
 * Project setup note:
 * Placeholder for final project. The final implementation will be completed after choosing the RSS source, domain entities, and database schema.
 */

package hr.algebra.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Placeholder tab: Administrator workflow (2026JAVA01 Project Task).
 * Will adapt Exercise 07-08 {@code UploadArticlesPanel} pattern: delete-all-data, RSS load via
 * {@code hr.algebra.parsers.rss} parser (application module, not Dao), assets cleanup.
 */
public final class AdminPanel extends JPanel {

    public AdminPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Admin panel placeholder"), BorderLayout.CENTER);
    }
}
