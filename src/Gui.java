import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import query.*;

public class Gui extends JFrame implements ActionListener {
	
	JTextField hostname = null;
	JTextField hostport = null;
	JLabel status = null;
	
	public Gui() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(220, 240);
		this.setTitle("Minecraft Server status check");
		this.setLocationRelativeTo(this.getRootPane());
		this.setLayout(null);
		//this.setResizable(false);
		
		JPanel content = new JPanel(null);
		content.setBounds(0, 0, 220, 240);
		
		this.status = new JLabel("Unknown");
		this.status.setFont(new Font("Oswald", Font.PLAIN, 24));
		this.status.setBounds(20, 20, 160, 30);
		
		this.hostname = new JTextField();
		this.hostname.setBounds(20, 70, 160, 30);
		
		this.hostport = new JTextField();
		this.hostport.setBounds(20, 110, 160, 30);
		
		JButton check = new JButton("Check status");
		check.setBounds(40, 150, 120, 30);
		check.addActionListener(this);
		
		content.add(this.status);
		content.add(this.hostname);
		content.add(this.hostport);
		content.add(check);
		this.getContentPane().add(content);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		Integer port = null;
		try {
			port = Integer.parseInt(this.hostport.getText());
		} catch (NumberFormatException nfe) {
			 port = null;
		}
		
		if (port == null) {
			JOptionPane.showMessageDialog(null, "Port number should be an integer", "Invalid input", JOptionPane.ERROR_MESSAGE);
		} else {
			MCQuery query = new MCQuery(this.hostname.getText(), port);
			try {
				//System.out.println(query.stats().toString());
				this.status.setText("Online");
			} catch (NullPointerException npe) {
				this.status.setText("Offline");
			}
		}
	}
}
