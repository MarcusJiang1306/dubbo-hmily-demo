package com.marcus.dubbo.boot.api.fxAccount.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FXAccountDTO {

    private long user_id;

    private long counterParty;

    private double amount;

//    true is buy and false is sell
    private boolean buySellFlag;



}
