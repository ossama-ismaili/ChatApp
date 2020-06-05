package App;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
	String clientName;
	String ipServer;
	int port;
	Socket socketClient;
	DataOutputStream dataOut;
	DataInputStream dataIn;
	
	ClientChatForm chatForm;
	
	public Client() {
		new ClientConnexionForm(this);
	}

	
	public static void main(String[] args) {
		new Client();
	}


	public void openConnexion() {
		try {
			System.out.println("Etape 1 : Se connecter au serveur");
			socketClient=new Socket(ipServer,port);
			System.out.println("Etape 1 -> OK ");
			
			System.out.println("Etape 2 : Creation des cannaux Out et In");
			dataIn=new DataInputStream(socketClient.getInputStream());
			dataOut=new DataOutputStream(socketClient.getOutputStream());
			System.out.println("Etape 2 -> OK ");
			
			System.out.println("Etape 3 : Envoyer le nom de client au serveur");
			dataOut.writeUTF(clientName);
			System.out.println("Etape 3 -> OK ");
			
		}  catch (IOException e) {
			System.err.println("Probleme de connexion au serveur");
			System.exit(0);
		}
	}

	//programmation de la boite de reception
	@Override
	public void run() {
		while(true) {
			try {
				String message= dataIn.readUTF();
				chatForm.HistoriqueChat.append("\n"+message);
			} catch (IOException e) {
				System.err.println("Probleme au boite de reception");
			}
		}
	}
}
