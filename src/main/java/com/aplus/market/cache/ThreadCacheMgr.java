package com.aplus.market.cache;

import com.alibaba.fastjson.JSONObject;
import com.aplus.market.constant.Constant;
import com.aplus.market.exception.PermissionAopException;
import com.aplus.market.model.Admin;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ldh
 * @Date: 2018/12/19 10:57
 */
public class ThreadCacheMgr {

    /**
     * 本地线程
     */
    private static ThreadLocal<Map<String,Object>> threadlocal = new ThreadLocal<>();

    /**
     *
     * 功能描述: 缓存数据到线程上下文<br>
     * 〈功能详细描述〉
     * @param context 入参内容
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static void cache(Map<String,Object> context){
        threadlocal.set(context);
    }

    /**
     *
     * 功能描述: 从线程上下文中获取数据<br>
     * 〈功能详细描述〉
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Map<String,Object> cache(){
        Map<String,Object> context =  threadlocal.get();
        if(context == null){
            context = new HashMap<String,Object>();
            cache(context);
        }
        return context;
    }

    @SuppressWarnings("unchecked")
    public static Map<String,Object> push(String apiname,String paramKey,String params){
        Map<String,Object> context = cache();
        Map<String,Object> apiParams = (Map<String, Object>) context.get(apiname);
        if( apiParams == null ){
            apiParams = new HashMap<String,Object>();
            context.put(apiname, apiParams);
        }
        apiParams.put(paramKey, params);
        return context;
    }

    public static Map<String,Object> push(String apiname, String params){
        Map<String,Object> context = cache();
        context.put(apiname, params);
        return context;
    }

    public static Map<String, Object> pushList(String apiname, List list){
        Map<String,Object> context = cache();
        context.put(apiname, list);
        return context;
    }

    public static Map<String, Object> pushObject(String apiname, Object object){
        Map<String,Object> context = cache();
        context.put(apiname, object);
        return context;
    }

    public static Object get(String apiname){
        Map<String,Object> context = cache();
        return context.get(apiname);
    }

    public static void remove(){
        threadlocal.remove();
    }

    /**
     * 获取金主ID
     * @return
     */
    public static Long getPartnerId(){
        String partnerIdStr = (String) get("partnerId");
        if (StringUtils.isEmpty(partnerIdStr)){
            return null;
        }
        if (StringUtils.isNumeric(partnerIdStr)){
            return Long.parseLong(partnerIdStr);
        } else {
            throw new PermissionAopException("partnerId 不为数字");
        }
    }

    /**
     * 获取ID
     * @return
     */
    public static Long getAdminId(){
        String channelId =(String) get(Constant.ADMIN_ID);
        if (StringUtils.isEmpty(channelId)){
            return null;
        }
        if (StringUtils.isNumeric(channelId)){
            return Long.parseLong(channelId);
        } else {
            throw new PermissionAopException("adminId 不为数字");
        }

    }

    /**
     * 获取admin
     * @return
     */
    public static Admin getAdmin(){
        String admin =(String) get("admin");
        return JSONObject.parseObject(admin, Admin.class);
    }
}
