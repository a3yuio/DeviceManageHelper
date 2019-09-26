package per.yyu.DeviceManageHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {
    private String JDBC_DRIVER;
    private String DB_ADDRESS;
    private String USERNAME;
    private String PASSWORD;

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBUtil(String dbAddress, String userID, String password) {
        this.JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        this.DB_ADDRESS = "jdbc:mysql://" + dbAddress + "?serverTimezone=UTC&characterEncoding=UTF-8";
        this.USERNAME = userID;
        this.PASSWORD = password;

        this.connection = null;
        this.statement = null;
        this.resultSet = null;
    }

    public void query_And_Make_Overdue_List(HelperModel helperModel) {
        try {
            Class.forName(JDBC_DRIVER);

            this.connection = DriverManager.getConnection(this.DB_ADDRESS, this.USERNAME, this.PASSWORD);
            this.statement = this.connection.createStatement();
            this.resultSet = statement.executeQuery(helperModel.get_DB_Query_Overdue());

            while(this.resultSet.next()) {
                helperModel.init_Overdue_Title();
                if(helperModel.is_There_Overdue() == false) {
                    helperModel.clean_Overdue_List();
                }


                // 비고가 null 일 때
                if(this.resultSet.getString("Note") == null) {
                    helperModel.add_Overdue_List(this.resultSet.getString("DeviceNo"), this.resultSet.getString("DeviceName"), this.resultSet.getString("Lender"), this.resultSet.getString("RentedDay"), "-");
                } else {
                    helperModel.add_Overdue_List(this.resultSet.getString("DeviceNo"), this.resultSet.getString("DeviceName"), this.resultSet.getString("Lender"), this.resultSet.getString("RentedDay"), this.resultSet.getString("Note"));
                }
            }

//            if(helperModel.is_There_Overdue() == true) {
//                helperModel.init_Overdue_Title();
//            }

            if(helperModel.is_There_Overdue() == false) {
                helperModel.set_Overdue_List("미반납자가 없습니다.");
            }

            this.resultSet.close();
            this.statement.close();
            this.connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void query_And_Make_Hold_List(HelperModel helperModel) {
        try {
            Class.forName(JDBC_DRIVER);

            this.connection = DriverManager.getConnection(this.DB_ADDRESS, this.USERNAME, this.PASSWORD);
            this.statement = this.connection.createStatement();
            this.resultSet = statement.executeQuery(helperModel.get_DB_Query_Hold());

            while(this.resultSet.next()) {
                helperModel.add_Hold_List(this.resultSet.getString("DeviceNo"), this.resultSet.getString("DeviceName"), this.resultSet.getString("OS"), this.resultSet.getString("Version"), this.resultSet.getString("Note"), this.resultSet.getString("Lender"), this.resultSet.getString("LongReason"), this.resultSet.getString("Action"), this.resultSet.getString("ActionTime"));
            }

            this.resultSet.close();
            this.statement.close();
            this.connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void query_And_Make_LongRent_List(HelperModel helperModel) {
        try {
            Class.forName(JDBC_DRIVER);

            this.connection = DriverManager.getConnection(this.DB_ADDRESS, this.USERNAME, this.PASSWORD);
            this.statement = this.connection.createStatement();
            this.resultSet = statement.executeQuery(helperModel.get_DB_Query_LongRent());

            while(this.resultSet.next()) {
                helperModel.add_LongRent_List(this.resultSet.getString("DeviceNo"), this.resultSet.getString("DeviceName"), this.resultSet.getString("RentedDay"), this.resultSet.getString("Note"), this.resultSet.getString("Overdue"), this.resultSet.getString("LongReason"));
            }

            this.resultSet.close();
            this.statement.close();
            this.connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}