package per.yyu.DeviceManageHelper;

public class HelperModel {
    // Helper Info
    private String filePath_HookURL;
    private String filePath_DBInfo;

    // DB Info
    private String db_Address;
    private String db_UserId;
    private String db_Password;
    private String db_Query_Overdue;
    private String db_Query_Hold;
    private String db_Query_LongRent;

    // Dooray Info
    private String dooray_BotName;
    private String dooray_BotImageURL;
    private String dooray_HookURL_Overdue;
    private String dooray_HookURL_Hold;
    private String dooray_HookURL_LongRent;

    // Result
    private String result_Overdue_Title;
    private String result_Overdue_List;
    private String result_Hold_Title;
    private String result_Hold_List;
    private String result_LongRent_Title;
    private String result_LongRent_List;

    public HelperModel() {
        this.filePath_HookURL = "C:\\DeviceManageHelper\\Resource\\Dooray\\HookURL.txt";
        this.filePath_DBInfo = "C:\\DeviceManageHelper\\Resource\\DB\\DBInfo.txt";

        this.db_Query_Overdue = "SELECT DeviceNo,DeviceName,Lender,RentedDay,Note FROM device.devices WHERE NOT DeviceName LIKE \"%(%\" AND (RentedDay < current_date() AND (Lender <> NULL OR Lender <> '관리자')) ORDER BY Lender";

        this.dooray_BotName = "BOGUS_BOT";
        this.dooray_BotImageURL = "https://pbs.twimg.com/profile_images/774914323688853504/jAXDo1BT_400x400.jpg";
        this.dooray_HookURL_Overdue = null;
        this.dooray_HookURL_Hold = null;
        this.dooray_HookURL_LongRent = null;

        this.result_Overdue_Title = "";
        this.result_Overdue_List = "";
        this.result_Hold_Title = "대여자 : 기기명 / 자산관리번호 / OS + Version / 장기대여 사유 / 비고 / 대여/반납 / 대여/반납 시간\n------------------------------------------------------------------------\n";
        this.result_Hold_List = "";
        this.result_LongRent_Title = "비고 : 반납 예정일 / 기기명 / 자산관리번호 / 장기대여 사유 / 대여일\n------------------------------------------------------------------------\n";
        this.result_LongRent_List = "";
    }
    // Helper Info
    public String get_FilePath_HookURL() {
        return this.filePath_HookURL;
    }

    public String get_FilePath_DBInfo() {
        return this.filePath_DBInfo;
    }

    // DB Info
    public String get_DB_Address() {
        return this.db_Address;
    }

    public void set_DB_Address(String dbAddress) {
        this.db_Address = dbAddress;
    }

    public String get_DB_UserID() {
        return this.db_UserId;
    }

    public void set_DB_UserID(String dbUserID) {
        this.db_UserId = dbUserID;
    }

    public String get_DB_Password() {
        return this.db_Password;
    }

    public void set_DB_Password(String dbPassword) {
        this.db_Password = dbPassword;
    }

    public String get_DB_Query_Overdue() {
        return this.db_Query_Overdue;
    }

    public String get_DB_Query_Hold() {
        return this.db_Query_Hold;
    }

    public void set_DB_Query_Hold(String days) {
        this.db_Query_Hold = "SELECT device.devices.DeviceNo, device.devices.DeviceName, device.devices.OS, device.devices.Version, device.devices.Note, device.devices.Lender, device.devices.LongReason, device.history.Action, device.history.ActionTime FROM device.devices LEFT OUTER JOIN device.history ON (device.devices.DeviceNo = device.history.DeviceNo) AND DATE(ActionTime) > (current_date() - interval " + days + " day) WHERE ActionTime IS NULL AND NOT device.devices.DeviceName LIKE \"%)%\" AND (device.devices.Lender IS NULL OR device.devices.Lender NOT IN('관리자')) ORDER BY device.devices.DeviceName";
    }

    public String get_DB_Query_LongRent() {
        return this.db_Query_LongRent;
    }

    public void set_DB_Query_LongRent(String days) {
        this.db_Query_LongRent = "SELECT device.devices.DeviceNo, device.devices.DeviceName, device.devices.Lender, device.devices.RentedDay, device.devices.Note, device.devices.Overdue, device.devices.LongReason FROM device.devices WHERE NOT device.devices.LongReason is NULL AND current_date() >= (device.devices.Overdue - " + days + ") ORDER BY Note";
    }

    // Dooray Info
    public String get_Dooray_Bot_Name() {
        return this.dooray_BotName;
    }

    public String get_Dooray_Bot_Image_URL() {
        return this.dooray_BotImageURL;
    }

    public String get_Dooray_HookURL_Overdue() {
        return this.dooray_HookURL_Overdue;
    }

    public void set_Dooray_HookURL_Overdue(String url) {
        this.dooray_HookURL_Overdue = url;
    }

    public String get_Dooray_HookURL_Hold() {
        return this.dooray_HookURL_Hold;
    }

    public void set_Dooray_HookURL_Hold(String url) {
        this.dooray_HookURL_Hold = url;
    }

    public String get_Dooray_HookURL_LongRent() {
        return this.dooray_HookURL_LongRent;
    }

    public void set_Dooray_HookURL_LongRent(String url) {
        this.dooray_HookURL_LongRent = url;
    }

    // Result
    //// Overdue
    public boolean is_There_Overdue() {
        if(this.result_Overdue_List.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String get_Result_Overdue_List() {
        return this.result_Overdue_Title + this.result_Overdue_List;
    }

    public void add_Overdue_List(String deviceNo, String deviceName, String lender, String rentedDay, String note) {
        this.result_Overdue_List =  this.result_Overdue_List + lender + " : " + deviceName+ " / " + deviceNo + " / " + rentedDay + " / " + note + "\n";
    }

    public void set_Overdue_List(String txt) {
        this.result_Overdue_List = txt;
    }

    public void clean_Overdue_List() {
        this.result_Overdue_List = "";
    }

    public void init_Overdue_Title() {
        this.result_Overdue_Title = "대여자 : 기기명 / 자산관리번호 / 대여일 / 비고\n------------------------------------------------------------------------\n";
    }

    //// Hold
    public boolean is_There_Hold() {
        if(this.result_Hold_List.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String get_Result_Hold_List() {
        return this.result_Hold_Title + this.result_Hold_List;
    }

    public void add_Hold_List(String deviceNo, String deviceName, String os, String version, String note, String lender, String longReason, String action, String actionTime) {
        this.result_Hold_List = this.result_Hold_List + lender + " : " + deviceName + " / " + deviceNo + " / " + os + " " + version + " / " + longReason + " / " + note + " / " + action + " / " + actionTime + "\n";
    }

    public void clean_Hold_List() {
        this.result_Hold_List = "";
    }

    //// Long Rent
    public boolean is_There_LongRent() {
        if(this.result_LongRent_List.equals("")) {
            return false;
        } else {
            return true;
        }
    }

    public String get_Result_LongRent_List() {
        return this.result_LongRent_Title + this.result_LongRent_List;
    }

    public void add_LongRent_List(String deviceNo, String deviceName, String rentedDay, String note, String overdue, String longReason) {
        this.result_LongRent_List = this.result_LongRent_List + note + " : " + overdue + " / " + deviceName + " / " + deviceNo + " / " + longReason + " / " + rentedDay + "\n";
    }

    public void clean_LongRent_List() {
        this.result_LongRent_List = "";
    }
}
