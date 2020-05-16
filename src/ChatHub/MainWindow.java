package ChatHub;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	JPanel loginPanel;
	JPanel userPanel;
	JPanel container;
	JPanel labelContainer;
	JPanel windowPanel;
	JPanel userWindowPanel;
	JPanel sendMessagePanel;
	JPanel myMessagesPanel;
	
	JTextField userNameInput;
	JPasswordField passwordInput;
	JTextField receiverNameInput;
	JTextArea messageInput;
	
	JButton loginButton;
	JButton sendMessage;
	JButton myMessages;
	JButton sendBtn;
	JButton logout;
	
	int loginWidth=500;
	int loginHeight=500;
	int userWidth=800;
	int userHeight=600;
	boolean isUser=false;
	String userFullName;
	
	
	MainWindow(){
		this.setTitle("Chat Hub");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage("resource/icon.png"));
		this.setBounds(0, 0, loginWidth, loginHeight);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		windowPanel=new JPanel(new CardLayout());
		
		mainPanel=new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(0,0,25));
		
		loginPanel=new JPanel(new GridLayout(3,1,60,60));
		loginPanel.setBackground(new Color(0,0,25));
		
		userPanel=new JPanel(new BorderLayout());
		userPanel.setBackground(new Color(0,0,25));
		
		loginButton=new JButton("Login");loginButton.addActionListener(this);
		loginButton.setPreferredSize(new Dimension(150,50));
		
		sendMessage=new JButton("Send Message");sendMessage.addActionListener(this);
		sendMessage.setPreferredSize(new Dimension(120,40));
		
		myMessages=new JButton("My Messages");myMessages.addActionListener(this);
		myMessages.setPreferredSize(new Dimension(120,40));
		
		logout=new JButton("Logout");logout.addActionListener(this);
		logout.setPreferredSize(new Dimension(120,40));
		
		sendBtn=new JButton("Send");sendBtn.addActionListener(this);
		sendBtn.setPreferredSize(new Dimension(100,40));
		
		JPanel headerPanel=new JPanel(new FlowLayout(1,20,20));
		JLabel title=new JLabel("ChatHub");
		title.setFont(new Font("Arial",Font.BOLD, 28));
		title.setForeground(new Color(255,95,0));
		headerPanel.add(title);
		headerPanel.setBackground(new Color(0,0,25));
		
		JPanel footerPanel=new JPanel(new FlowLayout(1,20,20));
		JLabel text=new JLabel("Copyright © Ossama Ismaili");
		text.setFont(new Font("Arial",Font.ITALIC, 16));
		text.setForeground(new Color(255,255,255));
		footerPanel.add(text);
		footerPanel.setBackground(new Color(0,0,25));
		
		this.loginPanelContent();
		
		this.userPanelContent();
		
		windowPanel.add(loginPanel,"login");
		windowPanel.add(userPanel,"user");
		
		mainPanel.add("North",headerPanel);
		mainPanel.add("South",footerPanel);
		mainPanel.add("Center",windowPanel);
		
		this.setContentPane(mainPanel);
		this.setVisible(true);
	}
	
	public void loginPanelContent() {
		userNameInput=new JTextField();
		userNameInput.setFont(new Font("Arial",0, 20));
		userNameInput.setBorder(BorderFactory.createCompoundBorder(userNameInput.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		passwordInput=new JPasswordField();
		passwordInput.setFont(new Font("Arial",0, 20));
		passwordInput.setBorder(BorderFactory.createCompoundBorder(passwordInput.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		container=new JPanel(new GridLayout(1,2));
		container.setBackground(new Color(0,0,25));
		
		JLabel label1=new JLabel("Username");
		label1.setFont(new Font("Arial",1, 18));
		label1.setForeground(new Color(255,255,255));
		labelContainer=new JPanel(new FlowLayout(0,20,20));
		labelContainer.setBackground(new Color(0,0,25));
		labelContainer.add(label1);
		container.add(labelContainer);
		container.add(userNameInput);
		
		loginPanel.add(container);
		
		container=new JPanel(new GridLayout(1,2));
		container.setBackground(new Color(0,0,25));
		
		JLabel label2=new JLabel("Password");
		label2.setFont(new Font("Arial",1, 18));
		label2.setForeground(new Color(255,255,255));
		labelContainer=new JPanel(new FlowLayout(0,20,20));
		labelContainer.setBackground(new Color(0,0,25));
		labelContainer.add(label2);
		container.add(labelContainer);
		container.add(passwordInput);
		
		loginPanel.add(container);
		
		container=new JPanel(new FlowLayout(1,20,20));
		container.setBackground(new Color(0,0,25));
		container.add(loginButton);
		
		loginPanel.add(container);
	}
	
	public void userPanelContent() {
		userWindowPanel=new JPanel(new CardLayout());
		
		JPanel headerPanel=new JPanel(new FlowLayout(0,20,20));
		headerPanel.add(sendMessage);
		headerPanel.add(myMessages);
		headerPanel.add(logout);
		headerPanel.setBackground(new Color(45,45,75));
		
		sendMessagePanel=new JPanel(new GridBagLayout());
		sendMessagePanel.setBackground(new Color(0,0,25));
		
		JLabel label1=new JLabel("To",SwingConstants.CENTER);
		label1.setFont(new Font("Arial",1, 18));
		label1.setForeground(new Color(255,255,255));
		
		receiverNameInput=new JTextField(10);
		receiverNameInput.setFont(new Font("Arial",0, 18));
		receiverNameInput.setBorder(BorderFactory.createCompoundBorder(receiverNameInput.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		JLabel label2=new JLabel("Message",SwingConstants.CENTER);
		label2.setFont(new Font("Arial",1, 18));
		label2.setForeground(new Color(255,255,255));

		messageInput=new JTextArea(10,5);
		messageInput.setFont(new Font("Arial",0, 16));
		messageInput.setBorder(BorderFactory.createCompoundBorder(messageInput.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		GridBagConstraints container = new GridBagConstraints();
		container.gridx = 0;
		container.gridy = 0;
		container.weightx=10;
		container.weighty=5;
		sendMessagePanel.add(label1,container);
		container.gridy++;
		sendMessagePanel.add(label2,container);
		container.anchor = GridBagConstraints.EAST;
		container.fill = GridBagConstraints.HORIZONTAL;
		container.gridx++;
		container.gridy = 0;
		sendMessagePanel.add(receiverNameInput,container);
		container.gridy++;
		sendMessagePanel.add(messageInput,container);
		container.anchor = GridBagConstraints.WEST;
		container.fill = GridBagConstraints.NONE;
		container.gridy++;
		sendMessagePanel.add(sendBtn, container);
		
		myMessagesPanel=new JPanel(new FlowLayout(1,100, 10));
		myMessagesPanel.setBackground(new Color(0,0,25));
		
		JScrollPane scrollPane = new JScrollPane(myMessagesPanel);    
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		userWindowPanel.add(sendMessagePanel,"sendMessage");
		userWindowPanel.add(scrollPane,"myMessages");
		
		userPanel.add("North",headerPanel);
		userPanel.add("Center",userWindowPanel);
	}

	public void resizeWindow(int width,int height) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - width) / 2);
	    int y = (int) ((dimension.getHeight() - height) / 2);
	    this.setBounds(x,y,width,height);
	}
	
	public void showUserMessages(String username) {
		MessageManager manager=new MessageManager();
		myMessagesPanel.removeAll();
		try {
			ArrayList<String[]> messages=manager.getMessages(username);
			if(messages.size()>0) {
				for(String[] msg : messages) {
					JPanel messagePanel=new JPanel(new GridBagLayout());
					messagePanel.setBackground(new Color(0,0,25));
					
					JLabel label1=new JLabel("From");
					label1.setFont(new Font("Arial",1, 18));
					label1.setForeground(new Color(255,255,255));
					
					JLabel label2=new JLabel("Message");
					label2.setFont(new Font("Arial",1, 18));
					label2.setForeground(new Color(255,255,255));
					
					JTextField rec=new JTextField(msg[0],15);
					rec.setEditable(false);
					rec.setFont(new Font("Arial",1, 16));
					rec.setForeground(new Color(0,0,0));
					
					JTextArea myMsg=new JTextArea(msg[1],10,10);
					myMsg.setEditable(false);
					myMsg.setFont(new Font("Arial",1, 16));
					myMsg.setForeground(new Color(0,0,0));
					
					GridBagConstraints container = new GridBagConstraints();
					container.gridx = 0;
					container.gridy = 0;
					messagePanel.add(label1,container);
					container.gridy++;
					messagePanel.add(label2,container);
					container.anchor = GridBagConstraints.EAST;
					container.fill = GridBagConstraints.HORIZONTAL;
					container.gridx++;
					container.gridy = 0;
					messagePanel.add(rec,container);
					container.gridy++;
					messagePanel.add(myMsg,container);
					
					myMessagesPanel.add(messagePanel);
				}
			}
			else {
				JLabel label=new JLabel("Your inbox is empty");
				label.setFont(new Font("Arial",1, 18));
				label.setForeground(new Color(180,180,180));
				myMessagesPanel.add(label);
			}
		} catch (SQLException e) {
			System.err.println("Error : Database Error");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CardLayout cl=(CardLayout)windowPanel.getLayout();
		CardLayout userCl=(CardLayout)userWindowPanel.getLayout();
		if(e.getSource()==loginButton) {
			if(userNameInput!=null && passwordInput!=null) {
				User user=new User();
				try {
					if(user.testUser(userNameInput.getText(),String.valueOf(passwordInput.getPassword()))){
						isUser=true;
						userFullName=user.getFullName(userNameInput.getText());
						this.showUserMessages(userNameInput.getText());
						this.resizeWindow(userWidth,userHeight);
						System.out.println(userFullName);
						cl.show(windowPanel, "user");
						System.out.println("Connected");
					}
					else {
						JOptionPane.showMessageDialog(null, "Username or Password is wrong", "Error",JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					System.err.println("Error : Database Error");
				}
			}
		}
		
		if(e.getSource()==sendMessage && isUser) {
			userCl.show(userWindowPanel, "sendMessage");
		}
		
		if(e.getSource()==myMessages && isUser) {
			userCl.show(userWindowPanel, "myMessages");
		}
		
		if(e.getSource()==logout && isUser) {
			isUser=false;
			this.resizeWindow(loginWidth,loginHeight);
			cl.show(windowPanel, "login");
		}
		
		
		if(e.getSource()==sendBtn && isUser) {
			MessageManager manager=new MessageManager();
			
			try {
				if(manager.testReciver(receiverNameInput.getText())) {
					manager.insertMessage(userNameInput.getText(), receiverNameInput.getText(), messageInput.getText());
					JOptionPane.showMessageDialog(null, "Message Sent", "Error",JOptionPane.INFORMATION_MESSAGE);
					receiverNameInput.setText("");
					messageInput.setText("");
				}
				else {
					JOptionPane.showMessageDialog(null, "This receiver doesn't exist", "Error",JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				System.err.println("Error : Database Error");
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		new MainWindow();
	}
}
