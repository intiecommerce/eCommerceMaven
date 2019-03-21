package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Admin;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
import fr.adaming.service.IAdminService;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "admMB")
@RequestScoped
public class AdminManagedBean implements Serializable{

	// Declaration des attributs
	private Admin admin;
	
	
	// association UML en JAVA
	@ManagedProperty(value="#{admService}")
	private IAdminService admService;

	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;
	
	@ManagedProperty(value="#{proService}")
	private IProduitService proService;

	
	// Constructeur
	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// getter et setter
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	
	// setters injection
	public void setAdmService(IAdminService admService){
		
		this.admService = admService;
	}
	
	public void setCatService(ICategorieService catService){
		
		this.catService = catService;
	}
	
	public void setProService(IProduitService proService){
		
		this.proService = proService;
	}
	

	// methode metier
	public String seConnecter() {
		// chercher le formateur par son mail et son mdp
		Admin admOut = admService.isExist(admin);

		if (admOut != null) {

			
			// recuperer les listes de produits et categories
		
			List<Categorie> catListe=catService.getAllCategorie();
			List<Produit> proListe=proService.getAllProduit();
			
			//mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catSession", catListe);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proSession", proListe);

			return "accueilAdmin";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Identifiant et/ou mot de passe incorrect"));
			return "login";
		}
	}
	
	public String decoAdmin(){
		
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

}
