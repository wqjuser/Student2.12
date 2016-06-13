package cn.hsd.student.activity.Json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/30.
 */


public class json<T> {

    public String ObjectToJson1(Object obj) {
        // 创建学生对象
        T objImple = (T) obj;
        // 把对象转换成Json字符串
        String json = new Gson().toJson(obj);
        // 控制台输出
        //   Log.e("TAG", json);
        return json;
    }

    public Object JsonToObject1(String jsonobj) {
        Object ob = new Gson().fromJson(jsonobj,
                new TypeToken<Object>() {
                }.getType());


        return ob;
    }

    public String ListToJson2(List<T> list) {

        String json = new Gson().toJson(list);
        return json;


    }


    public Map<String, Object> JsonToMap(String json) {

        //2.将json格式的字符串{}转换为Map对象
        Map<String, Object> map = new Gson().fromJson(json,
                new TypeToken<Map<String, Object>>() {
                }.getType());

        //3.输出
        return map;

    }


    public List<T> jsonToList2(String jsonList) {

        List<T> list = new Gson().fromJson(jsonList,
                new TypeToken<List<T>>() {
                }.getType());
        // 3.把新生成的对象输出在控制台
        System.out.println(list.toString());
        return list;
    }
}
