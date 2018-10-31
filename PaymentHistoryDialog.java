import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

class PaymentHistoryDialog extends JDialog{
	JLabel lblCustomerNo,lblCustomerNoValue,lblCustomerName,lblCustomerNameValue;
	JScrollPane scrollPane;

	String[] columns = {"Date of Payment","Amount"};
	Vector<Vector<String>> data;
	Vector<String> columnVector;
	JTable table;

	DefaultTableModel customerTableModel,paymentTableModel;
	PaymentDao paymentDao;

	int selectedRowIndex;

	PaymentHistoryDialog(int selectedRowIndex){
		setTitle("View Payment History");
		this.selectedRowIndex = selectedRowIndex;

		customerTableModel = CollectionViewModelFactory.getInstance();
		paymentTableModel = PaymentViewModelFactory.getInstance();
		paymentDao = new PaymentDao();

		lblCustomerNo = new JLabel("Customer No:");
		lblCustomerNoValue = new JLabel((String)customerTableModel.getValueAt(selectedRowIndex,0));
		lblCustomerName = new JLabel("Customer Name:");
		lblCustomerNameValue = new JLabel((String)customerTableModel.getValueAt(selectedRowIndex,1));

		data = paymentDao.getByCustomerNo(Integer.parseInt(lblCustomerNoValue.getText()));
		columnVector = new Vector<String>(Arrays.asList(columns));
		paymentTableModel.setDataVector(data,columnVector);

		table = new JTable(paymentTableModel);
		scrollPane = new JScrollPane(table);

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10,10,10,10);
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

		gc.gridx = 0;
		gc.gridy = 2;
		gc.gridwidth = 2;
		add(scrollPane,gc);

		pack();
	}

}