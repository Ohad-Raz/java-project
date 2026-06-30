package hr.algebra.utilities;

import javax.swing.JOptionPane;

// JOptionPane helpers (Exercise 07-08 MessageUtils pattern).
public final class MessageUtils {

    private MessageUtils() {
    }

    public static void showInformationMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showConfirmDialog(String title, String message) {
        return JOptionPane.showConfirmDialog(
                null,
                message,
                title,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.ERROR_MESSAGE
        ) == JOptionPane.OK_OPTION;
    }
}
