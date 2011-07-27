/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * mainFrame.java
 *
 * Created on Jul 24, 2011, 6:54:14 AM
 */
package convertorama;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.view.mxGraph;
import convertorama.nodes.CSVInputNode;
import convertorama.nodes.ConsoleOutputNode;
import convertorama.nodes.Node;
import convertorama.nodes.SearchAndReplaceNode;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author kasper
 */
public class mainFrame extends javax.swing.JFrame {

    /** Creates new form mainFrame */
    public mainFrame() {
        initComponents();
        initPalette();
        initGraph();

        mainSplitPane.setLeftComponent(paletteScroller);
        mainSplitPane.setRightComponent(graphScroller);
        this.getContentPane().add(mainSplitPane);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        validateToolBarButton = new javax.swing.JButton();
        processGraphToolBarButton = new javax.swing.JButton();
        mainSplitPane = new javax.swing.JSplitPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        actionsMenu = new javax.swing.JMenu();
        validateGraphMenuItem = new javax.swing.JMenuItem();
        processGraphMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Convert'O'Rama");

        toolBar.setRollover(true);

        validateToolBarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/convertorama/images/checkmark24.png"))); // NOI18N
        validateToolBarButton.setFocusable(false);
        validateToolBarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        validateToolBarButton.setMaximumSize(new java.awt.Dimension(28, 28));
        validateToolBarButton.setMinimumSize(new java.awt.Dimension(28, 28));
        validateToolBarButton.setPreferredSize(new java.awt.Dimension(28, 28));
        validateToolBarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        validateToolBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateToolBarButtonActionPerformed(evt);
            }
        });
        toolBar.add(validateToolBarButton);

        processGraphToolBarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/convertorama/images/run24.png"))); // NOI18N
        processGraphToolBarButton.setToolTipText("Click to run the graph");
        processGraphToolBarButton.setFocusable(false);
        processGraphToolBarButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        processGraphToolBarButton.setMaximumSize(new java.awt.Dimension(28, 28));
        processGraphToolBarButton.setMinimumSize(new java.awt.Dimension(28, 28));
        processGraphToolBarButton.setPreferredSize(new java.awt.Dimension(28, 28));
        processGraphToolBarButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        processGraphToolBarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processGraphToolBarButtonActionPerformed(evt);
            }
        });
        toolBar.add(processGraphToolBarButton);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        mainSplitPane.setDividerSize(1);
        mainSplitPane.setPreferredSize(new java.awt.Dimension(760, 600));
        getContentPane().add(mainSplitPane, java.awt.BorderLayout.CENTER);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open");
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        actionsMenu.setMnemonic('e');
        actionsMenu.setText("Actions");

        validateGraphMenuItem.setMnemonic('p');
        validateGraphMenuItem.setText("Validate Graph");
        actionsMenu.add(validateGraphMenuItem);

        processGraphMenuItem.setMnemonic('d');
        processGraphMenuItem.setText("Process Graph");
        processGraphMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processGraphMenuItemActionPerformed(evt);
            }
        });
        actionsMenu.add(processGraphMenuItem);

        menuBar.add(actionsMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void validateToolBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateToolBarButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_validateToolBarButtonActionPerformed

    private void processGraphToolBarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processGraphToolBarButtonActionPerformed
        processGraph();
    }//GEN-LAST:event_processGraphToolBarButtonActionPerformed

    private void processGraphMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processGraphMenuItemActionPerformed
        processGraph();
    }//GEN-LAST:event_processGraphMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new mainFrame().setVisible(true);
            }
        });
    }

    private boolean processGraph() {
        //First get input vertices:
        List<mxCell> inputVertices = new LinkedList<mxCell>();
        //get vertices:
        Object[] vertices = graph.getChildVertices(graph.getDefaultParent());
        for (int i = 0; i < vertices.length; i++) {
            mxCell cell = (mxCell)vertices[i];
            if (cell.getValue() instanceof InputNode) {
                System.out.println("found input node");
                inputVertices.add(cell);
            }
        }
        System.out.println("total input nodes found: "+inputVertices.size());
        //start the processing:
        mxCell first = inputVertices.get(0);
        InputNode firstNode = (InputNode)first.getValue();
        firstNode.startProcessing();
        Object[] row;
        while ((row = firstNode.processRow()) != null) {
            
        }
        firstNode.stopProcessing();

        return false;
    }
    private mxGraphComponent graphPanel;
    private javax.swing.JScrollPane graphScroller;
    private Palette palette;
    private javax.swing.JScrollPane paletteScroller;
    private mxGraph graph;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenu actionsMenu;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JSplitPane mainSplitPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem processGraphMenuItem;
    private javax.swing.JButton processGraphToolBarButton;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JMenuItem validateGraphMenuItem;
    private javax.swing.JButton validateToolBarButton;
    // End of variables declaration//GEN-END:variables

    private void initPalette() {
        paletteScroller = new javax.swing.JScrollPane();
        palette = new Palette();
        CSVInputNode csvInputNode = new CSVInputNode("CSV File Input", "/convertorama/images/csv-icon.png");
        palette.addTemplate(csvInputNode);
        SearchAndReplaceNode searchAndReplaceNode = new SearchAndReplaceNode("Search and Replace", "/convertorama/images/actor.png");
        palette.addTemplate(searchAndReplaceNode);
        ConsoleOutputNode consoleOutNode = new ConsoleOutputNode("Console Output", "/convertorama/images/console-out.png");
        palette.addTemplate(consoleOutNode);

        palette.addTemplate("Bell", new ImageIcon(ConvertORama.class.getResource("/convertorama/images/bell.png")),
                "shape=image;image=/bell.png;labelBackgroundColor=white;verticalLabelPosition=bottom;verticalAlign=top", 50, 50, "NOIE NODIE");
        //Setup Palette
        paletteScroller.setViewportView(palette);
    }

    private void initGraph() {
        graphScroller = new javax.swing.JScrollPane();
        graph = new mxGraph();
        graph.setCellsEditable(false);
        graphPanel = new mxGraphComponent(graph);

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
                            terminal.edgeTargetEvent((Node) sourceCell.getValue());
                            System.out.println("Terminal: " + terminal.toString() + " (" + terminal.getNodeType() + ")");
                            System.out.println("Source: " + ((Node) sourceCell.getValue()).toString() + " (" + ((Node) sourceCell.getValue()).getNodeType() + ")");

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

        graphScroller.setViewportView(graphPanel);
        final JFrame mainFrame = this;
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
    }
}