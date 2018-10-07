import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class CustomerPanel extends JPanel implements ActionListener{
	JLabel lblSearchBy;
	JTextField txtSearch;
	JComboBox cbField;
	String[] cbFields = {"Customer No","Customer Name","Address","Contact No","Monthly Charge"};
	JButton btnSearch,btnRegister,btnUpdate;
	JScrollPane scrollPane;
	JTable table;

	RegisterCustomerDialog registerCustomerDialog;

	CustomerPanel(){
		lblSearchBy = new JLabel("Search By:");
		txtSearch = new JTextField(20);
		cbField = new JComboBox(cbFields);
		btnSearch = new JButton("Search");
		btnRegister = new JButton("Register");
		btnUpdate = new JButton("Update");
		String[][] data = null;
		table = new JTable();
        table.setModel(new DefaultTableModel(data,cbFields){
            public boolean isCellEditable(int row,int column){
                return false;
            }
        });
        scrollPane = new JScrollPane(table);

        registerCustomerDialog = new RegisterCustomerDialog();

		setLayout(new FlowLayout());

		add(lblSearchBy);
		add(cbField);
		add(txtSearch);
		add(btnSearch);
		add(btnRegister);
		add(btnUpdate);
		add(scrollPane);

		btnRegister.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e){
		Object src = e.getSource();

		if(src.equals(btnRegister)){
			registerCustomerDialog.setVisible(true);
		}
	}
}

