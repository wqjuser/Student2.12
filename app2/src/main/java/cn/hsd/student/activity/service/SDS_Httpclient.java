package cn.hsd.student.activity.service;

import android.os.Message;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/5/29.
 */
public class SDS_Httpclient {
    public void Postclient(String path,String data,SDS_ZMHandler SDShandler){
        String sds_return=null;

        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setReadTimeout(4000);
            conn.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");

            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", data.length()+"");

            conn.setDoOutput(true);
            OutputStream os=conn.getOutputStream();
            String datas = URLEncoder.encode(data,"utf-8");
            os.write(datas.getBytes());


            int code = conn.getResponseCode();

            if (code == 200) {
                InputStream is = conn.getInputStream();
                sds_return = IoToString.isToString(is);

                is.close();



                Message msg = new Message();
                msg.what=1;
                msg.obj=sds_return;

                SDShandler.sendMessage(msg);

            } else {
                sds_return=null;
            }


        } catch (Exception e) {

            Message msg = new Message();
            msg.what=2;
            msg.obj="请求失败";

            SDShandler.sendMessage(msg);
            e.printStackTrace();
            sds_return=null;
        }

    }
}