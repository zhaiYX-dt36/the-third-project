package cn.java.mapper;

import java.util.List;
import java.util.Map;

import cn.java.entity.Member;

public interface MemberMapper {
    int deleteByPrimaryKey(Long mid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Long mid);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    List<Map<String, Object>> selectAll();
}