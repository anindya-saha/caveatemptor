package auction.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

@ManagedBean(name = "SearchManagedBean")
@SessionScoped
public class SearchManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";
	
	private boolean myAuctions;
	
	private boolean activeAuctions;
	
	public SearchManagedBean() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("SearchManagedBean");
	}
	
	public String search(){
		return SUCCESS;
	}

	public boolean isMyAuctions() {
		return myAuctions;
	}

	public void setMyAuctions(boolean myAuctions) {
		this.myAuctions = myAuctions;
	}

	public boolean isActiveAuctions() {
		return activeAuctions;
	}

	public void setActiveAuctions(boolean activeAuctions) {
		this.activeAuctions = activeAuctions;
	}
	
}
