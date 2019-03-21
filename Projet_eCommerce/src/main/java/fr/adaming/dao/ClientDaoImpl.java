package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Admin;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Repository
public class ClientDaoImpl implements IClientDao{
	
	@Autowired
	private SessionFactory sf;
	
	
	// setter pour l'injection de dépendence
	public void setSf(SessionFactory sf){
		this.sf = sf;
	}
	
	@Override
	public Client isExist(Client cIn) {
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		//requete JPQL		on ne travail pas avec la base de donné avec jpql, c'est avec les objets
		String req="SELECT c FROM Client as c WHERE c.email=:pMail";
		
		//recuperer un objet de type Query
		Query query=s.createQuery(req);
		
		//passage des parametres
		query.setParameter("pMail", cIn.getEmail());

		try{
		return (Client) query.uniqueResult();
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	
	}
	@Override
	public Client ajouterClient(Client cIn) {
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		s.save(cIn);
		return cIn;
	}

	@Override
	public Commande ajoutCommande(Commande cIn) {
		Session s =sf.getCurrentSession();
		
		s.save(cIn);
		return cIn;
	}

	@Override
	public LigneCommande ajoutLigneCommande(LigneCommande lc) {
	Session s =sf.getCurrentSession();
		
		s.save(lc);
		return lc;
	}
	

}
