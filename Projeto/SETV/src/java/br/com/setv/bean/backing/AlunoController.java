package br.com.setv.bean.backing;

import br.com.setv.entidades.Aluno;
import br.com.setv.bean.session.AlunoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "alunoController")
@ViewScoped
public class AlunoController extends AbstractController<Aluno> {

    @EJB
    private AlunoFacade ejbFacade;
    private CursoController cursoIdController;

    /**
     * Initialize the concrete Aluno controller bean. The AbstractController
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
        cursoIdController = context.getApplication().evaluateExpressionGet(context, "#{cursoController}", CursoController.class);
    }

    public AlunoController() {
        // Inform the Abstract parent controller of the concrete Aluno?cap_first Entity
        super(Aluno.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        cursoIdController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Fato entities that are
     * retrieved from Aluno?cap_first and returns the navigation outcome.
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
