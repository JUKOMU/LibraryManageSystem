package utils;

import okhttp3.*;
import org.json.JSONObject;

import java.io.*;

public class FaceUtil {
    public static final String API_KEY = "MNZql61qdDCcBg5QqcKBymji";
    public static final String SECRET_KEY = "q0cNhSC8zUtM6ayIimhESzrGSjnSrjbO";

    static final OkHttpClient HTTP_CLIENT = new OkHttpClient().newBuilder().build();

    public static void main(String []args) throws IOException {
        String imageName = "E:\\Java\\LibraryManageSystem\\src\\main\\resources\\img\\1.jpg";
        String imageBase64 = ImageUtil.convertImageToBase64Str(imageName);
        faceMatch(imageBase64);
    }


    /**
     * 从用户的AK，SK生成鉴权签名（Access Token）
     *
     * @return 鉴权签名（Access Token）
     * @throws IOException IO异常
     */
    static String getAccessToken() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&client_id=" + API_KEY
                + "&client_secret=" + SECRET_KEY);
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/oauth/2.0/token")
                .method("POST", body)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        return new JSONObject(response.body().string()).getString("access_token");
    }

    public static double[][] faceDetect(String image) throws IOException {
        double[][] num = new double[4][2];
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"image\":\""+image+"\",\"image_type\":\"BASE64\",\"face_field\":\",face_shape\",\"max_face_num\":1}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v3/detect?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        String json = response.body().string();
        System.out.println(json);

        int leftIndex = json.indexOf("\"left\"");
        double left;
        int topIndex = json.indexOf("\"top\"");
        double top;
        int widthIndex = json.indexOf("\"width\"");
        double width;
        int heightIndex = json.indexOf("\"height\"");
        double height;
        int rotationIndex = json.indexOf("\"rotation\"");
        try {
            left = Double.parseDouble(json.substring(leftIndex+7,topIndex-1));
            top = Double.parseDouble(json.substring(topIndex+6,widthIndex-1));
            width = Double.parseDouble(json.substring(widthIndex+8,heightIndex-1));
            height = Double.parseDouble(json.substring(heightIndex+9,rotationIndex-1));

            height = height + top * 0.2;
            top = top - top * 0.2;
            num[0][0] = (int) left;
            num[0][1] = (int) top;
            num[1][0] = (int) left + width;
            num[1][1] = (int) top;
            num[2][0] = (int) left + width;
            num[2][1] = (int) top + height;
            num[3][0] = (int) left;
            num[3][1] = (int) top + height;
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
        //System.out.println(left);
        //System.out.println(top);
        //System.out.println(width);
        //System.out.println(height);
        return num;

    }

    public static boolean faceMatch(String image) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"group_id_list\":\"1\",\"image\":\"" + image + "\",\"image_type\":\"BASE64\"}");
        Request request = new Request.Builder()
                .url("https://aip.baidubce.com/rest/2.0/face/v3/search?access_token=" + getAccessToken())
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = HTTP_CLIENT.newCall(request).execute();
        System.out.println(response.body().string());
        return true;
    }
}
