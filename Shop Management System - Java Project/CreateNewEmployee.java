import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class CreateNewEmployee extends JPanel{
	private JLabel lblMenuName = new JLabel("Create New Employee Menu", SwingConstants.CENTER);
	private JPanel panelCenter=new JPanel();
	private JPanel panelLabels = new JPanel();
	private JPanel paneltxtPanel = new JPanel();
	private JPanel panelButtons = new JPanel();
	
	private JLabel lblusername = new JLabel("UserName");
	private JLabel lblpassword = new JLabel("Password");
	private JLabel lblname = new JLabel("Name");
	private JLabel lbladdress = new JLabel("Address");
	private JLabel lblcontactnumber = new JLabel("Contact Number");
	
	private JTextField txtSearchusername = new JTextField();
	private JTextField txtSearchpassword = new JTextField();
	private JTextField txtSearchname = new JTextField();
	private JTextField txtSearchaddress = new JTextField();
	private JTextField txtSearchcontactnumber = new JTextField();
	
	private JButton btnaddemployee = new JButton("Add Employee");
	private JButton btnresetform = new JButton("Reset Form");
	
	
	public CreateNewEmployee() {
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelCenter.setLayout(new BorderLayout());
		
		panelLabels.setLayout(new BoxLayout(panelLabels, BoxLayout.Y_AXIS));
		paneltxtPanel.setLayout(new BoxLayout(paneltxtPanel, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		txtSearchusername.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSearchusername.getMinimumSize().height+10));
		txtSearchpassword.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSearchpassword.getMinimumSize().height+10));
		txtSearchname.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSearchname.getMinimumSize().height+10));
		txtSearchaddress.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSearchaddress.getMinimumSize().height+10));
		txtSearchcontactnumber.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtSearchcontactnumber.getMinimumSize().height+10));
		
		addToGUI();
		buttonListeners();
	}

	private void buttonListeners() {
		btnresetform.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				txtSearchaddress.setText("");
				txtSearchcontactnumber.setText("");
				txtSearchname.setText("");
				txtSearchpassword.setText("");
				txtSearchusername.setText("");
			}
		});
		
		btnaddemployee.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (txtSearchaddress.getText().equals("") || txtSearchcontactnumber.getText().equals("") || txtSearchname.getText().equals("")|| txtSearchpassword.getText().equals("")|| txtSearchusername.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields.");
					return;
				}
				
				String userName=txtSearchusername.getText();
				String password=txtSearchpassword.getText();
				String name = txtSearchname.getText();
				String address = txtSearchaddress.getText();
				String contact = txtSearchcontactnumber.getText();
				String accType = "Employee";
				
				String query = "INSERT INTO `person` (`username`, `password`, `name`, `address`, `contact_number`, `account_type`) VALUES ('" + userName + "', '" + password +"', '" + name + "', '"+ address +"', '"+contact+"', '"+accType+"')";    
		        Connection con=null;//for connection
		        Statement st = null;//for query execution
		        try
				{
					Class.forName("com.mysql.jdbc.Driver");//load driver
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","1234");
					st = con.createStatement();//create statement
					st.executeUpdate(query);//getting result
							
				}
		        catch(Exception ex)
				{
					System.out.println("Exception : " +ex.getMessage());
		        }
		        finally
				{
		            try
		            {
		                if(st!=null)
							st.close();

		                if(con!=null)
							con.close();
		            }
		            catch(Exception ex){}
		            
		            JOptionPane.showMessageDialog(null, "New Employee added.");
		        }
			}
		});
	}

	private void addToGUI() {
		
		panelLabels.add(lblusername);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 10)));
		panelLabels.add(lblpassword);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 10)));
		panelLabels.add(lblname);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 10)));
		panelLabels.add(lbladdress);
		panelLabels.add(Box.createRigidArea(new Dimension(0, 10)));
		panelLabels.add(lblcontactnumber);
		
		lblusername.setFont(new Font("Times New Roamn", Font.BOLD, 20));
		lblpassword.setFont(new Font("Times New Roamn", Font.BOLD, 20));
		lblname.setFont(new Font("Times New Roamn", Font.BOLD, 20));
		lbladdress.setFont(new Font("Times New Roamn", Font.BOLD, 20));
		lblcontactnumber.setFont(new Font("Times New Roamn", Font.BOLD, 20));
		
		paneltxtPanel.add(txtSearchusername);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paneltxtPanel.add(txtSearchpassword);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paneltxtPanel.add(txtSearchname);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paneltxtPanel.add(txtSearchaddress);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		paneltxtPanel.add(txtSearchcontactnumber);
		paneltxtPanel.add(Box.createRigidArea(new Dimension(0, 10)));
		
		panelButtons.add(btnaddemployee);
		panelButtons.add(btnresetform);
		
		
		
		panelCenter.add(panelLabels, BorderLayout.WEST);
		panelCenter.add(paneltxtPanel, BorderLayout.CENTER);
		panelCenter.add(panelButtons, BorderLayout.EAST);
		
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter, BorderLayout.CENTER);
	}
}
