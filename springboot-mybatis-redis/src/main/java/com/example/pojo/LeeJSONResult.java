package com.example.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * 封装JSON对象的类
 *  200：成功
 *  500：错误，错误信息在msg字段中
 *  501：bean验证错误，不管多少个错误都以map形式返回
 *  502：拦截用户token出错
 *  555：异常抛出信息
 */
public class LeeJSONResult {
    //定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    //相应业务状态
    private Integer status;
    //响应信息
    private String msg;
    //响应中的数据
    private Object data;
    
    private String ok;  //不使用

    public LeeJSONResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public LeeJSONResult(Object data) {
        this.status = 200;
        this.data = data;
        this.msg = "OK";
    }

    public LeeJSONResult() {
    }

    public static LeeJSONResult build(Integer status, String msg, Object data) {
        return new LeeJSONResult(status, msg, data);
    }

    public static LeeJSONResult ok(Object data) {
        return new LeeJSONResult(data);
    }

    public static LeeJSONResult errorMsg(String msg) {
        return new LeeJSONResult(500, msg, null);
    }

    public static LeeJSONResult errorMap(Object data) {
        return new LeeJSONResult(501, "error", data);
    }

    public static LeeJSONResult errorTokenMsg(String msg) {
        return new LeeJSONResult(502, msg, null);
    }

    public static LeeJSONResult errorException(String msg) {
        return new LeeJSONResult(555, msg, null);
    }

    public boolean isOK() {
        return this.status == 200;
    }

    /**
     * 将json结果集转化为LeeJSONResult对象
     *  需要转换的对象是一个类
     *
     * @param jsonData
     * @param clazz
     * @return
     */
    public static LeeJSONResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, LeeJSONResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object object = null;
            if (clazz != null) {
                if (data.isObject()) {
                    object = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    object = MAPPER.readValue(data.asText(), clazz);
                }
            }

            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), object);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     *
     * @param json
     * @return
     */
    public static LeeJSONResult format(String json) {
        try {
            return MAPPER.readValue(json, LeeJSONResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 转换对象为一个集合，需要转换的对象是list
     * @param jsonData
     * @param clazz
     * @return
     */
    public static LeeJSONResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object object = null;
            if (data.isArray() && data.size() > 0) {
                object = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }

            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), object);
        } catch (IOException e) {
            return null;
        }

    }

    public static ObjectMapper getMAPPER() {
        return MAPPER;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}
