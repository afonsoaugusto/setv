package br.com.setv.bean.backing;

import br.com.setv.entidades.HabitoVerbo;
import br.com.setv.bean.session.HabitoVerboFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "habitoVerboController")
@ViewScoped
public class HabitoVerboController extends AbstractController<HabitoVerbo> {

    @EJB
    private HabitoVerboFacade ejbFacade;
    private VerboController verboIdController;
    private HabitoController habitoIdController;

    /**
     * Initialize the concrete HabitoVerbo controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
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
        verboIdController = context.getApplication().evaluateExpressionGet(context, "#{verboController}", VerboController.class);
        habitoIdController = context.getApplication().evaluateExpressionGet(context, "#{habitoController}", HabitoController.class);
    }

    public HabitoVerboController() {
        // Inform the Abstract parent controller of the concrete HabitoVerbo?cap_first Entity
        super(HabitoVerbo.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        verboIdController.setSelected(null);
        habitoIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Fato entities that are
     * retrieved from HabitoVerbo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Fato page
     */
    public String navigateFatoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Fato_items", this.getSelected().getFatoList());
        }
        return "/pages/primefaces/fato/index";
    }

    /**
     * Sets the "selected" attribute of the Verbo controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareVerboId(ActionEvent event) {
        if (this.getSelected() != null && verboIdController.getSelected() == null) {
            verboIdController.setSelected(this.getSelected().getVerboId());
        }
    }

    /**
     * Sets the "selected" attribute of the Habito controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHabitoId(ActionEvent event) {
        if (this.getSelected() != null && habitoIdController.getSelected() == null) {
            habitoIdController.setSelected(this.getSelected().getHabitoId());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Regra entities that are
     * retrieved from HabitoVerbo?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Regra page
     */
    public String navigateRegraList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Regra_items", this.getSelected().getRegraList());
        }
        return "/pages/primefaces/regra/index";
    }

}
