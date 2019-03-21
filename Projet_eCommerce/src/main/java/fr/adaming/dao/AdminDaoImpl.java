package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired
	private SessionFactory sf;

	// setter pour l'injection de dépendence
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin aIn) {

		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();

		// requete JPQL on ne travail pas avec la base de donné avec jpql, c'est
		// avec les objets
		String req = "SELECT a FROM Admin as a WHERE a.mail=:pMail and a.mdp=:pMdp";

		// recuperer un objet de type Query
		Query query = s.createQuery(req);

		// passage des parametres
		query.setParameter("pMail", aIn.getMail());
		query.setParameter("pMdp", aIn.getMdp());

		try {
			return (Admin) query.uniqueResult();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;

	}
}
