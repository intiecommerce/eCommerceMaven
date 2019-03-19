package fr.adaming.service;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieService {
	
	public List<Categorie> getAllCategorie();

	public Categorie addCategorie(Categorie eIn);

	public int modifCategorie(Categorie eIn);

	public int supprCategorie(Categorie eIn);

	public Categorie getCategorieById(Categorie eIn);


}
