/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.service.AdminImportResult;
import hr.algebra.service.AdminImportService;
import hr.algebra.utilities.MessageUtils;
import javax.swing.SwingWorker;

// Admin tab for NASA RSS import and clear (07-08 upload + 09-10 SwingWorker).
public class AdminPanel extends javax.swing.JPanel {

    private static final String TITLE = "Admin";

    private final AdminImportService importService = new AdminImportService();

    public AdminPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImport = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        lblWorking = new javax.swing.JLabel();
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

        taStatus.setEditable(false);
        taStatus.setColumns(60);
        taStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        taStatus.setLineWrap(true);
        taStatus.setRows(12);
        taStatus.setText("Ready.\nUse Import to load the NASA RSS feed, download images, and save them to the database.");
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
                        .addComponent(btnImport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblWorking, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(lblWorking))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, Short.MAX_VALUE)
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

    // Starts NASA RSS import in SwingWorker so the GUI stays responsive.
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

    // Clears imported data in SwingWorker.
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

    // Disables import buttons while background work runs.
    private void setAdminControlsEnabled(boolean enabled) {
        btnImport.setEnabled(enabled);
        btnClear.setEnabled(enabled);
    }

    // Updates the status area with import counters.
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

    // Appends one line to the admin status area.
    private void updateStatusMessage(String message) {
        taStatus.setText(message);
        taStatus.setCaretPosition(0);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnImport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblWorking;
    private javax.swing.JTextArea taStatus;
    // End of variables declaration//GEN-END:variables
}
