/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.vocacional;

import br.com.setv.bean.backing.AlunoController;
import br.com.setv.bean.backing.CursoController;
import br.com.setv.bean.backing.HabitoVerboController;
import br.com.setv.bean.backing.util.JsfUtil;
import br.com.setv.entidades.Aluno;
import br.com.setv.entidades.Curso;
import br.com.setv.entidades.HabitoVerbo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Afonso
 */
@ManagedBean(name = "vocacionalController")
@ViewScoped
public class VocacionalController implements Serializable {

    @EJB
    private PrologService prologService;
    private AlunoController alunoController;
    private CursoController cursoController;
    private HabitoVerboController habitoVerboController;
    private Aluno aluno;
    private List<HabitoVerbo> listHabitoVerbo;
    private List<Curso> listCurso;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        alunoController = context.getApplication().evaluateExpressionGet(context, "#{alunoController}", AlunoController.class);
        habitoVerboController = context.getApplication().evaluateExpressionGet(context, "#{habitoVerboController}", HabitoVerboController.class);
        cursoController = context.getApplication().evaluateExpressionGet(context, "#{cursoController}", CursoController.class);

    }

    public void resetParents() {
        habitoVerboController.setSelected(null);
        alunoController.setSelected(null);
    }

    public void createAvaliacao(ActionEvent event) {
        try {
            final List<String> executeAnalise = prologService.executeAnalise(aluno);
            populaListaCursos(executeAnalise);
            associaCursoAluno();
        } catch (Exception ex) {
            JsfUtil.addErrorMessage(ex.getMessage());
            Logger.getLogger(VocacionalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void populaListaCursos(final List<String> executeAnalise) {
        listCurso = new ArrayList<>();
        for (int i = 0; i < executeAnalise.size(); i++) {
            final String get1 = String.valueOf(executeAnalise.get(i));
            listCurso.add(cursoController.getCursoId(getID(get1)));
        }
//        for (String eA : executeAnalise) {
//            listCurso.add(cursoController.getCursoId(getID(eA)));
//        }
    }

    private int getID(String eA) {
        int retorno = 0;
        String str = eA;
        str = str.replaceAll("[^-?0-9]+", " ");
        final List<String> asList = Arrays.asList(str.trim().split(" "));
        retorno = Integer.parseInt(asList.get(asList.size() - 1));
        return retorno;
    }

    public PrologService getPrologService() {
        return prologService;
    }

    public void setPrologService(PrologService prologService) {
        this.prologService = prologService;
    }

    public AlunoController getAlunoController() {
        return alunoController;
    }

    public void setAlunoController(AlunoController alunoController) {
        this.alunoController = alunoController;
    }

    public HabitoVerboController getHabitoVerboController() {
        return habitoVerboController;
    }

    public void setHabitoVerboController(HabitoVerboController habitoVerboController) {
        this.habitoVerboController = habitoVerboController;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<HabitoVerbo> getListHabitoVerbo() {
        return listHabitoVerbo;
    }

    public void setListHabitoVerbo(List<HabitoVerbo> listHabitoVerbo) {
        this.listHabitoVerbo = listHabitoVerbo;
    }

    public CursoController getCursoController() {
        return cursoController;
    }

    public void setCursoController(CursoController cursoController) {
        this.cursoController = cursoController;
    }

    public List<Curso> getListCurso() {
        return listCurso;
    }

    public void setListCurso(List<Curso> listCurso) {
        this.listCurso = listCurso;
    }

    private String getNomeCursos() {
        StringBuilder sb = new StringBuilder();
        for (Curso curso : listCurso) {
            sb.append(curso.getCurso()).append(" ");
        }
        return sb.toString();
    }

    private void associaCursoAluno() {
        if (!listCurso.isEmpty()) {
            if (listCurso.get(0) != null) {
                aluno.setCursoId(listCurso.get(0));
                alunoController.setSelected(aluno);
                alunoController.save(null);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/SETV_BUNDLE").getString("VocacionalCursosEscolhidos"), getNomeCursos()));
            } else {
                JsfUtil.addWarnMessage(ResourceBundle.getBundle("/SETV_BUNDLE").getString("VocacionalListaVazia"));
            }
        } else {
            JsfUtil.addWarnMessage(ResourceBundle.getBundle("/SETV_BUNDLE").getString("VocacionalListaVazia"));
        }
    }

}
