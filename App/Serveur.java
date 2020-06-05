package App;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Serveur {
	public ServerSocket socketServer;
	String ip;
	int port=3000;
	
	public ArrayList<ClientSpace> listClients=new ArrayList<ClientSpace>();

	public static void main(String[] args) {
		Serveur server=new Serveur();
		server.exec();
	}

	public void exec() {
		try {
			ip=InetAddress.getLocalHost().getHostAddress().toString();
			socketServer=new ServerSocket(port);
			System.out.println("À communiquer aux clients [ IP : "+ip+" , Port : "+port+" ]");
			while(true) {
				Socket clientSocket=socketServer.accept();
				ClientSpace c=new ClientSpace(clientSocket,this);
				c.start();
				this.listClients.add(c);
				System.out.println("Server : nouveau client connecte il y a "+this.listClients.size()+" clients");
			}
		} catch (UnknownHostException e) {
			System.out.println("Impossible de resoudre l'adresse du serveur!");
			System.exit(0);
		} catch (IOException e) {
			System.out.println("Impossible de crée serveur!");
			System.exit(0);
		}
		
	}

}
