package com.session;

import java.util.List;

import com.users.Administration;
import com.users.Etudiant;
import com.users.Reclamation;
import com.users.Utilisateur;

import jakarta.ejb.Stateless;
import jakarta.jws.WebParam;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

//heda el raqs
@Stateless(name = "AUTH")
public class AuthentificationServiceImpl implements AuthentificationServiceLocal {
	@PersistenceContext(unitName = "Projet_J2ee")
	private EntityManager em;

	@Override
	public Utilisateur authentifier(String login, String pwd) {

		Query req = em.createQuery("SELECT u FROM Utilisateur u WHERE  u.email=:login AND u.password=:pwd")
				.setParameter("login", login).setParameter("pwd", pwd);

		return (Utilisateur) req.getSingleResult();

	}

	@Override
	public boolean existeEmail(String login) {
		Query query = em.createQuery("SELECT u FROM Utilisateur u WHERE u.email=:login ").setParameter("login", login);
		Utilisateur utl = (Utilisateur) query.getSingleResult();
		if (utl != null) {
			return true;
		}
		return false;
	}

	@Override
	public void sauvegarderUtilisateur(Utilisateur utilisateur) {
		
			em.persist(utilisateur);
		
		
	}

	@Override
	public List<Etudiant> listerEtudiants() {
		Query req = em.createQuery("SELECT e FROM Etudiant e");
		return req.getResultList();
	}

	@Override
	public boolean authentifieretd(String login, String pwd) {

		Query req = em.createQuery("SELECT u FROM Etudiant u WHERE  u.email=:login AND u.password=:pwd")
				.setParameter("login", login).setParameter("pwd", pwd);
		try {
			Etudiant ut = (Etudiant) req.getSingleResult();
			if (ut != null) {

				return true;
			}
		} catch (NoResultException e) {
			return false;
		}
		return true;

	}

	@Override
	public boolean authentifieradmin(String login, String pwd) {

		Query req = em.createQuery("SELECT u FROM Administration u WHERE  u.email=:login AND u.password=:pwd")
				.setParameter("login", login).setParameter("pwd", pwd);
		try {
			Administration ut = (Administration) req.getSingleResult();
			if (ut != null) {

				return true;
			}
		} catch (NoResultException e) {
			return false;
		}
		return true;

	}

	/*
	 * @Override public void addEtudiant(String nom, String prenom, String email,
	 * String password, boolean active) { Etudiant etd = new Etudiant(nom, prenom,
	 * email, password, true); if (existeEmail(email) == false) { em.persist(etd);
	 * em.flush(); }
	 * 
	 * }
	 */

	@Override
	public void ActiverCompte(Etudiant et) {
		
		int req = em.createQuery("UPDATE Etudiant e SET e.active =:s WHERE e.ID=:a").setParameter("s", true).setParameter("a", et.getIdUtilisateur()).executeUpdate();
		

	}
	@Override
	public void DesactiverCompte(Etudiant et) {
		
		int req = em.createQuery("UPDATE Etudiant e SET e.active =:s WHERE e.ID=:a").setParameter("s", false).setParameter("a", et.getIdUtilisateur()).executeUpdate();
		

	}


	public Etudiant recherherEtudiant(String login, String pass) {// faux
		Query req = em.createQuery("select etd from Etudiant etd where etd.email= :login AND  etd.password= :pass")
				.setParameter("login", login).setParameter("pass", pass);
		Etudiant etd = (Etudiant) req.getSingleResult();
		return etd;
	}

	public Administration recherherAdmin(String login, String pass) {// faux
		Query req = em
				.createQuery("SELECT etd FROM Administration etd WHERE etd.email= :login AND  etd.password= :pass")
				.setParameter("login", login).setParameter("pass", pass);
		Administration ad = (Administration) req.getSingleResult();
		return ad;
	}

}
