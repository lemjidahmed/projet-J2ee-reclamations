package com.service;

import com.session.AuthentificationServiceLocal;
import com.session.ReclamationServiceLocal;
import com.users.Administration;
import com.users.Etudiant;
import com.users.Reclamation;
import com.users.TypeReclamation;
import com.users.Utilisateur;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

@Stateless
@WebService
public class AuthentificationServ {
	@EJB
	private AuthentificationServiceLocal metier;
	@EJB
	private ReclamationServiceLocal metier1;
	/*@WebMethod
	public void addtout() {
		Etudiant etd = new Etudiant("azeé", "azeé", "azeé", "azeé", false);
		
		TypeReclamation tr = new TypeReclamation("ee");

		metier1.ajouterTout(re, etd,tr);
	}*/
	@WebMethod
	public void addEtudiant(@WebParam String nom, @WebParam String prenom, @WebParam String email,
			@WebParam String password, @WebParam boolean active) {
		
		Etudiant etd = new Etudiant(nom, prenom, email, password, active);
		metier.sauvegarderUtilisateur(etd);
	}

	public void addAdministrateur(@WebParam String email, @WebParam String password, @WebParam boolean active) {
		Utilisateur admin = new Administration(email, password, active);
		metier.sauvegarderUtilisateur(admin);
	}
	/*public boolean authentifierAdmin(@WebParam String login,@WebParam String pwd) {
		return  metier.authentifieradmin(login, pwd);
	}
	@WebMethod
	public boolean authentifierEtd(@WebParam String login,@WebParam String pwd) {
		return  metier.authentifieretd(login, pwd);
	}*/
	/*public void activerCompte(String nom, String prenom) {
		Etudiant etd = metier.recherherEtudiant(nom, prenom);
		Administration admin = new Administration("iheb", "iheb", true);
		metier.ActiverCompte(etd, admin);	}

	public void desactiverCompte(String nom, String prenom) {
		Etudiant etd = metier.recherherEtudiant(nom, prenom);
		Administration admin = new Administration("iheb", "iheb", true);
		metier.DeactiverCompte(etd, admin);
	}*/

	public Etudiant findEtd(String nom, String prenom) {
		return (Etudiant) metier.recherherEtudiant(nom, prenom);
	}
	public Etudiant recherherEtudiant(String nom, String prenom) {
		return metier.recherherEtudiant(nom, prenom);
	}
}
