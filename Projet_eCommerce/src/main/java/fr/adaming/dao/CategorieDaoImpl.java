package fr.adaming.dao;

import java.util.List;

//import org.apache.commons.codec.binary.Base64;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Base64Utils;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Repository
public class CategorieDaoImpl implements ICategorieDao {

	@Autowired
	private SessionFactory sf;
	
	
	// setter pour l'injection de d�pendence
	public void setSf(SessionFactory sf){
		this.sf = sf;
	}

	public List<Categorie> recCat(){
		
		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
		
		//requete JPQL
		String req="SELECT c FROM Categorie as c";
		
		//Recuperer un objet de type query
		Query query = s.createQuery(req);
		
		List<Categorie> listeCat = query.list();
		
		for(Categorie c:listeCat){

			//c.setImg("data:image/png;base64,"+Base64.encodeBase64String(c.getPhoto()));   version pr�c�dente
			c.setImg("data:image/png;base64,"+Base64Utils.encodeToString(c.getPhoto()));
		}
		return listeCat;
	}
	
	
	@Override
	public List<Categorie> getAllCategorie() {

		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		// Requete JPQL
		String req = "SELECT c FROM Categorie as c";

		// recuperer un objet de type Query
		Query query = s.createQuery(req);

		return query.list();
	}

	@Override
	public Categorie addCategorie(Categorie cIn) {
		
		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		s.save(cIn);
		return cIn;
	}

	@Override
	public int modifCategorie(Categorie cIn) {

		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
						
		String req = "UPDATE Categorie as c SET c.nomCategorie=:pCat, c.description=:pDes WHERE idCategorie=:pId";
		Query queryJPQL = s.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pCat", cIn.getNomCategorie());
		queryJPQL.setParameter("pDes", cIn.getDescription());
		queryJPQL.setParameter("pId", cIn.getIdCategorie());

		return queryJPQL.executeUpdate();
	}

	@Override
	public int supprCategorie(Categorie cIn) {

		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		String req = "Delete Categorie as c WHERE idCategorie=:pId";

		Query queryJPQL = s.createQuery(req);

		// passage des parametres
		queryJPQL.setParameter("pId", cIn.getIdCategorie());

		return queryJPQL.executeUpdate();

	}

	@Override
	public Categorie getCategorieById(Categorie cIn) {
		
		// r�cup�rer le bus (session de hibernate)
		Session s = sf.getCurrentSession();
				
		//Categorie cOut = s.find(Categorie.class, cIn.getIdCategorie());  ancienne version
		
		// requ�te JPQL
		String req = "FROM Categorie as c WHERE c.idCategorie=:pId";
						
		// r�cup�rer l'objet Query
		Query query = s.createQuery(req);
						
		// passage des param�tres
		query.setParameter("pId", cIn.getIdCategorie());
					
		return (Categorie) query.uniqueResult();
	}

}
