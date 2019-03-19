package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

public interface IProduitService {
	public List<Produit> getAllProduit();

	public Produit addProduit(Produit eIn);

	public int modifProduit(Produit eIn);

	public int supprProduit(Produit eIn);

	public Produit getProduitById(Produit eIn);
	
	public List<Produit> chercherProduitParCategorie(Categorie cIn);
	
	public List<Produit> afficherProduit(Produit pIn);

}
