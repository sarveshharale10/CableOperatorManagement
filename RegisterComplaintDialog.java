import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Date;

class RegisterComplaintDialog extends JDialog implements ActionListener{
    JLabel lblCustomerNo,lblDescription;
    JTextField txtCustomerNo;
    JTextArea txtAreaDescription;
    JButton btnRegister,btnCancel;
    
    DefaultTableModel complaintModel;
    ComplaintDao complaintDao;
    CustomerDao customerDao;

    RegisterComplaintDialog(){
        setTitle("Register Complaint");

        lblCustomerNo = new JLabel("Customer No:",SwingConstants.RIGHT);
        lblDescription = new JLabel("Description:",SwingConstants.LEFT);

        txtCustomerNo = new JTextField(10);
        txtAreaDescription = new JTextArea(10,40);

        btnRegister = new JButton("Register");
        btnCancel = new JButton("Cancel");

        complaintModel = ComplaintViewModelFactory.getInstance();
        complaintDao = new ComplaintDao();
        customerDao = new CustomerDao();

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        
        gc.gridx = 0;
		gc.gridy = 0;
		gc.insets = new Insets(10,10,10,10);
        add(lblCustomerNo,gc);

        gc.gridx = 1;
        gc.gridy = 0;
        add(txtCustomerNo);
        
        gc.gridx = 0;
        gc.gridy = 1;
        add(lblDescription,gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 2;
        add(txtAreaDescription,gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 1;
        add(btnRegister,gc);

        gc.gridx = 1;
        gc.gridy = 3;
        add(btnCancel,gc);

        btnRegister.addActionListener(this);
        btnCancel.addActionListener(this);

        pack();
    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if(src.equals(btnRegister)){
            int complaintNo = (int)(Math.random() * 1000);
            int customerNo = Integer.parseInt(txtCustomerNo.getText());
            String customerName = customerDao.getByCustomerNo(customerNo).get(1);
            String description = txtAreaDescription.getText();
            Date dateOpened = new Date(System.currentTimeMillis());
            complaintDao.add(complaintNo,customerNo,customerName,description,dateOpened);

            complaintModel.addRow(new Object[]{Integer.toString(complaintNo),Integer.toString(customerNo),customerName,description,dateOpened.toString()});

            JOptionPane.showMessageDialog(this,"Complaint Registered");
        }
        else if(src.equals(btnCancel)){
            setVisible(false);
            dispose();
        }
    }
}