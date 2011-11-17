package convertorama.nodes;

import convertorama.TableDescriptor;
import java.util.Iterator;
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
        try {
            if (sourceNode == null) {
                //if sourceNode has not yet been set, we set it as source
                sourceNode = source;
            }
            updateTableDescriptor();
        } catch (Exception e) {
            System.err.println("wasn't able to retrieve schema from source");
            //TODO: do something with the error
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
                String field = (String) rulesTable.getValueAt(i, 0);
                if (field.trim().isEmpty()) {
                    continue; //TODO: tell user that this line is being skipped
                }
                String search = (String) rulesTable.getValueAt(i, 1);
                String replace = (String) rulesTable.getValueAt(i, 2);
                boolean fullMatch = (boolean) rulesTable.getValueAt(i, 3);
                rules.add(new Rule(field, search, replace, fullMatch));
            }
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    @Override
    public void startProcessing()  throws Exception {
        Iterator<Node> it = targetNodes.iterator();
        while (it.hasNext()) {
            Node target = it.next();
            target.startProcessing();
        }
    }

    @Override
    public boolean processRow(Object[] fields) throws Exception  {
        //run the rules
        //            private TableDescriptor tableDescriptor;
        //    private List<Rule> rules;
        Iterator<Rule> it = rules.iterator();
        while (it.hasNext()) {
            Rule rule = it.next();
            System.out.println("SR - Column index for: "+rule.getField()+"" +tableDescriptor.getColumnIndex(rule.getField()));
            tableDescriptor.getColumnNames();
            for (int i = 0; i < tableDescriptor.getColumnNames().length; i++) {
                System.out.println("OF columns: "+tableDescriptor.getColumnNames()[i]);
            }
        }

        return true;
    }
    
    
    @Override
    public void stopProcessing()  throws Exception {
        Iterator<Node> it = targetNodes.iterator();
        while (it.hasNext()) {
            Node target = it.next();
            target.stopProcessing();
        }
    }

    @Override
    public void validate() throws Exception {
        //TODO: When needed/time enter rules for search and replace
        //Criterias:
        // * Table descriptor should be retrievable from sourceNode
        // * We should have at least 1 outgoing edge
        try {
            updateTableDescriptor();
        } catch (Exception e) {
            System.err.println("CSVInputNode.Validate: Error in trans node");
            throw e;
        }
        if (outgoingEdges.size() > 0 || targetNodes.size() == outgoingEdges.size()) {
            System.err.println("There is no output for the Transformation node");
            throw new Exception("Trans Validation failed");
        }
        Iterator<Node> it = targetNodes.iterator();
        while (it.hasNext()) {
            Node target = it.next();
            target.validate();
        }
        
    }

    @Override
    protected void updateTableDescriptor() throws Exception {       
//        TableDescriptor tableDescriptor = sourceNode.getOutputTableDescriptor();
        System.out.println("SearchAndReplaceNode got table descriptor from previous node");
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
