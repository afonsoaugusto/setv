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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Afonso
 */
@Entity
@Table(name = "HABITO_VERBO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HabitoVerbo.findAll", query = "SELECT h FROM HabitoVerbo h"),
    @NamedQuery(name = "HabitoVerbo.findById", query = "SELECT h FROM HabitoVerbo h WHERE h.id = :id")})
public class HabitoVerbo implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HABITOVERBOSEQ")
    @SequenceGenerator(name = "HABITOVERBOSEQ", sequenceName = "SEQ_HABITO_VERBO_ID",allocationSize=1)
    private BigDecimal id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitoVerboId")
    private List<Fato> fatoList;
    @JoinColumn(name = "VERBO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Verbo verboId;
    @JoinColumn(name = "HABITO_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Habito habitoId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "habitoVerboId")
    private List<Regra> regraList;
    
    @Transient
    private String habitoVerboDesc;

    public HabitoVerbo() {
        
    }

    public HabitoVerbo(BigDecimal id) {
        this.id = id;
    }

    public String getHabitoVerboDesc() {
        habitoVerboDesc = "";
        habitoVerboDesc += verboId.getVerbo();
        habitoVerboDesc += " " + habitoId.getHabito();
        return habitoVerboDesc;
    }
    
    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    @XmlTransient
    public List<Fato> getFatoList() {
        return fatoList;
    }

    public void setFatoList(List<Fato> fatoList) {
        this.fatoList = fatoList;
    }

    public Verbo getVerboId() {
        return verboId;
    }

    public void setVerboId(Verbo verboId) {
        this.verboId = verboId;
    }

    public Habito getHabitoId() {
        return habitoId;
    }

    public void setHabitoId(Habito habitoId) {
        this.habitoId = habitoId;
    }

    @XmlTransient
    public List<Regra> getRegraList() {
        return regraList;
    }

    public void setRegraList(List<Regra> regraList) {
        this.regraList = regraList;
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
        if (!(object instanceof HabitoVerbo)) {
            return false;
        }
        HabitoVerbo other = (HabitoVerbo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.setv.entidades.HabitoVerbo[ id=" + id + " ]";
    }
    
}
