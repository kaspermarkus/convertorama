package convertorama.nodes;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import convertorama.TableDescriptor;

/**
 *
 * @author kasper
 */
public abstract class InputNode extends Node {

    public static final String NODE_TYPE = "InputNode";
    protected Node targetNode;
    protected mxCell outgoingEdge;
    protected TableDescriptor tableDescriptor;

    public TableDescriptor getOutputTableDescriptor() throws Exception {
        if (tableDescriptor == null) {
            updateTableDescriptor();
        }
        return tableDescriptor;
    }

    protected abstract void updateTableDescriptor() throws Exception;

    /**
     * Criterias for validation:
     * - successfully read/update table descriptor
     * - have 1 output edge
     * 
     * @throws Exception 
     */
    @Override
    public void validate() throws Exception {
        System.out.println("VALIDATION: validating inputnode");
        try {
            updateTableDescriptor();
        } catch (Exception e) {
            //TODO Color node - mark that it's erroneous somehow
            System.err.println("InputNode.Validate: Error in Input node");
            throw e;
        }
        if (outgoingEdge == null || targetNode == null) {
            System.err.println("There is no output for the Input Node");
            throw new Exception("Input Validation failed");
        }
        //Now validate target:
        targetNode.validate();
    }

    public void updateMxData(mxCell cell, Node caller) {
        this.cell = cell;
        
        mxCell targetCell = null;
        if (cell.getEdgeCount() > 0) {
            //if there is an outgoing edge, set this
            this.outgoingEdge = (mxCell) cell.getEdgeAt(0);
            //and set which node it points to
            targetCell = (mxCell) outgoingEdge.getTerminal(false);
            targetNode = (Node) targetCell.getValue();
            System.out.println("PRIMARY: " + cell);
            System.out.println("PRIMARY: Set input cell to have no cell, node to be: " + this.getNodeLabel());
            System.out.println("PRIMARY: Set child node to: " + targetNode.getNodeLabel());
            System.out.println("PRIMARY: updating sourcenode's data");
            
        }
        if (caller == null && cell.getEdgeCount() != 0) { //if target hasn't been initialized (or: if this is the first node to bet initialized)
                //update the target node:
                targetNode.updateMxData(targetCell, this);
        }
    }

//    /**
//     * SHOULD NOT BE USED FOR ANYTHING BUT THE mxCodec
//     */
//    public InputNode() { 
//        super.setNodeType(NODE_TYPE);
//    }
    public InputNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        super.setNodeType(NODE_TYPE);
    }

//    public abstract boolean processRow();
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
