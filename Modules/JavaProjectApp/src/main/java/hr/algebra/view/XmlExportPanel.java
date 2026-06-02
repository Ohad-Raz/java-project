/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.algebra.view;

import hr.algebra.dal.Repository;
import hr.algebra.dal.RepositoryFactory;
import hr.algebra.model.NasaImage;
import hr.algebra.model.Tag;
import hr.algebra.utilities.JAXBUtils;
import hr.algebra.utilities.MessageUtils;
import hr.algebra.view.model.RelatedNasaImageTableModel;
import hr.algebra.view.util.TableRenderUtils;
import hr.algebra.xml.NasaImageArchive;
import hr.algebra.xml.NasaImageExportDto;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.SwingWorker;

/**
 * XML export tab: exports NASA images and their assigned tags with JAXB.
 */
public class XmlExportPanel extends javax.swing.JPanel {

    private static final String TITLE = "XML export";
    private static final String DEFAULT_FILENAME = "nasa-image-archive.xml";

    private Repository repository;
    private RelatedNasaImageTableModel imageTableModel;
    private NasaImage selectedImage;

    public XmlExportPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHeader = new javax.swing.JLabel();
        jScrollPaneImages = new javax.swing.JScrollPane();
        tbImages = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnExportAll = new javax.swing.JButton();
        btnExportSelected = new javax.swing.JButton();
        lblSelectedImage = new javax.swing.JLabel();
        jScrollPaneStatus = new javax.swing.JScrollPane();
        taStatus = new javax.swing.JTextArea();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        lblHeader.setText("NASA images to export");

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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnExportAll.setText("Export all images");
        btnExportAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportAllActionPerformed(evt);
            }
        });

        btnExportSelected.setText("Export selected image");
        btnExportSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportSelectedActionPerformed(evt);
            }
        });

        lblSelectedImage.setText("Selected image: (none)");

        taStatus.setColumns(20);
        taStatus.setEditable(false);
        taStatus.setRows(5);
        taStatus.setText("Ready. Export creates a JAXB XML archive with NASA images and assigned tags.");
        jScrollPaneStatus.setViewportView(taStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHeader)
                    .addComponent(jScrollPaneImages)
                    .addComponent(lblSelectedImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportSelected))
                    .addComponent(jScrollPaneStatus))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPaneImages, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSelectedImage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportAll, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExportSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        if (repository == null) {
            init();
        } else {
            refreshImages();
        }
    }//GEN-LAST:event_formComponentShown

    private void tbImagesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbImagesMouseClicked
        updateSelectedImage();
    }//GEN-LAST:event_tbImagesMouseClicked

    private void tbImagesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbImagesKeyReleased
        updateSelectedImage();
    }//GEN-LAST:event_tbImagesKeyReleased

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        refreshImages();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnExportAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportAllActionPerformed
        exportAllImages();
    }//GEN-LAST:event_btnExportAllActionPerformed

    private void btnExportSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportSelectedActionPerformed
        exportSelectedImage();
    }//GEN-LAST:event_btnExportSelectedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportAll;
    private javax.swing.JButton btnExportSelected;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPaneImages;
    private javax.swing.JScrollPane jScrollPaneStatus;
    private javax.swing.JLabel lblHeader;
    private javax.swing.JLabel lblSelectedImage;
    private javax.swing.JTextArea taStatus;
    private javax.swing.JTable tbImages;
    // End of variables declaration//GEN-END:variables

    /** Initializes repository and image table once when the panel becomes visible. */
    private void init() {
        try {
            initRepository();
            initTable();
            refreshImages();
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to initialize XML export panel.");
            appendStatus("Initialization failed: " + ex.getMessage());
        }
    }

    /** Obtains repository implementation from the factory. */
    private void initRepository() throws Exception {
        repository = RepositoryFactory.getRepository();
    }

    /** Binds the compact image table model and related table styling. */
    private void initTable() throws Exception {
        tbImages.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbImages.setAutoCreateRowSorter(true);
        tbImages.setRowHeight(25);
        imageTableModel = new RelatedNasaImageTableModel(repository.selectNasaImages());
        tbImages.setModel(imageTableModel);
        TableRenderUtils.applyRelatedNasaImageTableStyle(tbImages);
    }

    /** Reloads NASA images for export and clears selection state. */
    private void refreshImages() {
        try {
            imageTableModel.setImages(repository.selectNasaImages());
            selectedImage = null;
            lblSelectedImage.setText("Selected image: (none)");
            appendStatus("Image list refreshed.");
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to refresh images for export.");
            appendStatus("Refresh failed: " + ex.getMessage());
        }
    }

    /** Tracks the image currently selected in the export table. */
    private void updateSelectedImage() {
        int selectedRow = tbImages.getSelectedRow();
        if (selectedRow < 0) {
            selectedImage = null;
            lblSelectedImage.setText("Selected image: (none)");
            return;
        }
        int modelRow = tbImages.convertRowIndexToModel(selectedRow);
        selectedImage = imageTableModel.getImageAt(modelRow);
        lblSelectedImage.setText("Selected image: " + selectedImage.getTitle());
    }

    /** Exports all available images and their tags to a JAXB XML file. */
    private void exportAllImages() {
        try {
            if (!repository.hasNasaImages()) {
                MessageUtils.showInformationMessage(TITLE, "No NASA images available for export.");
                appendStatus("Export skipped: no images.");
                return;
            }
            List<NasaImage> images = repository.selectNasaImages();
            startExportWorker(images);
        } catch (Exception ex) {
            MessageUtils.showErrorMessage("Error", "Unable to load images for export.");
            appendStatus("Load failed: " + ex.getMessage());
        }
    }

    /** Exports the currently selected image and its tags to JAXB XML. */
    private void exportSelectedImage() {
        if (selectedImage == null) {
            MessageUtils.showInformationMessage(TITLE, "Please select an image first.");
            return;
        }
        startExportWorker(List.of(selectedImage));
    }

    /** Prompts for destination file and runs XML export in background. */
    private void startExportWorker(List<NasaImage> imagesToExport) {
        File targetFile = chooseOutputFile();
        if (targetFile == null) {
            appendStatus("Export canceled by user.");
            return;
        }

        appendStatus("Export started: " + targetFile.getAbsolutePath());
        setButtonsEnabled(false);
        SwingWorker<NasaImageArchive, Void> worker = new SwingWorker<>() {
            @Override
            protected NasaImageArchive doInBackground() throws Exception {
                return buildArchive(imagesToExport);
            }

            @Override
            protected void done() {
                try {
                    NasaImageArchive archive = get();
                    JAXBUtils.save(archive, targetFile.getAbsolutePath());
                    appendStatus("Export completed: " + targetFile.getAbsolutePath());
                    MessageUtils.showInformationMessage(TITLE, "XML export completed successfully.");
                } catch (Exception ex) {
                    appendStatus("Export failed: " + ex.getMessage());
                    MessageUtils.showErrorMessage("Error", "Unable to export XML archive.");
                } finally {
                    setButtonsEnabled(true);
                }
            }
        };
        worker.execute();
    }

    /** Builds JAXB archive DTOs by loading assigned tags for each exported image. */
    private NasaImageArchive buildArchive(List<NasaImage> images) throws Exception {
        List<NasaImageExportDto> exportDtos = new ArrayList<>();
        for (NasaImage image : images) {
            NasaImageExportDto dto = new NasaImageExportDto();
            dto.setId(image.getId());
            dto.setTitle(image.getTitle());
            dto.setLink(image.getLink());
            dto.setDescription(image.getDescription());
            dto.setPicturePath(image.getPicturePath());
            dto.setPublishedDate(NasaImage.formatPublishedDate(image.getPublishedDate()));
            dto.setGuid(image.getGuid());

            List<Tag> tags = repository.selectTagsForNasaImage(image.getId());
            dto.setTags(tags.stream().map(Tag::getName).sorted().collect(Collectors.toList()));
            exportDtos.add(dto);
        }
        return new NasaImageArchive(exportDtos);
    }

    /** Opens file chooser for XML save path and applies default archive filename. */
    private File chooseOutputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Choose XML export file");
        chooser.setSelectedFile(new File(DEFAULT_FILENAME));
        int result = chooser.showSaveDialog(this);
        if (result != JFileChooser.APPROVE_OPTION) {
            return null;
        }
        File selected = chooser.getSelectedFile();
        if (!selected.getName().toLowerCase().endsWith(".xml")) {
            selected = new File(selected.getParentFile(), selected.getName() + ".xml");
        }
        return selected;
    }

    /** Appends one line to the export status area. */
    private void appendStatus(String message) {
        taStatus.append(System.lineSeparator() + message);
    }

    /** Enables or disables export-related controls while background work runs. */
    private void setButtonsEnabled(boolean enabled) {
        btnExportAll.setEnabled(enabled);
        btnExportSelected.setEnabled(enabled);
        btnRefresh.setEnabled(enabled);
    }
}
