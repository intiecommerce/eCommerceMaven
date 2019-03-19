package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	//declaration des attributs
	private Client client;
	
	// association UML en JAVA
	@ManagedProperty(value="#{cliService}")
	private IClientService cliService;
	
	@ManagedProperty(value="#{catService}")
	private ICategorieService catService;
	
	@ManagedProperty(value="#{proService}")
	private IProduitService proService;


	//constructeur
	public ClientManagedBean() {
		this.client =new Client();
	}

	//getter et setter
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	//methode
	
	public String seConnecter() {
		
		// chercher le client
		Client cliOut = cliService.isExist(client);

		if (cliOut != null) {

			// recuperer les listes de produits et categories
			List<Categorie> catListe=catService.getAllCategorie();
			List<Produit> proListe=proService.getAllProduit();
			
			//mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catSession", catListe);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proSession", proListe);
			
			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Email incorrect"));
			return "login";
		}
	}
	public String ajouterClient(){
		//appel de la methode service
		Client cliAjout=cliService.ajouterClient(client);
		
		if(cliAjout.getIdClient()!=0){
	
	
			
			return "accueilClient";
			
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("L'ajout a échoué"));
			return "creationClient";
		}
	}
	
	
}
