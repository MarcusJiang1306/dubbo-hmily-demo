package com.marcus.dubbo.bootConsumer.controller;

import com.marcus.dubbo.boot.api.fxAccount.dto.FXAccountDTO;
import com.marcus.dubbo.boot.api.fxAccount.entity.FXAccountDO;
import com.marcus.dubbo.bootConsumer.service.ConsumerFXService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class consumerFXController {

    @Autowired
    private ConsumerFXService consumerFXService;

    @GetMapping("all")
    @ApiOperation(value = "测试")
    public List<FXAccountDO> all() {
        return consumerFXService.findAllFXAccount();
    }

    @GetMapping("hello")
    @ApiOperation(value = "测试")
    public String hello() {
        return "hello";
    }

    @PostMapping("fx")
    @ApiOperation(value = "测试")
    public int doFX(@RequestBody()FXAccountDTO fxAccountDTO) {
        return consumerFXService.updateFXAccount(fxAccountDTO);
    }

}
