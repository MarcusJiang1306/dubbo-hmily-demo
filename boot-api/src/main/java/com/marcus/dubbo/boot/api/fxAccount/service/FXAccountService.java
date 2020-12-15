package com.marcus.dubbo.boot.api.fxAccount.service;

import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

public interface FXAccountService {

    List<FXAccountDO> findAllFXAccount();

    FXAccountDO findFXAccountByUserId(long user_id);

    @Hmily
    boolean updateFXAccount(FXAccountDO fxAccountDO);
}
