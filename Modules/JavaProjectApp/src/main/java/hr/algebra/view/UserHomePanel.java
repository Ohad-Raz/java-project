/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.controller.AppSession;
import hr.algebra.model.AppUser;
import hr.algebra.utilities.IconUtils;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User role home tab with a quick guide for final-project features.
 */
public class UserHomePanel extends javax.swing.JPanel {

    private static final String BANNER_RESOURCE = "/assets/home_nasa.png";

    public UserHomePanel() {
        initComponents();
        showCurrentUser();
        lblBanner.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent evt) {
                loadBannerImage();
            }
        });
        loadBannerImage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        lblCurrentUserCaption = new javax.swing.JLabel();
        lblCurrentUserValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taGuide = new javax.swing.JTextArea();
        lblBanner = new javax.swing.JLabel();

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setText("NASA Image of the Day Manager");

        lblDescription.setText("Swing application for NASA RSS import, image/tag management, and JAXB XML export.");

        lblCurrentUserCaption.setText("Current user:");

        lblCurrentUserValue.setText("(not available)");

        taGuide.setColumns(20);
        taGuide.setEditable(false);
        taGuide.setLineWrap(true);
        taGuide.setRows(5);
        taGuide.setText("Guide:\n- Admin: import or clear NASA RSS data.\n- Images: manage NASA image records (CRUD).\n- Tags: manage tag vocabulary (CRUD).\n- Related: assign tags to images with Drag and Drop.\n- XML export: export images with assigned tags.");
        taGuide.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taGuide);

        lblBanner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBanner.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCurrentUserCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCurrentUserValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(lblBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurrentUserCaption)
                    .addComponent(lblCurrentUserValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /** Displays the authenticated user and role from the current application session. */
    private void showCurrentUser() {
        AppUser user = AppSession.getCurrentUser();
        if (user == null) {
            lblCurrentUserValue.setText("(not available)");
            return;
        }
        lblCurrentUserValue.setText(user.getUsername() + " (" + user.getRole() + ")");
    }

    /** Scales and shows the NASA home banner from classpath resources. */
    private void loadBannerImage() {
        int width = lblBanner.getWidth();
        int height = lblBanner.getHeight();
        if (width <= 0 || height <= 0) {
            width = 640;
            height = 280;
        }
        try {
            lblBanner.setIcon(IconUtils.createIconFromResource(getClass(), BANNER_RESOURCE, width, height));
            lblBanner.setText("");
        } catch (IOException ex) {
            Logger.getLogger(UserHomePanel.class.getName()).log(Level.WARNING, "Unable to load home banner image.", ex);
            lblBanner.setText("NASA Image of the Day Manager");
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBanner;
    private javax.swing.JLabel lblCurrentUserCaption;
    private javax.swing.JLabel lblCurrentUserValue;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea taGuide;
    // End of variables declaration//GEN-END:variables
}
