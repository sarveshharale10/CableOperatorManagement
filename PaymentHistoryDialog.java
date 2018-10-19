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
		tableModel = CustomerViewModelFactory.getInstance();

		lblCustomerNo = new JLabel("Customer No:");
		lblCustomerNoValue = new JLabel();
		lblCustomerName = new JLabel("Customer Name:");
		lblCustomerNameValue = new JLabel();

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		Insets insets = new Insets(10,10,10,10);
		add(lblCustomerNo,gc);

		gc.gridx = 1;
		gc.gridy = 0;		
		add(lblCustomerNoValue,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(lblCustomerName,gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(lblCustomerNameValue,gc);
	}

	void setSelectedRowIndex(int selectedRowIndex){
		this.selectedRowIndex = selectedRowIndex;
		lblCustomerNoValue.setText((String)tableModel.getValueAt(selectedRowIndex,0));
		lblCustomerNameValue.setText((String)tableModel.getValueAt(selectedRowIndex,1));
	}

}