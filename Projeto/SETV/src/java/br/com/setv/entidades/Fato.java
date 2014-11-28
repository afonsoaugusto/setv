/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.setv.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Afonso
 */
@Entity
@Table(name = "FATO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fato.findAll", query = "SELECT f FROM Fato f"),
    @NamedQuery(name = "Fato.findById", query = "SELECT f FROM Fato f WHERE f.id = :id")})
public class Fato implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FATOSEQ")
    @SequenceGenerator(name = "FATOSEQ", sequenceName = "SEQ_FATO_ID",allocationSize=1)
    private BigDecimal id;
    @JoinColumn(name = "HABITO_VERBO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private HabitoVerbo habitoVerboId;
    @JoinColumn(name = "ALUNO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Aluno alunoId;

    public Fato() {
    }

    public Fato(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public HabitoVerbo getHabitoVerboId() {
        return habitoVerboId;
    }

    public void setHabitoVerboId(HabitoVerbo habitoVerboId) {
        this.habitoVerboId = habitoVerboId;
    }

    public Aluno getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(Aluno alunoId) {
        this.alunoId = alunoId;
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
        if (!(object instanceof Fato)) {
            return false;
        }
        Fato other = (Fato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setv.entidades.Fato[ id=" + id + " ]";
    }

}
