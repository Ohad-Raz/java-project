/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.dnd.RelatedEntityDragDropSupport;
import hr.algebra.view.model.RelatedNasaImageTableModel;
import hr.algebra.view.util.TableRenderUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 * Related entities tab: link NASA images to tags via drag-and-drop (Exercise 11-12 pattern).
 */
public class RelatedEntitiesPanel extends javax.swing.JPanel {

    private static final String TITLE = "Related";

    private Repository repository;
    private RelatedNasaImageTableModel imageTableModel;

    private List<Tag> allTags = new ArrayList<>();
    private NasaImage selectedImage;

    private DefaultListModel<Tag> availableTagsModel;
    private DefaultListModel<Tag> assignedTagsModel;

    public RelatedEntitiesPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelectImage = new javax.swing.JLabel();
        jScrollPaneImages = new javax.swing.JScrollPane();
        tbImages = new javax.swing.JTable();
        lblSelectedImage = new javax.swing.JLabel();
        lblAvailableTags = new javax.swing.JLabel();
        lblAssignedTags = new javax.swing.JLabel();
        jScrollPaneAvailable = new javax.swing.JScrollPane();
        lstAvailableTags = new javax.swing.JList<>();
        jScrollPaneAssigned = new javax.swing.JScrollPane();
        lstAssignedTags = new javax.swing.JList<>();
        btnRefresh = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lblSelectImage.setText("Select NASA Image");

