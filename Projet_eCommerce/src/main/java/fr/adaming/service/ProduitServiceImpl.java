package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Service("proService")
@Transactional
public class ProduitServiceImpl implements IProduitService {

	// association UML en JAVA
	@Autowired
	private IProduitDao proDao;
	
	// setter pour injection de dépendence
	public void setProDao(IProduitDao proDao){
		this.proDao = proDao;
	}
		
		
	@Override
	public List<Produit> getAllProduit() {
		return proDao.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit pIn) {
		
		return proDao.addProduit(pIn);
	}

	@Override
	public int modifProduit(Produit pIn) {
		
		return proDao.modifProduit(pIn);
	}

	@Override
	public int supprProduit(Produit pIn) {
	
		return proDao.supprProduit(pIn);
	}

	@Override
	public Produit getProduitById(Produit pIn) {
		
		return proDao.getProduitById(pIn);
	}

	@Override
	public List<Produit> chercherProduitParCategorie(Categorie cIn) {
		
		return proDao.chercherProduitParCategorie(cIn);
	}

	@Override
	public List<Produit> afficherProduit(Produit pIn) {
		pIn.setCategorie(new Categorie());
		
		return proDao.afficherProduit(pIn);
	}

}
