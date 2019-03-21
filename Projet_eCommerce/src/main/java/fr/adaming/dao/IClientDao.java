package fr.adaming.dao;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;


public interface IClientDao {
	public Client isExist(Client cIn);
	
	public Client ajouterClient(Client cIn);
	
	public Commande ajoutCommande(Commande cIn);
	
	public LigneCommande ajoutLigneCommande(LigneCommande lc );
}
