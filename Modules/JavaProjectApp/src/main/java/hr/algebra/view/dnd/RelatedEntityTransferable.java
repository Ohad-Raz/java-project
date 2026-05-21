/*
 * Project setup note:
 * Based on Exercise 11-12, Transferable/DataFlavor pattern (AuthorTransferable reference). Placeholder for related-entity DnD.
 */

package hr.algebra.view.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * Transferable placeholder (Exercise 11-12 AuthorTransferable pattern).
 * Later: replace Object payload with final related-entity type and use in drag source.
 */
public final class RelatedEntityTransferable implements Transferable {

    public static final DataFlavor RELATED_ENTITY_FLAVOR = new DataFlavor(Object.class, "RelatedEntity");

    private static final DataFlavor[] SUPPORTED_FLAVORS = {RELATED_ENTITY_FLAVOR};

    private final Object relatedEntity;

    public RelatedEntityTransferable(Object relatedEntity) {
        this.relatedEntity = relatedEntity;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS.clone();
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return RELATED_ENTITY_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return relatedEntity;
        }
        throw new UnsupportedFlavorException(flavor);
    }
}
