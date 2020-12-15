package com.marcus.dubbo.boot.api.fxAccount.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FXAccountDO implements Serializable {

    private long user_id;

    private double ccy_USD;

    private double ccy_CNH;

    private double freeze_amount;

    private String freeze_ccy;

}
