package hr.algebra.view.dnd;

import hr.algebra.model.Tag;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

// Related tab drag-and-drop support (Exercise 11-12 TransferHandler pattern).
public final class RelatedEntityDragDropSupport {

    private static final Logger LOGGER = Logger.getLogger(RelatedEntityDragDropSupport.class.getName());

    private RelatedEntityDragDropSupport() {
    }

    // Enables drag from Available Tags to Assigned Tags and calls back on drop.
    public static void wireTagLists(
            JList<Tag> sourceList,
            JList<Tag> destinationList,
            BooleanSupplier canImport,
            Consumer<Tag> onTagDropped
    ) {
        sourceList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        sourceList.setDragEnabled(true);
        sourceList.setTransferHandler(new ExportTransferHandler(sourceList));

        destinationList.setDropMode(DropMode.ON);
        destinationList.setTransferHandler(new ImportTransferHandler(canImport, onTagDropped));
    }

    private static final class ExportTransferHandler extends TransferHandler {

        private final JList<Tag> sourceList;

        private ExportTransferHandler(JList<Tag> sourceList) {
            this.sourceList = sourceList;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return COPY;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Tag selected = sourceList.getSelectedValue();
            if (selected == null) {
                return null;
            }
            return new RelatedEntityTransferable(selected);
        }
    }

    private static final class ImportTransferHandler extends TransferHandler {

        private final BooleanSupplier canImport;
        private final Consumer<Tag> onTagDropped;

        private ImportTransferHandler(BooleanSupplier canImport, Consumer<Tag> onTagDropped) {
            this.canImport = canImport;
            this.onTagDropped = onTagDropped;
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(RelatedEntityTransferable.TAG_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport.getAsBoolean()) {
                return false;
            }
            Transferable transferable = support.getTransferable();
            try {
                Tag tag = (Tag) transferable.getTransferData(RelatedEntityTransferable.TAG_FLAVOR);
                onTagDropped.accept(tag);
                return true;
            } catch (UnsupportedFlavorException | IOException | ClassCastException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
                return false;
            }
        }
    }
}
