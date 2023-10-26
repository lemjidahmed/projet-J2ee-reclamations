package com.session;

import java.util.List;

import com.users.Administration;
import com.users.Etudiant;
import com.users.Utilisateur;

import jakarta.ejb.Local;

@Local
public interface AuthentificationServiceLocal {
	Utilisateur authentifier(String login, String pwd);


	boolean existeEmail(String login);

	void sauvegarderUtilisateur(Utilisateur utilisateur);

	List<Etudiant> listerEtudiants();

	public Etudiant recherherEtudiant(String login, String pass);

	public Administration recherherAdmin(String login, String pass);

	

	

	
	boolean authentifieradmin(String login, String pwd);

	boolean authentifieretd(String login, String pwd);


	


	void DesactiverCompte(Etudiant et);


	void ActiverCompte(Etudiant et);


}
