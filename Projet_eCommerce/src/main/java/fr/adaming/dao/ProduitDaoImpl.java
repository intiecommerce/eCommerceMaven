package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao{

	@Autowired
	private SessionFactory sf;
	
	
	// setter pour l'injection de dépendence
	public void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}
	
	public List<Produit> recPro(){
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		//requete JPQL
		String req="SELECT p FROM Produit as p";
		
		//Recuperer un objet de type query
		Query query = s.createQuery(req);
		
		List<Produit> listePro = query.list();
		
		for(Produit p:listePro){
			//p.setImage("data:image/png;base64,"+Base64.encodeBase64String(p.getPhoto())); ancienne version
			p.setImage("data:image/png;base64,"+Base64Utils.encodeToString(p.getPhoto()));
		}
		return listePro;
	}

	
	@Override
	public List<Produit> getAllProduit() {

		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
						
		// Requete JPQL
		String req = "SELECT p FROM Produit as p";

		// recuperer un objet de type Query
		Query query = s.createQuery(req);

		return query.list();

	}

	@Override
	public Produit addProduit(Produit pIn) {
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
						
		s.save(pIn);
		return pIn;
	}

	@Override
	public int modifProduit(Produit pIn) {
		

		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		// Requete JPQL
		String req = "UPDATE Produit as p SET p.designation=:pDesi, p.description=:pDesc, p.prix=:pPri, p.quantite=:pQua, p.selectionne=:pSel WHERE idProduit=:pId";
		
		// recuperer un objet de type Query
		Query query = s.createQuery(req);

		// passage des parametres
		query.setParameter("pDesi", pIn.getDesignation());
		query.setParameter("pDesc", pIn.getDescription());
		query.setParameter("pPri", pIn.getPrix());
		query.setParameter("pQua", pIn.getQuantite());
		query.setParameter("pSel", pIn.isSelectionne());
		query.setParameter("pId", pIn.getIdProduit());

		return query.executeUpdate();
	}

	@Override
	public int supprProduit(Produit pIn) {
		

		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		String req = "Delete Produit as p WHERE idProduit=:pId";

		Query queryJPQL = s.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pId", pIn.getIdProduit());

		return queryJPQL.executeUpdate();
	}

	@Override
	public Produit getProduitById(Produit pIn) {
		

		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		//Produit pOut = em.find(Produit.class, pIn.getIdProduit()); ancienne version
		
		// requête JPQL
		String req = "FROM Produit as p WHERE p.idProduit=:pId";
								
		// récupérer l'objet Query
		Query query = s.createQuery(req);
								
		// passage des paramètres
		query.setParameter("pId", pIn.getIdProduit());
							
		return (Produit) query.uniqueResult();
	}


	@Override
	public List<Produit> chercherProduitParCategorie(Categorie cIn) {
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		// Requete JPQL

		String req = "SELECT p FROM Produit as p WHERE p.categorie.idCategorie=:pId";

		// recuperer un objet de type Query
		Query query = s.createQuery(req);
		
		// passage des parametres
		query.setParameter("pId", cIn.getIdCategorie());

		return query.list();

	}
	
	public List<Produit> afficherProduit(Produit pIn){
		
		// récupérer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		// Requete JPQL

		String req = "SELECT p FROM Produit as p WHERE p.categorie.listeProduits=:pId";
		
		// recuperer un objet de type Query
		Query query = s.createQuery(req);
		
		// passage des parametres
		query.setParameter("pId", pIn.getCategorie().getListeProduits());

		return query.list();
	}

}
