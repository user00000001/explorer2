package com.github.TesraSupernet.explorer.statistics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_node_rank_change")
public class NodeRankChange {
    @Id
    @Column(name = "public_key")
    @GeneratedValue(generator = "JDBC")
    private String publicKey;

    private String address;

    private String name;

    @Column(name = "rank_change")
    private Integer rankChange;

    @Column(name = "change_block_height")
    private Long changeBlockHeight;

}