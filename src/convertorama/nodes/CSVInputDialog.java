/*
 * CSVInputDialog2.java
 *
 * Created on Jul 21, 2011, 2:32:18 PM
 */
package convertorama.nodes;

import convertorama.Table;
import convertorama.TableDescriptor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

/**
 *
 * @author kasper
 */
public class CSVInputDialog extends javax.swing.JDialog {

    private File file = null;
    /** A return status code - returned if Cancel button has been pressed */
    public static final int RET_CANCEL = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int RET_OK = 1;

    /** Creates new form CSVInputDialog2 */
    public CSVInputDialog(File file, char separator, boolean firstLineIsHeader, JFrame parent) {
        super(parent, true);
        initComponents();
        this.setFile(file);
        this.setSeparator(separator);
        this.setFirstLineIsHeader(firstLineIsHeader);

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
    }

    private void setFile(File file) {
        String str = (file == null) ? "----" : file.toString();
        this.file = file;
        this.fileNameValue.setText(str);
        System.out.println("CSVInputDialog: Setting file to: " + str);
    }

    private void setFirstLineIsHeader(boolean firstLineIsHeader) {
        firstLineCheckbox.setSelected(firstLineIsHeader);
    }

    private void setSeparator(char separator) {
        separatorField.setText(""+separator);
    }

    public File getFile() {
        return file;
    }

    public boolean isFirstLineHeader() {
        return firstLineCheckbox.isSelected();
    }

    public char getSeparator() {
        return separatorField.getText().charAt(0);
    }

    /** @return the return status of this dialog - one of RET_OK or RET_CANCEL */
    public int getReturnStatus() {
        return returnStatus;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        fileNameLabel = new javax.swing.JLabel();
        fileNameValue = new javax.swing.JLabel();
        browseButton = new javax.swing.JButton();
        separatorLabel = new javax.swing.JLabel();
        separatorField = new javax.swing.JTextField();
        firstLineLabel = new javax.swing.JLabel();
        firstLineCheckbox = new javax.swing.JCheckBox();
        previewButton = new javax.swing.JButton();
        previewPanel = new javax.swing.JPanel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        fileNameLabel.setLabelFor(fileNameValue);
        fileNameLabel.setText("File:");

        fileNameValue.setText("\"\"");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        separatorLabel.setLabelFor(separatorField);
        separatorLabel.setText("Separator:");

        separatorField.setColumns(1);
        separatorField.setText(";");

        firstLineLabel.setLabelFor(firstLineCheckbox);
        firstLineLabel.setText("First Line is Header:");

        firstLineCheckbox.setSelected(true);
        firstLineCheckbox.setText("(Check box if true)");
        firstLineCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstLineCheckboxActionPerformed(evt);
            }
        });

        previewButton.setText("Preview");
        previewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previewButtonActionPerformed(evt);
            }
        });

        previewPanel.setBackground(new java.awt.Color(213, 188, 163));
        previewPanel.setLayout(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(fileNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fileNameValue))
                            .addComponent(separatorLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(separatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 377, Short.MAX_VALUE)
                        .addComponent(browseButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(firstLineLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstLineCheckbox))
                    .addComponent(previewButton))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fileNameLabel)
                            .addComponent(fileNameValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(separatorLabel)
                            .addComponent(separatorField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(browseButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstLineLabel)
                    .addComponent(firstLineCheckbox))
                .addGap(18, 18, 18)
                .addComponent(previewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(402, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cancelButton)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        getRootPane().setDefaultButton(okButton);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        doClose(RET_OK);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {            
            System.out.println(fileChooser.getSelectedFile());
            setFile(fileChooser.getSelectedFile());
        }

    }//GEN-LAST:event_browseButtonActionPerformed

    private void firstLineCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstLineCheckboxActionPerformed
        this.setFirstLineIsHeader(firstLineCheckbox.isSelected());
    }//GEN-LAST:event_firstLineCheckboxActionPerformed

    private void previewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previewButtonActionPerformed
        char separator = getSeparator();
        boolean firstLineHeader = isFirstLineHeader();
        
        if (file != null) {
            TableDescriptor tableDescriptor = CSVInputNode.getTableDescriptor(file, separator, firstLineHeader);
            Table table = CSVInputNode.readTable(tableDescriptor, file, separator, firstLineHeader, 3);
            System.out.println(table.toString());
            previewPanel.removeAll();
            previewPanel.setLayout(new GridLayout(1,0));
            previewPanel.add(table.toGUI());
            previewPanel.updateUI();            
        } else {
            System.err.println("No file selected. Please select one using the 'browse' button, then click preview retry");
        }
    }//GEN-LAST:event_previewButtonActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel fileNameLabel;
    private javax.swing.JLabel fileNameValue;
    private javax.swing.JCheckBox firstLineCheckbox;
    private javax.swing.JLabel firstLineLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JButton previewButton;
    private javax.swing.JPanel previewPanel;
    private javax.swing.JTextField separatorField;
    private javax.swing.JLabel separatorLabel;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}