package convertorama;

import javax.swing.JTable;

public class ColumnDescriptor {

    public static final int STRING = 0,
            INTEGER = 1,
            FLOAT = 2,
            DOUBLE = 3;
    private int columnType;
    private String columnName;

    public ColumnDescriptor(String name, int type) {
        this.columnName = name;
        this.columnType = type;
    }

    public String[] getColumnPair() {
        String[] ret = { columnName, typeToString() };
        return ret;
    }
    public String typeToString() {
        if (columnType == STRING) {
            return "String";
        } else if (columnType == FLOAT) {
            return "Float";
        } else if (columnType == DOUBLE) {
            return "Double";
        } else if (columnType == INTEGER) {
            return "Integer";
        } else {
            //TODO: Proper error handling
            return null;
        }
    }
    
    public boolean validate(Object obj) {
        if (columnType == STRING) {
            return obj.getClass().getSimpleName().equals("String");
        } else if (columnType == FLOAT) {
            return obj.getClass().getSimpleName().equals("Float");
        } else if (columnType == DOUBLE) {
            return obj.getClass().getSimpleName().equals("Double");
        } else if (columnType == INTEGER) {
            return obj.getClass().getSimpleName().equals("Integer");
        } else {
            return false;
        }
    }

    public static int getColumnType(Object obj) throws Exception {
        if (obj.getClass().getSimpleName().equals("String")) {
            return STRING;
        } else if (obj.getClass().getSimpleName().equals("Float")) {
            return FLOAT;
        } else if (obj.getClass().getSimpleName().equals("Double")) {
            return DOUBLE;
        } else if (obj.getClass().getSimpleName().equals("Integer")) {
            return INTEGER;
        } else {
            throw new Exception("Unsupported Object type for column: " + obj.getClass().getName() + " (" + obj.getClass().getSimpleName() + ")");
        }
    }

    public String getColumnName() {
        return columnName;
    }

    public int getColumnType() {
        return columnType;
    }
}