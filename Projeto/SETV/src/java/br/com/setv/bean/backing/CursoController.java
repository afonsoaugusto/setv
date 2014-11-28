package br.com.setv.bean.backing;

import br.com.setv.entidades.Curso;
import br.com.setv.bean.session.CursoFacade;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.annotation.PostConstruct;

@ManagedBean(name = "cursoController")
@ViewScoped
public class CursoController extends AbstractController<Curso> {

    @EJB
    private CursoFacade ejbFacade;

    /**
     * Initialize the concrete Curso controller bean. The AbstractController
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

    public CursoController() {
        // Inform the Abstract parent controller of the concrete Curso?cap_first Entity
        super(Curso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Aluno entities that are
     * retrieved from Curso?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Aluno page
     */
    public String navigateAlunoList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Aluno_items", this.getSelected().getAlunoList());
        }
        return "/pages/primefaces/aluno/index";
    }

    /**
     * Sets the "items" attribute with a collection of Regra entities that are
     * retrieved from Curso?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Regra page
     */
    public String navigateRegraList() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Regra_items", this.getSelected().getRegraList());
        }
        return "/pages/primefaces/regra/index";
    }

    public Curso getCursoId(int i) {
        BigDecimal bD = new BigDecimal(i);
        return ejbFacade.find(bD);
    }

}
