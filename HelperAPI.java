package per.yyu.DeviceManageHelper;

public class HelperAPI {
    private HelperModel helperModel = new HelperModel();
    private FileUtil fileUtil = new FileUtil(helperModel);
    private DBUtil dbUtil = new DBUtil(helperModel.get_DB_Address(), helperModel.get_DB_UserID(), helperModel.get_DB_Password());
    private DoorayUtil doorayUtil = new DoorayUtil();

    // Overdue
    public void overdue_Begin() {
        System.out.println(helperModel.get_DB_Address());
        System.out.println(helperModel.get_DB_UserID());
        System.out.println(helperModel.get_DB_Password());

        dbUtil.query_And_Make_Overdue_List(helperModel);

        System.out.println(helperModel.get_Result_Overdue_List());
    }

    public void overdue_Finish() {
        helperModel.clean_Overdue_List();
    }

    public void send_To_Dooray_Overdue() {
        if(helperModel.is_There_Overdue() == true) {
            doorayUtil.make_JSON_Object(helperModel,"미반납자 알림", helperModel.get_Result_Overdue_List());
            doorayUtil.send_Dooray_Message(helperModel.get_Dooray_HookURL_Overdue());
            doorayUtil.clean_JSON_Object();
        } else {
            doorayUtil.make_JSON_Object(helperModel,"미반납자 알림", "축하드립니다.\n미반납자가 없습니다.");
            doorayUtil.send_Dooray_Message(helperModel.get_Dooray_HookURL_Overdue());
            doorayUtil.clean_JSON_Object();
        }
    }

    // Hold
    public void hold_Begin(String value) {
        helperModel.set_DB_Query_Hold(value);
        dbUtil.query_And_Make_Hold_List(helperModel);

        System.out.println(helperModel.get_Result_Hold_List());
    }

    public void hold_Finish() {
        helperModel.clean_Hold_List();
    }

    public void send_To_Dooray_Hold() {
        doorayUtil.make_JSON_Object(helperModel,"디바이스 홀드 알림", helperModel.get_Result_Hold_List());
        doorayUtil.send_Dooray_Message(helperModel.get_Dooray_HookURL_Hold());
        doorayUtil.clean_JSON_Object();
    }

    // Long Rent
    public void longRent_Begin(String value) {
        helperModel.set_DB_Query_LongRent(value);
        dbUtil.query_And_Make_LongRent_List(helperModel);

        System.out.println(helperModel.get_Result_LongRent_List());
    }

    public void longRent_Finish() {
        helperModel.clean_LongRent_List();
    }

    public void send_To_Dooray_LongRent() {
        doorayUtil.make_JSON_Object(helperModel, "장기대여 반납 알림", helperModel.get_Result_LongRent_List());
        doorayUtil.send_Dooray_Message(helperModel.get_Dooray_HookURL_LongRent());
        doorayUtil.clean_JSON_Object();
    }
}
