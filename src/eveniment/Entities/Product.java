package eveniment.Entities;

import eveniment.Entities.Enums.PriceRate;
import eveniment.Entities.Enums.RowState;

public class Product {
    
    private int _id;
    private String _name;
    private String _description;
    private float _price;
    private PriceRate _rate;
    private int _categoryId;
    private RowState _state;
    
    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String _description) {
        this._description = _description;
    }

    public float getPrice() {
        return _price;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public PriceRate getRate() {
        return _rate;
    }

    public void setRate(PriceRate _rate) {
        this._rate = _rate;
    }

    public int getCategoryId() {
        return _categoryId;
    }

    public void setCategoryId(int _categoryId) {
        this._categoryId = _categoryId;
    }

    public RowState getState() {
        return _state;
    }

    public void setState(RowState _state) {
        this._state = _state;
    }

    
}
