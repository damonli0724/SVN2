package com.saltedfish.mapper.app;

import com.saltedfish.entity.app.CifMember;

public interface CifMemberMapper {
    int deleteByPrimaryKey(Integer memberId);

    int insert(CifMember record);

    int insertSelective(CifMember record);

    CifMember selectByPrimaryKey(Integer memberId);

    int updateByPrimaryKeySelective(CifMember record);

    int updateByPrimaryKey(CifMember record);
}