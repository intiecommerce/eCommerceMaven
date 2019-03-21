package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "cliMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	// declaration des attributs
	private Client client;
	private Produit produit;
	private LigneCommande ligneCommande;
	private Commande commande;



	// association UML en JAVA
	@ManagedProperty(value = "#{cliService}")
	private IClientService cliService;

	@ManagedProperty(value = "#{catService}")
	private ICategorieService catService;

	@ManagedProperty(value = "#{proService}")
	private IProduitService proService;

	// constructeur
	public ClientManagedBean() {
		this.client = new Client();
		this.client.setListeCommandes(new ArrayList<Commande>());
		List<Commande> lc = this.client.getListeCommandes();
		lc.add(new Commande());
		this.client.setListeCommandes(lc);
		//this.client.getListeCommandes().add(new Commande());
		this.client.getListeCommandes().get(0).setListeLigneCommandes(new ArrayList<LigneCommande>());
		List<LigneCommande> ligc = this.client.getListeCommandes().get(0).getListeLigneCommandes();
		ligc.add(new LigneCommande());
		this.client.getListeCommandes().get(0).setListeLigneCommandes(ligc);
		//this.client.getListeCommandes().get(0).add(new LigneCommande());
		this.client.getListeCommandes().get(0).getListeLigneCommandes().get(0).setProduit(new Produit());
		this.produit=new Produit();
		this.commande=new Commande();
		this.commande.setClient(client);
		this.commande.setListeLigneCommandes(new ArrayList<LigneCommande>());
		this.ligneCommande=new LigneCommande();
		this.ligneCommande.setCommande(commande);
	}

	// getter et setter
	public Client getClient() {
		return client;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	
	
	
	
	
	public LigneCommande getLigneCommande() {
		return ligneCommande;
	}

	public void setLigneCommande(LigneCommande ligneCommande) {
		this.ligneCommande = ligneCommande;
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	// setter injection
	public void setCatService(ICategorieService catService) {

		this.catService = catService;
	}

	public void setCliService(IClientService cliService) {

		this.cliService = cliService;
	}

	public void setProService(IProduitService proService) {

		this.proService = proService;
	}

	// methode

	public String seConnecter() {

		// chercher le client
		Client cliOut = cliService.isExist(client);

		if (cliOut != null) {

			// recuperer les listes de produits et categories
			List<Categorie> catListe = catService.getAllCategorie();
			List<Produit> proListe = proService.getAllProduit();

			// mettre les listes dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catSession", catListe);
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proSession", proListe);

			return "accueilClient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email incorrect"));
			return "login";
		}
	}

	public String ajouterClient() {
		// appel de la methode service
		Client cliAjout = cliService.ajouterClient(client);

		if (cliAjout.getIdClient() != 0) {

			return "accueilClient";

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a échoué"));
			return "creationClient";
		}
	}
	
	public String ajouterProduitLigne(){
		Produit pOut = proService.getProduitById(produit);
		
		if(pOut!=null){
			ligneCommande.setProduit(pOut);
			
			List<LigneCommande> lc = this.commande.getListeLigneCommandes();
			lc.add(this.ligneCommande);
			this.commande.setListeLigneCommandes(lc);
			
			List<Commande> c = this.client.getListeCommandes();
			c.add(this.commande);
			this.client.setListeCommandes(c);
			
			cliService.ajoutCommande(this.commande);
			cliService.ajoutLigneCommande(this.ligneCommande);
			System.out.println("dd");
			return "accueilClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit indisponible"));
			System.out.println("ee");
			return "accueilClient";
		}
	}
	
	public String supprimerProduitPanier(){
		
	Produit pOut = proService.getProduitById(produit);
		
		if(pOut!=null){
			ligneCommande.setProduit(pOut);
			commande.getListeLigneCommandes().add(ligneCommande);
			client.getListeCommandes().add(commande);
			
			return "accueilClient";
		}else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Produit indisponible"));
			return "accueilClient";
		}
	}
	
	public String decoClient(){
		
		HttpSession session=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "login";
	}

}
