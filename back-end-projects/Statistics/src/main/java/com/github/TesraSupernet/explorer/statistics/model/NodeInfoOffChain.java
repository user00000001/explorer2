package com.github.TesraSupernet.explorer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_node_info_off_chain")
public class NodeInfoOffChain {
    @Id
    @Column(name = "public_key")
    @GeneratedValue(generator = "JDBC")
    private String publicKey;

    private String name;

    private String address;

    @Column(name = "tst_id")
    private String tstId;

    @Column(name = "node_type")
    private Integer nodeType;

    private String introduction;

    @Column(name = "logo_url")
    private String logoUrl;

    private String region;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String ip;

    private String website;

    @Column(name = "social_media")
    private String socialMedia;

}