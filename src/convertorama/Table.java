package convertorama;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author kasper
 */
public class Table {

    private TableDescriptor descriptor;
    private List<Object[]> rows;

    public Table(TableDescriptor descriptor) {
        this.descriptor = descriptor;
        this.rows = new ArrayList<Object[]>();
    }

    public void addRow(Object[] vals) {
        if (!descriptor.validateRows(vals)) {
            throw new InvalidParameterException("Row didn't validate");
        } else {
            rows.add(vals);
        }
    }

    public Object[] getRow(int rowIndex) {
        return rows.get(rowIndex);
    }
    
    public Object[][] toDoubleArray() {
        Object[][] obj = new Object[rows.size()][descriptor.numColumns()];
        for (int i = 0; i < rows.size(); i++) {
            obj[i] = rows.get(i);
        }
        return obj;
    }

    public List<Object[]> getRows() {
        return rows;
    }

    public Object getCell(int rowIndex, int columnIndex) {
        return rows.get(rowIndex)[columnIndex];
    }
    
    public JScrollPane toGUI() {
        final JTable table = new JTable(this.toDoubleArray(), descriptor.getColumnNames());
        table.setEnabled(false);
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(descriptor.toString());
        Iterator<Object[]> it = rows.iterator();
        while (it.hasNext()) {
            sb.append("|");
            Object[] arr = it.next();
            for (int i = 0; i < arr.length; i++) {
                sb.append(" ");
                sb.append(arr[i].toString());
                sb.append(" |");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}