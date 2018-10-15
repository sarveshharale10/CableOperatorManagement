import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class CollectionPanel extends JPanel implements ActionListener{
	JLabel lblCustomerNo,lblCustomerName,lblDue,lblAmount,lblDate;
	JTextField txtCustomerNo,txtCustomerName,txtDue,txtAmount,txtDate;
	JButton btnSave,btnViewPaymentHistory;
	JScrollPane scrollPane;
sad;gjahsglajkhglksjfghlkfjhalsdkfjhasdlkjfhasdlkjfh
	JTable table;
	DefaultTableModel customerModel;

	PaymentHistoryDialog paymentHistoryDialog;

	CollectionPanel(){
		lblCustomerNo = new JLabel("Customer No:",SwingConstants.RIGHT);
		lblCustomerName = new JLabel("Customer Name:",SwingConstants.RIGHT);
		lblDue = new JLabel("Due Amount:",SwingConstants.RIGHT);
		lblAmount = new JLabel("Amount Received:",SwingConstants.RIGHT);
		lblDate = new JLabel("Date Received:",SwingConstants.RIGHT);

		txtCustomerNo = new JTextField(20);
		txtCustomerName = new JTextField(20);
		txtDue = new JTextField(20);
		txtAmount = new JTextField(20);
		txtDate = new JTextField(20); //Just for now

		btnSave = new JButton("Save");
		btnViewPaymentHistory = new JButton("View Payment History");

		customerModel = CustomerTableModelFactory.getInstance();
		table = new JTable(customerModel);
		scrollPane = new JScrollPane(table);

		paymentHistoryDialog = new PaymentHistoryDialog();

		setLayout(new GridBagLayout());

		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10,10,10,10);
		gc.fill = GridBagConstraints.BOTH;
		add(lblCustomerNo,gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(txtCustomerNo,gc);

		gc.gridx = 2;
		gc.gridy = 0;
		add(lblCustomerName,gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(txtCustomerName,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		add(lblDue,gc);

		gc.gridx = 1;
		gc.gridy = 1;
		add(txtDue,gc);

		gc.gridx = 2;
		gc.gridy = 1;
		add(lblAmount,gc);

		gc.gridx = 3;
		gc.gridy = 1;
		add(txtAmount,gc);

		gc.gridx = 0;
		gc.gridy = 2;
		add(lblDate,gc);

		gc.gridx = 1;
		gc.gridy = 2;
		add(txtDate,gc);

		gc.gridx = 2;
		gc.gridy = 2;
		add(btnSave,gc);

		gc.gridx = 3;
		gc.gridy = 2;
		add(btnViewPaymentHistory,gc);

		gc.gridx = 0;
		gc.gridy = 3;
		gc.gridwidth = 4;
		add(scrollPane,gc);

		btnViewPaymentHistory.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnSave)){

		}
		else if(src.equals(btnViewPaymentHistory)){
			int selectedRowIndex = table.getSelectedRow();
			selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
			paymentHistoryDialog.setSelectedRowIndex(selectedRowIndex);
			paymentHistoryDialog.setVisible(true);
		}
	}
}