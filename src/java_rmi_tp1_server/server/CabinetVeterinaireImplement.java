package java_rmi_tp1_server.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import java_rmi_tp1_communs.classes.DossierDeSuivi;
import java_rmi_tp1_communs.classes.Espece;
import java_rmi_tp1_communs.interfaces.Animal;
import java_rmi_tp1_communs.interfaces.CabinetVeterinaire;
import java_rmi_tp1_communs.interfaces.IClient;

@SuppressWarnings("serial")
public class CabinetVeterinaireImplement extends UnicastRemoteObject implements CabinetVeterinaire {
	private ArrayList<Animal> animaux;
	private int nombreAnimaux = 0; // Compteur d'animaux
	private static final int[] SEUILS = { 3, 4, 5 };
	private List<IClient> clientsObservateurs = new ArrayList<>();

	public CabinetVeterinaireImplement() throws RemoteException {
		super();
		this.animaux = new ArrayList<>();
	}

	@Override
	public Animal chercherAnimalParNom(String nom) throws RemoteException {
		for (Animal animal : this.animaux) {
			if (animal.getNom().equals(nom)) {
				return animal;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Animal> listerAnimaux() throws RemoteException {
		return new ArrayList<>(this.animaux);
	}
	
	@Override
	public void ajouterAnimal(String nom, String nomMaitre, Espece espece, String race, DossierDeSuivi dossierDeSuivi)
			throws RemoteException {
		this.animaux.add(new AnimalImplement(nom, nomMaitre, espece, race, dossierDeSuivi));
		this.nombreAnimaux++;
		verifierEtNotifierSeuils();
	}

	private void verifierEtNotifierSeuils() {
		for (int seuil : SEUILS) {
			if (nombreAnimaux == seuil) {
				notifierClients("Le seuil de " + seuil + " animaux a été atteint.");
			}
		}
	}

	@Override
	public void enregistrerObservateur(IClient client) throws RemoteException {
		clientsObservateurs.add(client);
	}

	private void notifierClients(String message) {
		for (IClient client : clientsObservateurs) {
			try {
				client.recevoirNotification(message);
			} catch (RemoteException e) {
				System.out.println("Erreur lors de la notification du client : " + e.getMessage());
			}
		}
	}
}
