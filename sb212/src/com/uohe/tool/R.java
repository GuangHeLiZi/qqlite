package com.uohe.tool;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author guanghelizi
 * @date 2017年11月18日 下午13:13:23
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 200);
    }

    public static R error() {
        return error(500, "未知异常异常请联系lovekami.com");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(String msg, String may) {
        return error(500, msg, may);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }
    public static R error(int code, String msg, String may) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        r.put("may", may);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg, String may) {
        R r = new R();
        r.put("msg", msg);
        r.put("may", may);
        return r;
    }

    public static R ok200(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok(int code, Map<String, Object> map) {
        R r = new R();
        r.put("code", code);
        r.putAll(map);
        return r;
    }


    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

