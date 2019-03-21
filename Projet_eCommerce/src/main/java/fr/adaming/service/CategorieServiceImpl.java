package fr.adaming.service;

import java.util.List;

//import javax.faces.view.EditableValueHolderAttachedObjectHandler; ancienne version

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	// association UML en JAVA
	@Autowired
	private ICategorieDao catDao;
	
	// setter pour injection de dépendence
	public void setCatDao(ICategorieDao catDao){
		this.catDao = catDao;
	}

	@Override
	public List<Categorie> getAllCategorie() {

		return catDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie eIn) {
	
		return catDao.addCategorie(eIn);
	}

	@Override
	public int modifCategorie(Categorie eIn) {
		
		return catDao.modifCategorie(eIn);
	}

	@Override
	public int supprCategorie(Categorie eIn) {
		
		return catDao.supprCategorie(eIn);
	}

	@Override
	public Categorie getCategorieById(Categorie eIn) {
	
		return catDao.getCategorieById(eIn);
	}

}
