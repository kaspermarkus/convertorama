package convertorama.nodes;


/*
 * SearchAndReplaceDialog.java
 *
 * Created on Jul 23, 2011, 6:41:49 AM
 */
import convertorama.ColumnDescriptor;
import convertorama.nodes.SearchAndReplaceNode.Rule;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.DefaultCellEditor;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author kasper
 */
public class SearchAndReplaceDialog extends javax.swing.JDialog {

    SearchAndReplaceNode node;
    /** A return status code - returned if Cancel button has been pressed */
    public static final int RET_CANCEL = 0;
    /** A return status code - returned if OK button has been pressed */
    public static final int RET_OK = 1;

    /** Creates new form SearchAndReplaceDialog */
    public SearchAndReplaceDialog(SearchAndReplaceNode node, java.awt.Frame parent) {
        super(parent, true);
        this.node = node;
        initComponents();
        updateTableDescriptorPane();
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
        setupRulesTable(node.getRules());
    }

    private void updateTableDescriptorPane() {
        System.out.println("Updating table descriptor pane");
        tableDescriptorPane.removeAll();
        if (node.getTableDescriptor() != null) {
            System.out.println("We got a table to put there");

            JScrollPane tableDescUI = node.getTableDescriptor().asJTableRO();
            tableDescriptorPane.setLayout(new BorderLayout());
            tableDescriptorPane.add(tableDescUI, BorderLayout.CENTER);
        } else {
            System.out.println("TableDescriptor == null");
        }
        tableDescriptorPane.repaint();
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
        tableDescriptorPane = new javax.swing.JPanel();
        rulesHeaderLabel = new javax.swing.JLabel();
        RulesPanel = new javax.swing.JPanel();
        addRuleButton = new javax.swing.JButton();

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

        tableDescriptorPane.setLayout(null);

        rulesHeaderLabel.setText("Search and Replacements:");

        RulesPanel.setLayout(null);

        addRuleButton.setText("Add Rule");
        addRuleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRuleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tableDescriptorPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addComponent(RulesPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(rulesHeaderLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addComponent(addRuleButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelButton, okButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableDescriptorPane, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(rulesHeaderLabel)
                    .addComponent(addRuleButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RulesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
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

    private void addRuleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRuleButtonActionPerformed
        replaceTableModel.addRow();
    }//GEN-LAST:event_addRuleButtonActionPerformed

    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel RulesPanel;
    private javax.swing.JButton addRuleButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel rulesHeaderLabel;
    private javax.swing.JPanel tableDescriptorPane;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
    private ReplaceTableModel replaceTableModel;
    private JTable rulesTable;
    public JTable getRulesTable() {
        return rulesTable;
    }
    
    private void setUpFieldColumn(JTable table, TableColumn fieldColumn) {
        //Set up the editor for the sport cells.
        JComboBox comboBox = new JComboBox();
        //TODO Handle no node set
        String[] stringFields = node.getTableDescriptor().getColumnsOfType(ColumnDescriptor.STRING);
        if (stringFields.length == 0) {
            //TODO HANDLE
            throw new UnsupportedOperationException("No Stringfields .. panik");
        }
        for (int i = 0; i < stringFields.length; i++) {
            comboBox.addItem(stringFields[i]);
        }
        fieldColumn.setCellEditor(new DefaultCellEditor(comboBox));

        //Set up tool tips for the sport cells.
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        fieldColumn.setCellRenderer(renderer);
    }

    public class JTableButtonRenderer implements TableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(UIManager.getColor("Button.background"));
            }
            return button;
        }
    }

    private static class JTableButtonMouseListener extends MouseAdapter {

        private final JTable table;
        private final ReplaceTableModel tableModel;

        public JTableButtonMouseListener(JTable table, ReplaceTableModel tableModel) {
            this.table = table;
            this.tableModel = tableModel;
        }

        public void mouseClicked(MouseEvent e) {
            int column = table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / table.getRowHeight();

            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
                Object value = table.getValueAt(row, column);
                if (value instanceof JButton) {
                    System.out.println("JASDJKLDSAJLKLKJDAS");
                    ((JButton) value).doClick();
                    tableModel.removeRow(row);
                }
            }
        }
    }

    private void setupRulesTable(List<Rule> rules) {
        replaceTableModel = new ReplaceTableModel();
        rulesTable = new JTable(replaceTableModel);
//        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        rulesTable.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(rulesTable);

        //Set up column sizes.
//        initColumnSizes(table);

        //Fiddle with the Sport column's cell editors/renderers.
        setUpFieldColumn(rulesTable, rulesTable.getColumnModel().getColumn(0));
        rulesTable.getColumnModel().getColumn(4).setCellRenderer(new JTableButtonRenderer());
        rulesTable.addMouseListener(new JTableButtonMouseListener(rulesTable, replaceTableModel));
        
        //Add the scroll pane to this panel.
        RulesPanel.setLayout(new BorderLayout());
        RulesPanel.add(scrollPane, BorderLayout.CENTER);
        
        Iterator<Rule> it = rules.iterator();
        while (it.hasNext()) {
            replaceTableModel.addRow(it.next());
        }
    }

    class ReplaceTableModel extends AbstractTableModel {

        private ArrayList<Object[]> data = new ArrayList<Object[]>();
        boolean DEBUG = true;
        private String[] columnNames = {"Field",
            "Search",
            "Replace",
            "Full Match",
            "Delete"};

        public void addRow() {            
            Object[] newRow = {"", "", "", false, new JButton("X") };
            data.add(newRow);
            fireTableRowsInserted(data.size() - 1, 1);
        }
        
        public void addRow(Rule rule) {
            Object[] newRow = {rule.getField(), rule.getSearchString(), rule.getReplaceString(), rule.isFullMatch(), new JButton("X") };
            data.add(newRow);
            fireTableRowsInserted(data.size() - 1, 1);            
        }

        public void removeRow(int rowIndex) {
            if (rowIndex > data.size()) {
                //TODO ERROR                
            } else {
                data.remove(rowIndex);
                fireTableRowsDeleted(rowIndex, 1);
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                        + " to " + value
                        + " (an instance of "
                        + value.getClass() + ")");
            }

            data.get(row)[col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i = 0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j = 0; j < numCols; j++) {
                    System.out.print("  " + data.get(i)[j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }
}
