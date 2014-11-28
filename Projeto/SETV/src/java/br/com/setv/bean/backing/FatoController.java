package br.com.setv.bean.backing;

import br.com.setv.entidades.Fato;
import br.com.setv.bean.session.FatoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "fatoController")
@ViewScoped
public class FatoController extends AbstractController<Fato> {

    @EJB
    private FatoFacade ejbFacade;
    private HabitoVerboController habitoVerboIdController;
    private AlunoController alunoIdController;

    /**
     * Initialize the concrete Fato controller bean. The AbstractController
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
        habitoVerboIdController = context.getApplication().evaluateExpressionGet(context, "#{habitoVerboController}", HabitoVerboController.class);
        alunoIdController = context.getApplication().evaluateExpressionGet(context, "#{alunoController}", AlunoController.class);
    }

    public FatoController() {
        // Inform the Abstract parent controller of the concrete Fato?cap_first Entity
        super(Fato.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        habitoVerboIdController.setSelected(null);
        alunoIdController.setSelected(null);
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
     * Sets the "selected" attribute of the Aluno controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareAlunoId(ActionEvent event) {
        if (this.getSelected() != null && alunoIdController.getSelected() == null) {
            alunoIdController.setSelected(this.getSelected().getAlunoId());
        }
    }
}
