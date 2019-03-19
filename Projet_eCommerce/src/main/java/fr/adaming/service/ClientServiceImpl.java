package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Client;

@Service("cliService")
@Transactional
public class ClientServiceImpl implements IClientService{
	
	// association UML en JAVA
	@Autowired
	private IClientDao cliDao;
	
	// setter pour injection de d�pendence
	public void setCliDao(IClientDao cliDao){
		this.cliDao = cliDao;
	}
		
	
	@Override
	public Client ajouterClient(Client cIn) {
		
		return cliDao.ajouterClient(cIn);
	}

	@Override
	public Client isExist(Client cIn) {
		
		return cliDao.isExist(cIn);
	}

}