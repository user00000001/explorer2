package com.github.TesraSupernet.explorer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_net_node_info")
public class NetNodeInfo {
    @Id
    @GeneratedValue(generator = "JDBC")
    private String ip;

    private String version;

    @Column(name = "is_consensus")
    private Boolean isConsensus;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "last_active_time")
    private Long lastActiveTime;

    private String country;

    private String longitude;

    private String latitude;

    public NetNodeInfo(LinkedHashMap object) {
        this.ip = (String) object.get("ip");
        String ver = (String) object.get("soft_version");
        this.version = ver.startsWith("v") ? ver : "";
        this.isConsensus = (Boolean) object.get("is_consensus");
        this.isActive = (Boolean) object.get("can_connect");
        this.lastActiveTime = (Long) object.get("last_active_time");
        this.country = (String) object.get("country");
        this.latitude = String.valueOf(object.get("lat"));
        this.longitude = String.valueOf(object.get("lon"));
    }
}