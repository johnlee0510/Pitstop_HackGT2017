package hackgt2017.pitstop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by AustinJ on 10/14/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper{
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 5;

    // Database Name
    private static final String DATABASE_NAME = "DripDrop_v2";

    // Table Names
    private static final String TABLE_USERS = "Registered_Accounts";
    private static final String TABLE_PROFILES = "Registered_Profiles";
    private static final String TABLE_WATER_PURITY = "Registered_WaterPurity";
    private static final String TABLE_WATER_SOURCE = "Registered_WaterSource";
    private static final String TABLE_LOGGING_SIGNIN = "Logging_Signin";
    private static final String TABLE_LOGGING_REPORTING = "Logging_Reporting";
    private static final String TABLE_Logging_ADMIN = "Logging_Admin";

    private static String currentUser;

    // Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_USERS = "userID";
    private static final String KEY_PASS = "passWord";
    private static final String KEY_NAME = "userName";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TYPE = "userType";
    private static final String KEY_HOME_ADDRESS = "homeAddress";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_REPORT_ID = "reportID";
    private static final String KEY_DATE = "reportDate";
    private static final String KEY_SUBMIT_BY = "reporterName";
    private static final String KEY_LATITUDE = "locLatitude";
    private static final String KEY_LONGITUDE = "locLongitude";
    private static final String KEY_WATER_TYPE = "waterType";
    private static final String KEY_CONDITION = "waterCondition";
    private static final String KEY_WORKER = "workerName";
    private static final String KEY_VIRUS = "virusPPM";
    private static final String KEY_CONTAMINANT = "contaminantPPM";
    private static final String KEY_SUCCESS = "loginSuccessful";
    private static final String KEY_INVALIDATTEMPT = "invalidTrys";
    private static final String KEY_LOCKOUT = "accountLockout";
    private static final String KEY_NOTES = "additionalNotes";
    private static final String KEY_SCREEN = "screenNavigation";
    private static final String KEY_ACTION = "actionSelected";
    private static final String KEY_LOGDATE = "DateTime";

    /**
     * DatabaseHandler constructor
     * @param context the activity the database is handling
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * onCreate constructor that creates the database tables for the passed database
     * @param db the activity the database is handling
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERS + " TEXT,"
                + KEY_PASS + " TEXT," + KEY_NAME + " TEXT,"
                + KEY_EMAIL + " TEXT," + KEY_TYPE + " TEXT,"
                + KEY_HOME_ADDRESS + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_PROFILES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_USERS + " TEXT,"
                + KEY_HOME_ADDRESS + " TEXT," + KEY_PHONE + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_WATER_SOURCE + "("
                + KEY_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_SUBMIT_BY + " TEXT," + KEY_LATITUDE + " DOUBLE,"
                + KEY_LONGITUDE + " DOUBLE," + KEY_WATER_TYPE + " TEXT,"
                + KEY_CONDITION + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_WATER_PURITY + "("
                + KEY_REPORT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                + KEY_DATE + " TEXT," + KEY_WORKER + " TEXT," + KEY_LATITUDE + " DOUBLE,"
                + KEY_LONGITUDE + " DOUBLE," + KEY_CONDITION + " TEXT,"
                + KEY_VIRUS + " DOUBLE," + KEY_CONTAMINANT + " DOUBLE" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_LOGGING_SIGNIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                + KEY_PASS + " TEXT," + KEY_LOGDATE + " TEXT," + KEY_SUCCESS + " INTEGER,"
                + KEY_INVALIDATTEMPT + " INTEGER," + KEY_LOCKOUT + " INTEGER,"
                + KEY_NOTES + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_LOGGING_REPORTING + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                + KEY_LOGDATE + " TEXT," + KEY_SCREEN + " TEXT," + KEY_ACTION + " TEXT,"
                + KEY_NOTES + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);

        CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_Logging_ADMIN + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                + KEY_LOGDATE + " TEXT," + KEY_SCREEN + " TEXT," + KEY_ACTION + " TEXT,"
                + KEY_NOTES + " TEXT" + ")";
        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    private void createNewDb(SQLiteDatabase db, int oldVersion, int newVersion) {
        String CREATE_ACCOUNT_TABLE = "";

        if (newVersion == 5) {
            CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_LOGGING_SIGNIN + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                    + KEY_PASS + " TEXT," + KEY_LOGDATE + " TEXT," + KEY_SUCCESS + " INTEGER,"
                    + KEY_INVALIDATTEMPT + " INTEGER," + KEY_LOCKOUT + " INTEGER,"
                    + KEY_NOTES + " TEXT" + ")";
            db.execSQL(CREATE_ACCOUNT_TABLE);

            CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_LOGGING_REPORTING + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                    + KEY_LOGDATE + " TEXT," + KEY_SCREEN + " TEXT," + KEY_ACTION + " TEXT,"
                    + KEY_NOTES + " TEXT" + ")";
            db.execSQL(CREATE_ACCOUNT_TABLE);

            CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_Logging_ADMIN + "("
                    + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_USERS + " TEXT,"
                    + KEY_LOGDATE + " TEXT," + KEY_SCREEN + " TEXT," + KEY_ACTION + " TEXT,"
                    + KEY_NOTES + " TEXT" + ")";
            db.execSQL(CREATE_ACCOUNT_TABLE);
        }
    }


    /**
     * onUpgrade constructor drops then recreates the database tables if the database version is
     * newer
     * @param db the activity the database is handling
     * @param oldVersion the old database version number
     * @param newVersion the new database version number
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        if (newVersion < 5 ) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILES);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_SOURCE);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATER_PURITY);
            onCreate(db);// Create tables again
        } else if (newVersion == 5) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGGING_SIGNIN);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGGING_REPORTING);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_Logging_ADMIN);
        }
        createNewDb(db, oldVersion, newVersion);
    }

    /**
     * addUsers method adds Users to the Registered_Accounts table
     * @param users the users you are adding to the table
     */
    void addUsers(Users users) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS, users.getUser()); // User Name
        values.put(KEY_PASS, users.getPassword()); // Password
        values.put(KEY_NAME, users.getName()); // User Name
        values.put(KEY_EMAIL, users.getEmail()); // Password
        values.put(KEY_TYPE, users.getType()); // Password

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    /**
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void loginLogging(LoggingSignin signin) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Date dateObj = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put(KEY_USERS, signin.get_userId()); // User Name
        values.put(KEY_PASS, signin.get_passWord()); // Password
        values.put(KEY_LOGDATE, sdf.format(signin.get_date()));
        values.put(KEY_SUCCESS, signin.is_success()); // User Name
        values.put(KEY_INVALIDATTEMPT, signin.get_attempt()); // User Name
        values.put(KEY_LOCKOUT, signin.is_lockout()); // User Name
        values.put(KEY_NOTES, signin.get_notes()); // User Name
        // Inserting Row
        db.insert(TABLE_LOGGING_SIGNIN, null, values);
        db.close(); // Closing database connection
    }

    /**
     * db.actionLogging(new LoggingNavigation(userId, new Date(), , attempt, "N"));
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void actionLogging(LoggingNavigation nav) {
        SQLiteDatabase db = this.getWritableDatabase();
        //Date dateObj = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ContentValues values = new ContentValues();
        values.put(KEY_USERS, nav.get_userId()); // User Name
        values.put(KEY_LOGDATE, sdf.format(nav.get_date()));
        values.put(KEY_SCREEN, nav.get_screen()); // User Name
        values.put(KEY_ACTION, nav.get_action()); // User Name
        values.put(KEY_NOTES, nav.get_notes()); // User Name
        // Inserting Row
        db.insert(TABLE_LOGGING_REPORTING, null, values);
        db.close(); // Closing database connection
    }

    /**
     * addProfiles method adds user profiles to the Registered_Profiles table
     * @param profile the profile you are adding to the table
     */
    void addProfiles(Profiles profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS, profile.getUser()); // User Name
        values.put(KEY_HOME_ADDRESS, profile.getHomeAddress()); // Password
        values.put(KEY_PHONE, profile.getPhone()); // User Name

        // Inserting Row
        db.insert(TABLE_PROFILES, null, values);
        db.close(); // Closing database connection
    }

    /**
     * addSource reports method adds users to the TABLE_WATER_SOURCE table
     * @param ws the water source report you wish to add to the table
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
//    public void addSourceReport(WaterSource ws) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Date dateObj = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERS, currentUser); // User Name
//        values.put(KEY_DATE, sdf.format(dateObj)); // User Name
//        values.put(KEY_SUBMIT_BY, ws.getName()); // User Name
//        values.put(KEY_LATITUDE, ws.getLatitude()); // Password
//        values.put(KEY_LONGITUDE, ws.getLongitude()); // Password
//        values.put(KEY_WATER_TYPE, ws.getWaterType()); // Password
//        values.put(KEY_CONDITION, ws.getCondition()); // Password
//
//        // Inserting Row
//        db.insert(TABLE_WATER_SOURCE, null, values);
//        db.close(); // Closing database connection
//    }
//
//    /**
//     * addPurityReport method adds users to the TABLE_WATER_PURITY table
//     * @param wp the purity  report you are adding to the table
//     */
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    void addPurityReport(WaterPurity wp) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        Date dateObj = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_USERS, currentUser); // User ID
//        values.put(KEY_DATE, sdf.format(dateObj)); // Date
//        values.put(KEY_WORKER, wp.getName()); // Worker Name
//        values.put(KEY_LATITUDE, wp.getLatitude()); // Latitude
//        values.put(KEY_LONGITUDE, wp.getLongitude()); // Longitude
//        values.put(KEY_CONDITION, wp.getCondition()); // Water Condition
//        values.put(KEY_VIRUS, wp.getVirusPPM()); // Virus PPM
//        values.put(KEY_CONTAMINANT, wp.getContaminantPPM()); // Contaminant PPM
//
//        // Inserting Row
//        db.insert(TABLE_WATER_PURITY, null, values);
//        db.close(); // Closing database connection
//    }

    /*
     * deleteAllAccounts method deletes all users in the Registered_Accounts table
     */
    /*    public void deleteAllAccounts() //Deletes all data in the database
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
    }*/

    /**
     * validateUser checks if the userId and password are in the table and match
     * @param username the username you are checking
     * @param password the password you are checking
     * @return boolean
     */
    public boolean validateUser(String username, String password){
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + username +"'AND "+KEY_PASS+"='"+password+"'" ,  null);
        if (c.getCount() > 0) {
            currentUser = username;
            c.close();
            return true;
        }

        else {
            c.close();
            return false;
        }
    }

    /**
     * sameUser checks if the userId is already in the table
     * @param username the username you are searching for/checking
     * @return boolean
     */
    public boolean sameUser(String username){
        if (username == null) {
            return false;
        }
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + username  + "'" ,  null);
        boolean hasCount = c.getCount() > 0;
        c.close();
        return hasCount;

    }

    /**
     * sameUser checks if the userId is already in the table
     * @param inputEmail the username you are searching for/checking
     * @return boolean
     */
    public boolean sameEmail(String inputEmail){
        if (inputEmail == null) {
            return false;
        }
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_EMAIL + "='" + inputEmail  + "'" ,  null);
        boolean hasCount = c.getCount() > 0;
        c.close();
        return hasCount;

    }

    /**
     * validRecovery checks if the email is already in the table
     * @param username the username you are searching for/checking
     * @param email the email you are searching for/checking
     * @return boolean
     */
    public boolean validRecovery (String username, String email){

        if (email == null){
            return false;
        }
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + username +"'AND "+KEY_EMAIL+"='"+email+"'" ,  null);
        boolean hasCount = c.getCount() > 0;
        c.close();
        return hasCount;
    }


    /**
     * getCurrentUser returns the current logged in user ID
     * @return String
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * getUserName returns the user name from the Registered_Accounts table
     * @return String
     */
    public String getUserName() {
        String userName;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT userName FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);
        c.moveToFirst();
        userName = c.getString(0);
        c.close();
        return userName;
    }

    /**
     * getUserName returns the user name from the Registered_Accounts table
     * @return String
     */
    public String getPassword(String user) {
        String userPassword;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT " + KEY_PASS + " FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + user  + "'" ,  null);
        if (c != null) {
            c.moveToFirst();
            userPassword = c.getString(0);
            c.close();
            return userPassword;
        }
        return "";
    }

    /**
     * getEmail returns the email address from the Registered_Accounts table
     * @return String
     */
    public String getEmail() {
        String email;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT email FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);

        if (c != null) {
            c.moveToFirst();
            email = c.getString(0);
            c.close();
            return email;
        }
        return "";

    }

    /**
     * getUserType returns the registered Type from the Registered_Accounts table
     * @return String
     */
    public String getUserType() {
        String userType;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT userType FROM " + TABLE_USERS + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);
        if (c != null) {
            c.moveToFirst();
            userType = c.getString(0);
            c.close();
            return userType;
        }
        return "";
    }

    /**
     * getHomeAddress returns the home address from the Registered_Profiles table
     * @return String
     */
    public String getHomeAddress() {
        String homeAddress;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT homeAddress FROM " + TABLE_PROFILES + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);
        if (c != null) {
            c.moveToFirst();
            homeAddress = c.getString(0);
            c.close();
            return homeAddress;
        }
        return "";
    }

    /**
     * getPass returns the home address from the Registered_Profiles table
     * @return String
     */
    public String getPass() {
        String password;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT KEY_PASS FROM " + TABLE_PROFILES + " WHERE "
                        + KEY_PASS + "='" + currentUser  + "'" ,  null);
        if (c != null) {
            c.moveToFirst();
            password = c.getString(0);
            c.close();
            return password;
        }
        return "";
    }

    /**
     * getPhone returns the phone number from the Registered_Profiles table
     * @return String
     */
    public String getPhone() {
        String phone;
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT phone FROM " + TABLE_PROFILES + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);
        if (c != null) {
            c.moveToFirst();
            phone = c.getString(0);
            c.close();
            return phone;
        }
        return "";
    }

    /**
     * updateProfile updates the user profile data in the Registered_Profile table
     * @param profile the profile of the user whose info you are updating
     */
    public void updateProfile(Profiles profile) {
        getReadableDatabase().execSQL("UPDATE " + TABLE_PROFILES + " SET "
                + KEY_HOME_ADDRESS + "='" + profile.getHomeAddress() + "',"
                + KEY_PHONE + "='" + profile.getPhone() + "'"
                + " WHERE " + KEY_USERS + "='" + profile.getUser() + "'");
    }

    public int getPurityReportId() {
        String reportId = "0";
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM Registered_WaterPurity ORDER BY reportID DESC",  null);

        if (!c.isAfterLast()) {
            c.moveToFirst();
            reportId = c.getString(0);
        }
        c.close();
        return Integer.parseInt(reportId) + 1;
    }

    /**
     * updateUsers updates the user account data in the Registered_Account table
     * @param userId userId to update
     * @param name new user name
     * @param email new email address
     * @param type new registered type
     */
    public void updateUsers(String userId, String name, String email, String type) {
        getReadableDatabase().execSQL("UPDATE " + TABLE_USERS + " SET "
                + KEY_NAME + "='" + name + "', "
                + KEY_EMAIL + "='" + email + "', "
                + KEY_TYPE + "='" + type + "'"
                + " WHERE " + KEY_USERS + "='" + userId + "'");
    }

    /**
     * profileExists checks if the profile for the current user is in the Registered_Profile table
     * @return boolean
     */
    public boolean profileExists() {
        Cursor c = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_PROFILES + " WHERE "
                        + KEY_USERS + "='" + currentUser  + "'" ,  null);
        boolean hasCount = c.getCount() > 0;
        c.close();
        return hasCount;
    }

    /**
     * listAllPurityReports returns the list of reports from the Water_Purity table
     * @return reportsList
     */
    public ArrayList<String> listAllPurityReports() {
        ArrayList<String> reportsList = new ArrayList<>();

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT "+ KEY_REPORT_ID + ", " + KEY_WORKER + ", " + KEY_DATE
                        + " FROM " + TABLE_WATER_PURITY
                        + " ORDER BY " + KEY_DATE + " DESC",  null);

        if (!c.isAfterLast() ) {
            if  (c.moveToFirst()) {
                do {
                    String report = "Report: " + c.getString(0);
                    report += "  -  " + c.getString(1);
                    report += "    " + c.getString(2);
                    reportsList.add(report);
                }while (c.moveToNext());
            }
        }
        c.close();
        return reportsList;
    }

    /**
     * listAllSourceReports returns the list of reports from the Water_Source table
     * @return reportsList
     */
    public ArrayList<String> listAllSourceReports() {
        ArrayList<String> reportsList = new ArrayList<>();

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT "+ KEY_REPORT_ID + ", " + KEY_SUBMIT_BY + ", " + KEY_DATE
                        + " FROM " + TABLE_WATER_SOURCE
                        + " ORDER BY " + KEY_DATE + " DESC",  null);

        if (!c.isAfterLast() ) {
            if  (c.moveToFirst()) {
                do {
                    String report = "Report: " + c.getString(0);
                    report += "  -  " + c.getString(1);
                    report += "    " + c.getString(2);
                    reportsList.add(report);
                }while (c.moveToNext());
            }
        }
        c.close();
        return reportsList;
    }



    /**
     * waterAvailabilityReports returns the list of reports from the Water_Source table
     * @return reportsList
     */
