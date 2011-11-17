package convertorama;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxMultiplicity;
import convertorama.nodes.InputNode;
import convertorama.Palette;
import convertorama.nodes.CSVInputNode;
import convertorama.nodes.ConsoleOutputNode;
import convertorama.nodes.Node;
import convertorama.nodes.OutputNode;
import convertorama.nodes.SearchAndReplaceNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author kasper
 */
public class ConvertORama {
    //holds the frame title

    private JFrame mainFrame;
    private mxGraphComponent graphPanel;
    private javax.swing.JScrollPane graphScroller;
    private Palette palette;
    private javax.swing.JScrollPane paletteScroller;
    private javax.swing.JSplitPane mainSplitFrame;
    private mxGraph graph;
    private ConvertORama convertORama;

    private void initComponents() {
        //Outer level panel. 
        //Splits screen vertically - Palette on left and graft on right
        mainSplitFrame = new javax.swing.JSplitPane();
        mainSplitFrame.setOneTouchExpandable(true);
        mainSplitFrame.setDividerLocation(200);
        mainSplitFrame.setDividerSize(6);
        mainSplitFrame.setBorder(null);
//        mxCodecRegistry.addPackage("convertorama");
//        mxCodecRegistry.register(new mxObjectCodec(new Node()));
//        mxCodecRegistry.register(new mxObjectCodec(new InputNode()));
//        mxCodecRegistry.register(new mxObjectCodec(new CSVInputNode()));
        //Left side; Holds palette inside a scrolling pane
        paletteScroller = new javax.swing.JScrollPane();
        palette = new Palette();
        CSVInputNode csvInputNode = new CSVInputNode("CSV File Input", "/csv-icon.png");
        palette.addTemplate(csvInputNode);
        SearchAndReplaceNode searchAndReplaceNode = new SearchAndReplaceNode("Search and Replace", "/actor.png");
        palette.addTemplate(searchAndReplaceNode);
        ConsoleOutputNode consoleOutNode = new ConsoleOutputNode("Console Output", "/console-out.png");
        palette.addTemplate(consoleOutNode);
        
        palette.addTemplate("Bell", new ImageIcon(ConvertORama.class.getResource("/bell.png")),
                "shape=image;image=/bell.png;labelBackgroundColor=white;verticalLabelPosition=bottom;verticalAlign=top", 50, 50, "NOIE NODIE");
        //Right side; Graph held inside a scrolling pane
        graphScroller = new javax.swing.JScrollPane();
        graph = new mxGraph();
        graph.setCellsEditable(false);
        graphPanel = new mxGraphComponent(graph);
//        graphPanel.setPageVisible(true);
        //         * mxEvent.CELL_CONNECTED fires between begin- and endUpdate in cellConnected.
        // * The <code>edge</code>, <code>terminal</code> and <code>source</code>
        // * properties contain the respective arguments that were passed to
        // * cellConnected.        
        graph.addListener(mxEvent.CELL_CONNECTED, new mxIEventListener() {

            @Override
            public void invoke(Object sender, mxEventObject evt) {
                if (((Boolean) evt.getProperty("source")).booleanValue() == false) {
                    mxCell terminalObj = (mxCell) evt.getProperty("terminal");
                    if (terminalObj.getValue() instanceof Node) {
                        Node terminal = (Node) terminalObj.getValue();
                        //use edge to find the source
                        mxCell edge = (mxCell) evt.getProperty("edge");
                        mxCell sourceCell = (mxCell) edge.getSource();
                        if (sourceCell.getValue() instanceof Node) {
                            terminal.edgeTargetEvent((Node)sourceCell.getValue());
                            System.out.println("Terminal: " + terminal.toString() + " (" + terminal.getNodeType() + ")");
                            System.out.println("Source: " + ((Node)sourceCell.getValue()).toString() + " (" + ((Node)sourceCell.getValue()).getNodeType() + ")");

                        } else {
                            System.err.println("Expected source object to be of type node");
                        }
                    } else {
                        System.err.println("Expected terminal object to be of type node");
                    }

                }

            }
        });
        graphPanel.setGridVisible(true);
        graphPanel.setToolTips(true);
        // Sets the background to white
        graphPanel.getViewport().setOpaque(true);
        graphPanel.getViewport().setBackground(Color.WHITE);
//        graphPanel.getConnectionHandler().setCreateTarget(true);
        //Setup Palette
        paletteScroller.setViewportView(palette);

        mainSplitFrame.setLeftComponent(paletteScroller);

        //Setup Graph
        graphScroller.setViewportView(graphPanel);

        mainSplitFrame.setRightComponent(graphScroller);

        graphPanel.getGraphControl().addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent event) {
                //if user has doubleclicked
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    //And if the clicked object is a cell:
                    Object clickedObj = graphPanel.getCellAt(event.getX(), event.getY());
                    if (clickedObj != null && clickedObj instanceof mxCell) {
                        //and if it's a node:
                        mxCell cell = (mxCell) clickedObj;
                        if (cell.getValue() instanceof Node) {
                            //show properties
                            ((Node) cell.getValue()).showPropertiesDialog(mainFrame);
                        }
                    }
                }
            }
        });
        //add to frame
        mainFrame.getContentPane().add(mainSplitFrame, java.awt.BorderLayout.CENTER);

