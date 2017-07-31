/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrei
 */
@Entity
@Table(name = "program")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Program.findAll", query = "SELECT p FROM Program p")
    , @NamedQuery(name = "Program.findById", query = "SELECT p FROM Program p WHERE p.id = :id")
    , @NamedQuery(name = "Program.findByName", query = "SELECT p FROM Program p WHERE p.name = :name")
    , @NamedQuery(name = "Program.findByPrice", query = "SELECT p FROM Program p WHERE p.price = :price")
    , @NamedQuery(name = "Program.findByRowState", query = "SELECT p FROM Program p WHERE p.rowState = :rowState")})
public class Program implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private Collection<ProgramCategories> programCategoriesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programId")
    private Collection<Event> eventCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "program")
    private Collection<ProgramProducts> programProductsCollection;

    public Program() {
    }

    public Program(Integer id) {
        this.id = id;
    }

    public Program(Integer id, String name, BigDecimal price, String rowState) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rowState = rowState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    @XmlTransient
    public Collection<ProgramCategories> getProgramCategoriesCollection() {
        return programCategoriesCollection;
    }

    public void setProgramCategoriesCollection(Collection<ProgramCategories> programCategoriesCollection) {
        this.programCategoriesCollection = programCategoriesCollection;
    }

    @XmlTransient
    public Collection<Event> getEventCollection() {
        return eventCollection;
    }

    public void setEventCollection(Collection<Event> eventCollection) {
        this.eventCollection = eventCollection;
    }

    @XmlTransient
    public Collection<ProgramProducts> getProgramProductsCollection() {
        return programProductsCollection;
    }

    public void setProgramProductsCollection(Collection<ProgramProducts> programProductsCollection) {
        this.programProductsCollection = programProductsCollection;
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
        if (!(object instanceof Program)) {
            return false;
        }
        Program other = (Program) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.Program[ id=" + id + " ]";
    }
    
}
