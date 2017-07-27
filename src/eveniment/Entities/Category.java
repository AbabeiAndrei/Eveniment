
package eveniment.Entities;

import eveniment.Entities.Enums.RowState;

public class Category {
    private int _id;
    private String _name;
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

    public RowState getState() {
        return _state;
    }

    public void setState(RowState _state) {
        this._state = _state;
    }
    
    
}
