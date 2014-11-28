package br.com.setv.bean.backing;

import br.com.setv.entidades.Habito;
import br.com.setv.bean.session.HabitoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "habitoController")
@ViewScoped
public class HabitoController extends AbstractController<Habito> {

    @EJB
    private HabitoFacade ejbFacade;

    /**
     * Initialize the concrete Habito controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    public HabitoController() {
        // Inform the Abstract parent controller of the concrete Habito?cap_first Entity
        super(Habito.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of HabitoVerbo entities that
     * are retrieved from Habito?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for HabitoVerbo page
     */
    public String navigateHabitoVerboList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("HabitoVerbo_items", this.getSelected().getHabitoVerboList());
        }
        return "/pages/primefaces/habitoVerbo/index";
    }

}
