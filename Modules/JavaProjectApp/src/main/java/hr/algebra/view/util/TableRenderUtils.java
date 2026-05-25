/*
 * Project setup note:
 * Shared JTable header/cell alignment and column width helpers (post-init, not in .form).
 */

package hr.algebra.view.util;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public final class TableRenderUtils {

    private TableRenderUtils() {
    }

    /** Centers all headers and cells; keeps Id narrow and Name wide. */
    public static void applyTagTableStyle(JTable table) {
        centerTableHeaders(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        TableColumnModel columnModel = table.getColumnModel();
        for (int i = 0; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setCellRenderer(centerRenderer);
        }

        TableColumn idColumn = columnModel.getColumn(0);
        idColumn.setPreferredWidth(80);
        idColumn.setMaxWidth(100);

        columnModel.getColumn(1).setPreferredWidth(400);
    }

    /** Centers headers; Id/date centered; Title left-aligned for Related tab image table. */
    public static void applyRelatedNasaImageTableStyle(JTable table) {
        centerTableHeaders(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        TableColumnModel columnModel = table.getColumnModel();
        setColumn(columnModel.getColumn(0), centerRenderer, 80, 100);
        setColumn(columnModel.getColumn(1), leftRenderer, 220, 400);
        setColumn(columnModel.getColumn(2), centerRenderer, 160, 220);
    }

    /** Centers headers; Id/date centered; text columns left-aligned with readable widths. */
    public static void applyNasaImageTableStyle(JTable table) {
        centerTableHeaders(table);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);

        TableColumnModel columnModel = table.getColumnModel();
        setColumn(columnModel.getColumn(0), centerRenderer, 80, 100);
        setColumn(columnModel.getColumn(1), leftRenderer, 140, 220);
        setColumn(columnModel.getColumn(2), leftRenderer, 120, 200);
        setColumn(columnModel.getColumn(3), leftRenderer, 180, 280);
        setColumn(columnModel.getColumn(4), leftRenderer, 140, 220);
        setColumn(columnModel.getColumn(5), centerRenderer, 180, 240);
    }

    private static void centerTableHeaders(JTable table) {
        DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) table.getTableHeader()
                .getDefaultRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private static void setColumn(TableColumn column, DefaultTableCellRenderer renderer,
            int preferredWidth, int maxWidth) {
        column.setCellRenderer(renderer);
        column.setPreferredWidth(preferredWidth);
        column.setMaxWidth(maxWidth);
    }
}
