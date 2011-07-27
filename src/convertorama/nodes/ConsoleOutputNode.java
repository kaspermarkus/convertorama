package convertorama.nodes;

public class ConsoleOutputNode extends OutputNode {
    public ConsoleOutputNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        this.setNodeType(OutputNode.NODE_TYPE);
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