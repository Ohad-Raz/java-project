/*
 * Project setup note:
 * Placeholder for final project. The final implementation will be completed after choosing the RSS source, domain entities, and database schema.
 */

package hr.algebra.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Placeholder tab: assign related entities to a main entity (drag-and-drop later).
 * See {@link hr.algebra.view.dnd.RelatedEntityDragDropSupport}.
 */
public final class RelatedEntitiesPanel extends JPanel {

    public RelatedEntitiesPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("Related entities panel placeholder (drag-and-drop wiring later)"), BorderLayout.CENTER);
    }
}
