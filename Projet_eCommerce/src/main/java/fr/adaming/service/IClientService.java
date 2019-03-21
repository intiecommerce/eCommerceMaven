package fr.adaming.service;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

public interface IClientService {

	public Client ajouterClient(Client cIn);
	public Client isExist(Client cIn);
	public Commande ajoutCommande(Commande cIn);
	
	public LigneCommande ajoutLigneCommande(LigneCommande lc );
	
}
