


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class ClientServer extends JFrame implements ActionListener , Runnable {
String name;
JLabel L1, L2;
JTextArea ta1;
JTextField t1;
JButton b1,b2;
JPanel p1 , inputPanel, centerPanel , buttonPanel;

Socket s;
BufferedReader br;
BufferedWriter bw;
Thread th1;
public ClientServer() {
	this.setVisible(true);
	this.setSize(300,400);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	name = JOptionPane.showInputDialog("Enter name ");
	p1 = new JPanel();
	inputPanel = new JPanel();
	centerPanel = new JPanel();
	buttonPanel = new JPanel();
	L1 = new JLabel(name);
	L2 = new JLabel(name);
	ta1 = new JTextArea(5,20);
	JScrollPane js = new JScrollPane(ta1);
	t1 = new JTextField(20);
	b1 = new JButton("Send");
	b2 = new JButton("Close");
	p1.setLayout(new BorderLayout());
	inputPanel.add(L1);
	inputPanel.add(L2);
	centerPanel.setLayout(new BorderLayout());
	centerPanel.add(L2);
	centerPanel.add(js,"Center");
	centerPanel.add(t1,"South");
	buttonPanel.add(b1);
	buttonPanel.add(b2);
	p1.add(inputPanel,"North");
	p1.add(centerPanel,"Center");
	p1.add(buttonPanel,"South");
	this.add(p1);
	p1.setBorder(BorderFactory.createTitledBorder("Client Server"));
	Font f = new Font("Serif",Font.BOLD,23);
	L2.setFont(f);
	L2.setForeground(Color.red);
	b1.addActionListener(this);
	b2.addActionListener(this);
	try {
		 s = new Socket("localhost",8888);
		
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		th1 = new Thread(this);
		th1.start();
		//bw.write("hello");
		bw.newLine();
		bw.flush();
		
		
	}
	catch(Exception ex) {
		
	}
		
	
	
	
	
	
	
}
public void run() {
	for(;;) {
		try {
			ta1.append(br.readLine()+"\n");
		}
		catch(Exception ex) {
			
		}
	}
}
public void actionPerformed(ActionEvent e) {
	if(e.getSource() == b1) {
		try {
			String msg =name+"-->"+t1.getText();
			bw.write(msg);
			
			bw.newLine();
			bw.flush();
			ta1.append(msg+"\n");
			t1.setText("");
			
			
			
		}
		catch(Exception ex) {
			
		}
	}
	else {
		System.exit(0);
		
	}
}
public static void main(String args[]) {
	new ClientServer();
	
}
}

