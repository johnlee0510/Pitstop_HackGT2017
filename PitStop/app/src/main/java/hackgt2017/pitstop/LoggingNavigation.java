package hackgt2017.pitstop;

import java.util.Date;

/**
 * Created by AustinJ on 10/14/17.
 */

public class LoggingNavigation {
    private String _userId;
    private String _screen;
    private Date _date;
    private String _action;
    private String _notes;

    public LoggingNavigation(String _userId, Date _date, String _screen, String _action,
                             String _notes) {
        this._userId = _userId;
        this._screen = _screen;
        this._date = _date;
        this._action = _action;
        this._notes = _notes;

    }

    public String get_userId() {
        return _userId;
    }

    public String get_screen() {
        return _screen;
    }

    public Date get_date() {
        return _date;
    }

    public String get_action() {
        return _action;
    }

    public String get_notes() {
        return _notes;
    }

    public String toString() {
        return "User id: " + _userId + " Date: " + _date + " Screen: " + _screen
                +" Action: " + _action + " Note: " + _notes;
    }
}
