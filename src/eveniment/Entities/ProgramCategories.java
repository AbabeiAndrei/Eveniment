
package eveniment.Entities;

public class ProgramCategories {
    private int _programId;
    private int _categoryId;
    private String _name;
    private Boolean _addMultiple;

    public int getProgramId() {
        return _programId;
    }

    public void setProgramId(int _programId) {
        this._programId = _programId;
    }

    public int getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(int _categoryId) {
        this._categoryId = _categoryId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
    
    public Boolean getAddMultiple() {
        return _addMultiple;
    }

    public void setAddMultiple(Boolean _addMultiple) {
        this._addMultiple = _addMultiple;
    }
}
