/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Andrei
 */
@Entity
@Table(name = "period")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Period.findAll", query = "SELECT p FROM Period p")
    , @NamedQuery(name = "Period.findById", query = "SELECT p FROM Period p WHERE p.id = :id")
    , @NamedQuery(name = "Period.findByFrom", query = "SELECT p FROM Period p WHERE p.from = :from")
    , @NamedQuery(name = "Period.findByTo", query = "SELECT p FROM Period p WHERE p.to = :to")
    , @NamedQuery(name = "Period.findByPrice", query = "SELECT p FROM Period p WHERE p.price = :price")
    , @NamedQuery(name = "Period.findByRowState", query = "SELECT p FROM Period p WHERE p.rowState = :rowState")})
public class Period implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "`from`")
    @Temporal(TemporalType.DATE)
    private Date from;
    @Basic(optional = false)
    @Column(name = "`to`")
    @Temporal(TemporalType.DATE)
    private Date to;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;

    public Period() {
    }

    public Period(Integer id) {
        this.id = id;
    }

    public Period(Integer id, Date from, Date to, BigDecimal price, String rowState) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.price = price;
        this.rowState = rowState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Period)) {
            return false;
        }
        Period other = (Period) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.Period[ id=" + id + " ]";
    }
    
}
