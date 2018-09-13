import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.shape.Box;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

//import com.sun.org.apache.bcel.internal.generic.NEW;


public class BackupDataBase extends JPanel{
	private JLabel lblMenuName = new JLabel("Backup DataBase Menu", SwingConstants.CENTER);
	
	private JPanel panelLebel = new JPanel();
	
	private JPanel panelInput = new JPanel();
	
	private JPanel panelButtons = new JPanel();
	
	private JPanel panelCenter=new JPanel();
	
	private JPanel paneltxtPanel = new JPanel();
	
	private JLabel lblLocation = new JLabel("Location: ");
	private JLabel lblRestoreLocation = new JLabel("Location: ");
	
	
	
	private JTextField txtBackupLocation = new JTextField();
	private JTextField txtRestoreLocation = new JTextField();
	
	
	private JButton btnbrowse = new JButton("Browse Path");
	private JButton btnbackup = new JButton("Back Up");
	
	private JButton btnbrowseRestore = new JButton("Browse Path");
	private JButton btnRestore = new JButton("Restore");
	
	
	String path = null;
	String fileName;
	
	public BackupDataBase() {
		setVisible(true);
		setLayout(new BorderLayout());
		
		panelCenter.setLayout(new BorderLayout());
		panelInput.setLayout(new BorderLayout());
		panelLebel.setLayout(new BoxLayout(panelLebel, BoxLayout.Y_AXIS));
		paneltxtPanel.setLayout(new BoxLayout(paneltxtPanel, BoxLayout.Y_AXIS));
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		txtBackupLocation.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtBackupLocation.getMinimumSize().height));
		txtRestoreLocation.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtRestoreLocation.getMinimumSize().height));
		txtBackupLocation.setEditable(false);
		txtRestoreLocation.setEditable(false);
		
		addToGUI();
		buttonListeners();
	}

	private void buttonListeners() {
		btnbrowse.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.showOpenDialog(null);
				
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				
				try {
					
					File f = fc.getSelectedFile();
					path = f.getAbsolutePath();
					path = path.replace('\\', '/');
					path = path + "_" + date + ".sql";
					txtBackupLocation.setText(path);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		btnbackup.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				String userName = "root";
				String password = "1234";
				String dbName = "project";
				
				String executeCmd = "C:/xampp/mysql/bin/mysqldump -u " + userName + " -p" + password + " --add-drop-database -B " + dbName + " -r " + path;
				Process runtimeProcess;
				
				try {
					runtimeProcess = Runtime.getRuntime().exec(new String [] {"cmd.exe", "/c", executeCmd});
					
					int processComplete = runtimeProcess.waitFor();
					
					if (processComplete != 0) {
						JOptionPane.showMessageDialog(null, "Backup successfully created");
					}else {
						JOptionPane.showMessageDialog(null, "Can't create backup file");
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	private void addToGUI() {
		
		panelLebel.add(lblLocation);
		panelLebel.add(javax.swing.Box.createRigidArea(new Dimension(0, 100)));
		panelLebel.add(lblRestoreLocation);
		
		
	
		paneltxtPanel.add(txtBackupLocation);
		paneltxtPanel.add(javax.swing.Box.createRigidArea(new Dimension(0, 110)));
		paneltxtPanel.add(txtRestoreLocation);
		
		
		
		lblLocation.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblRestoreLocation.setFont(new Font("Times New Roman", Font.BOLD, 22));
		
		
		panelButtons.add(btnbrowse);
		panelButtons.add(btnbackup);
		panelButtons.add(javax.swing.Box.createRigidArea(new Dimension(0, 80)));
		panelButtons.add(btnbrowseRestore);
		panelButtons.add(btnRestore);
		
		
		
		panelInput.add(panelLebel, BorderLayout.WEST);
		
		
		panelInput.add(paneltxtPanel, BorderLayout.CENTER);
		
		
		panelCenter.add(panelInput, BorderLayout.CENTER);
		
		
		panelCenter.add(panelButtons, BorderLayout.EAST);
		
		add(lblMenuName, BorderLayout.PAGE_START);
		add(panelCenter,BorderLayout.CENTER);
		
		
		
	}
	
	
}
