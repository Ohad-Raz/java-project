package hr.algebra.view.model;

import hr.algebra.model.NasaImage;
import java.util.List;
import javax.swing.table.AbstractTableModel;

// Compact NasaImage table for Related and XML export tabs.
public final class RelatedNasaImageTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Title", "Published date"};

    private List<NasaImage> images;

    public RelatedNasaImageTableModel(List<NasaImage> images) {
        this.images = images;
    }

    public void setImages(List<NasaImage> images) {
        this.images = images;
        fireTableDataChanged();
    }

    public NasaImage getImageAt(int rowIndex) {
        return images.get(rowIndex);
    }

    @Override
    public int getRowCount() {
        return images.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NasaImage image = images.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> image.getId();
            case 1 -> image.getTitle();
            case 2 -> NasaImage.formatPublishedDate(image.getPublishedDate());
            default -> throw new RuntimeException("No such column");
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 0) {
            return Integer.class;
        }
        return super.getColumnClass(columnIndex);
    }
}
