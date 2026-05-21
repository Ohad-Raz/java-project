/*
 * Project setup note:
 * Based on Exercise 11-12, JDialog form pattern (AuthorDialog reference). Placeholder until entity forms exist.
 */

package hr.algebra.view.dialog;

import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Dialog pattern placeholder (Exercise 11-12 AuthorDialog reference).
 * Final validation fields and entity binding will be added later.
 */
public final class PlaceholderDialog extends JDialog {

    public PlaceholderDialog(Frame owner) {
        super(owner, "Placeholder dialog", true);
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout(8, 8));
        add(new JLabel("Dialog placeholder - entity form will be implemented later."), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        JButton close = new JButton("Close");
        close.addActionListener(e -> dispose());
        actions.add(close);
        add(actions, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getOwner());
    }
}
