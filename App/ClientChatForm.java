package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientChatForm extends JFrame 
{
	JTextField message;
	JTextArea HistoriqueChat;
	JScrollPane defil; 
	JLabel chatroom;
	JButton send;
	JPanel borderPanel,flowPanel1, flowPanel2;
	Client client;
	
	public ClientChatForm(Client x) 
	{
		client=x;
		initFrame();
		send.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String ch=message.getText();
					client.dataOut.writeUTF(ch);
					message.setText("");
					message.requestFocusInWindow();
				} catch (IOException e1) {
					System.err.println("ChatForm : probleme d'envoie");
				}
				
			}
		});
		
		message.addKeyListener(new KeyAdapter() {
			@Override
			//Pour envoyer le message par le button <Enter>
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					try {
						String ch=message.getText();
						client.dataOut.writeUTF(ch);
						message.setText("");
						message.requestFocusInWindow();
					} catch (IOException e1) {
						System.err.println("ChatForm : probleme d'envoie");
					}
				}
			}
		});
		
		
		setVisible(true);
	}
	
	public void initFrame()
	{
		this.setBounds(750,100,450,450);
		this.setTitle ("Connecté au Serveur[IP:"+client.ipServer+"Port: "+client.port+"]") ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		message = new JTextField(25);
		send = new JButton("Send");
		chatroom = new JLabel(client.clientName+" - Chat Room");
		chatroom.setFont(new java.awt.Font("Arial", 0, 22));
		chatroom.setForeground(new Color(255,95,0));

		HistoriqueChat = new JTextArea();
		HistoriqueChat.setBackground(Color.white);
		JScrollPane defil = new JScrollPane (HistoriqueChat) ;
		
		flowPanel1=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		flowPanel1.add(message);
		flowPanel1.add(send);
		flowPanel1.setBackground(new Color(10,10,50));
		flowPanel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		flowPanel2.add(chatroom);
		flowPanel2.setBackground(new Color(10,10,50));
		
		borderPanel = new JPanel(new BorderLayout ());
		borderPanel.add(defil);
		borderPanel.add(flowPanel1, "South");
		borderPanel.add(flowPanel2, "North");
		
		this.setContentPane(borderPanel);
	}		
}
