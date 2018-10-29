import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;


class SearchFilter implements DocumentListener{
    JTextField txtSearch;
    JTable table;
    TableRowSorter sorter;

    SearchFilter(JTextField txtSearch,JTable table){
        this.txtSearch = txtSearch;
        this.table = table;
        sorter = new TableRowSorter(table.getModel());
    }

    public void insertUpdate(DocumentEvent e){
        updateTable();
    }

    public void removeUpdate(DocumentEvent e){
        updateTable();
    }

    public void changedUpdate(DocumentEvent e){}

    void updateTable(){
        sorter.setRowFilter(RowFilter.regexFilter("(?i).*"+txtSearch.getText()+".*"));
        table.setRowSorter(sorter);
    }
}