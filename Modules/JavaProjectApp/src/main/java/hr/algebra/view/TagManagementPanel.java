/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.Tag;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.model.TagTableModel;
import hr.algebra.view.util.TableRenderUtils;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

/**
 * Tag CRUD tab (Exercise 07-08 EditArticlesPanel pattern, simplified for Tag).
 */
public class TagManagementPanel extends javax.swing.JPanel {

    private static final String TITLE = "Tags";

    private Map<JTextComponent, JLabel> validationMap;

    private Repository repository;
    private TagTableModel tagTableModel;

    private Tag selectedTag;

    public TagManagementPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbTags = new javax.swing.JTable();
        lblName = new javax.swing.JLabel();
        tfName = new javax.swing.JTextField();
        lbNameError = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tbTags.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTags.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTagsMouseClicked(evt);
            }
        });
        tbTags.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbTagsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbTags);

        lblName.setText("Name");

        lbNameError.setForeground(new java.awt.Color(204, 0, 0));
        lbNameError.setText("X");
        lbNameError.setVisible(false);

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(204, 0, 51));
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addGap(18, 18, 18)
                        .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addGap(18, 18, 18)
                        .addComponent(btnClear)
                        .addGap(18, 18, 18)
                        .addComponent(btnRefresh))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(tfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!formValid()) {
            return;
        }
        try {
            Tag tag = new Tag(tfName.getText().trim());
            repository.createTag(tag);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            handlePersistenceError(ex, "create");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedTag == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose a tag to update");
            return;
        }
        if (!formValid()) {
            return;
        }
        try {
            selectedTag.setName(tfName.getText().trim());
            repository.updateTag(selectedTag.getId(), selectedTag);
            refreshTable();
            clearForm();
        } catch (Exception ex) {
            handlePersistenceError(ex, "update");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedTag == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose a tag to delete");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Delete tag",
                "Do you really want to delete tag \"" + selectedTag.getName() + "\"?")) {
            try {
                repository.deleteTag(selectedTag.getId());
                refreshTable();
                clearForm();
            } catch (Exception ex) {
                handlePersistenceError(ex, "delete");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearForm();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        try {
            refreshTable();
        } catch (Exception ex) {
            Logger.getLogger(TagManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to refresh tags!");
        }
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tbTagsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTagsMouseClicked
        showTag();
    }//GEN-LAST:event_tbTagsMouseClicked

    private void tbTagsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbTagsKeyReleased
        showTag();
    }//GEN-LAST:event_tbTagsKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            if (repository == null) {
                init();
            } else {
                refreshTable();
            }
        } catch (Exception ex) {
            Logger.getLogger(TagManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to load tags!");
        }
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbNameError;
    private javax.swing.JLabel lblName;
    private javax.swing.JTable tbTags;
    private javax.swing.JTextField tfName;
    // End of variables declaration//GEN-END:variables

    /** Initializes validation, repository, and table when the tab is first shown. */
    private void init() throws Exception {
        initValidation();
        hideErrors();
        initRepository();
        initTable();
    }

    /** Wires the name field to its validation error label. */
    private void initValidation() {
        validationMap = new LinkedHashMap<>();
        validationMap.put(tfName, lbNameError);
    }

    /** Hides all validation error markers. */
    private void hideErrors() {
        validationMap.values().forEach(label -> label.setVisible(false));
    }

    /** Obtains the shared repository from the factory. */
    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    /** Binds the table to {@link TagTableModel} and loads tags from the database. */
    private void initTable() throws Exception {
        tbTags.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbTags.setAutoCreateRowSorter(true);
        tbTags.setRowHeight(25);
        tagTableModel = new TagTableModel(repository.selectTags());
        tbTags.setModel(tagTableModel);
        configureTableAppearance();
    }

    /** Applies centered headers/cells and column widths after the model is bound. */
    private void configureTableAppearance() {
        TableRenderUtils.applyTagTableStyle(tbTags);
    }

    /** Reloads tag rows from the database into the table. */
    private void refreshTable() throws Exception {
        tagTableModel.setTags(repository.selectTags());
    }

    /** Validates that the tag name is not empty. */
    private boolean formValid() {
        hideErrors();
        boolean ok = true;

        for (Map.Entry<JTextComponent, JLabel> entry : validationMap.entrySet()) {
            JTextComponent field = entry.getKey();
            JLabel errorLabel = entry.getValue();
            boolean empty = field.getText().trim().isEmpty();
            ok &= !empty;
            errorLabel.setVisible(empty);
        }
        return ok;
    }

    /** Clears the edit form and current selection state. */
    private void clearForm() {
        hideErrors();
        validationMap.keySet().forEach(field -> field.setText(""));
        selectedTag = null;
    }

    /** Loads the selected table row into the edit form. */
    private void showTag() {
        int selectedRow = tbTags.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        hideErrors();
        validationMap.keySet().forEach(field -> field.setText(""));
        selectedTag = null;

        int rowIndex = tbTags.convertRowIndexToModel(selectedRow);
        int selectedTagId = (int) tagTableModel.getValueAt(rowIndex, 0);

        try {
            Optional<Tag> optTag = repository.selectTag(selectedTagId);
            if (optTag.isPresent()) {
                selectedTag = optTag.get();
                fillForm(selectedTag);
            }
        } catch (Exception ex) {
            Logger.getLogger(TagManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show tag!");
        }
    }

    /** Populates the form from the selected {@link Tag}. */
    private void fillForm(Tag tag) {
        tfName.setText(tag.getName());
    }

    /** Shows a friendly message for duplicate tag names or a generic persistence error. */
    private void handlePersistenceError(Exception ex, String action) {
        if (isDuplicateNameError(ex)) {
            MessageUtils.showErrorMessage("Error", "A tag with this name already exists.");
            return;
        }
        Logger.getLogger(TagManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
        MessageUtils.showErrorMessage("Error", "Unable to " + action + " tag!");
    }

    /** Detects SQL Server unique-key violations on Tag.Name. */
    private boolean isDuplicateNameError(Exception ex) {
        Throwable cause = ex;
        while (cause != null) {
            if (cause instanceof SQLException sqlEx && (sqlEx.getErrorCode() == 2627 || sqlEx.getErrorCode() == 2601)) {
                return true;
            }
            String message = cause.getMessage();
            if (message != null) {
                String lower = message.toLowerCase();
                if (lower.contains("unique") || lower.contains("duplicate")) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }
}
