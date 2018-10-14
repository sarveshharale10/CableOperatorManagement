import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class PaymentHistoryDialog extends JDialog{
	JLabel lblCustomerNo,lblCustomerNoValue,lblCustomerName,lblCustomerNameValue;
	JScrollPane scrollPane;

	DefaultTableModel tableModel;
	JTable table;

	int selectedRowIndex;

	PaymentHistoryDialog(){
		tableModel = CustomerTableModelFactory.getInstance();

		lblCustomerNo = new JLabel("Customer No:");
		lblCustomerNoValue = new JLabel();
		lblCustomerName = new JLabel("Customer Name:");
		lblCustomerNameValue = new JLabel();

		setLayout(new GridLayout(2,2));

		add(lblCustomerNo);
		add(lblCustomerNoValue);
		add(lblCustomerName);
		add(lblCustomerNameValue);
	}

	void setSelectedRowIndex(int selectedRowIndex){
		this.selectedRowIndex = selectedRowIndex;
		lblCustomerNoValue.setText((String)tableModel.getValueAt(selectedRowIndex,0));
		lblCustomerNameValue.setText((String)tableModel.getValueAt(selectedRowIndex,1));
	}

}