        tbImages.setModel(new javax.swing.table.DefaultTableModel(
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
        tbImages.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbImagesMouseClicked(evt);
            }
        });
        tbImages.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbImagesKeyReleased(evt);
            }
        });
        jScrollPaneImages.setViewportView(tbImages);

        lblSelectedImage.setText("Selected image: (none)");

        lblAvailableTags.setText("Available Tags");

        lblAssignedTags.setText("Assigned Tags");

        jScrollPaneAvailable.setViewportView(lstAvailableTags);

        jScrollPaneAssigned.setViewportView(lstAssignedTags);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnRemove.setText("Remove selected assigned tag");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSelectImage)
                    .addComponent(jScrollPaneImages)
                    .addComponent(lblSelectedImage)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAvailableTags)
                            .addComponent(jScrollPaneAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRefresh))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAssignedTags)
                            .addComponent(jScrollPaneAssigned, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemove))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSelectImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneImages, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelectedImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAvailableTags)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneAvailable, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAssignedTags)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneAssigned, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshAll();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        removeSelectedAssignedTag();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void tbImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImagesMouseClicked
        showSelectedImage();
    }//GEN-LAST:event_tbImagesMouseClicked

    private void tbImagesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbImagesKeyReleased
        showSelectedImage();
    }//GEN-LAST:event_tbImagesKeyReleased

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            if (repository == null) {
                init();
            } else {
                refreshAll();
            }
        } catch (Exception ex) {
            Logger.getLogger(RelatedEntitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to load related data!");
        }
    }//GEN-LAST:event_formComponentShown

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnRemove;
    private javax.swing.JScrollPane jScrollPaneAssigned;
    private javax.swing.JScrollPane jScrollPaneAvailable;
    private javax.swing.JScrollPane jScrollPaneImages;
    private javax.swing.JLabel lblAssignedTags;
    private javax.swing.JLabel lblAvailableTags;
    private javax.swing.JLabel lblSelectImage;
    private javax.swing.JLabel lblSelectedImage;
    private javax.swing.JList<Tag> lstAssignedTags;
    private javax.swing.JList<Tag> lstAvailableTags;
    private javax.swing.JTable tbImages;
    // End of variables declaration//GEN-END:variables

    /** Initializes repository, table, list models, and drag-and-drop wiring. */
    private void init() throws Exception {
        initRepository();
        initListModels();
        initTable();
        configureDragAndDrop();
        refreshAll();
    }

    /** Obtains the shared repository from the factory. */
    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    /** Creates empty typed list models for available and assigned tags. */
    private void initListModels() {
        availableTagsModel = new DefaultListModel<>();
        assignedTagsModel = new DefaultListModel<>();
        lstAvailableTags.setModel(availableTagsModel);
        lstAssignedTags.setModel(assignedTagsModel);
    }

    /** Binds the NASA image table and selection listener. */
    private void initTable() throws Exception {
        tbImages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbImages.setAutoCreateRowSorter(true);
        tbImages.setRowHeight(25);
        imageTableModel = new RelatedNasaImageTableModel(repository.selectNasaImages());
        tbImages.setModel(imageTableModel);
        configureTableAppearance();
        tbImages.getSelectionModel().addListSelectionListener(this::onImageSelectionChanged);
    }

    /** Applies compact column alignment and widths for the image table. */
    private void configureTableAppearance() {
        TableRenderUtils.applyRelatedNasaImageTableStyle(tbImages);
    }

    /** Configures drag from Available Tags to Assigned Tags. */
    private void configureDragAndDrop() {
        RelatedEntityDragDropSupport.wireTagLists(
                lstAvailableTags,
                lstAssignedTags,
                this::canAcceptTagDrop,
                this::linkDroppedTag
        );
    }

    /** Reloads images, all tags, and tag lists for the current selection. */
    private void refreshAll() {
        try {
            int selectedImageId = selectedImage != null ? selectedImage.getId() : -1;
            imageTableModel.setImages(repository.selectNasaImages());
            allTags = repository.selectTags();
            reselectImageRow(selectedImageId);
            reloadTagListsForSelectedImage();
        } catch (Exception ex) {
            Logger.getLogger(RelatedEntitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to refresh related data!");
        }
    }

    /** Re-selects the image row after a table refresh when possible. */
    private void reselectImageRow(int selectedImageId) {
        if (selectedImageId < 0) {
            selectedImage = null;
            updateSelectedImageLabel();
            return;
        }
        for (int viewRow = 0; viewRow < tbImages.getRowCount(); viewRow++) {
            int modelRow = tbImages.convertRowIndexToModel(viewRow);
            int id = (int) imageTableModel.getValueAt(modelRow, 0);
            if (id == selectedImageId) {
                tbImages.setRowSelectionInterval(viewRow, viewRow);
                showSelectedImage();
                return;
            }
        }
        selectedImage = null;
        updateSelectedImageLabel();
        clearTagLists();
    }

    /** Loads the selected NASA image and its available/assigned tag lists. */
    private void showSelectedImage() {
        int selectedRow = tbImages.getSelectedRow();
        if (selectedRow < 0) {
            selectedImage = null;
            updateSelectedImageLabel();
            clearTagLists();
            return;
        }
        int modelRow = tbImages.convertRowIndexToModel(selectedRow);
        selectedImage = imageTableModel.getImageAt(modelRow);
        updateSelectedImageLabel();
        try {
            reloadTagListsForSelectedImage();
        } catch (Exception ex) {
            Logger.getLogger(RelatedEntitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to load tags for the selected image!");
        }
    }

    /** Keeps selection in sync when the user navigates the image table with the keyboard. */
    private void onImageSelectionChanged(ListSelectionEvent event) {
        if (event.getValueIsAdjusting()) {
            return;
        }
        showSelectedImage();
    }

    /** Reloads assigned tags from the database and recomputes available tags. */
    private void reloadTagListsForSelectedImage() throws Exception {
        if (selectedImage == null) {
            clearTagLists();
            return;
        }
        List<Tag> assignedTags = repository.selectTagsForNasaImage(selectedImage.getId());
        Set<Integer> assignedIds = assignedTags.stream().map(Tag::getId).collect(Collectors.toSet());

        assignedTagsModel.clear();
        for (Tag tag : new TreeSet<>(assignedTags)) {
            assignedTagsModel.addElement(tag);
        }

        availableTagsModel.clear();
        TreeSet<Tag> availableTags = new TreeSet<>();
        for (Tag tag : allTags) {
            if (!assignedIds.contains(tag.getId())) {
                availableTags.add(tag);
            }
        }
        for (Tag tag : availableTags) {
            availableTagsModel.addElement(tag);
        }
    }

    /** Clears both tag lists when no image is selected. */
    private void clearTagLists() {
        availableTagsModel.clear();
        assignedTagsModel.clear();
    }

    /** Updates the label that shows the currently selected image title. */
    private void updateSelectedImageLabel() {
        if (selectedImage == null) {
            lblSelectedImage.setText("Selected image: (none)");
        } else {
            lblSelectedImage.setText("Selected image: " + selectedImage.getTitle());
        }
    }

    /** Returns whether a tag may be dropped onto Assigned Tags. */
    private boolean canAcceptTagDrop() {
        if (selectedImage == null) {
            MessageUtils.showInformationMessage(TITLE, "Please select a NASA image first.");
            return false;
        }
        return true;
    }

    /** Links a dropped tag to the selected NASA image and refreshes the lists. */
    private void linkDroppedTag(Tag tag) {
        if (selectedImage == null) {
            return;
        }
        try {
            repository.linkNasaImageTag(selectedImage.getId(), tag.getId());
            reloadTagListsForSelectedImage();
        } catch (Exception ex) {
            Logger.getLogger(RelatedEntitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to assign tag to image!");
        }
    }

    /** Unlinks the selected assigned tag from the current NASA image. */
    private void removeSelectedAssignedTag() {
        if (selectedImage == null) {
            MessageUtils.showInformationMessage(TITLE, "Please select a NASA image first.");
            return;
        }
        Tag tag = lstAssignedTags.getSelectedValue();
        if (tag == null) {
            MessageUtils.showInformationMessage(TITLE, "Please select an assigned tag to remove.");
            return;
        }
        try {
            repository.unlinkNasaImageTag(selectedImage.getId(), tag.getId());
            reloadTagListsForSelectedImage();
        } catch (Exception ex) {
            Logger.getLogger(RelatedEntitiesPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Unable to remove tag from image!");
        }
    }
}
