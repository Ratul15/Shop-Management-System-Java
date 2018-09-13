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


public class AddCustomer extends JPanel{
		
		private JLabel lblMenuName = new JLabel("Add Customer Menu", SwingConstants.CENTER);
		
		private JPanel panelLebel = new JPanel();
		private JPanel panelTextBox = new JPanel();
		private JPanel panelButtons = new JPanel();
		
		private JLabel lblcustomerName = new JLabel("Customer Name: ");
		private JLabel lblcustomeraddress = new JLabel("Customer Address: ");
		private JLabel lblcustomercontactnumber = new JLabel("Customer Contact Number: ");
		
		private JTextField txtcustomerName = new JTextField();
		private JTextField txtcustomeraddress = new JTextField();
		private JTextField txtcustomercontactnumber = new JTextField();
		
		private JButton btnAdd = new JButton("Add");
		private JButton btnReset = new JButton("Reset");
		
		
		
		public AddCustomer() {
			setVisible(true);
			setLayout(new BorderLayout());
			panelLebel.setBackground(Color.YELLOW);
			panelTextBox.setBackground(Color.GRAY);
			
			panelLebel.setLayout(new BoxLayout(panelLebel, BoxLayout.Y_AXIS));
			panelTextBox.setLayout(new BoxLayout(panelTextBox, BoxLayout.Y_AXIS));
			panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
			
			txtcustomerName.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtcustomerName.getMinimumSize().height));
			txtcustomeraddress.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtcustomeraddress.getMinimumSize().height));
			txtcustomercontactnumber.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtcustomercontactnumber.getMinimumSize().height));
			
			addToGUI();
			btnListeners();
		}

		private void btnListeners() {
			btnAdd.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					
					if (txtcustomerName.getText().equals("") || txtcustomeraddress.getText().equals("") || txtcustomercontactnumber.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please fill all the fields.");
						return;
					}
					
					String customername=txtcustomerName.getText();
					String customeraddress=txtcustomeraddress.getText();
					String customercontactnumber = txtcustomercontactnumber.getText();
					
					String query = "INSERT INTO `customer` (`name`, `address`, `Contact_number`) VALUES ('" + customername + "', '" + customeraddress + "', '" + customercontactnumber +  "')";     
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
			            
			            JOptionPane.showMessageDialog(null, "Customer added.");
			        }
				}
				}
			);
			
			btnReset.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					txtcustomerName.setText("");			
					txtcustomeraddress.setText("");
					txtcustomercontactnumber.setText("");
				}
			});
		}

		private void addToGUI() {
			
			panelLebel.add(lblcustomerName);
			panelLebel.add(Box.createRigidArea(new Dimension(0, 8)));
			panelLebel.add(lblcustomeraddress);
			panelLebel.add(Box.createRigidArea(new Dimension(0, 7)));
			panelLebel.add(lblcustomercontactnumber);
			
			panelTextBox.add(txtcustomerName);
			panelTextBox.add(txtcustomeraddress);
			panelTextBox.add(txtcustomercontactnumber);
			
			panelButtons.add(btnReset);
			panelButtons.add(btnAdd);
			
			add(lblMenuName, BorderLayout.PAGE_START);
			add(panelLebel, BorderLayout.WEST);
			add(panelTextBox, BorderLayout.CENTER);
			add(panelButtons, BorderLayout.EAST);
		}
		
	}


