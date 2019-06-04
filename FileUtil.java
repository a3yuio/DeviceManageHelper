package per.yyu.DeviceManageHelper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {
    public FileUtil(HelperModel helperModel) {
        this.read_Dooray_HookURL_File(helperModel);
        this.read_DB_Info_File(helperModel);
    }

    private void read_Dooray_HookURL_File(HelperModel helperModel) {
        try {
            FileInputStream fileInputStream = new FileInputStream(helperModel.get_FilePath_HookURL());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            helperModel.set_Dooray_HookURL_Overdue(bufferedReader.readLine());
            helperModel.set_Dooray_HookURL_Hold(bufferedReader.readLine());
            helperModel.set_Dooray_HookURL_LongRent(bufferedReader.readLine());

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void read_DB_Info_File(HelperModel helperModel) {
        try {
            FileInputStream fileInputStream = new FileInputStream(helperModel.get_FilePath_DBInfo());
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "euc-kr");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            helperModel.set_DB_Address(bufferedReader.readLine());
            helperModel.set_DB_UserID(bufferedReader.readLine());
            helperModel.set_DB_Password(bufferedReader.readLine());

            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
