/*
 * Project setup note:
 * Based on Exercise 09-10, non-freezing Swing background task pattern. Adapted for future RSS/database loading.
 */

package hr.algebra.utilities;

import java.awt.EventQueue;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;

/**
 * Background work + EDT-safe GUI updates (Exercise 09-10 pattern).
 */
public final class SwingGuiHelper {

    private static final Logger LOGGER = Logger.getLogger(SwingGuiHelper.class.getName());

    private SwingGuiHelper() {
    }

    /**
     * Runs work on a background thread and applies GUI updates on the EDT.
     */
    public static void runInBackground(Runnable backgroundTask, Runnable onEdtAfterFinish) {
        new Thread(() -> {
            try {
                backgroundTask.run();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Background task failed", ex);
            } finally {
                if (onEdtAfterFinish != null) {
                    EventQueue.invokeLater(onEdtAfterFinish);
                }
            }
        }).start();
    }

    /**
     * SwingWorker variant for tasks that return a result to the EDT.
     */
    public static <T> void runWithSwingWorker(
            Callable<T> backgroundTask,
            Consumer<T> onSuccess,
            Consumer<Exception> onError
    ) {
        new SwingWorker<T, Void>() {
            @Override
            protected T doInBackground() throws Exception {
                return backgroundTask.call();
            }

            @Override
            protected void done() {
                try {
                    if (onSuccess != null) {
                        onSuccess.accept(get());
                    }
                } catch (Exception ex) {
                    LOGGER.log(Level.SEVERE, "SwingWorker failed", ex);
                    if (onError != null) {
                        onError.accept(ex);
                    }
                }
            }
        }.execute();
    }
}
