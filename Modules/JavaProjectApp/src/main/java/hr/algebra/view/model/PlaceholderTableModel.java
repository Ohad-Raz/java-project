/*
 * Project setup note:
 * Based on Exercise 07-08, AbstractTableModel pattern (ArticleTableModel reference). Placeholder columns until entities exist.
 */

package hr.algebra.view.model;

import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * AbstractTableModel reference structure (Exercise 07-08 ArticleTableModel pattern).
 * Column names and row mapping will be added with final entities.
 */
public final class PlaceholderTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Id", "Title", "Details"};

    private List<Object[]> rows = Collections.emptyList();

    public void setRows(List<Object[]> rows) {
        this.rows = rows;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return rows.get(rowIndex)[columnIndex];
    }
}
