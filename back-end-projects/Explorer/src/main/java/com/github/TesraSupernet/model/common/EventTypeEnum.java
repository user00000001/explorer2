package com.github.TesraSupernet.model.common;

/**
 * @author zhouq
 * @version 1.0
 * @date 2019/4/28
 */
public enum EventTypeEnum {

    Others("others", 0),
    DeployContract("deploy contract", 1),
    Gasconsume("gasconsume", 2),
    Transfer("transfer", 3),
    Tstid("tstid", 4),
    Claimrecord("claimRecord", 5),
    Auth("auth", 6);


    private String des;

    private Integer type;

    EventTypeEnum(String des, Integer type) {
        this.des = des;
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
