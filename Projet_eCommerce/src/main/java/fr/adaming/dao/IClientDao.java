package fr.adaming.dao;

import fr.adaming.model.Client;


public interface IClientDao {
	public Client isExist(Client cIn);
	
	public Client ajouterClient(Client cIn);
}
