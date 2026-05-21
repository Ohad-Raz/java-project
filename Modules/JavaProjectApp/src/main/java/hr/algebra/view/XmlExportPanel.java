/*
 * Project setup note:
 * Based on Exercise 11-12, XML export UI placeholder. Will use JAXBUtils when archive types exist.
 */

package hr.algebra.view;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Placeholder tab: XML export/import UI.
 * Persistence will use {@link hr.algebra.utilities.JAXBUtils} with final JAXB root types.
 */
public final class XmlExportPanel extends JPanel {

    public XmlExportPanel() {
        setLayout(new BorderLayout());
        add(new JLabel("XML export panel placeholder (JAXB archive types later)"), BorderLayout.CENTER);
    }
}
