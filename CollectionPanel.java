import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class CollectionPanel extends JPanel implements ActionListener{
	JLabel lblSearch;
	JTextField txtSearch;
	JButton btnAddPayment,btnViewPaymentHistory;
	JScrollPane scrollPane;
	JTable table;
	DefaultTableModel customerModel;

	PaymentHistoryDialog paymentHistoryDialog;

	CollectionPanel(){
		lblSearch = new JLabel("Search:",SwingConstants.RIGHT);
		txtSearch = new JTextField(20);

		btnAddPayment = new JButton("Add Payment");
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
		add(lblSearch,gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(txtSearch,gc);

		gc.gridx = 2;
		gc.gridy = 0;
		add(btnAddPayment,gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(btnViewPaymentHistory,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 4;
		add(scrollPane,gc);

		btnViewPaymentHistory.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnAddPayment)){

		}
		else if(src.equals(btnViewPaymentHistory)){
			int selectedRowIndex = table.getSelectedRow();
			selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
			paymentHistoryDialog.setSelectedRowIndex(selectedRowIndex);
			paymentHistoryDialog.setVisible(true);
		}
	}
}