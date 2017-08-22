/**
 * Project Name:dt36_ssm1
 * File Name:FrontService.java
 * Package Name:cn.java.service.impl
 * Date:2017年7月28日上午11:37:11
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.service;

import java.util.List;
import java.util.Map;

import cn.java.entity.Member;

/**
 * Description: <br/>
 * Date: 2017年7月28日 上午11:37:11 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public interface FrontService {

    List<Map<String, Object>> selectAll();

    int insertSelective(Member record);

    int updateByPrimaryKeySelective(Member record);

    int deleteByPrimaryKey(Long mid);
}
