package hackgt2017.pitstop;

import java.util.Date;

/**
 * Created by AustinJ on 10/14/17.
 */

public class LoggingSignin {

    private String _userId;
    private String _passWord;
    private boolean _success;
    private Date _date;
    private int _attempt;
    private boolean _lockout;
    private String _notes;


    public LoggingSignin(String _userId, String _passWord, Date _date, boolean _success, int _attempt,
                         boolean _lockout, String _notes) {
        this._userId = _userId;
        this._passWord = _passWord;
        this._success = _success;
        this._attempt = _attempt;
        this._lockout = _lockout;
        this._notes = _notes;
        this._date = _date;

    }


    public String get_userId() {
        return _userId;
    }

    public String get_passWord() {
        return _passWord;
    }

    public boolean is_success() {
        return _success;
    }

    public int get_attempt() {
        return _attempt;
    }

    public boolean is_lockout() {
        return _lockout;
    }

    public String get_notes() {
        return _notes;
    }

    public Date get_date() {
        return _date;
    }

    public String toString() {
        return "User id: " + _userId + " Date: " + _date + " Log in attempt: " + _attempt
                +" id locked out: " + _lockout+ " Note: " + _notes;
    }
}
