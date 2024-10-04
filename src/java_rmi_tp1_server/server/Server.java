package java_rmi_tp1_server.server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java_rmi_tp1_communs.classes.DossierDeSuivi;
import java_rmi_tp1_communs.classes.Espece;



public class Server {

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {

		try {
			
			// Définition de l'URI de codebase où le serveur peut charger les classes clients
			System.setProperty("java.rmi.server.codebase", "file:////home/ahmedou/eclipse-workspace/java_rmi_tp1_client/bin/");


	        System.setProperty("java.security.policy", "file:///home/ahmedou/eclipse-workspace/java_rmi_tp1_server/src/java_rmi_tp1/utility/server.policy");


	        if (System.getSecurityManager() == null) {
	            System.setSecurityManager(new SecurityManager());
	        }
			
			CabinetVeterinaireImplement cabinetObj = new CabinetVeterinaireImplement();
			
			// Ajout d'animaux au cabinet vétérinaire
            cabinetObj.ajouterAnimal("Peper", "Roland", new Espece("Chien", 15), "inconnue", new DossierDeSuivi());
            cabinetObj.ajouterAnimal("Misty", "Alice", new Espece("Chat", 14), "Persan", new DossierDeSuivi());
			Registry registry = LocateRegistry.createRegistry(1099);
//			Registry registry = LocateRegistry.getRegistry();
			if (registry == null) {
				System.err.println("RmiRegistry not found");
			} else {
				registry.bind("cabinetObj", cabinetObj);
				System.err.println("Server ready");
				
			}
		} catch (RemoteException | AlreadyBoundException e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
