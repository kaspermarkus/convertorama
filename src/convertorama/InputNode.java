package convertorama;

import convertorama.nodes.Node;
import java.rmi.server.ExportException;

/**
 *
 * @author kasper
 */
public abstract class InputNode extends Node { 
    public static final String NODE_TYPE = "InputNode";
    
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
     
    public abstract TableDescriptor startProcessing();
    public abstract Object[] processRow();
    public abstract void stopProcessing();
     
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
