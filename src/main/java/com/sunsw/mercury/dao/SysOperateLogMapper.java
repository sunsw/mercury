package com.sunsw.mercury.dao;

import com.sunsw.mercury.generic.GenericDao;
import com.sunsw.mercury.model.SysOperateLog;
import com.sunsw.mercury.model.SysOperateLogExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysOperateLogMapper extends GenericDao<SysOperateLog, Long> {
    long countByExample(SysOperateLogExample example);

    int deleteByExample(SysOperateLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysOperateLog record);

    int insertSelective(SysOperateLog record);

    List<SysOperateLog> selectByExample(SysOperateLogExample example);

    SysOperateLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SysOperateLog record, @Param("example") SysOperateLogExample example);

    int updateByExample(@Param("record") SysOperateLog record, @Param("example") SysOperateLogExample example);

    int updateByPrimaryKeySelective(SysOperateLog record);

    int updateByPrimaryKey(SysOperateLog record);
}