package convertorama.nodes;

import convertorama.TableDescriptor;
import javax.swing.ImageIcon;

/**
 *
 * @author kasper
 */
public abstract class SingleInMultiOutNode extends Node {
    public static final String NODE_TYPE = Node.SINGLE_IN_MULTI_OUT;
    
    public SingleInMultiOutNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
    }
    
    public abstract void startProcessing(TableDescriptor tableDescriptor);
    public abstract Object[] processRow(Object[] lkjadfs);
    public abstract void endProcessing();
    
    
}
