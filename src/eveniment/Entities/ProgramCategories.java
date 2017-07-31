/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
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
@Table(name = "program_categories")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProgramCategories.findAll", query = "SELECT p FROM ProgramCategories p")
    , @NamedQuery(name = "ProgramCategories.findByProgramId", query = "SELECT p FROM ProgramCategories p WHERE p.programCategoriesPK.programId = :programId")
    , @NamedQuery(name = "ProgramCategories.findByCategoryId", query = "SELECT p FROM ProgramCategories p WHERE p.programCategoriesPK.categoryId = :categoryId")
    , @NamedQuery(name = "ProgramCategories.findByName", query = "SELECT p FROM ProgramCategories p WHERE p.name = :name")
    , @NamedQuery(name = "ProgramCategories.findByAddMultiple", query = "SELECT p FROM ProgramCategories p WHERE p.addMultiple = :addMultiple")
    , @NamedQuery(name = "ProgramCategories.findByRowState", query = "SELECT p FROM ProgramCategories p WHERE p.rowState = :rowState")})
public class ProgramCategories implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProgramCategoriesPK programCategoriesPK;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "add_multiple")
    private short addMultiple;
    @Basic(optional = false)
    @Column(name = "row_state")
    private String rowState;
    @JoinColumn(name = "program_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Program program;

    public ProgramCategories() {
    }

    public ProgramCategories(ProgramCategoriesPK programCategoriesPK) {
        this.programCategoriesPK = programCategoriesPK;
    }

    public ProgramCategories(ProgramCategoriesPK programCategoriesPK, String name, short addMultiple, String rowState) {
        this.programCategoriesPK = programCategoriesPK;
        this.name = name;
        this.addMultiple = addMultiple;
        this.rowState = rowState;
    }

    public ProgramCategories(int programId, int categoryId) {
        this.programCategoriesPK = new ProgramCategoriesPK(programId, categoryId);
    }

    public ProgramCategoriesPK getProgramCategoriesPK() {
        return programCategoriesPK;
    }

    public void setProgramCategoriesPK(ProgramCategoriesPK programCategoriesPK) {
        this.programCategoriesPK = programCategoriesPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (programCategoriesPK != null ? programCategoriesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramCategories)) {
            return false;
        }
        ProgramCategories other = (ProgramCategories) object;
        if ((this.programCategoriesPK == null && other.programCategoriesPK != null) || (this.programCategoriesPK != null && !this.programCategoriesPK.equals(other.programCategoriesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.ProgramCategories[ programCategoriesPK=" + programCategoriesPK + " ]";
    }
    
}
