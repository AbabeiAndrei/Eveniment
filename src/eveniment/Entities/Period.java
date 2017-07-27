package eveniment.Entities;

import eveniment.Entities.Enums.RowState;
import java.util.Date;

public class Period {
    private int _id;
    private Date _from;
    private Date _to;
    private float _price;
    private RowState _state;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Date getFrom() {
        return _from;
    }

    public void setFrom(Date _from) {
        this._from = _from;
    }

    public Date getTo() {
        return _to;
    }

    public void setTo(Date _to) {
        this._to = _to;
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
