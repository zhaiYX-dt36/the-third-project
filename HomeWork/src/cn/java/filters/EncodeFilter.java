/**
 * Project Name:dt36_springmvc1
 * File Name:EncodeFilter.java
 * Package Name:cn.java.filters
 * Date:2017年7月23日下午4:31:03
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Description: <br/>
 * Date: 2017年7月23日 下午4:31:03 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class EncodeFilter implements Filter {

    @Override
    public void destroy() {

        // Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

        // Auto-generated method stub

    }

}
