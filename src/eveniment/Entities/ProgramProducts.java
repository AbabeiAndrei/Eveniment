
package eveniment.Entities;

public class ProgramProducts {
    private int _programId;
    private int _productId;
    private String _name;
    private float _price;
    private Boolean _addMultiple;

    public int getProgramId() {
        return _programId;
    }

    public void setProgramId(int _programId) {
        this._programId = _programId;
    }

    public int getProductId() {
        return _productId;
    }

    public void setProductId(int _productId) {
        this._productId = _productId;
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

    public float getPrice() {
        return _price;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public void setAddMultiple(Boolean _addMultiple) {
        this._addMultiple = _addMultiple;
    }
}
