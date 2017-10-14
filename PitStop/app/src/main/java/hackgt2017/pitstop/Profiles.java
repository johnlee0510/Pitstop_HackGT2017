package hackgt2017.pitstop;

/**
 * Created by AustinJ on 10/14/17.
 */

public class Profiles {

    private final String _userId;
    private final String _homeAddress;
    private final String _phone;

    /**
     * Profiles constructor
     * @param userId user ID
     * @param _homeAddress user name
     * @param _phone user password
     */
    public Profiles(String userId, String _homeAddress, String _phone) {
        this._userId = userId;
        this._homeAddress = _homeAddress;
        this._phone = _phone;

    }

    /**
     * Get user id
     * @return String
     */
    public String getUser(){
        return this._userId;
    }

    /**
     * Get Home Address
     * @return String
     */
    public String getHomeAddress(){
        return this._homeAddress;
    }

    /**
     * Get Phone Number
     * @return String
     */
    public String getPhone(){
        return this._phone;
    }

}
