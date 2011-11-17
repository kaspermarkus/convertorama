package convertorama.nodes;

import com.mxgraph.model.mxCell;
import convertorama.ConvertORama;
import convertorama.TableDescriptor;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author kasper
 */
public abstract class Node implements Serializable {
    public static String SINGLE_IN_MULTI_OUT = "SingleInMultiOut";
    private final String NODE_TYPE = "None"; 
    protected mxCell cell;
    private ImageIcon icon;
    private String nodeType;    
    private String nodeLabel;
    private String style;

//    /**
//     * SHOULD NOT BE USED FOR ANYTHING BUT THE mxCodec
//     */
//    public Node() { 
//    }
    
    public Node(String nodeLabel, String imagePath) {
        this.icon = new ImageIcon(ConvertORama.class.getResource(imagePath));
        this.nodeLabel = nodeLabel;
        this.style = "shape=image;image="+imagePath+";labelBackgroundColor=white;verticalLabelPosition=bottom;verticalAlign=top";
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }

    public String getNodeLabel() {
        return nodeLabel;
    }

    public void setNodeLabel(String NodeLabel) {
        this.nodeLabel = NodeLabel;
    }

    public String getNodeType() {
        return nodeType;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    protected void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
 
    public String getStyle() {
        return style;
    }
    
    public void showPropertiesDialog(JFrame parent) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return nodeLabel;
    } 
    
    public mxCell getCell() {
        return cell;
    }
    /**
     * Called when a new edge points to this node
     */
    public void edgeTargetEvent(Node source) {
        
    }
    
    public abstract void validate() throws Exception;
    /* -------------- mxGraph related stuff ------------------ */
    public abstract void updateMxData(mxCell cell, Node caller);
    public abstract void startProcessing() throws Exception;
    public boolean processRow(Object[] values) throws Exception {
        System.out.println("NODE: Called in node!!!!!");
        return true;
    };
    
    public abstract void stopProcessing() throws Exception;
}
