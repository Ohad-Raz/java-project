/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.model.AppUser;
import hr.algebra.model.Role;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.dialog.PlaceholderDialog;
import hr.algebra.view.util.LookAndFeelUtils;
import java.awt.EventQueue;

/**
 * JFrame host with menu bar and tabbed panels (Exercise 07-08 ArticleManager pattern).
 */
public class MainFrame extends javax.swing.JFrame {

    private static final String TITLE = "NASA Image of the Day";

    private final AppUser loggedInUser;

    public MainFrame(AppUser loggedInUser) {
        this.loggedInUser = loggedInUser;
        initComponents();
        setSize(1200, 780);
        setLocationRelativeTo(null);
        postInitMenuBar();
        configureTabsForRole(loggedInUser.getRole());
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
        miSampleDialog = new javax.swing.JMenuItem();
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

        miSampleDialog.setText("Sample dialog");
        miSampleDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSampleDialogActionPerformed(evt);
            }
        });
        menuHelp.add(miSampleDialog);

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
                "Logged in as: " + loggedInUser.getUsername() + " (" + loggedInUser.getRole() + ")"
        );
    }//GEN-LAST:event_miAboutActionPerformed

    private void miSampleDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSampleDialogActionPerformed
        new PlaceholderDialog(this).setVisible(true);
    }//GEN-LAST:event_miSampleDialogActionPerformed

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
    private javax.swing.JMenuItem miSampleDialog;
    private javax.swing.JTabbedPane tpContent;
    // End of variables declaration//GEN-END:variables
}
