/*
 * Project setup note:
 * Based on Exercise 07-08, JFrame host with JMenuBar and JTabbedPane (ArticleManager shell). Adapted for final project tabs.
 */

package hr.algebra.view;

import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.dialog.PlaceholderDialog;
import hr.algebra.view.util.LookAndFeelUtils;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

/**
 * JFrame host with menu bar and tabbed panels (Exercise 07-08 ArticleManager pattern).
 */
public final class MainFrame extends JFrame {

    private static final String TITLE = "Java Programming 1 - Final Project (Setup)";

    private final JTabbedPane tabbedPane = new JTabbedPane();

    public MainFrame() {
        super(TITLE);
        initMenuBar();
        initTabs();
        initFrame();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> dispatchExit());
        menuFile.add(exitItem);

        JMenu menuView = new JMenu("View");
        JMenu menuLookAndFeel = new JMenu("Look and Feel");
        LookAndFeelUtils.populateLookAndFeelMenu(menuLookAndFeel, this);
        menuView.add(menuLookAndFeel);

        JMenu menuHelp = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> MessageUtils.showInformationMessage(
                TITLE,
                "Setup phase foundation. Business screens and persistence come in the next steps."
        ));
        JMenuItem sampleDialogItem = new JMenuItem("Sample dialog");
        sampleDialogItem.addActionListener(e -> new PlaceholderDialog(this).setVisible(true));
        menuHelp.add(aboutItem);
        menuHelp.add(sampleDialogItem);

        menuBar.add(menuFile);
        menuBar.add(menuView);
        menuBar.add(menuHelp);
        setJMenuBar(menuBar);
    }

    private void initTabs() {
        tabbedPane.addTab("Admin", new AdminPanel());
        tabbedPane.addTab("User home", new UserHomePanel());
        tabbedPane.addTab("Entities", new EntityManagementPanel());
        tabbedPane.addTab("Related", new RelatedEntitiesPanel());
        tabbedPane.addTab("XML export", new XmlExportPanel());
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispatchExit();
            }
        });
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
        setSize(1200, 780);
        setLocationRelativeTo(null);
    }

    private void dispatchExit() {
        if (MessageUtils.showConfirmDialog(TITLE, "Exit application?")) {
            dispose();
            System.exit(0);
        }
    }

    public static void launch() {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
