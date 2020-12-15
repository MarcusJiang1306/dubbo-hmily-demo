package com.marcus.dubbo.bootProvider.service;


import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import com.marcus.dubbo.boot.api.fxAccount.mapper.FXAccountMapper;
import com.marcus.dubbo.boot.api.fxAccount.service.FXAccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@DubboService(retries = 0)
public class FXAccountServiceImpl implements FXAccountService {

    @Autowired(required = false)
    private FXAccountMapper fxAccountMapper;

    @Override
    public List<FXAccountDO> findAllFXAccount() {
        return fxAccountMapper.findAll();
    }

    @Override
    public FXAccountDO findFXAccountByUserId(long user_id) {
        return null;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean updateFXAccount(FXAccountDO fxAccountDO) {
        log.info("provider update FXAccount called");
        FXAccountDO updateDO ;
        if ("USD".equals(fxAccountDO.getFreeze_ccy())) {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), fxAccountDO.getCcy_USD(), 0, fxAccountDO.getFreeze_amount(), fxAccountDO.getFreeze_ccy());
        } else {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), 0, fxAccountDO.getCcy_CNH(), fxAccountDO.getFreeze_amount(), fxAccountDO.getFreeze_ccy());
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }

    public boolean confirm(FXAccountDO fxAccountDO) {
        log.info("provider update FXAccount confirmed");
        FXAccountDO updateDO ;
        if ("USD".equals(fxAccountDO.getFreeze_ccy())) {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), 0, fxAccountDO.getCcy_CNH(), 0, null);
        } else {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), fxAccountDO.getCcy_USD(), 0, 0, null);
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }

    public boolean cancel(FXAccountDO fxAccountDO) {
        log.info("provider update FXAccount cancelled");
        FXAccountDO updateDO ;
        if ("USD".equals(fxAccountDO.getFreeze_ccy())) {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), fxAccountDO.getFreeze_amount(), 0,0, null);
        } else {
            updateDO = new FXAccountDO(fxAccountDO.getUser_id(), 0, fxAccountDO.getFreeze_amount(), 0, null);
        }
        log.info("{}",updateDO.toString());
        return fxAccountMapper.update(updateDO) > 0;
    }
}

