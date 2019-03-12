package com.aplus.market.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Author: ldh
 * @Date: 2018/12/19 11:27
 */
@Component
public class PathConfig {

    @Value("${permission.noauth.prefix:}")
    private String noauthPrefixPath;

    @Value("${permission.noauth.path:}")
    private String noauthFixPath;

    /**
     * 固定路径
     *
     * @param uri
     * @return
     */
    public boolean hasFixPath(String uri){
        List<String> fixPaths = new ArrayList<>();
        fixPaths.add("/favicon.ico");
        if (StringUtils.isNotEmpty(noauthFixPath)){
            fixPaths.addAll(Arrays.asList(noauthFixPath.split(",")));
        }
        for (String path : fixPaths){
            if (path.equals(uri)){
                return true;
            }
        }
        return false;
    }

    /**
     * 前置路径
     *
     * @param uri
     * @return
     */
    public boolean hasPrefixPath(String uri){
        List<String> preFixes = new ArrayList<>();
        preFixes.add("/actuator");
        if (StringUtils.isNotEmpty(noauthPrefixPath)){
            preFixes.addAll(Arrays.asList(noauthPrefixPath.split(",")));
        }
        for (String prefix : preFixes){
            if (uri.startsWith(prefix)){
                return true;
            }
        }
        return false;
    }
}
