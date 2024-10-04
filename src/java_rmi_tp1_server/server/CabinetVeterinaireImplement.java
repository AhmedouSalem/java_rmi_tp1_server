package java_rmi_tp1_server.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import java_rmi_tp1_communs.classes.DossierDeSuivi;
import java_rmi_tp1_communs.classes.Espece;
import java_rmi_tp1_communs.interfaces.Animal;
import java_rmi_tp1_communs.interfaces.CabinetVeterinaire;

@SuppressWarnings("serial")
public class CabinetVeterinaireImplement extends UnicastRemoteObject implements CabinetVeterinaire {
	private ArrayList<Animal> animaux;

	public CabinetVeterinaireImplement() throws RemoteException {
		super();
		this.animaux = new ArrayList<>(); // Assurez-vous que cela est bien initialisé
	}

	@Override
	public void ajouterAnimal(String nom, String nomMaitre, Espece espece,String race, DossierDeSuivi dossierDeSuivi) throws RemoteException {
		animaux.add(new AnimalImplement(nom, nomMaitre, espece, race, dossierDeSuivi));
	}

	@Override
	public Animal chercherAnimalParNom(String nom) throws RemoteException {
		for (Animal animal : animaux) {
			if (animal.getNom().equals(nom)) {
				return animal;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Animal> listerAnimaux() throws RemoteException {
		return new ArrayList<>(animaux);
	}

	@Override
	public void ajouterAnimal(String nom, String nomMaitre, String espece, int dureeVie, String race,
			String dateVaccination) throws RemoteException {
		Animal animal = new AnimalImplement(nom, nomMaitre, new Espece(espece, dureeVie), race,
				new DossierDeSuivi(dateVaccination));
		animaux.add(animal);
	}

}
