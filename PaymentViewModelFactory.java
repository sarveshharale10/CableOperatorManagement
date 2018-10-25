import javax.swing.table.*;
import java.util.*;
import java.sql.*;

class PaymentViewModelFactory{

	static DefaultTableModel tableModel;

	static{
		tableModel = new DefaultTableModel(){
			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
	}

	static DefaultTableModel getInstance(){
		return tableModel;
	}    
}