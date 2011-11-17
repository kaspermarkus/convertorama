/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertorama.nodes;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;

/**
 *
 * @author kasper
 */
public class OutputNode extends Node {
    mxCell cell;
    mxCell incomingEdge;
    Node sourceNode;

    public static final String NODE_TYPE = "OutputNode";

    public OutputNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        super.setNodeType(NODE_TYPE);
    }

    @Override
    public void updateMxData(mxCell cell, Node caller) {
        //call will always come from parent:so ignore caller parameter
        this.cell = cell;
        if (cell.getEdgeCount() > 0) {
            System.out.println("Output Node got one input");
            this.incomingEdge = (mxCell)cell.getEdgeAt(0);
            this.sourceNode = (Node)incomingEdge.getTarget().getValue(); //DO I NEED THIS?
        }
    }

    @Override
    public void startProcessing() throws Exception {
    }

    @Override
    public void stopProcessing() throws Exception  {
    }

    @Override
    public boolean processRow(Object[] values) throws Exception  {
        System.out.print("|");
        for (int i = 0; i < values.length; i++) {
            System.out.print("| "+values[i]+" |");
        }
        System.out.println("|");
        return true;
    }

    @Override
    public void validate() throws Exception {
        System.out.println("VALIDATION: validating output note");
        //Criterias:
        // * 1 input edge+node
        if (incomingEdge == null || sourceNode == null) {
            System.err.println("No data for output node to output. Needs 1 edge");
            throw new Exception("No data for output node to output. Needs 1 edge");
        } 
    }
}
