package com.cc.dc.common.http;

import com.cc.dc.common.http.volley.VolleyHttpService;

/**
 * Created by dc on 2017/9/18.
 */
public class HttpFactory {

    private static HttpFactory factory = new HttpFactory();

    private static String httpServiceName = VolleyHttpService.class.getName();

    private HttpFactory(){
    }

    public static HttpFactory getInstance() {
        return factory;
    }

    public static HttpService getHttpService() {
        try {
            return (HttpService) Class.class.getClassLoader().loadClass(httpServiceName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
