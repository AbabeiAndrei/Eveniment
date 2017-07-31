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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "event_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EventItem.findAll", query = "SELECT e FROM EventItem e")
    , @NamedQuery(name = "EventItem.findById", query = "SELECT e FROM EventItem e WHERE e.id = :id")
    , @NamedQuery(name = "EventItem.findByName", query = "SELECT e FROM EventItem e WHERE e.name = :name")
    , @NamedQuery(name = "EventItem.findByDescription", query = "SELECT e FROM EventItem e WHERE e.description = :description")
    , @NamedQuery(name = "EventItem.findByPrice", query = "SELECT e FROM EventItem e WHERE e.price = :price")
    , @NamedQuery(name = "EventItem.findByQuantity", query = "SELECT e FROM EventItem e WHERE e.quantity = :quantity")
    , @NamedQuery(name = "EventItem.findByRowState", query = "SELECT e FROM EventItem e WHERE e.rowState = :rowState")})
public class EventItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "quantity")
    private int quantity;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;
    @JoinColumn(name = "event_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Event eventId;
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Product productId;

    public EventItem() {
    }

    public EventItem(Integer id) {
        this.id = id;
    }

    public EventItem(Integer id, String name, String description, BigDecimal price, int quantity, String rowState) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    public Event getEventId() {
        return eventId;
    }

    public void setEventId(Event eventId) {
        this.eventId = eventId;
    }

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
        this.productId = productId;
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
        if (!(object instanceof EventItem)) {
            return false;
        }
        EventItem other = (EventItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.EventItem[ id=" + id + " ]";
    }
    
}
