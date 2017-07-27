
package eveniment.Entities;

import eveniment.Entities.Enums.EventState;
import java.sql.Timestamp;
import java.util.Date;

public class Event {
    private int _id;
    private Date _date;
    private int _numberOfPersons;
    private int _createdBy;
    private Timestamp _createdAt;
    private int _forUser;
    private int _programId;
    private EventState _state;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public Date getDate() {
        return _date;
    }

    public void setDate(Date _date) {
        this._date = _date;
    }

    public int getNumberOfPersons() {
        return _numberOfPersons;
    }

    public void setNumberOfPersons(int _numberOfPersons) {
        this._numberOfPersons = _numberOfPersons;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int _createdBy) {
        this._createdBy = _createdBy;
    }

    public Timestamp getCreatedAt() {
        return _createdAt;
    }

    public void setCreatedAt(Timestamp _createdAt) {
        this._createdAt = _createdAt;
    }

    public int getForUser() {
        return _forUser;
    }

    public void setForUser(int _forUser) {
        this._forUser = _forUser;
    }

    public int getProgramId() {
        return _programId;
    }

    public void setProgramId(int _programId) {
        this._programId = _programId;
    }

    public EventState getState() {
        return _state;
    }

    public void setState(EventState _state) {
        this._state = _state;
    }
    
}
