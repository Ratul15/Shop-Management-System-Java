import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;


public class ManageEmployee extends JPanel{
	private JTabbedPane tabbedPane = new JTabbedPane();
	
	private JLabel lblMenuName = new JLabel("Manage Employee Menu", SwingConstants.CENTER);
	
	private CreateNewEmployee createNewEmployee = new CreateNewEmployee();
	private ManageExistingEmployee manageExistingEmployee = new ManageExistingEmployee();
	
	public ManageEmployee() {
		setVisible(true);
		setLayout(new BorderLayout());
		tabbedPane.setTabPlacement(SwingConstants.TOP);
		
		addToGui();
	}

	private void addToGui() {
		tabbedPane.addTab("Create New Employee", createNewEmployee);
		tabbedPane.addTab("Manage Existing Employees", manageExistingEmployee);
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(tabbedPane, BorderLayout.CENTER);
	}
}