//    public ArrayList<WaterSource> waterAvailabilityReports() {
//        ArrayList<WaterSource> reportsList = new ArrayList<>();
//
//        Cursor c = getReadableDatabase().rawQuery(
//                "SELECT "+ KEY_SUBMIT_BY + ", " + KEY_LATITUDE + ", " + KEY_LONGITUDE + ", "
//                        + KEY_WATER_TYPE + ", " + KEY_CONDITION
//                        + " FROM " + TABLE_WATER_SOURCE,  null);
//
//        if (!c.isAfterLast() ) {
//            if  (c.moveToFirst()) {
//                do {
//                    reportsList.add(new WaterSource(c.getString(0), Double.parseDouble(c.getString(1)),
//                            Double.parseDouble(c.getString(2)), c.getString(3), c.getString(4)));
//                }while (c.moveToNext());
//            }
//        }
//        c.close();
//        return reportsList;
//    }
//
//    /**
//     * waterAvailabilityReports returns the list of reports from the Water_Source table
//     * @return reportsList
//     */
//    public ArrayList<WaterPurity> waterPurityReports() {
//        ArrayList<WaterPurity> reportsList = new ArrayList<>();
//
//        Cursor c = getReadableDatabase().rawQuery(
//                "SELECT "+ KEY_WORKER + ", " + KEY_LATITUDE + ", " + KEY_LONGITUDE + ", "
//                        + KEY_CONDITION + ", " + KEY_VIRUS + ", " + KEY_CONTAMINANT
//                        + " FROM " + TABLE_WATER_PURITY,  null);
//
//        if (!c.isAfterLast() ) {
//            if  (c.moveToFirst()) {
//                do {
//                    reportsList.add(new WaterPurity(c.getString(0), Double.parseDouble(c.getString(1)),
//                            Double.parseDouble(c.getString(2)), c.getString(3),
//                            Double.parseDouble(c.getString(4)), Double.parseDouble(c.getString(5))));
//                }while (c.moveToNext());
//            }
//        }
//        c.close();
//        return reportsList;
//    }

    /**
     * waterAvailabilityReports returns the list of reports from the Water_Source table
     * @return reportsList
     */
    public ArrayList<Integer> waterPurityReportYears() {
        ArrayList<Integer> reportsList = new ArrayList<>();

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT DISTINCT strftime('%Y', "+ KEY_DATE + ") as Year "
                        + " FROM " + TABLE_WATER_PURITY,  null);

        if (!c.isAfterLast() ) {
            if  (c.moveToFirst()) {
                do {
                    reportsList.add(Integer.parseInt(c.getString(0)));
                }while (c.moveToNext());
            }
        }
        c.close();
        return reportsList;
    }

    /**
     * waterAvailabilityReports returns the list of reports from the Water_Source table
     * @return reportsList
     */
