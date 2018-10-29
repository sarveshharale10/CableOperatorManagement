import javax.swing.*;

class ContainerFrame extends JFrame{
	JTabbedPane tabbedPane;
	JPanel customerPanel,collectionPanel,complaintPanel;
	ContainerFrame(){
		super("Cable Operator Management");
		for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){
			if ("Windows".equals(info.getName())) {
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
		collectionPanel = new CollectionPanel();
		complaintPanel = new ComplaintPanel();

		tabbedPane.add("Customer",customerPanel);
		tabbedPane.add("Collection",collectionPanel);
		tabbedPane.add("Complaints",complaintPanel);

		add(tabbedPane);

		pack();
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);


	}
}

class CableOperator{
	public static void main(String[] args) {
		new ContainerFrame();
	}
}