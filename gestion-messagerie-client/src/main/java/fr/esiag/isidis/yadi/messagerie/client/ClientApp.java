package fr.esiag.isidis.yadi.messagerie.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.tuple.Pair;

import fr.esiag.isidis.yadi.messagerie.model.Contact;
import fr.esiag.isidis.yadi.messagerie.model.Message;

/**
 * 
 * @author Yassine Diouri
 *
 */
public class ClientApp {

	private static List<Pair<String, String>> replicants = new ArrayList<Pair<String, String>>();
	
	private static ClientManager cm;
	private static Scanner scan;
	private static boolean exit = false;
	private static int current = 0;
	
	private String port;
	
	private static Contact c = null;

	private static String action;
	
	public ClientApp(String _port) throws IOException {
		this.port = _port;
		
		replicants.add(Pair.of("127.0.0.1", "2500"));
		replicants.add(Pair.of("127.0.0.1", "2600"));
		replicants.add(Pair.of("127.0.0.1", "2700"));
		
		cm = new ClientManager();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		String port = args[1]!=null?args[1]:"1500";
		
		new ClientApp(port);
		
		scan = new Scanner(System.in);
		
		System.out.println("----------------------------CONSOLE CLIENT----------------------------\n");
		help();

		while (!exit) {
			init();
			Thread.sleep(500);
			
			System.out.println("[ "+ replicants.get(current).getLeft() +" : "+ replicants.get(current).getRight() +" ]");
			if(c == null) System.out.print("console > ");
			else System.out.print(c.getFirstName() + " > ");
				
			action = scan.nextLine();

			switch (action) {

			case "signup":
				c = new Contact();
				System.out.print("console > First Name : ");
				c.setFirstName(scan.nextLine());
				System.out.print("console > Last Name : ");
				c.setLastName(scan.nextLine());
				System.out.print("console > Login : ");
				c.setLogin(scan.nextLine());
				System.out.print("console > Password : ");
				c.setPassword(scan.nextLine());
				Boolean r = cm.inscription(c, replicants.get(current).getLeft(), replicants.get(current).getRight());
				if (r) System.out.println("console > Inscription r�ussie");
				else System.out.println("console > Inscription �chou�e");
				c = null;
				current++;
				current %= replicants.size();
				break;
				
			case "signin":
				c = new Contact();
				System.out.print("console > Login : ");
				c.setLogin(scan.nextLine());
				System.out.print("console > Password : ");
				c.setPassword(scan.nextLine());
				c = cm.authentification(c, replicants.get(current).getLeft(), replicants.get(current).getRight());
				if(c.getFirstName() != null) System.out.println(c.getFirstName() + " > Connexion r�ussie");
				else System.out.println("console > Connexion refus�e");
				current++;
				current %= replicants.size();
				break;
				
			case "send":
				if(c != null){
					Message m = new Message();
					System.out.print(c.getFirstName() + " > To : ");
					m.setTo(scan.nextLine());
					System.out.print(c.getFirstName() + " > Objet : ");
					m.setObject(scan.nextLine());
					System.out.print(c.getFirstName() + " > Body : ");
					m.setBody(scan.nextLine());
					m.setFrom(c.getLogin());
					m = cm.envoieMSG(m, replicants.get(current).getLeft(), replicants.get(current).getRight());
					if(m.getId() != 0) System.out.println(c.getFirstName() + " > Envoie Message R�ussie");
					else System.out.println(c.getFirstName() + " > Envoie Message Echou�");
				} else {
					System.out.println("console > Veuillez vous authentifier pour envoyer un message");
				}
				current++;
				current %= replicants.size();
				break;
			
			case "list":
				if(c != null){
					List<Message> list = cm.listMSGRecu(c.getLogin(), replicants.get(current).getLeft(), replicants.get(current).getRight());
					if(list.size() != 0) {
						afficherMessages(list);
						System.out.print(" Pour afficher le Message veuillez saisir le num�ro �quivalent : ");
						String selected = scan.nextLine();
						if(!selected.isEmpty()) afficherMessage(list.get(Integer.parseInt(selected) - 1));
					}
					else System.out.println(c.getFirstName() + " > Vous n'avez aucun message");
				} else {
					System.out.println("console > Veuillez vous authentifier pour envoyer un message");
				}
				current++;
				current %= replicants.size();
				break;
			
			case "listSent":
				if(c != null){
					List<Message> list = cm.listMSGEnvoye(c.getLogin(), replicants.get(current).getLeft(), replicants.get(current).getRight());
					if(list.size() != 0) {
						afficherMessages(list);
						System.out.print(" Pour afficher le Message veuillez saisir le num�ro �quivalent : ");
						String selected = scan.nextLine();
						if(!selected.isEmpty()) afficherMessage(list.get(Integer.parseInt(selected) - 1));
					}
					else System.out.println(c.getFirstName() + " > Vous n'avez aucun message");
				} else {
					System.out.println("console > Veuillez vous authentifier pour envoyer un message");
				}
				current++;
				current %= replicants.size();
				break;
				
			case "logout":
				c = null;
				break;

			case "help":
				help();
				break;

			case "quit":
				exit();
				System.out.println("EN QUITTANT LA CONSOLE TOUTES VOS DONNEES SERONT SUPPRIMEES");
				break;

			default:
				System.err.println("console > COMMANDE INEXISTANTE");
			}
		}
	}

	private static void afficherMessage(Message message) {
		System.out.println(" Message :");
		System.out.println("\tID : " + message.getId());
		System.out.println("\tFrom : " + message.getFrom());
		System.out.println("\tTo : " + message.getTo());
		System.out.println("\tObjet : " + message.getObject());
		System.out.println("\tBody : " + message.getBody());
	}

	private static void afficherMessages(List<Message> list) {
		System.out.println(" List Messages Re�us :");
		for(int i = 0; i < list.size(); i++){
			System.out.println("\t" + (i+1) + " : " + list.get(i).getFrom() + " | " + list.get(i).getObject());
		}
	}

	private static void help() {
		System.out.println("/**ACTION ==>");
		System.out.println("/**\tSignUP   : s'inscrire � l'application");
		System.out.println("/**\tSignIN   : s'authentifier � l'application");
		System.out.println("/**\tSend     : envoyer un message");
		System.out.println("/**\tList     : r�cup�rer les messages re�us");
		System.out.println("/**\tListSent : r�cup�rer les messages envoy�s");
		System.out.println("/**\tHELP     : afficher assistant");
		System.out.println("/**\tQUIT     : quitter client");
	}

	private static void exit() {
		exit = true;
	}

	private static void init() {
		action = "";
	}
}
