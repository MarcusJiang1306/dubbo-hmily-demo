package com.marcus.dubbo.bootConsumer.service;

import com.marcus.dubbo.boot.api.fxAccount.dto.FXAccountDTO;
import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import org.dromara.hmily.annotation.Hmily;

import java.util.List;

public interface ConsumerFXService {


    List<FXAccountDO> findAllFXAccount();

    FXAccountDO findFXAccountByUserId(long user_id);

    int updateFXAccount(FXAccountDTO fxAccountDTO);
}
