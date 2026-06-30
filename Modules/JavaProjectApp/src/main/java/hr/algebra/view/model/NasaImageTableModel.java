package hr.algebra.view.model;

import hr.algebra.model.NasaImage;
import java.util.List;
import javax.swing.table.AbstractTableModel;

// NasaImage table model for the Images tab (Exercise 07-08 ArticleTableModel pattern).
public final class NasaImageTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {
        "Id", "Title", "Link", "Description", "Picture path", "Published date"
    };

    private List<NasaImage> images;

    public NasaImageTableModel(List<NasaImage> images) {
        this.images = images;
    }

    public void setImages(List<NasaImage> images) {
        this.images = images;
        fireTableDataChanged();
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
            case 2 -> image.getLink();
            case 3 -> image.getDescription();
            case 4 -> image.getPicturePath();
            case 5 -> NasaImage.formatPublishedDate(image.getPublishedDate());
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
