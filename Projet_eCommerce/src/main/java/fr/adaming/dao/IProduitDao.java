package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;


public interface IProduitDao {
	
	public List<Produit> getAllProduit();

	public Produit addProduit(Produit cIn);

	public int modifProduit(Produit cIn);

	public int supprProduit(Produit cIn);

	public Produit getProduitById(Produit cIn);
	
	public List<Produit> chercherProduitParCategorie(Categorie cIn);
	
	public List<Produit> afficherProduit(Produit pIn);
	
	

}
