/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrei
 */
@Entity
@Table(name = "program_products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramProducts.findAll", query = "SELECT p FROM ProgramProducts p")
    , @NamedQuery(name = "ProgramProducts.findByProgramId", query = "SELECT p FROM ProgramProducts p WHERE p.programProductsPK.programId = :programId")
    , @NamedQuery(name = "ProgramProducts.findByProductId", query = "SELECT p FROM ProgramProducts p WHERE p.programProductsPK.productId = :productId")
    , @NamedQuery(name = "ProgramProducts.findByName", query = "SELECT p FROM ProgramProducts p WHERE p.name = :name")
    , @NamedQuery(name = "ProgramProducts.findByPrice", query = "SELECT p FROM ProgramProducts p WHERE p.price = :price")
    , @NamedQuery(name = "ProgramProducts.findByAddMultiple", query = "SELECT p FROM ProgramProducts p WHERE p.addMultiple = :addMultiple")
    , @NamedQuery(name = "ProgramProducts.findByRowState", query = "SELECT p FROM ProgramProducts p WHERE p.rowState = :rowState")})
public class ProgramProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramProductsPK programProductsPK;
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "add_multiple")
    private short addMultiple;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Program program;

    public ProgramProducts() {
    }

    public ProgramProducts(ProgramProductsPK programProductsPK) {
        this.programProductsPK = programProductsPK;
    }

    public ProgramProducts(ProgramProductsPK programProductsPK, short addMultiple, String rowState) {
        this.programProductsPK = programProductsPK;
        this.addMultiple = addMultiple;
        this.rowState = rowState;
    }

    public ProgramProducts(int programId, int productId) {
        this.programProductsPK = new ProgramProductsPK(programId, productId);
    }

    public ProgramProductsPK getProgramProductsPK() {
        return programProductsPK;
    }

    public void setProgramProductsPK(ProgramProductsPK programProductsPK) {
        this.programProductsPK = programProductsPK;
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

    public short getAddMultiple() {
        return addMultiple;
    }

    public void setAddMultiple(short addMultiple) {
        this.addMultiple = addMultiple;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programProductsPK != null ? programProductsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramProducts)) {
            return false;
        }
        ProgramProducts other = (ProgramProducts) object;
        if ((this.programProductsPK == null && other.programProductsPK != null) || (this.programProductsPK != null && !this.programProductsPK.equals(other.programProductsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.ProgramProducts[ programProductsPK=" + programProductsPK + " ]";
    }
    
}
