package fr.adaming.dao;

import java.util.List;

import fr.adaming.model.Categorie;

public interface ICategorieDao {
	public List<Categorie> getAllCategorie();

	public Categorie addCategorie(Categorie cIn);

	public int modifCategorie(Categorie cIn);

	public int supprCategorie(Categorie cIn);

	public Categorie getCategorieById(Categorie cIn);

}
