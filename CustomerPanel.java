import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import java.util.*;

class CustomerPanel extends JPanel implements ActionListener{
	JLabel lblSearchBy;
	JTextField txtSearch;
	JComboBox cbField;
	String[] cbFields = {"Customer No","Customer Name","Address","Contact No","Monthly Charge"};
	JButton btnSearch,btnRegister,btnUpdate;
	JScrollPane scrollPane;
	
	JTable table;
	DefaultTableModel customerModel;

	Connection conn;
	Statement st;

	RegisterCustomerDialog registerCustomerDialog;
	UpdateCustomerDialog updateCustomerDialog;

	CustomerPanel(){
		lblSearchBy = new JLabel("Search By:");
		txtSearch = new JTextField(20);
		cbField = new JComboBox(cbFields);
		btnSearch = new JButton("Search");
		btnRegister = new JButton("Register");
		btnUpdate = new JButton("Update");

		conn = Db.getConnection();
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		try{
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from customer");

			while(rs.next()){
				Vector<String> row = new Vector<String>();
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				data.add(row);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		Vector<String> columns = new Vector<String>(Arrays.asList(cbFields));

		customerModel = CustomerTableModelFactory.getInstance();
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
		add(cbField,gc);

		gc.gridx = 2;
		gc.gridy = 0;
		add(txtSearch,gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(btnSearch,gc);

		gc.gridx = 4;
		gc.gridy = 0;
		add(btnRegister,gc);

		gc.gridx = 5;
		gc.gridy = 0;
		add(btnUpdate,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 6;
		add(scrollPane,gc);

		btnRegister.addActionListener(this);
		btnUpdate.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnRegister)){
			registerCustomerDialog.setVisible(true);
		}
		else if(src.equals(btnUpdate)){
			int selectedRowIndex = table.getSelectedRow();
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

