package com.marcus.dubbo.boot.api.fxAccount.mapper;

import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FXAccountMapper {

    @Select("select * from fx_account")
    List<FXAccountDO> findAll();

    @Select("select * from fx_account where user_id = #{user_id}")
    FXAccountDO findByUserId(long user_id);

    @Update("update fx_account set ccy_USD = ccy_USD + #{ccy_USD}, " +
            "ccy_CNH = ccy_CNH + #{ccy_CNH}, " +
            "freeze_amount=#{freeze_amount}, " +
            "freeze_ccy=#{freeze_ccy} where user_id=#{user_id}")
    int update(FXAccountDO fxAccountDO);




}
