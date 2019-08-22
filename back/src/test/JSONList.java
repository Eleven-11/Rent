import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JSONList
 * @Description list
 * @Author Lingling00
 * @DATE 8/22/2019 17:41
 * @VERSION 1.0
 **/
public class JSONList {


    @Test
    public void show() {
        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("bde");

        System.out.println(JSON.toJSONString(list));
    }
}
