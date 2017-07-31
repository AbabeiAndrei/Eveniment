/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Andrei
 */
@Entity
@Table(name = "event")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Event.findAll", query = "SELECT e FROM Event e")
    , @NamedQuery(name = "Event.findById", query = "SELECT e FROM Event e WHERE e.id = :id")
    , @NamedQuery(name = "Event.findByDate", query = "SELECT e FROM Event e WHERE e.date = :date")
    , @NamedQuery(name = "Event.findByNumberOfPersons", query = "SELECT e FROM Event e WHERE e.numberOfPersons = :numberOfPersons")
    , @NamedQuery(name = "Event.findByCreatedAt", query = "SELECT e FROM Event e WHERE e.createdAt = :createdAt")
    , @NamedQuery(name = "Event.findByRowState", query = "SELECT e FROM Event e WHERE e.rowState = :rowState")})
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @Column(name = "number_of_persons")
    private int numberOfPersons;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;
    @JoinColumn(name = "program_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Program programId;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @JoinColumn(name = "for_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users forUser;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventId")
    private Collection<EventItem> eventItemCollection;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public Event(Integer id, Date date, int numberOfPersons, Date createdAt, String rowState) {
        this.id = id;
        this.date = date;
        this.numberOfPersons = numberOfPersons;
        this.createdAt = createdAt;
        this.rowState = rowState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getRowState() {
        return rowState;
    }

    public void setRowState(String rowState) {
        this.rowState = rowState;
    }

    public Program getProgramId() {
        return programId;
    }

    public void setProgramId(Program programId) {
        this.programId = programId;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }

    public Users getForUser() {
        return forUser;
    }

    public void setForUser(Users forUser) {
        this.forUser = forUser;
    }

    @XmlTransient
    public Collection<EventItem> getEventItemCollection() {
        return eventItemCollection;
    }

    public void setEventItemCollection(Collection<EventItem> eventItemCollection) {
        this.eventItemCollection = eventItemCollection;
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
        if (!(object instanceof Event)) {
            return false;
        }
        Event other = (Event) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.Event[ id=" + id + " ]";
    }
    
}
