package fr.adaming.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import fr.adaming.service.IProduitService;

@ManagedBean(name="ligComMB")
@RequestScoped
public class LigneCommandeManagedBean {

	// association UML en JAVA
	@ManagedProperty(value="#{proService}")
	private IProduitService proService;
	
	
	// setter injection
	public void setProService(IProduitService proService){
		
		this.proService = proService;
	}
	
}
