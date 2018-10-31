import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

class CollectionPanel extends JPanel implements ActionListener{
	JLabel lblSearch;
	JTextField txtSearch;
	JButton btnAddPayment,btnViewPaymentHistory;
	String[] columns = {"customerNo","customerName","dueAmount","monthlyCharge","contactNo"};
	String[] jTableColumns = {"Customer No","Customer Name","Due Amount","Monthly Charge","Contact No"};

	JScrollPane scrollPane;
	JTable table;
	DefaultTableModel collectionModel;
	CustomerDao customerDao;

	AddPaymentDialog addPaymentDialog;
	PaymentHistoryDialog paymentHistoryDialog;

	CollectionPanel(){
		lblSearch = new JLabel("Search:",SwingConstants.RIGHT);
		txtSearch = new JTextField(20);

		btnAddPayment = new JButton("Add Payment");
		btnViewPaymentHistory = new JButton("View Payment History");

		collectionModel = CollectionViewModelFactory.getInstance();
		customerDao = new CustomerDao();

		Vector<String> columnVector = new Vector<String>(Arrays.asList(jTableColumns));

		Vector<Vector<String>> data = customerDao.getAllWithDue(columns);

		collectionModel.setDataVector(data,columnVector);
		table = new JTable(collectionModel);
		scrollPane = new JScrollPane(table);

		txtSearch.getDocument().addDocumentListener(new SearchFilter(txtSearch,table));

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
		btnAddPayment.addActionListener(this);

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		int selectedRowIndex = table.getSelectedRow();
		selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
		if(selectedRowIndex == -1){
			JOptionPane.showMessageDialog(this,"Please Select a Row","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(src.equals(btnAddPayment)){
			addPaymentDialog = new AddPaymentDialog(selectedRowIndex);
			addPaymentDialog.setVisible(true);
		}
		else if(src.equals(btnViewPaymentHistory)){
			paymentHistoryDialog = new PaymentHistoryDialog(selectedRowIndex);
			paymentHistoryDialog.setVisible(true);
		}
	}
}