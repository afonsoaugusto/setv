/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Afonso
 */
@Entity
@Table(name = "REGRA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Regra.findAll", query = "SELECT r FROM Regra r"),
    @NamedQuery(name = "Regra.findById", query = "SELECT r FROM Regra r WHERE r.id = :id"),
    @NamedQuery(name = "Regra.findByNegaHabito", query = "SELECT r FROM Regra r WHERE r.negaHabito = :negaHabito")})
public class Regra implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGRASEQ")
    @SequenceGenerator(name = "REGRASEQ", sequenceName = "SEQ_REGRA_ID",allocationSize=1)
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NEGA_HABITO")
    private Character negaHabito;
    @OneToMany(mappedBy = "regraPaiId")
    private List<Regra> regraList;
    @JoinColumn(name = "REGRA_PAI_ID", referencedColumnName = "ID")
    @ManyToOne
    private Regra regraPaiId;
    @JoinColumn(name = "HABITO_VERBO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HabitoVerbo habitoVerboId;
    @JoinColumn(name = "CURSO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Curso cursoId;

    public Regra() {
    }

    public Regra(BigDecimal id) {
        this.id = id;
    }

    public Regra(BigDecimal id, Character negaHabito) {
        this.id = id;
        this.negaHabito = negaHabito;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Character getNegaHabito() {
        return negaHabito;
    }

    public void setNegaHabito(Character negaHabito) {
        this.negaHabito = negaHabito;
    }

    @XmlTransient
    public List<Regra> getRegraList() {
        return regraList;
    }

    public void setRegraList(List<Regra> regraList) {
        this.regraList = regraList;
    }

    public Regra getRegraPaiId() {
        return regraPaiId;
    }

    public void setRegraPaiId(Regra regraPaiId) {
        this.regraPaiId = regraPaiId;
    }

    public HabitoVerbo getHabitoVerboId() {
        return habitoVerboId;
    }

    public void setHabitoVerboId(HabitoVerbo habitoVerboId) {
        this.habitoVerboId = habitoVerboId;
    }

    public Curso getCursoId() {
        return cursoId;
    }

    public void setCursoId(Curso cursoId) {
        this.cursoId = cursoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Regra)) {
            return false;
        }
        Regra other = (Regra) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setv.entidades.Regra[ id=" + id + " ]";
    }
    
}
