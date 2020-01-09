package com.github.TesraSupernet.model.common;

/**
 * @author user00000001
 * @version 0.0.1
 * @date 2020/1/2
 */
public enum EventTypeEnum {

    Others("others", 0),
    DeployContract("deploy contract", 1),
    Gasconsume("gasconsume", 2),
    Transfer("transfer", 3),
    Tstid("tstid-", 4),
    Claimrecord("claimRecord-", 5),
    Auth("auth", 6);


    private String des;

    private Integer type;

    EventTypeEnum(String des, Integer type) {
        this.des = des;
        this.type = type;
    }

    public String des() {
        return des;
    }

    public Integer type() {
        return type;
    }

}
