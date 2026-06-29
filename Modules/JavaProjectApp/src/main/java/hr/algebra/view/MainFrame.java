/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.model.AppUser;
import hr.algebra.model.Role;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.util.LookAndFeelUtils;
import hr.algebra.view.util.ClasspathImageUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * JFrame host with menu bar and tabbed panels (Exercise 07-08 ArticleManager pattern).
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String TITLE = "NASA Image of the Day";
    private static final String NASA_LOGO_RESOURCE = "/assets/nasa_logo.png";
    private static final int NASA_HEADER_LOGO_SIZE = 48;
    private static final int TARGET_NORMAL_WIDTH = 1500;
    private static final int TARGET_NORMAL_HEIGHT = 975;
    private static final int SCREEN_MARGIN = 80;

    private final AppUser loggedInUser;
    private final Dimension normalSize;

    public MainFrame(AppUser loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.normalSize = computeNormalSize();
        initComponents();
        configureHeaderLogo();
        configureRestoredWindowBehavior();
        setMinimumSize(normalSize);
        setSize(normalSize);
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        postInitMenuBar();
        configureTabsForRole(loggedInUser.getRole());
    }

    /** Caps the preferred restored size to the current screen working area. */
    private static Dimension computeNormalSize() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        int width = Math.min(TARGET_NORMAL_WIDTH, Math.max(800, bounds.width - SCREEN_MARGIN));
        int height = Math.min(TARGET_NORMAL_HEIGHT, Math.max(600, bounds.height - SCREEN_MARGIN));
        return new Dimension(width, height);
    }

    /** Adds a small NASA logo above the tabbed content without changing role-based tabs. */
    private void configureHeaderLogo() {
        getContentPane().removeAll();
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(2, 8, 2, 8));
        JLabel lblLogo = new JLabel();
        lblLogo.setHorizontalAlignment(SwingConstants.RIGHT);
        ClasspathImageUtils.setLabelIconFromResource(
                lblLogo,
                MainFrame.class,
                NASA_LOGO_RESOURCE,
                NASA_HEADER_LOGO_SIZE,
                NASA_HEADER_LOGO_SIZE,
                "");
        header.add(lblLogo, BorderLayout.EAST);

        JPanel body = new JPanel(new BorderLayout());
        body.add(header, BorderLayout.NORTH);
        body.add(tpContent, BorderLayout.CENTER);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(body, BorderLayout.CENTER);
    }

    /** Restores a large centered window when leaving maximized state. */
    private void configureRestoredWindowBehavior() {
        addWindowStateListener(event -> {
            boolean wasMaximized = (event.getOldState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;
            boolean isNormal = (event.getNewState() & Frame.NORMAL) == Frame.NORMAL;
            if (wasMaximized && isNormal) {
                setSize(normalSize);
                setLocationRelativeTo(null);
            }
        });
    }

    /** Fills the Look and Feel submenu at runtime (dynamic menu items). */
    private void postInitMenuBar() {
        LookAndFeelUtils.populateLookAndFeelMenu(menuLookAndFeel, this);
    }

    /** Configures visible tabs according to ADMIN or USER role. */
    private void configureTabsForRole(Role role) {
        if (role == Role.ADMIN) {
            tpContent.addTab("Admin", new AdminPanel());
        }
        tpContent.addTab("User home", new UserHomePanel());
        tpContent.addTab("Images", new EntityManagementPanel());
        tpContent.addTab("Tags", new TagManagementPanel());
        tpContent.addTab("Related", new RelatedEntitiesPanel());
        if (role == Role.ADMIN) {
            tpContent.addTab("XML export", new XmlExportPanel());
        }
    }

    private void dispatchExit() {
        if (MessageUtils.showConfirmDialog(TITLE, "Exit application?")) {
            dispose();
            System.exit(0);
        }
    }

    /** Opens MainFrame on the EDT for the authenticated user. */
    public static void launch(AppUser user) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame(user);
            frame.setVisible(true);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        miExit = new javax.swing.JMenuItem();
        menuView = new javax.swing.JMenu();
        menuLookAndFeel = new javax.swing.JMenu();
        menuHelp = new javax.swing.JMenu();
        miAbout = new javax.swing.JMenuItem();
        tpContent = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("NASA Image of the Day");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        menuFile.setText("File");

        miExit.setText("Exit");
        miExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExitActionPerformed(evt);
            }
        });
        menuFile.add(miExit);

        menuView.setText("View");

        menuLookAndFeel.setText("Look and Feel");

        menuView.add(menuLookAndFeel);

        menuHelp.setText("Help");

        miAbout.setText("About");
        miAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAboutActionPerformed(evt);
            }
        });
        menuHelp.add(miAbout);

        mainMenuBar.add(menuFile);
        mainMenuBar.add(menuView);
        mainMenuBar.add(menuHelp);
        setJMenuBar(mainMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tpContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void miExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExitActionPerformed
        dispatchExit();
    }//GEN-LAST:event_miExitActionPerformed

    private void miAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAboutActionPerformed
        MessageUtils.showInformationMessage(
                TITLE,
                """
                NASA Image of the Day Manager
                
                Logged in as: %s (%s)
                
                Features:
                - Admin import and clear workflow
                - Images and Tags CRUD
                - Drag and Drop image-tag linking
                - JAXB XML export
                """.formatted(loggedInUser.getUsername(), loggedInUser.getRole())
        );
    }//GEN-LAST:event_miAboutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispatchExit();
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JMenu menuLookAndFeel;
    private javax.swing.JMenu menuView;
    private javax.swing.JMenuItem miAbout;
    private javax.swing.JMenuItem miExit;
    private javax.swing.JTabbedPane tpContent;
    // End of variables declaration//GEN-END:variables
}
