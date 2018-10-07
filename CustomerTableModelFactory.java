import javax.swing.table.*;

class CustomerTableModelFactory{

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