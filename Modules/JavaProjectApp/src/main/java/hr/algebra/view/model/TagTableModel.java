/*
 * Project setup note:
 * Based on Exercise 07-08 ArticleTableModel. Adapted for Tag CRUD table.
 */

package hr.algebra.view.model;

import hr.algebra.model.Tag;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Table model for tag list (Exercise 07-08 ArticleTableModel pattern).
 */
public final class TagTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Name"};

    private List<Tag> tags;

    public TagTableModel(List<Tag> tags) {
        this.tags = tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return tags.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Tag tag = tags.get(rowIndex);
        return switch (columnIndex) {
            case 0 -> tag.getId();
            case 1 -> tag.getName();
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
