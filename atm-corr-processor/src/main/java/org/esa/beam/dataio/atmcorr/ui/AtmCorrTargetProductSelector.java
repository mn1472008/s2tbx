package org.esa.beam.dataio.atmcorr.ui;

import com.bc.ceres.swing.TableLayout;
import com.bc.ceres.swing.binding.BindingContext;
import org.esa.beam.framework.dataio.ProductIOPlugInManager;
import org.esa.beam.util.io.FileChooserFactory;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

/**
 * WARNING: This class belongs to a preliminary API and may change in future releases.
 *
 * @author Ralf Quast
 * @version $Revision$ $Date$
 */
public class AtmCorrTargetProductSelector {

    private JLabel productNameLabel;
    private JTextField productNameTextField;
    private JLabel productDirLabel;
    private JTextField productDirTextField;
    private JButton productDirChooserButton;

    private JCheckBox openInAppCheckBox;
    private AtmCorrTargetProductSelectorModel model;

    public AtmCorrTargetProductSelector() {
        this(new AtmCorrTargetProductSelectorModel(), false);
    }

    public AtmCorrTargetProductSelector(AtmCorrTargetProductSelectorModel model) {
        this(model, false);
    }

    public AtmCorrTargetProductSelector(AtmCorrTargetProductSelectorModel model, boolean alwaysWriteOutput) {
        this.model = model;

        initComponents();
        bindComponents();
        updateUIState();
    }

    private void initComponents() {
        productNameLabel = new JLabel("Name: ");
        productNameTextField = new JTextField(25);
        productDirLabel = new JLabel("Directory:");
        productDirTextField = new JTextField(25);
        productDirChooserButton = new JButton(new ProductDirChooserAction());

        final Dimension size = new Dimension(26, 16);
        productDirChooserButton.setPreferredSize(size);
        productDirChooserButton.setMinimumSize(size);

            openInAppCheckBox = new JCheckBox("Open in application");

    }

    private void bindComponents() {
        final BindingContext bc = new BindingContext(model.getValueContainer());

        bc.bind("productName", productNameTextField);
        bc.bind("productDir", productDirTextField);

            bc.bind("openInAppSelected", openInAppCheckBox);

        model.getValueContainer().addPropertyChangeListener("productDir", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                productDirTextField.setToolTipText(model.getProductDir().getPath());
            }
        });
        model.getValueContainer().addPropertyChangeListener("formatName", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                updateUIState();
            }
        });
    }

    public AtmCorrTargetProductSelectorModel getModel() {
        return model;
    }

    public JLabel getProductNameLabel() {
        return productNameLabel;
    }

    public JTextField getProductNameTextField() {
        return productNameTextField;
    }

    public JLabel getProductDirLabel() {
        return productDirLabel;
    }

    public JTextField getProductDirTextField() {
        return productDirTextField;
    }

    public JButton getProductDirChooserButton() {
        return productDirChooserButton;
    }

    public JCheckBox getOpenInAppCheckBox() {
        return openInAppCheckBox;
    }

    public JPanel createDefaultPanel() {
        final JPanel subPanel1 = new JPanel(new BorderLayout(3, 3));
        subPanel1.add(getProductNameLabel(), BorderLayout.NORTH);
        subPanel1.add(getProductNameTextField(), BorderLayout.CENTER);

        JPanel subPanel2 = null;

        final JPanel subPanel3 = new JPanel(new BorderLayout(3, 3));
        subPanel3.add(getProductDirLabel(), BorderLayout.NORTH);
        subPanel3.add(getProductDirTextField(), BorderLayout.CENTER);
        subPanel3.add(getProductDirChooserButton(), BorderLayout.EAST);

        final TableLayout tableLayout = new TableLayout(1);
        tableLayout.setTableAnchor(TableLayout.Anchor.WEST);
        tableLayout.setTableFill(TableLayout.Fill.HORIZONTAL);
        tableLayout.setTableWeightX(1.0);

        tableLayout.setCellPadding(0, 0, new Insets(3, 3, 3, 3));
        tableLayout.setCellPadding(1, 0, new Insets(3, 3, 3, 3));
        tableLayout.setCellPadding(2, 0, new Insets(0, 24, 3, 3));
        tableLayout.setCellPadding(3, 0, new Insets(3, 3, 3, 3));

        final JPanel panel = new JPanel(tableLayout);
        panel.setBorder(BorderFactory.createTitledBorder("Target Product"));
        panel.add(subPanel1);

        panel.add(subPanel3);

            panel.add(getOpenInAppCheckBox());

        return panel;
    }

    private void updateUIState() {
//        if (model.isSaveToFileSelected()) {
//            if (!alwaysWriteOutput) {
//                openInAppCheckBox.setEnabled(canReadOutputFormat(model.getFormatName()));
//                formatNameComboBox.setEnabled(true);
//            }
//            productDirLabel.setEnabled(true);
//            productDirTextField.setEnabled(true);
//            productDirChooserButton.setEnabled(true);
//        } else {
//            if (!alwaysWriteOutput) {
//                openInAppCheckBox.setEnabled(false);
//                formatNameComboBox.setEnabled(false);
//            }
//            productDirTextField.setEnabled(false);
//            productDirTextField.setEnabled(false);
//            productDirChooserButton.setEnabled(false);
//        }
    }

    private static boolean canReadOutputFormat(String formatName) {
        return ProductIOPlugInManager.getInstance().getReaderPlugIns(formatName).hasNext();
    }

    private class ProductDirChooserAction extends AbstractAction {

        private static final String APPROVE_BUTTON_TEXT = "Select";

        public ProductDirChooserAction() {
            super("...");
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            Window windowAncestor = null;
            if (event.getSource() instanceof JComponent) {
                JButton button = (JButton) event.getSource();
                if (button != null) {
                    windowAncestor = SwingUtilities.getWindowAncestor(button);
                }
            }
            final JFileChooser chooser = FileChooserFactory.getInstance().createDirChooser(model.getProductDir());
            chooser.setDialogTitle("Select Target Directory");
            if (chooser.showDialog(windowAncestor, APPROVE_BUTTON_TEXT) == JFileChooser.APPROVE_OPTION) {
                final File selectedDir = chooser.getSelectedFile();
                if (selectedDir != null) {
                    model.setProductDir(selectedDir);
                } else {
                    model.setProductDir(new File("."));
                }
            }
        }
    }
}

