package com.marcus.dubbo.bootConsumer.service.impl;

import com.marcus.dubbo.boot.api.fxAccount.dto.FXAccountDTO;
import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import com.marcus.dubbo.boot.api.fxAccount.mapper.FXAccountMapper;
import com.marcus.dubbo.boot.api.fxAccount.service.FXAccountService;
import com.marcus.dubbo.bootConsumer.service.ConsumerFXService;
import com.marcus.dubbo.bootConsumer.service.FXRateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsumerFXServiceImpl implements ConsumerFXService {

    @DubboReference
    private FXAccountService fxAccountService;

    private final FXAccountMapper fxAccountMapper;

    private final FXRateService fxRateService;

    @Autowired(required = false)
    public ConsumerFXServiceImpl(FXAccountMapper fxAccountMapper, FXRateService fxRateService) {
        this.fxAccountMapper = fxAccountMapper;
        this.fxRateService = fxRateService;
    }

    @Override
    public List<FXAccountDO> findAllFXAccount() {
        return fxAccountMapper.findAll();
    }

    @Override
    public FXAccountDO findFXAccountByUserId(long order_id) {
        return fxAccountMapper.findByUserId(order_id);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public int updateFXAccount(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount called");
        fxAccountMapper.update(buildUpdateFXAccount(fxAccountDTO));
        fxAccountService.updateFXAccount(buildCtpyFXAccount(fxAccountDTO));
        return 1;
    }


    public void confirm(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount confirmed");
        FXAccountDO fxAccountDO = buildConfirmFXAccount(fxAccountDTO);
        log.info("{}",fxAccountDO.toString());
        fxAccountMapper.update(fxAccountDO);

    }

    public void cancel(FXAccountDTO fxAccountDTO) {
        log.info("consumer update FXAccount cancelled");
        FXAccountDO fxAccountDO = buildCancelFXAccount(fxAccountDTO);
        log.info("{}",fxAccountDO.toString());
        fxAccountMapper.update(fxAccountDO);
    }

    private FXAccountDO buildUpdateFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccountDO fxAccountDO ;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(),0, secAmount * -1, secAmount, "CNH");
        } else {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(), priAmount * -1, 0, priAmount, "USD");
        }
        return fxAccountDO;
    }

    private FXAccountDO buildConfirmFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccountDO fxAccountDO ;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(),priAmount, 0, 0, null);
        } else {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(), 0, secAmount, 0, null);
        }
        log.info(fxAccountDO.toString());
        return fxAccountDO;
    }

    private FXAccountDO buildCancelFXAccount(FXAccountDTO fxAccountDTO) {
        FXAccountDO fxAccountDO ;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(),0, fxAccountDTO.getAmount(), 0, null);
        } else {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getUser_id(), fxAccountDTO.getAmount(), 0, 0, null);
        }
        return fxAccountDO;
    }

    private FXAccountDO buildCtpyFXAccount(FXAccountDTO fxAccountDTO) {
        double priAmount = fxAccountDTO.getAmount();
        double secAmount = priAmount * 7;
        FXAccountDO fxAccountDO;
        if (fxAccountDTO.isBuySellFlag()) {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getCounterParty(), priAmount * -1, secAmount, priAmount, "USD");
        } else {
            fxAccountDO = new FXAccountDO(fxAccountDTO.getCounterParty(), priAmount, secAmount * -1, secAmount, "CNH");
        }
        return fxAccountDO;
    }
}
