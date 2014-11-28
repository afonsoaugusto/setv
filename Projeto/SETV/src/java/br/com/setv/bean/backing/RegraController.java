package br.com.setv.bean.backing;

import br.com.setv.entidades.Regra;
import br.com.setv.bean.session.RegraFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "regraController")
@ViewScoped
public class RegraController extends AbstractController<Regra> {

    @EJB
    private RegraFacade ejbFacade;
    //private RegraController regraPaiIdController;
    private HabitoVerboController habitoVerboIdController;
    private CursoController cursoIdController;

    /**
     * Initialize the concrete Regra controller bean. The AbstractController
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
//        regraPaiIdController = context.getApplication().evaluateExpressionGet(context, "#{regraController}", RegraController.class);
        habitoVerboIdController = context.getApplication().evaluateExpressionGet(context, "#{habitoVerboController}", HabitoVerboController.class);
        cursoIdController = context.getApplication().evaluateExpressionGet(context, "#{cursoController}", CursoController.class);
    }

    public RegraController() {
        // Inform the Abstract parent controller of the concrete Regra?cap_first Entity
        super(Regra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
//        regraPaiIdController.setSelected(null);
        habitoVerboIdController.setSelected(null);
        cursoIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Regra entities that are
     * retrieved from Regra?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Regra page
     */
    public String navigateRegraList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Regra_items", this.getSelected().getRegraList());
        }
        return "/pages/primefaces/regra/index";
    }

    /**
     * Sets the "selected" attribute of the Regra controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareRegraPaiId(ActionEvent event) {
//        if (this.getSelected() != null && regraPaiIdController.getSelected() == null) {
  //          regraPaiIdController.setSelected(this.getSelected().getRegraPaiId());
    //    }
    }

    /**
     * Sets the "selected" attribute of the HabitoVerbo controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareHabitoVerboId(ActionEvent event) {
        if (this.getSelected() != null && habitoVerboIdController.getSelected() == null) {
            habitoVerboIdController.setSelected(this.getSelected().getHabitoVerboId());
        }
    }

    /**
     * Sets the "selected" attribute of the Curso controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCursoId(ActionEvent event) {
        if (this.getSelected() != null && cursoIdController.getSelected() == null) {
            cursoIdController.setSelected(this.getSelected().getCursoId());
        }
    }
}
