import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class RegisterCustomerDialog extends JDialog implements ActionListener{
	JLabel lblName,lblAddress,lblContact,lblMonthlyCharge;
	JTextField txtName,txtAddress,txtContact,txtMonthlyCharge;
	JButton btnRegister,btnCancel;
	
	Connection conn;
	PreparedStatement st;

	RegisterCustomerDialog(){

		setLayout(new GridLayout(5,2,10,10));
		JPanel panel = (JPanel)getContentPane();
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		lblName = new JLabel("Name:",SwingConstants.CENTER);
		txtName = new JTextField(20);

		lblAddress = new JLabel("Address:",SwingConstants.CENTER);
		txtAddress = new JTextField(20);

		lblContact = new JLabel("Contact:",SwingConstants.CENTER);
		txtContact = new JTextField(20);

		lblMonthlyCharge = new JLabel("Monthly Charge:",SwingConstants.CENTER);
		txtMonthlyCharge = new JTextField(20);

		btnRegister = new JButton("Register");
		btnCancel = new JButton("Cancel");

		conn = Db.getConnection();
		try{
			st = conn.prepareStatement("insert into customer values(?,?,?,?,?,?)");
			System.out.println("Statement created");
		}catch(Exception e){}

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

		btnRegister.addActionListener(this);
		btnCancel.addActionListener(this);

		pack();

	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnRegister)){
			int customerNo = (int)(Math.random() * 1000);
			String customerName = txtName.getText();
			String address = txtAddress.getText();
			String contactNo = txtContact.getText();
			int monthlyCharge = Integer.parseInt(txtMonthlyCharge.getText());
			int dueAmount = 0;

			try{
				st.setInt(1,customerNo);
				st.setString(2,customerName);
				st.setString(3,address);
				st.setString(4,contactNo);
				st.setInt(5,monthlyCharge);
				st.setInt(6,dueAmount);
				st.executeUpdate();

				JOptionPane.showMessageDialog(this,"New Customer Registered");
			}catch(Exception exception){
				System.out.println("In exception");
				System.out.println(exception.getMessage());
			}
			
		}
		else if(src.equals(btnCancel)){
			setVisible(false);
			dispose();
		}

	}
}