//        pack();
    }

    public ConvertORama(String frameTitle) {
        mainFrame = new JFrame(frameTitle);

        this.initComponents();
        List<mxMultiplicity> constraints = new LinkedList<mxMultiplicity>();
        //INPUT NODE
        //Can only have 1 outgoing edge. Cannot output to OUTPUT type node.        
        constraints.add(new mxMultiplicity(true, InputNode.NODE_TYPE, null, null, 0,
                "1", Arrays.asList(new String[]{OutputNode.NODE_TYPE}),
                "Input node can only have one output. Use a map to distribute data to multiple nodes.",
                "Input node can not output directly to output node. Use at least a map in between", false));
        //Cannot have any ingoing edges
        constraints.add(new mxMultiplicity(false, InputNode.NODE_TYPE, null, null, 0,
                "0", null,
                "Input node can reads from files or databases, not from other nodes in Convert'O'Rama. To select what file or database to read from, edit properties of this node.",
                null, true));
        //ONEINMULTIOUT
        //Can only have 1 ingoing edge. Cannot input directly from InputNode.
        constraints.add(new mxMultiplicity(false, Node.SINGLE_IN_MULTI_OUT, null, null, 0,
                "1", null,
                "Only one input allowed.",
                null, true));
        //Can have multiple outgoing edges        
        constraints.add(new mxMultiplicity(true, Node.SINGLE_IN_MULTI_OUT, null, null, 0,
                "n", null,
                null,
                null, true));
        //OUTPUT NODE
        //Cannot have any outgoing edge
        constraints.add(new mxMultiplicity(true, OutputNode.NODE_TYPE, null, null, 0,
                "0", null,
                "Output node cannot have any ingoing edges. To select what file or database to write to, edit properties of this node.",
                null, true));
        //Can only have 1 ingoing edge. Cannot input directly from InputNode.
        constraints.add(new mxMultiplicity(false, OutputNode.NODE_TYPE, null, null, 0,
                "1", Arrays.asList(new String[]{InputNode.NODE_TYPE}),
                "Output node can only have one ingoing edge. If you want to merge more data into the output, use a map or something similar to join the data, and then output from map into the output node.",
                "Output node can not get its data directly from an input node. Use at least a map in between", false));

        graph.setMultiplicities(constraints.toArray(new mxMultiplicity[1]));
        graph.setMultigraph(false);
        graph.setAllowDanglingEdges(false);
        graphPanel.setConnectable(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(870, 640);
        mainFrame.setVisible(true);
    }
    
    private boolean processGraph() {
        Object tmp = graph.getCurrentRoot();
        
        return false;
    }

    public static void main(String[] args) {
        ConvertORama convertORama = new ConvertORama("Convert'o'Rama");
    }
}
