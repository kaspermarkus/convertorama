/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertorama.nodes;

/**
 *
 * @author kasper
 */
public class OutputNode extends Node { 
    public static final String NODE_TYPE = "OutputNode";
    
    public OutputNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        super.setNodeType(NODE_TYPE);
    }
}
