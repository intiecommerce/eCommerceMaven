package fr.adaming.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.model.Admin;

@Service("admService")
@Transactional
public class AdminServiceImpl implements IAdminService{

	// associatio UML en JAVA
	@Autowired
	private IAdminDao admDao;
	
	// setter pour injection de dépendence
	public void setAdmDao(IAdminDao admDao){
		this.admDao = admDao;
	}
	
	@Override
	public Admin isExist(Admin aIn) {
		return admDao.isExist(aIn);
	}

}
