import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.ThreadPoolExecutor;

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
		this.setResizable(false);
		
		JPanel content = new JPanel(null);
		content.setBounds(0, 0, 220, 240);
		
		this.status = new JLabel("Unknown",SwingConstants.CENTER);
		this.status.setFont(new Font("Oswald", Font.PLAIN, 24));
		this.status.setBounds(20, 20, 180, 30);
		
		JLabel hostnameLabel = new JLabel("Hostname:");
		hostnameLabel.setBounds(20, 70, 60, 30);
		this.hostname = new JTextField();
		this.hostname.setBounds(100, 70, 100, 30);
		
		JLabel hostportLabel = new JLabel("Port:");
		hostportLabel.setBounds(20, 110, 60, 30);
		this.hostport = new JTextField("19132");
		this.hostport.setBounds(100, 110, 100, 30);
		
		JButton check = new JButton("Check status");
		check.setBounds(50, 150, 120, 30);
		check.addActionListener(this);
		
		content.add(this.status);
		content.add(hostnameLabel);
		content.add(this.hostname);
		content.add(hostportLabel);
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
			final Integer finalPort = port;
			new Thread() {
				public void run() {
					MCQuery query = new MCQuery(hostname.getText(), finalPort);
					try {
						System.out.println(query.stats().toString());
						status.setText("Online");
					} catch (NullPointerException npe) {
						status.setText("Offline");
					}
				}
			}.start();
		}
	}
}
