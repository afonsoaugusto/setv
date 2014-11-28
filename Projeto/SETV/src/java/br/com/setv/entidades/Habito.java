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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Afonso
 */
@Entity
@Table(name = "HABITO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Habito.findAll", query = "SELECT h FROM Habito h"),
    @NamedQuery(name = "Habito.findById", query = "SELECT h FROM Habito h WHERE h.id = :id"),
    @NamedQuery(name = "Habito.findByHabito", query = "SELECT h FROM Habito h WHERE h.habito = :habito")})
public class Habito implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HABITOSEQ")
    @SequenceGenerator(name = "HABITOSEQ", sequenceName = "SEQ_HABITO_ID",allocationSize=1)
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "HABITO")
    private String habito;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitoId")
    private List<HabitoVerbo> habitoVerboList;

    public Habito() {
    }

    public Habito(BigDecimal id) {
        this.id = id;
    }

    public Habito(BigDecimal id, String habito) {
        this.id = id;
        this.habito = habito;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getHabito() {
        return habito;
    }

    public void setHabito(String habito) {
        this.habito = habito;
    }

    @XmlTransient
    public List<HabitoVerbo> getHabitoVerboList() {
        return habitoVerboList;
    }

    public void setHabitoVerboList(List<HabitoVerbo> habitoVerboList) {
        this.habitoVerboList = habitoVerboList;
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
        if (!(object instanceof Habito)) {
            return false;
        }
        Habito other = (Habito) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setv.entidades.Habito[ id=" + id + " ]";
    }

}
