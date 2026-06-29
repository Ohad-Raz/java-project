/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.service.AdminImportResult;
import hr.algebra.service.AdminImportService;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.util.ClasspathImageUtils;
import javax.swing.SwingWorker;

/**
 * Administrator tab: NASA RSS import (Exercise 07-08 upload + Exercise 09-10 background work).
 */
public class AdminPanel extends javax.swing.JPanel {

    private static final String TITLE = "Admin";
    private static final String NASA_LOGO_RESOURCE = "/assets/nasa_logo.png";
    private static final int NASA_LOGO_SIZE = 72;

    private final AdminImportService importService = new AdminImportService();

    public AdminPanel() {
        initComponents();
        loadNasaLogo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImport = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblWorking = new javax.swing.JLabel();
        lblNasaLogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taStatus = new javax.swing.JTextArea();

        btnImport.setText("Import NASA Images");
        btnImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportActionPerformed(evt);
            }
        });

        btnClear.setText("Clear Imported Data");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        lblWorking.setText(" ");

        lblNasaLogo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNasaLogo.setText(" ");

        taStatus.setColumns(60);
        taStatus.setEditable(false);
        taStatus.setLineWrap(true);
        taStatus.setRows(12);
        taStatus.setText("Ready. Import downloads RSS images and saves them through stored procedures.");
        taStatus.setWrapStyleWord(true);
        jScrollPane1.setViewportView(taStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnImport)
                            .addComponent(btnClear)
                            .addComponent(lblWorking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblNasaLogo))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImport)
                    .addComponent(btnClear)
                    .addComponent(lblWorking)
                    .addComponent(lblNasaLogo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportActionPerformed
        if (!MessageUtils.showConfirmDialog(
                TITLE,
                "Import NASA images? Existing imported images and runtime assets will be cleared first.")) {
            return;
        }
        runImportInBackground(true);
    }//GEN-LAST:event_btnImportActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        if (!MessageUtils.showConfirmDialog(TITLE, "Clear all imported NASA images and asset files?")) {
            return;
        }
        clearImportedDataInBackground();
    }//GEN-LAST:event_btnClearActionPerformed

    /** Starts NASA RSS import on a background thread so the GUI stays responsive. */
    private void runImportInBackground(boolean clearExistingFirst) {
        setAdminControlsEnabled(false);
        lblWorking.setText("Import running...");
        updateStatusMessage("Import started. Please wait...");

        new SwingWorker<AdminImportResult, Void>() {
            @Override
            protected AdminImportResult doInBackground() throws Exception {
                return importService.importImages(clearExistingFirst);
            }

            @Override
            protected void done() {
                try {
                    AdminImportResult result = get();
                    showImportResult(result);
                } catch (Exception ex) {
                    MessageUtils.showErrorMessage(TITLE, "Import failed: " + ex.getMessage());
                    updateStatusMessage("Import failed.");
                } finally {
                    lblWorking.setText(" ");
                    setAdminControlsEnabled(true);
                }
            }
        }.execute();
    }

    /** Clears imported data in the background without blocking the EDT. */
    private void clearImportedDataInBackground() {
        setAdminControlsEnabled(false);
        lblWorking.setText("Clearing...");

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                importService.clearImportedData();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    updateStatusMessage("Imported data and runtime assets cleared.");
                } catch (Exception ex) {
                    MessageUtils.showErrorMessage(TITLE, "Clear failed: " + ex.getMessage());
                } finally {
                    lblWorking.setText(" ");
                    setAdminControlsEnabled(true);
                }
            }
        }.execute();
    }

    /** Disables import buttons while background work is running. */
    private void setAdminControlsEnabled(boolean enabled) {
        btnImport.setEnabled(enabled);
        btnClear.setEnabled(enabled);
    }

    /** Updates the status area with import results. */
    private void showImportResult(AdminImportResult result) {
        String message = """
                Import finished.
                Parsed: %d
                Inserted: %d
                Skipped duplicates: %d
                Linked tags: %d
                Failed images: %d
                """.formatted(
                result.getParsedCount(),
                result.getInsertedCount(),
                result.getSkippedDuplicateCount(),
                result.getLinkedTagCount(),
                result.getFailedImageCount());
        updateStatusMessage(message);
        MessageUtils.showInformationMessage(TITLE, "NASA import completed.");
    }

    /** Sets the status text shown in the admin panel. */
    private void updateStatusMessage(String message) {
        taStatus.setText(message);
        taStatus.setCaretPosition(0);
    }

    /** Shows the NASA logo from classpath resources on the admin header row. */
    private void loadNasaLogo() {
        ClasspathImageUtils.setLabelIconFromResource(
                lblNasaLogo,
                AdminPanel.class,
                NASA_LOGO_RESOURCE,
                NASA_LOGO_SIZE,
                NASA_LOGO_SIZE,
                "NASA");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnImport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNasaLogo;
    private javax.swing.JLabel lblWorking;
    private javax.swing.JTextArea taStatus;
    // End of variables declaration//GEN-END:variables
}