//    public ArrayList<Location> waterPurityReportLocations(int selectedYear) {
//        ArrayList<Location> reportsList = new ArrayList<>();
//
//        Cursor c = getReadableDatabase().rawQuery(
//                "SELECT DISTINCT " + KEY_LATITUDE + ", " + KEY_LONGITUDE
//                        + " FROM " + TABLE_WATER_PURITY
//                        + " WHERE "+ KEY_DATE + " between '" + selectedYear + "-01-01 00:00:00' AND '"
//                        + selectedYear + "-12-31 23:59:59'",  null);
//
//        if (!c.isAfterLast() ) {
//            if  (c.moveToFirst()) {
//                do {
//                    reportsList.add(new Location(Double.parseDouble(c.getString(0)), Double.parseDouble(c.getString(1))));
//                }while (c.moveToNext());
//            }
//        }
//        c.close();
//        return reportsList;
//    }

    public void testAddUser(String usr, String name, String pass, String email, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USERS, usr); // User Name
        values.put(KEY_PASS, pass); // Password
        values.put(KEY_NAME, name); // User Name
        values.put(KEY_EMAIL, email); // Password
        values.put(KEY_TYPE, type); // Password

        // Inserting Row
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addTestPurityReport(String usr, String datetime, String name, Double lat, Double log, String condition, Double virus, Double contam) {
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateObj = null;
        try {
            dateObj = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(KEY_USERS, usr); // User ID
        values.put(KEY_DATE, sdf.format(dateObj)); // Date
        values.put(KEY_WORKER, name); // Worker Name
        values.put(KEY_LATITUDE, lat); // Latitude
        values.put(KEY_LONGITUDE, log); // Longitude
        values.put(KEY_CONDITION, condition); // Water Condition
        values.put(KEY_VIRUS, virus); // Virus PPM
        values.put(KEY_CONTAMINANT, contam); // Contaminant PPM

        // Inserting Row
        db.insert(TABLE_WATER_PURITY, null, values);
        db.close(); // Closing database connection
    }

    /**
     * waterAvailabilityReports returns the list of reports from the Water_Source table
     * @return reportsList
     */
//    public ArrayList<GraphValues> waterPurityVirusGraph(int selectedYear, Double lat, Double log) {
//        ArrayList<GraphValues> reportsList = new ArrayList<>();
//
//        Cursor c = getReadableDatabase().rawQuery(
//                "SELECT DISTINCT strftime('%m', "+ KEY_DATE + ") as Month "
//                        + " , " + KEY_VIRUS
//                        + " FROM " + TABLE_WATER_PURITY
//                        + " WHERE "+ KEY_DATE + " between '" + selectedYear + "-01-01 00:00:00' AND '"
//                        + selectedYear + "-12-31 23:59:59' and " + KEY_LATITUDE + " = "
//                        + lat + " and " + KEY_LONGITUDE + " = " + log,  null);
//
//        if (!c.isAfterLast() ) {
//            if  (c.moveToFirst()) {
//                do {
//                    reportsList.add(new GraphValues(Integer.parseInt(c.getString(0)), Double.parseDouble(c.getString(1))));
//                }while (c.moveToNext());
//            }
//        }
//        c.close();
//        return reportsList;
//    }
//
//    public ArrayList<GraphValues> waterPurityContaminantGraph(int selectedYear, Double lat, Double log) {
//        ArrayList<GraphValues> reportsList = new ArrayList<>();
//
//        Cursor c = getReadableDatabase().rawQuery(
//                "SELECT DISTINCT strftime('%m', "+ KEY_DATE + ") as Month "
//                        + " , " + KEY_CONTAMINANT
//                        + " FROM " + TABLE_WATER_PURITY
//                        + " WHERE "+ KEY_DATE + " between '" + selectedYear + "-01-01 00:00:00' AND '"
//                        + selectedYear + "-12-31 23:59:59' and " + KEY_LATITUDE + " = "
//                        + lat + " and " + KEY_LONGITUDE + " = " + log,  null);
//
//        if (!c.isAfterLast() ) {
//            if  (c.moveToFirst()) {
//                do {
//                    reportsList.add(new GraphValues(Integer.parseInt(c.getString(0)), Double.parseDouble(c.getString(1))));
//                }while (c.moveToNext());
//            }
//        }
//        c.close();
//        return reportsList;
//    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<LoggingSignin> userLoggingList() {
        ArrayList<LoggingSignin> reportsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT DISTINCT " + KEY_USERS + ", " + KEY_PASS + ", " + KEY_LOGDATE
                        + ", " + KEY_SUCCESS + ", " + KEY_INVALIDATTEMPT + ", " + KEY_LOCKOUT
                        + ", " + KEY_NOTES
                        + " FROM " + TABLE_LOGGING_SIGNIN
                        + " ORDER BY " + KEY_LOGDATE + " DESC", null);

        if (!c.isAfterLast() ) {
            if  (c.moveToFirst()) {
                do {
                    try {
                        reportsList.add(new LoggingSignin(c.getString(0), c.getString(1), sdf.parse(c.getString(2)),
                                Integer.parseInt(c.getString(3)) > 0,
                                Integer.parseInt(c.getString(4)), Integer.parseInt(c.getString(5)) > 0,
                                c.getString(6)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }while (c.moveToNext());
            }
        }
        c.close();
        return reportsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ArrayList<LoggingNavigation> actionLoggingList() {
        ArrayList<LoggingNavigation> reportsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Cursor c = getReadableDatabase().rawQuery(
                "SELECT DISTINCT " + KEY_USERS + ", " + KEY_LOGDATE
                        + ", " + KEY_SCREEN + ", " + KEY_ACTION
                        + ", " + KEY_NOTES
                        + " FROM " + TABLE_LOGGING_REPORTING
                        + " ORDER BY " + KEY_LOGDATE + " DESC", null);

        if (!c.isAfterLast() ) {
            if  (c.moveToFirst()) {
                do {
                    try {
                        reportsList.add(new LoggingNavigation(c.getString(0), sdf.parse(c.getString(1)),
                                c.getString(2), c.getString(3), c.getString(4)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }while (c.moveToNext());
            }
        }
        c.close();
        return reportsList;
    }
}
