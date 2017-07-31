/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eveniment.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Andrei
 */
@Embeddable
public class ProgramCategoriesPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "program_id")
    private int programId;
    @Basic(optional = false)
    @Column(name = "category_id")
    private int categoryId;

    public ProgramCategoriesPK() {
    }

    public ProgramCategoriesPK(int programId, int categoryId) {
        this.programId = programId;
        this.categoryId = categoryId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) programId;
        hash += (int) categoryId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramCategoriesPK)) {
            return false;
        }
        ProgramCategoriesPK other = (ProgramCategoriesPK) object;
        if (this.programId != other.programId) {
            return false;
        }
        if (this.categoryId != other.categoryId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.ProgramCategoriesPK[ programId=" + programId + ", categoryId=" + categoryId + " ]";
    }
    
}
