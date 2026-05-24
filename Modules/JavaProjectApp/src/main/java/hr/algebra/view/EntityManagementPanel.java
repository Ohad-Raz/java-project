/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.utilities.FileUtils;
import hr.algebra.utilities.IconUtils;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.model.NasaImageTableModel;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

/**
 * NASA image CRUD tab (Exercise 07-08 EditArticlesPanel pattern).
 */
public class EntityManagementPanel extends javax.swing.JPanel {

    private static final String DIR = "assets";
    private static final String RESOURCES_ASSETS = "src/main/resources/assets";

    private Map<JTextComponent, JLabel> validationMap;

    private Repository repository;
    private NasaImageTableModel nasaImageTableModel;

    private NasaImage selectedImage;

    public EntityManagementPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbNasaImages = new javax.swing.JTable();
        lbIcon = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfTitle = new javax.swing.JTextField();
        lbTitleError = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfLink = new javax.swing.JTextField();
        lbLinkError = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbDescriptionError = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taDescription = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        tfPublishedDate = new javax.swing.JTextField();
        lbPublishedDateError = new javax.swing.JLabel();
        tfPicturePath = new javax.swing.JTextField();
        btnChooseImage = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lbPicturePathError = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tbNasaImages.setModel(new javax.swing.table.DefaultTableModel(
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
        tbNasaImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbNasaImagesMouseClicked(evt);
            }
        });
        tbNasaImages.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbNasaImagesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbNasaImages);

        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/no_image.png"))); // NOI18N

        jLabel2.setText("Title");

        lbTitleError.setForeground(new java.awt.Color(204, 0, 0));
        lbTitleError.setText("X");

        jLabel4.setText("Link");

        lbLinkError.setForeground(new java.awt.Color(204, 0, 0));
        lbLinkError.setText("X");

        jLabel6.setText("Description");

        lbDescriptionError.setForeground(new java.awt.Color(204, 0, 0));
        lbDescriptionError.setText("X");

        taDescription.setColumns(20);
        taDescription.setLineWrap(true);
        taDescription.setRows(5);
        jScrollPane2.setViewportView(taDescription);

        jLabel8.setText("Published date (RFC 1123 GMT)");

        tfPublishedDate.setName("Date"); // NOI18N

        lbPublishedDateError.setForeground(new java.awt.Color(204, 0, 0));
        lbPublishedDateError.setText("X");

        tfPicturePath.setEditable(false);

        btnChooseImage.setText("Choose");
        btnChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseImageActionPerformed(evt);
            }
        });

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

        lbPicturePathError.setForeground(new java.awt.Color(204, 0, 0));
        lbPicturePathError.setText("X");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lbTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfLink, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(tfPublishedDate)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                                                    .addGap(18, 18, 18)
                                                    .addComponent(lbPublishedDateError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbLinkError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(tfPicturePath)
                                .addGap(18, 18, 18)
                                .addComponent(btnChooseImage)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbPicturePathError, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTitleError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbLinkError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbDescriptionError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbPublishedDateError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPublishedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbPicturePathError, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPicturePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnChooseImage)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!formValid()) {
            return;
        }
        try {
            String localPicturePath = uploadPicture();
            NasaImage image = new NasaImage(
                    tfTitle.getText().trim(),
                    tfLink.getText().trim(),
                    taDescription.getText().trim(),
                    localPicturePath,
                    NasaImage.parsePublishedDate(tfPublishedDate.getText().trim()),
                    UUID.randomUUID().toString()
            );
            repository.createNasaImage(image);
            nasaImageTableModel.setImages(repository.selectNasaImages());
            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to create NASA image!");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedImage == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose a NASA image to update");
            return;
        }
        if (!formValid()) {
            return;
        }
        try {
            if (!tfPicturePath.getText().trim().equals(selectedImage.getPicturePath())) {
                deleteRuntimePictureFile(selectedImage.getPicturePath());
                String localPicturePath = uploadPicture();
                selectedImage.setPicturePath(localPicturePath);
            }

            selectedImage.setTitle(tfTitle.getText().trim());
            selectedImage.setLink(tfLink.getText().trim());
            selectedImage.setDescription(taDescription.getText().trim());
            selectedImage.setPublishedDate(
                    NasaImage.parsePublishedDate(tfPublishedDate.getText().trim()));

            repository.updateNasaImage(selectedImage.getId(), selectedImage);
            nasaImageTableModel.setImages(repository.selectNasaImages());
            clearForm();
        } catch (Exception ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to update NASA image!");
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedImage == null) {
            MessageUtils.showInformationMessage("Wrong operation", "Please choose a NASA image to delete");
            return;
        }
        if (MessageUtils.showConfirmDialog(
                "Delete NASA image",
                "Do you really want to delete this NASA image?")) {
            try {
                deleteRuntimePictureFile(selectedImage.getPicturePath());
                repository.deleteNasaImage(selectedImage.getId());
                nasaImageTableModel.setImages(repository.selectNasaImages());
                clearForm();
            } catch (Exception ex) {
                Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
                MessageUtils.showErrorMessage("Error", "Unable to delete NASA image!");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseImageActionPerformed
        Optional<File> optFile = FileUtils.uploadFile("Images", "jpg", "jpeg", "png");
        if (optFile.isEmpty()) {
            return;
        }
        File file = optFile.get();
        tfPicturePath.setText(file.getAbsolutePath());
        setIcon(lbIcon, file);
    }//GEN-LAST:event_btnChooseImageActionPerformed

    private void tbNasaImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbNasaImagesMouseClicked
        showNasaImage();
    }//GEN-LAST:event_tbNasaImagesMouseClicked

    private void tbNasaImagesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbNasaImagesKeyReleased
        showNasaImage();
    }//GEN-LAST:event_tbNasaImagesKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            if (repository == null) {
                init();
            } else {
                refreshTable();
            }
        } catch (Exception ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to refresh NASA images!");
        }
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChooseImage;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbDescriptionError;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbLinkError;
    private javax.swing.JLabel lbPicturePathError;
    private javax.swing.JLabel lbPublishedDateError;
    private javax.swing.JLabel lbTitleError;
    private javax.swing.JTextArea taDescription;
    private javax.swing.JTable tbNasaImages;
    private javax.swing.JTextField tfLink;
    private javax.swing.JTextField tfPicturePath;
    private javax.swing.JTextField tfPublishedDate;
    private javax.swing.JTextField tfTitle;
    // End of variables declaration//GEN-END:variables

    /** Loads validation map, repository, and table when the tab is first shown. */
    private void init() {
        if (repository != null) {
            return;
        }
        try {
            initValidation();
            hideErrors();
            initRepository();
            initTable();
        } catch (Exception ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Unrecoverable error", "Cannot initiate the form");
            System.exit(1);
        }
    }

    /** Wires each input field to its validation error label (stable field order). */
    private void initValidation() {
        validationMap = new LinkedHashMap<>();
        validationMap.put(tfTitle, lbTitleError);
        validationMap.put(tfLink, lbLinkError);
        validationMap.put(taDescription, lbDescriptionError);
        validationMap.put(tfPublishedDate, lbPublishedDateError);
        validationMap.put(tfPicturePath, lbPicturePathError);
    }

    /** Hides all validation error markers. */
    private void hideErrors() {
        validationMap.values().forEach(label -> label.setVisible(false));
    }

    /** Obtains the shared repository from the factory. */
    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    /** Binds the table to {@link NasaImageTableModel} and loads all images. */
    private void initTable() throws Exception {
        tbNasaImages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbNasaImages.setAutoCreateRowSorter(true);
        tbNasaImages.setRowHeight(25);
        nasaImageTableModel = new NasaImageTableModel(repository.selectNasaImages());
        tbNasaImages.setModel(nasaImageTableModel);
    }

    /** Reloads table rows from the repository (e.g. after Admin import). */
    private void refreshTable() throws Exception {
        nasaImageTableModel.setImages(repository.selectNasaImages());
    }

    /** Validates required fields and RFC 1123 published date before save. */
    private boolean formValid() {
        hideErrors();
        boolean ok = true;

        for (Map.Entry<JTextComponent, JLabel> entry : validationMap.entrySet()) {
            JTextComponent field = entry.getKey();
            JLabel errorLabel = entry.getValue();
            boolean empty = field.getText().trim().isEmpty();
            ok &= !empty;
            errorLabel.setVisible(empty);
            if ("Date".equals(field.getName())) {
                try {
                    NasaImage.parsePublishedDate(field.getText().trim());
                } catch (Exception e) {
                    ok = false;
                    errorLabel.setVisible(true);
                }
            }
        }
        return ok;
    }

    /** Clears the edit form and preview after add/update/delete. */
    private void clearForm() {
        hideErrors();
        validationMap.keySet().forEach(field -> field.setText(""));
        resetIcon();
        selectedImage = null;
    }

    /** Copies the chosen file into runtime {@code assets/} and returns the relative path. */
    private String uploadPicture() throws IOException {
        String picturePath = tfPicturePath.getText();
        String ext = picturePath.substring(picturePath.lastIndexOf('.'));
        String pictureName = UUID.randomUUID() + ext;
        String localPicturePath = DIR + File.separator + pictureName;
        FileUtils.copy(picturePath, localPicturePath);
        return localPicturePath;
    }

    /** Loads the selected table row into the edit form. */
    private void showNasaImage() {
        int selectedRow = tbNasaImages.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        clearForm();
        int rowIndex = tbNasaImages.convertRowIndexToModel(selectedRow);
        int selectedImageId = (int) nasaImageTableModel.getValueAt(rowIndex, 0);

        try {
            Optional<NasaImage> optImage = repository.selectNasaImage(selectedImageId);
            if (optImage.isPresent()) {
                selectedImage = optImage.get();
                fillForm(selectedImage);
            }
        } catch (Exception ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to show NASA image!");
        }
    }

    /** Populates text fields and preview from a {@link NasaImage}. */
    private void fillForm(NasaImage image) {
        if (image.getPicturePath() != null && Files.exists(Paths.get(image.getPicturePath()))) {
            tfPicturePath.setText(image.getPicturePath());
            setIcon(lbIcon, new File(image.getPicturePath()));
        }
        tfTitle.setText(image.getTitle());
        tfLink.setText(image.getLink());
        taDescription.setText(image.getDescription());
        tfPublishedDate.setText(NasaImage.formatPublishedDate(image.getPublishedDate()));
    }

    /** Scales and shows the image on the preview label. */
    private void setIcon(JLabel label, File file) {
        try {
            label.setIcon(IconUtils.createIcon(file, label.getWidth(), label.getHeight()));
            label.setText("");
        } catch (IOException ex) {
            Logger.getLogger(EntityManagementPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to set icon!");
        }
    }

    /** Restores the default placeholder preview image. */
    private void resetIcon() {
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/no_image.png")));
        lbIcon.setText("");
    }

    /** Deletes a runtime asset file only (never under {@code src/main/resources/assets}). */
    private void deleteRuntimePictureFile(String picturePath) throws IOException {
        if (picturePath == null || picturePath.isBlank()) {
            return;
        }
        String normalized = picturePath.replace('\\', '/');
        if (normalized.contains(RESOURCES_ASSETS)) {
            return;
        }
        Path path = Paths.get(picturePath).normalize();
        if (path.isAbsolute()) {
            Path assetsRoot = Paths.get(DIR).toAbsolutePath().normalize();
            if (!path.startsWith(assetsRoot)) {
                return;
            }
        } else if (!normalized.startsWith(DIR + "/") && !normalized.equals(DIR)) {
            return;
        }
        Files.deleteIfExists(path);
    }
}
