/*
 * Project setup note:
 * Based on Exercise 11-12 AuthorTransferable. Adapted for Tag drag-and-drop.
 */

package hr.algebra.view.dnd;

import hr.algebra.model.Tag;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Transferable wrapper for {@link Tag} (Exercise 11-12 AuthorTransferable pattern).
 */
public final class RelatedEntityTransferable implements Transferable {

    public static final DataFlavor TAG_FLAVOR = new DataFlavor(Tag.class, "Tag");

    private static final DataFlavor[] SUPPORTED_FLAVORS = {TAG_FLAVOR};

    private final Tag tag;

    public RelatedEntityTransferable(Tag tag) {
        this.tag = tag;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return TAG_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return tag;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
