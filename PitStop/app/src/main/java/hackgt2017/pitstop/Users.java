package hackgt2017.pitstop;

/**
 * Created by AustinJ on 10/14/17.
 */

public class Users {

    //private variables
    private String _userId;
    private String _passWord;
    private String _userName;
    private String _userEmail;
    private String _userType;
    // Empty constructor
    public Users(){

    }

    /**
     * Users constructor
     * @param userId user ID
     * @param _userName user name
     * @param _passWord user password
     * @param _userEmail user email
     * @param _userType Registered type (registered user, worker, manager, admin)
     */
    public Users(String userId, String _passWord, String _userName, String _userEmail,
                 String _userType){
        this._userId = userId;
        this._passWord = _passWord;
        this._userName = _userName;
        this._userEmail = _userEmail;
        this._userType = _userType;
    }
    /**
     * Get user id
     * @return String
     */
    public String getUser(){
        return this._userId;
    }

    /**
     * Get user password
     * @return String
     */
    public String getPassword(){
        return this._passWord;
    }

    /**
     * Get user Name
     * @return String
     */
    public String getName(){
        return this._userName;
    }

    /**
     * Get user email address
     * @return String
     */
    public String getEmail(){
        return this._userEmail;
    }

    /**
     * Get user Registered Type
     * @return String
     */
    public String getType(){
        return this._userType;
    }

    @Override
    public String toString(){
        switch(getType()) {
            case "Worker":
                return "Hello Employee , let's get to work!";
            case "User":
                return "Hello User, let's explore!";
            case "Manager":
                return "Hello All Powerful Ruler , let's create!";
            default:
                return "Hi";
        }
//        if (getType().equals("Worker")) {
//            return "Hello Employee , let's get to work!";
//        } else if (getType().equals("User")){
//            return "Hello User, let's explore!";
//
//        }else if (getType().equals("Manager")){
//            return "Hello All Powerful Ruler , let's create!";
//        }else{
//
//            return "Hi";
//        }
    }
}
