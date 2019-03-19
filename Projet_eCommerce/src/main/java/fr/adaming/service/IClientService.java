package fr.adaming.service;

import fr.adaming.model.Client;

public interface IClientService {

	public Client ajouterClient(Client cIn);
	public Client isExist(Client cIn);
}
