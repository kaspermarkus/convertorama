package convertorama.nodes;

import convertorama.TableDescriptor;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;

/**
 *
 * @author kasper
 */
public class SearchAndReplaceNode extends SingleInMultiOutNode {
    public static final String NODE_TYPE = Node.SINGLE_IN_MULTI_OUT;
    private TableDescriptor tableDescriptor;
    private List<Rule> rules;

    public SearchAndReplaceNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        this.setNodeType(NODE_TYPE);
        this.rules = new LinkedList<Rule>();
    }

    @Override
    public void edgeTargetEvent(Node source) {
        TableDescriptor tableDescriptor = source.getOutputTableDescriptor();
        if (tableDescriptor != null) {
            this.tableDescriptor = tableDescriptor;
            System.out.println("SearchAndReplaceNode got table descriptor from previous node");
        }
    }

    public TableDescriptor getTableDescriptor() {
        return tableDescriptor;
    }

    @Override
    public void showPropertiesDialog(JFrame parent) {
        SearchAndReplaceDialog dialog = new SearchAndReplaceDialog(this, parent);
        dialog.setVisible(true);
        //if ok was hit, save values
        if (dialog.getReturnStatus() == dialog.RET_OK) {
            rules.clear();
            //Make rules from values entered into dialog:
            JTable rulesTable = dialog.getRulesTable();
            int rowCount = rulesTable.getRowCount();
            for (int i = 0; i < rowCount; i++) {
                String field = (String)rulesTable.getValueAt(i, 0);
                if (field.trim().isEmpty()) {
                    continue; //TODO: tell user that this line is being skipped
                }
                String search = (String)rulesTable.getValueAt(i, 1);
                String replace = (String)rulesTable.getValueAt(i, 2);
                boolean fullMatch = (boolean)rulesTable.getValueAt(i, 3);
                rules.add(new Rule(field, search, replace, fullMatch));
            }
        }
    }
    
    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public void startProcessing(TableDescriptor tableDescriptor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] processRow(Object[] lkjadfs) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void endProcessing() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    class Rule {
        String field;
        String searchString;
        String ReplaceString;
        boolean fullMatch;

        public Rule(String field, String searchString, String ReplaceString, boolean fullMatch) {
            this.field = field;
            this.searchString = searchString;
            this.ReplaceString = ReplaceString;
            this.fullMatch = fullMatch;
        }

        public String getReplaceString() {
            return ReplaceString;
        }

        public String getField() {
            return field;
        }

        public boolean isFullMatch() {
            return fullMatch;
        }

        public String getSearchString() {
            return searchString;
        }
    }

    @Override
    public boolean equals(Object o) {
        //We need to base equals on the string identifying node for the mxMultiplicity constraints to work
        if (o.toString().equals(NODE_TYPE)) {
            return true;
        } else {
            return super.equals(o);
        }
    }
}
