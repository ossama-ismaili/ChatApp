package App;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSpace extends Thread {
	public Serveur serveur;
	Socket clientSocket;
	String clientName, msgIn, msgOut;
	boolean status=true;
	public DataInputStream dataIn;
	public DataOutputStream dataOut;

	public ClientSpace(Socket clientSocket, Serveur serveur) {
		this.serveur=serveur;
		this.clientSocket=clientSocket;
		
		try {
			dataIn=new DataInputStream(this.clientSocket.getInputStream());
			dataOut=new DataOutputStream(this.clientSocket.getOutputStream());
			clientName=dataIn.readUTF();
			dataOut.writeUTF("Bonjour "+clientName);
			System.out.println("Bonjour "+clientName);
		} catch (IOException e) {
			System.out.println("Problème dans le reception du nom d'un client");
			serveur.listClients.remove(this);
		}
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				msgIn=dataIn.readUTF();
				for(ClientSpace c:serveur.listClients) {
					c.dataOut.writeUTF(this.clientName+" : "+msgIn);
				}
			}
		}catch (IOException e) {
			System.out.println("Client deconnecté "+clientName);
			serveur.listClients.remove(this);
			System.out.println("Il reste "+serveur.listClients.size()+" clients");
			return;
		}
	}
}
