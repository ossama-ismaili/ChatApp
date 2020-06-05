package App;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClientConnexionForm extends JFrame
{
	JLabel labelAdr,labelUser,labelPort,labelNorth, labelSouth;
	JTextField textAdr,textUser, textPort;
	JButton valider, annuler;
	JPanel borderPanel,gridPanel;
	
	String ip;
	int port;	
	Client client;
	
	public ClientConnexionForm(Client x)
	{
		client=x;
		this.setTitle ("Login") ;
		this.setSize(450, 275);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
		labelNorth= new JLabel("Chat Room",SwingConstants.CENTER);
		labelNorth.setFont(new Font("Arial",Font.BOLD, 16));
		labelNorth.setForeground(new Color(255,95,0));
		labelSouth= new JLabel("Réalisé par : Ossama Ismaili 17/05/2020",SwingConstants.CENTER);
		labelSouth.setFont(new Font("Arial",Font.ITALIC, 12));
		labelSouth.setForeground(Color.white);
		labelAdr  = new JLabel("Adresse IP du serveur : ", SwingConstants.RIGHT);
		labelAdr.setForeground(Color.white);
		labelUser = new JLabel("Nom Utilisateur :", SwingConstants.RIGHT);
		labelUser.setForeground(Color.white);
		labelPort = new JLabel("Numero de Port :", SwingConstants.RIGHT);
		labelPort.setForeground(Color.white);
		 
		try {
			ip = InetAddress.getLocalHost().getHostAddress().toString();
			port=3000;
		} catch (UnknownHostException e1) {
			System.out.println("Impossible de resoudre l'adresse du serveur!");
		}
		textAdr   = new JTextField(ip, 15);
		textPort  = new JTextField(String.valueOf(port), 15);
		textUser  = new JTextField("", 15);
		valider   = new JButton("Valider");
		annuler   = new JButton("Annuler");
		
		gridPanel = new JPanel(new GridLayout(4, 2,10,10));
		gridPanel.setBackground(new Color(10,10,50));
		gridPanel.add(labelUser);
		gridPanel.add(textUser);
		gridPanel.add(labelAdr);
		gridPanel.add(textAdr);
		gridPanel.add(labelPort);
		gridPanel.add(textPort);
		gridPanel.add(valider);
		gridPanel.add(annuler);
		borderPanel = new JPanel(new BorderLayout(10,10));		
		borderPanel.add(gridPanel);
		borderPanel.add(labelNorth, "North");
		borderPanel.add(labelSouth, "South");
		borderPanel.setBackground(new Color(10,10,50));
		
		this.setContentPane(borderPanel);
		
		//programmation de btn annuler
		annuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		//programmation de btn valider
		valider.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textAdr.getText().equals("") || textPort.getText().equals("") || textUser.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Veuillez saisie tous les champs", "Error",JOptionPane.ERROR_MESSAGE);
				}
				else {
					client.clientName=textUser.getText();
					client.ipServer=textAdr.getText();
					client.port=Integer.parseInt(textPort.getText());
					dispose();//fermer le formulaire de connexion
					client.chatForm = new ClientChatForm(client);//lancer form de chat
					client.openConnexion(); //connecte au serveur
					client.start(); //lancer boite de reception de client
				}
			}
		});
		
		setVisible(true);
		
	}
}

