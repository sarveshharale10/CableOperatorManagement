import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class RegisterCustomerDialog extends JDialog implements ActionListener{
	JLabel lblName,lblAddress,lblContact,lblMonthlyCharge;
	JTextField txtName,txtAddress,txtContact,txtMonthlyCharge;
	JButton btnRegister,btnCancel;

	RegisterCustomerDialog(){

		setLayout(new GridLayout(5,2));

		lblName = new JLabel("Name:");
		txtName = new JTextField(20);

		lblAddress = new JLabel("Address:");
		txtAddress = new JTextField(20);

		lblContact = new JLabel("Contact");
		txtContact = new JTextField(20);

		lblMonthlyCharge = new JLabel("Monthly Charge:");
		txtMonthlyCharge = new JTextField(20);

		btnRegister = new JButton("Register");
		btnCancel = new JButton("Cancel");

		add(lblName);
		add(txtName);
		add(lblAddress);
		add(txtAddress);
		add(lblContact);
		add(txtContact);
		add(lblMonthlyCharge);
		add(txtMonthlyCharge);
		add(btnRegister);
		add(btnCancel);

		btnCancel.addActionListener(this);

		pack();

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnRegister)){

		}
		else if(src.equals(btnCancel)){
			setVisible(false);
			dispose();
		}

	}
}