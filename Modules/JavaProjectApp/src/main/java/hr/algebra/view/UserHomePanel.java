/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.controller.AppSession;
import hr.algebra.model.AppUser;

// User home tab with a short feature guide.
public class UserHomePanel extends javax.swing.JPanel {

    public UserHomePanel() {
        initComponents();
        showCurrentUser();
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

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitle.setText("NASA Image of the Day Manager");

        lblDescription.setText("application for NASA RSS import, image/tag management, and XML export.");

        lblCurrentUserCaption.setText("Current user:");

        lblCurrentUserValue.setText("(not available)");

        taGuide.setEditable(false);
        taGuide.setColumns(20);
        taGuide.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        taGuide.setLineWrap(true);
        taGuide.setRows(5);
        taGuide.setText("Guide:\n- Admin: import or clear NASA RSS data.\n- Images: manage NASA image records (CRUD).\n- Tags: manage tag vocabulary (CRUD).\n- Related: assign tags to images with Drag and Drop.\n- XML export: export images with assigned tags.");
        taGuide.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taGuide);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(lblDescription, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCurrentUserCaption)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCurrentUserValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 789, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Shows the logged-in username and role from AppSession.
    private void showCurrentUser() {
        AppUser user = AppSession.getCurrentUser();
        if (user == null) {
            lblCurrentUserValue.setText("(not available)");
            return;
        }
        lblCurrentUserValue.setText(user.getUsername() + " (" + user.getRole() + ")");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCurrentUserCaption;
    private javax.swing.JLabel lblCurrentUserValue;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JTextArea taGuide;
    // End of variables declaration//GEN-END:variables
}
