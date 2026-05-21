/*
 * Project setup note:
 * Based on Exercise 11-12, TransferHandler drag-and-drop pattern. Placeholder wiring for related entities.
 */

package hr.algebra.view.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

/**
 * Reusable drag-and-drop skeleton (Exercise 11-12 DragNDrop + AuthorTransferable).
 * Later: assign a related entity from source list to main entity on destination drop.
 */
public final class RelatedEntityDragDropSupport {

    private static final Logger LOGGER = Logger.getLogger(RelatedEntityDragDropSupport.class.getName());

    private RelatedEntityDragDropSupport() {
    }

    public static void wireListToList(
            JList<Object> sourceList,
            JList<Object> destinationList,
            Consumer<Object> onRelatedEntityAssigned
    ) {
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sourceList.setDragEnabled(true);
        sourceList.setTransferHandler(new ExportTransferHandler(sourceList));

        destinationList.setDropMode(DropMode.ON);
        destinationList.setTransferHandler(new ImportTransferHandler(onRelatedEntityAssigned));
    }

    private static final class ExportTransferHandler extends TransferHandler {

        private final JList<Object> sourceList;

        private ExportTransferHandler(JList<Object> sourceList) {
            this.sourceList = sourceList;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Object selected = sourceList.getSelectedValue();
            if (selected == null) {
                return null;
            }
            return new RelatedEntityTransferable(selected);
        }
    }

    private static final class ImportTransferHandler extends TransferHandler {

        private final Consumer<Object> onRelatedEntityAssigned;

        private ImportTransferHandler(Consumer<Object> onRelatedEntityAssigned) {
            this.onRelatedEntityAssigned = onRelatedEntityAssigned;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(RelatedEntityTransferable.RELATED_ENTITY_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            Transferable transferable = support.getTransferable();
            try {
                Object data = transferable.getTransferData(RelatedEntityTransferable.RELATED_ENTITY_FLAVOR);
                onRelatedEntityAssigned.accept(data);
                return true;
            } catch (UnsupportedFlavorException | IOException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
}
