package eveniment.Entities;

import eveniment.Entities.Enums.RowState;

public class Program {
    private int _id;
    private String _name;
    private float _price;
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

    public float getPrice() {
        return _price;
    }

    public void setPrice(float _price) {
        this._price = _price;
    }

    public RowState getState() {
        return _state;
    }

    public void setState(RowState _state) {
        this._state = _state;
    }
    
    
}
