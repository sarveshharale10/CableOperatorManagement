import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

class UpdateCustomerDialog extends JDialog implements ActionListener{
	JLabel lblCustomerNo,lblName,lblAddress,lblContact,lblMonthlyCharge;
	JTextField txtCustomerNo,txtName,txtAddress,txtContact,txtMonthlyCharge;
	JButton btnUpdate,btnCancel;

	int selectedRowIndex;
	DefaultTableModel customerModel;
	CustomerDao customerDao;

	UpdateCustomerDialog(){
		setTitle("Update Customer Details");
		setLayout(new GridLayout(6,2,10,10));
		JPanel panel = (JPanel)getContentPane();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		lblCustomerNo = new JLabel("Customer No:",SwingConstants.CENTER);
		txtCustomerNo = new JTextField(20);
		txtCustomerNo.setEditable(false);

		lblName = new JLabel("Name:",SwingConstants.CENTER);
		txtName = new JTextField(20);

		lblAddress = new JLabel("Address:",SwingConstants.CENTER);
		txtAddress = new JTextField(20);

		lblContact = new JLabel("Contact:",SwingConstants.CENTER);
		txtContact = new JTextField(20);

		lblMonthlyCharge = new JLabel("Monthly Charge:",SwingConstants.CENTER);
		txtMonthlyCharge = new JTextField(20);

		btnUpdate = new JButton("Update");
		btnCancel = new JButton("Cancel");

		customerModel = CustomerViewModelFactory.getInstance();
		customerDao = new CustomerDao();

		add(lblCustomerNo);
		add(txtCustomerNo);
		add(lblName);
		add(txtName);
		add(lblAddress);
		add(txtAddress);
		add(lblContact);
		add(txtContact);
		add(lblMonthlyCharge);
		add(txtMonthlyCharge);
		add(btnUpdate);
		add(btnCancel);

		btnUpdate.addActionListener(this);
		btnCancel.addActionListener(this);

		pack();

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnUpdate)){
			String customerName = txtName.getText();
			String address = txtAddress.getText();
			String contactNo = txtContact.getText();
			int monthlyCharge = Integer.parseInt(txtMonthlyCharge.getText());
			int customerNo = Integer.parseInt(txtCustomerNo.getText());

			try{
				customerDao.update(customerNo, customerName, address, contactNo, monthlyCharge);

				customerModel.removeRow(selectedRowIndex);
				customerModel.insertRow(selectedRowIndex,new String[]{new Integer(customerNo).toString(),customerName,address,contactNo,new Integer(monthlyCharge).toString()});
			
				JOptionPane.showMessageDialog(this,"Customer Details Updated");
			}catch(Exception exception){
				System.out.println(exception.getMessage());
			}
		}
		else if(src.equals(btnCancel)){
			setVisible(false);
			dispose();
		}
	}

	void setSelectedRowIndex(int index){
		selectedRowIndex = index;
		populateFields();
	}

	void populateFields(){
		txtCustomerNo.setText((String)customerModel.getValueAt(selectedRowIndex,0));
		txtName.setText((String)customerModel.getValueAt(selectedRowIndex,1));
		txtAddress.setText((String)customerModel.getValueAt(selectedRowIndex,2));
		txtContact.setText((String)customerModel.getValueAt(selectedRowIndex,3));
		txtMonthlyCharge.setText((String)customerModel.getValueAt(selectedRowIndex,4));
	}

}