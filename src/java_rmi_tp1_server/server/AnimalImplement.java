package java_rmi_tp1_server.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import java_rmi_tp1_communs.classes.DossierDeSuivi;
import java_rmi_tp1_communs.classes.Espece;
import java_rmi_tp1_communs.interfaces.Animal;

@SuppressWarnings("serial")
public class AnimalImplement extends UnicastRemoteObject implements Animal {
	private String nom;
	private String nomMaitre;
	private Espece espece;
	private String race;
	private DossierDeSuivi dossierDeSuivi;

	public AnimalImplement(String nom, String nomMaitre, Espece espece, String race, DossierDeSuivi dossierDeSuivi)
			throws RemoteException {
		this.nom = nom.toLowerCase(); //casser la sensibilité à la casse
		this.nomMaitre = nomMaitre;
		this.espece = espece;
		this.race = race;
		this.dossierDeSuivi = dossierDeSuivi;
	}
    

	public String getNomMaitre() {
		return nomMaitre;
	}

	public Espece getEspece() {
		return espece;
	}

	public String getRace() {
		return race;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setNomMaitre(String nomMaitre) {
		this.nomMaitre = nomMaitre;
	}

	public void setEspece(Espece espece) {
		this.espece = espece;
	}

	public void setRace(String race) {
		this.race = race;
	}


	@Override
	public String printAnimalInfos() throws RemoteException {
		// TODO Auto-generated method stub
		return "\nnom : " + this.getNom() + "\nnomMaitre : " + this.getNomMaitre() + "\nespcece : " + this.getEspece()
				+ "\nrace : " + this.getRace() + dossierDeSuivi.infosDossierSuivi();
	}

	@Override
	public String getDateVaccination() throws RemoteException {
		// TODO Auto-generated method stub
		return this.dossierDeSuivi.infosDossierSuivi();
	}

	@Override
	public void setDateVaccination(String dateDeVaccination) throws RemoteException {
		// TODO Auto-generated method stub
		this.dossierDeSuivi.setDateVaccination(dateDeVaccination);
	}


	@Override
	public String getNom() throws RemoteException {
		return this.nom;
	}

}
