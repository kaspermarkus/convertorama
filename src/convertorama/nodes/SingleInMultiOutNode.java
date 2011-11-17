package convertorama.nodes;

import com.mxgraph.model.mxCell;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author kasper
 */
public abstract class SingleInMultiOutNode extends Node {

    public static final String NODE_TYPE = Node.SINGLE_IN_MULTI_OUT;
    protected mxCell cell;
    protected Node sourceNode;
    protected mxCell incomingEdge;
    protected List<Node> targetNodes;
    protected List<mxCell> outgoingEdges;

    public SingleInMultiOutNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
    }

    /**
     * Validation criterias:
     * - Table descriptor should be retrievable from sourceNode
     * - We should have at least 1 outgoing edge
     * @throws Exception 
     */
    @Override
    public void validate() throws Exception {
        System.out.println("VALIDATION: single input, multi out node");
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

    protected abstract void updateTableDescriptor() throws Exception;

    @Override
    public void updateMxData(mxCell cell, Node caller) {
        //save cell
        this.cell = cell;
        //init olders for outgoing edges and target notes
        this.outgoingEdges = new LinkedList<mxCell>();
        this.targetNodes = new LinkedList<Node>();

        //save the outgoing edges and target nodes - and init if necessary:
        int numEdges = cell.getEdgeCount();
        for (int edgeIndex = 0; edgeIndex < numEdges; edgeIndex++) {
            //for each edge:
            mxCell currentEdge = (mxCell) cell.getEdgeAt(edgeIndex);
            System.out.println("EDGE " + edgeIndex + ": source: " + currentEdge.getSource() + " target: " + currentEdge.getTarget());
            if (currentEdge.getSource() == cell) { //outgoing edge:
                //add edge
                System.out.println("Found outgoing edge");
                outgoingEdges.add(currentEdge);
                //add node
                mxCell targetCell = (mxCell) currentEdge.getTarget();
                Node targetNode = (Node) targetCell.getValue();
                targetNodes.add(targetNode);
                //update that cell if hit hasn't already happened (if it's not the caller)
                if (targetNode != caller) {
                    targetNode.updateMxData(targetCell, this);
                }
            } else {                     //if edge is incoming:
                System.out.println("Found ingoing edge");
                this.incomingEdge = currentEdge;
                mxCell sourceCell = (mxCell) currentEdge.getTarget();
                this.sourceNode = (Node) sourceCell.getValue();
                if (sourceNode != caller) { //if node hasn't been updated already
                    sourceNode.updateMxData(sourceCell, this);
                }

            }
        }
        System.out.println("Total: outgoing: " + outgoingEdges.size() + " incoming: " + (incomingEdge != null));
    }
//    public abstract void startProcessing(TableDescriptor tableDescriptor);
//    public abstract Object[] processRow(Object[] lkjadfs);
//    public abstract void stopProcessing();
}
