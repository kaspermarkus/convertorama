package convertorama;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author kasper
 */
public class TableDescriptor {
    private ArrayList<ColumnDescriptor> columns;

    public TableDescriptor() {
        columns = new ArrayList<ColumnDescriptor>();
    }

    public TableDescriptor(Object[] objects, boolean useAsHeader) {
        columns = new ArrayList<ColumnDescriptor>(objects.length);
        try {
            for (int i = 0; i < objects.length; i++) {
                int type = ColumnDescriptor.getColumnType(objects[i]);
                columns.add(new ColumnDescriptor((useAsHeader) ? objects[i].toString() : "col" + (i + 1), type));
            }
        } catch (Exception exc) {
            System.err.println(exc.getMessage());
            exc.printStackTrace();
            columns = null;
        }
    }
    
    public String[] getColumnsOfType(int type) {
        List<String> stringColumns = new LinkedList<String>();
        Iterator<ColumnDescriptor> it = columns.iterator();
        while (it.hasNext()) {
            ColumnDescriptor cd = it.next();
            if (cd.getColumnType() == type) {
                stringColumns.add(cd.getColumnName());
            }
        }
        return stringColumns.toArray(new String[1]);
                
    }
    public JScrollPane asJTableRO() {
        String[] headers = { "Field", "Type" };
        String[][] values = new String[columns.size()][headers.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = columns.get(i).getColumnPair();
        }
        JTable jtable = new JTable(values, headers);
        jtable.setEnabled(false);
        return new JScrollPane(jtable);
    }
    
    public String[] getColumnNames() {
        String[] columnNames = new String[columns.size()];
        for (int i = 0; i < columnNames.length; i++) {
            columnNames[i] = columns.get(i).getColumnName();
        }
        return columnNames;
    }
    
    public int numColumns() {
        return columns.size();
    }
//
//    public int addColumnDescriptor(String name, int type) {
//        columns.add(new ColumnDescriptor(name, type));
//        return columns.size()-1;
//    }
//    
//    public void addColumnDescriptor(String name, int type, int index) {
//        columns.add(index, new ColumnDescriptor(name, type));        
//    }
//    
//    public ColumnDescriptor getColumnDescriptor(int index) {
//        return columns.get(index);        
//    }
//    
//    public boolean validateColumn(Object obj, int index) {
//        return columns.get(index).validate(obj);
//    }
//    

    public boolean validateRows(Object[] row) {
        if (row.length != columns.size()) {
            System.out.println("Validation error: (Rows, columns) = "+row.length+", "+columns.size());
            return false;
        } else {
            for (int i = 0; i < row.length; i++) {
                if (!columns.get(i).validate(row[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Iterator<ColumnDescriptor> it = columns.iterator();
        sb.append("|");
        while (it.hasNext()) {
            ColumnDescriptor col = it.next();
            sb.append(" ");
            sb.append(col.getColumnName());
            sb.append(" |");
        }
        sb.append("\n");
        return sb.toString();
    }
//    
//    public boolean validateRow(List row) {
//        return validateRow(row.toArray());
//    }
}
