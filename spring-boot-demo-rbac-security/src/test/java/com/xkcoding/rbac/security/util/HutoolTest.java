package com.xkcoding.rbac.security.util;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jia Binbin
 * @version 1.0
 * @date 2020/5/12 11:14
 * @describe HutoolTest
 */
@Slf4j
public class HutoolTest {
    @Test
    public void JsonTest(){
        Map<String, Object> data = new HashMap<>();
        data.put("zs","123");
        data.put("ls","456");
        data.put("ww",null);
        data.forEach((k,v)->{
            log.info(k + "_" +String.valueOf(v));
        });
        log.info("false--> [{}]",new JSONObject(data,false));
        log.info("true--> [{}]",new JSONObject(data,true));
    }
}
