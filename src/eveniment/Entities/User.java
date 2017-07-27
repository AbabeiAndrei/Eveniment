
package eveniment.Entities;

import eveniment.Entities.Enums.AccessLevel;

public class User {
    private int _id;
    private String _email;
    private String _phone;
    private String _password;
    private AccessLevel _accessLevel;

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String _email) {
        this._email = _email;
    }

    public String getPhone() {
        return _phone;
    }

    public void setPhone(String _phone) {
        this._phone = _phone;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public AccessLevel getAccessLevel() {
        return _accessLevel;
    }

    public void setAccessLevel(AccessLevel _accessLevel) {
        this._accessLevel = _accessLevel;
    }
}
