import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

class CustomerPanel extends JPanel implements ActionListener{
	JLabel lblSearchBy;
	JTextField txtSearch;
	String[] cbFields = {"Customer No","Customer Name","Address","Contact No","Monthly Charge"};
	JButton btnRegister,btnUpdate;
	JScrollPane scrollPane;
	
	JTable table;
	DefaultTableModel customerModel;
	CustomerDao customerDao;

	RegisterCustomerDialog registerCustomerDialog;
	UpdateCustomerDialog updateCustomerDialog;

	CustomerPanel(){
		lblSearchBy = new JLabel("Search: ");
		txtSearch = new JTextField(20);
		btnRegister = new JButton("Register Customer");
		btnUpdate = new JButton("Update Customer Details");

		Vector<String> columns = new Vector<String>(Arrays.asList(cbFields));

		customerModel = CustomerViewModelFactory.getInstance();
		customerDao = new CustomerDao();

		Vector<Vector<String>> data = customerDao.getAll("customerNo","customerName","address","contactNo","monthlyCharge");
		customerModel.setDataVector(data,columns);
		table = new JTable(customerModel);

        scrollPane = new JScrollPane(table);

        registerCustomerDialog = new RegisterCustomerDialog();
        updateCustomerDialog = new UpdateCustomerDialog();

		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		gc.gridx = 0;
		gc.gridy = 0;
		gc.fill = GridBagConstraints.BOTH;
		gc.insets = new Insets(10,10,10,10);
		add(lblSearchBy,gc);

		gc.gridx = 1;
		gc.gridy = 0;
		add(txtSearch,gc);

		gc.gridx = 2;
		gc.gridy = 0;
		add(btnRegister,gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(btnUpdate,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 4;
		add(scrollPane,gc);

		btnRegister.addActionListener(this);
		btnUpdate.addActionListener(this);
		txtSearch.getDocument().addDocumentListener(new SearchFilter(txtSearch,table));
		
	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnRegister)){
			registerCustomerDialog.setVisible(true);
		}
		else if(src.equals(btnUpdate)){
			int selectedRowIndex = table.getSelectedRow();
			selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
			if(selectedRowIndex == -1){
				JOptionPane.showMessageDialog(this,"Please Select a Row","Error",JOptionPane.ERROR_MESSAGE);
			}
			else{
				updateCustomerDialog.setSelectedRowIndex(selectedRowIndex);
				updateCustomerDialog.setVisible(true);
			}
		}
	}
}

