/**
 * Project Name:HomeWork
 * File Name:FrontServiceImpl.java
 * Package Name:cn.java.service.impl
 * Date:2017年8月2日下午3:50:58
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
 */

package cn.java.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.java.entity.Member;
import cn.java.mapper.MemberMapper;
import cn.java.service.FrontService;

/**
 * Description: <br/>
 * Date: 2017年8月2日 下午3:50:58 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
@Service
public class FrontServiceImpl implements FrontService {

    @Autowired
    private MemberMapper mm;

    @Override
    public List<Map<String, Object>> selectAll() {
        return mm.selectAll();
    }

    @Override
    public int insertSelective(Member record) {
        return mm.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(Member record) {
        return mm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteByPrimaryKey(Long mid) {
        return mm.deleteByPrimaryKey(mid);
    }

}
