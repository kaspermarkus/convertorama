package convertorama.nodes;

import convertorama.InputNode;
import convertorama.Table;
import convertorama.TableDescriptor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;

/**
 *
 * @author kasper
 */
public class CSVInputNode extends InputNode {

    private File file = null;
    private char separator = ';';
    private boolean firstLineHeader = false;
    private TableDescriptor tableDescriptor;
    //The following is used for processing
    private BufferedReader bufferedReader;

//    /**
//     * SHOULD NOT BE USED FOR ANYTHING BUT THE mxCodec
//     */
//    public CSVInputNode() { 
//         super.setNodeType(NODE_TYPE);
//   }
    public CSVInputNode(String nodeLabel, String imagePath) {
        super(nodeLabel, imagePath);
        super.setNodeType(NODE_TYPE);
    }

    @Override
    public void showPropertiesDialog(JFrame parent) {
        CSVInputDialog dialog = new CSVInputDialog(file, separator, firstLineHeader, parent);
        dialog.setVisible(true);
        //if ok was hit, save values
        if (dialog.getReturnStatus() == dialog.RET_OK) {
            try {
                tableDescriptor = getTableDescriptor(dialog.getFile(), dialog.getSeparator(), dialog.isFirstLineHeader());
                this.file = dialog.getFile();
                this.separator = dialog.getSeparator();
                this.firstLineHeader = dialog.isFirstLineHeader();
            } catch (Exception e) {
                //Catch error if some choices are invalid.. If so, mark node as faulty
                e.printStackTrace();
            }
        }
        System.out.println("Selected:");
        System.out.println(dialog.getSeparator());
        System.out.println((dialog.getFile() == null) ? "NULL" : dialog.getFile().toString());
        System.out.println(dialog.isFirstLineHeader());
        System.out.println(dialog.getReturnStatus());
    }

    public TableDescriptor getOutputTableDescriptor() {
        return tableDescriptor;
    }

    private void updateTableDescriptor() {
        tableDescriptor = CSVInputNode.getTableDescriptor(file, separator, firstLineHeader);
    }

    /**
     * Read 1 line of the csv file and base table descriptor on that. CSV files have all their fields
     * interpreted as String objects, which is reflected by the resulting TableDescriptor. If firstLineIsHeader
     * is true, the values from the first line are used as column names.
     * 
     * @param file
     * @param separator
     * @param firstLineIsHeader
     * @return 
     */
    public static TableDescriptor getTableDescriptor(File file, char separator, boolean firstLineIsHeader) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();
            if (line != null) {
                String[] cells = line.split(separator + "");
                bufferedReader.close();
                inputStreamReader.close();
                return new TableDescriptor(cells, firstLineIsHeader);
            } else {
                bufferedReader.close();
                inputStreamReader.close();
                throw new IOException("Was not able to read the first line of the file: " + file.toString());
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + file.toString() + ". Errr message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static Table readTable(TableDescriptor tableDescriptor, File file, char separator, boolean firstLineIsHeader, int linesToRead) {
        int linesRead = 0;

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            Table table = new Table(tableDescriptor);

            String line;

            if (firstLineIsHeader) { //if first line is header, skip it
                bufferedReader.readLine();
            }

            while ((line = bufferedReader.readLine()) != null) {
                linesRead++;
                String[] vals = line.split(separator + "");
                table.addRow(vals);
            }
            bufferedReader.close();
            inputStreamReader.close();
            return table;
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.toString() + ". Errr message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public TableDescriptor startProcessing() {
        //TODO: Lock stuff while doing this
        //make sure table descriptor is up to date:
        this.updateTableDescriptor();
        //get input readers ready:
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            this.bufferedReader = new BufferedReader(inputStreamReader);
            if (firstLineHeader) { //if first line is header, skip it
                bufferedReader.readLine();
            }
        } catch (IOException ioe) {
            //TODO Error handling
            ioe.printStackTrace();
            return null;
        }
        return tableDescriptor;
    }

    @Override
    public Object[] processRow() {
        try {
            String line = bufferedReader.readLine();
            if (line == null) {
                return null; //indicates EOF
            } else {
                return line.split(separator + "");
            }
        } catch (Exception e) {
            //TODO Release semaphore
            try {
                bufferedReader.close();
            } catch (Exception e2) {
                //TODO Error handling
                e2.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public void stopProcessing() {
        try {
            bufferedReader.close();
        } catch (Exception e) {
            //TODO Error handling
            e.printStackTrace();
        }
    }    
}
