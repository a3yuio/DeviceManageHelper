package per.yyu.DeviceManageHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoorayUtil {
    private JSONObject rootJson;
    private JSONObject jsonObject;
    private JSONArray jsonArray;

    private HttpURLConnection httpURLConnection;
    private OutputStream outputStream;
    private InputStream inputStream;

    public DoorayUtil() {
        this.rootJson = new JSONObject();
        this.jsonObject = new JSONObject();
        this.jsonArray = new JSONArray();

        this.httpURLConnection = null;
        this.outputStream = null;
        this.inputStream = null;
    }

    public void make_JSON_Object(HelperModel helperModel, String mainText, String description) {
        try {
            rootJson.put("botName", helperModel.get_Dooray_Bot_Name());
            rootJson.put("botIconImage", helperModel.get_Dooray_Bot_Image_URL());
            rootJson.put("text", mainText);
            this.jsonObject.put("text", description);
            this.jsonArray.put(this.jsonObject);
            this.rootJson.put("attachments", this.jsonArray);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void clean_JSON_Object() {
        this.rootJson = new JSONObject();
        this.jsonObject = new JSONObject();
        this.jsonArray = new JSONArray();
    }

    public void send_Dooray_Message(String doorayHookURL) {
        try {
            if(this.rootJson == null || this.rootJson.equals("")) {
                System.out.println("[Dooray Util] : json data is null");
            }

            URL url = new URL(doorayHookURL);
            this.httpURLConnection = (HttpURLConnection)url.openConnection();
            this.httpURLConnection.setRequestMethod("POST");
            this.httpURLConnection.setRequestProperty("Content-Type", "application/json");
            this.httpURLConnection.setDoOutput(true);

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.httpURLConnection.getOutputStream(), "UTF-8"));
            bufferedWriter.write(this.rootJson.toString());
            bufferedWriter.flush();

            int responseCode = this.httpURLConnection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK) {
                this.inputStream = this.httpURLConnection.getInputStream();
                this.inputStream.close();
                httpURLConnection.disconnect();
            } else {
                System.out.println("[Dooray Util] : Send HTTP Post Error");
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            if(httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
}
