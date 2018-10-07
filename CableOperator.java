import javax.swing.*;

class ContainerFrame extends JFrame{
	JTabbedPane tabbedPane;
	JPanel customerPanel,collection,complaint;
	ContainerFrame(){
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			if ("Nimbus".equals(info.getName())) {
				try{
	            	UIManager.setLookAndFeel(info.getClassName());
	            	break;
				}catch(Exception e){
					System.err.println("Error: "+e.toString());
				}
        	}
		}
		JTabbedPane tabbedPane = new JTabbedPane();

		customerPanel = new CustomerPanel();
		collection = new JPanel();
		collection.add(new JLabel("Collection Tab"));
		complaint = new JPanel();
		complaint.add(new JLabel("Complaint Tab"));

		tabbedPane.add("Customer",customerPanel);
		tabbedPane.add("Collection",collection);
		tabbedPane.add("Complaints",complaint);

		add(tabbedPane);

		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);


	}
}

class CableOperator{
	public static void main(String[] args) {
		new ContainerFrame();
	}
}