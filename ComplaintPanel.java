import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

class ComplaintPanel extends JPanel implements ActionListener{
    JLabel lblSearch;
    JTextField txtSearch;
    JButton btnRegisterComplaint,btnCloseComplaint;

    String[] columns = {"Complaint No","Customer No","Customer Name","Description","Date Opened"};

    JScrollPane scrollPane;
	JTable table;
	DefaultTableModel complaintModel;
    ComplaintDao complaintDao;
    
    RegisterComplaintDialog registerComplaintDialog;
    CloseComplaintDialog closeComplaintDialog;
    
    ComplaintPanel(){
        lblSearch = new JLabel("Search:",SwingConstants.RIGHT);
		txtSearch = new JTextField(20);

        btnRegisterComplaint = new JButton("Register Complaint");
        btnCloseComplaint = new JButton("Close Complaint");
        
        complaintModel = ComplaintViewModelFactory.getInstance();
        complaintDao = new ComplaintDao();
        
        Vector<Vector<String>> data = complaintDao.getAllOpenComplaints();
        Vector<String> columnVector = new Vector<String>(Arrays.asList(columns));
        complaintModel.setDataVector(data,columnVector);

		table = new JTable(complaintModel);
        scrollPane = new JScrollPane(table);
        
        registerComplaintDialog = new RegisterComplaintDialog();

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
		add(btnRegisterComplaint,gc);

		gc.gridx = 3;
		gc.gridy = 0;
		add(btnCloseComplaint,gc);

		gc.gridx = 0;
		gc.gridy = 1;
		gc.gridwidth = 4;
        add(scrollPane,gc);
        
        btnRegisterComplaint.addActionListener(this);
        btnCloseComplaint.addActionListener(this);

        txtSearch.getDocument().addDocumentListener(new SearchFilter(txtSearch,table));

    }

    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if(src.equals(btnRegisterComplaint)){
            registerComplaintDialog.setVisible(true);
        }
        else if(src.equals(btnCloseComplaint)){
            int selectedRowIndex = table.getSelectedRow();
            if(selectedRowIndex == -1){
                JOptionPane.showMessageDialog(this,"Please Select a Row","Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
            closeComplaintDialog = new CloseComplaintDialog(selectedRowIndex);
            closeComplaintDialog.setVisible(true);
        }
    }

}