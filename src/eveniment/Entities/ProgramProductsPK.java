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
public class ProgramProductsPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "program_id")
    private int programId;
    @Basic(optional = false)
    @Column(name = "product_id")
    private int productId;

    public ProgramProductsPK() {
    }

    public ProgramProductsPK(int programId, int productId) {
        this.programId = programId;
        this.productId = productId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) programId;
        hash += (int) productId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramProductsPK)) {
            return false;
        }
        ProgramProductsPK other = (ProgramProductsPK) object;
        if (this.programId != other.programId) {
            return false;
        }
        if (this.productId != other.productId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eveniment.Entities.ProgramProductsPK[ programId=" + programId + ", productId=" + productId + " ]";
    }
    
}
