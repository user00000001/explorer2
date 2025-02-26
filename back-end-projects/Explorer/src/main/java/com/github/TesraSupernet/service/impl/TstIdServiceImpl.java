/*
 * Copyright (C) 2018 The TesraSupernet Authors
 * This file is part of The TesraSupernet library.
 *
 * The TesraSupernet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The TesraSupernet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The TesraSupernet.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.github.TesraSupernet.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.TesraSupernet.config.ParamsConfig;
import com.github.TesraSupernet.mapper.CurrentMapper;
import com.github.TesraSupernet.mapper.TstidTxDetailMapper;
import com.github.TesraSupernet.model.common.ClaimContextEnum;
import com.github.TesraSupernet.model.common.PageResponseBean;
import com.github.TesraSupernet.model.common.ResponseBean;
import com.github.TesraSupernet.model.dto.CurrentDto;
import com.github.TesraSupernet.model.dto.TstidTxDetailDto;
import com.github.TesraSupernet.service.ITstIdService;
import com.github.TesraSupernet.util.ConstantParam;
import com.github.TesraSupernet.util.ErrorInfo;
import com.github.TesraSupernet.util.Helper;
import com.github.TesraSupernet.util.TesraSDKService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouq
 * @version 1.0
 * @date 2018/2/27
 */
@Slf4j
@Service("TstIdService")
public class TstIdServiceImpl implements ITstIdService {


    private final TstidTxDetailMapper tstidTxDetailMapper;
    private final CurrentMapper currentMapper;
    private final ParamsConfig paramsConfig;

    private TesraSDKService sdkService;

    @Autowired
    public TstIdServiceImpl(TstidTxDetailMapper tstidTxDetailMapper, CurrentMapper currentMapper, ParamsConfig paramsConfig) {
        this.tstidTxDetailMapper = tstidTxDetailMapper;
        this.currentMapper = currentMapper;
        this.paramsConfig = paramsConfig;
    }

    private synchronized void initSDK() {
        if (sdkService == null) {
            sdkService = TesraSDKService.getInstance(paramsConfig);
        }
    }

    @Override
    public ResponseBean queryLatestTstIdTxs(int count) {

        List<TstidTxDetailDto> tstidTxDetailDtos = tstidTxDetailMapper.selectTstidTxsByPage("", 0, count);

        tstidTxDetailDtos.forEach(item -> {
            item.setDescription(Helper.templateTstIdOperation(item.getDescription()));
        });

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), tstidTxDetailDtos);
    }

    @Override
    public ResponseBean queryTstidTxsByPage(int pageSize, int pageNumber) {

        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);
        List<TstidTxDetailDto> tstidTxDetailDtos = tstidTxDetailMapper.selectTstidTxsByPage("", start, pageSize);

        tstidTxDetailDtos.forEach(item -> {
            item.setDescription(Helper.templateTstIdOperation(item.getDescription()));
        });

        CurrentDto currentDto = currentMapper.selectSummaryInfo();

        PageResponseBean pageResponseBean = new PageResponseBean(tstidTxDetailDtos, currentDto.getTstidTxCount());

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);
    }

    @Override
    public ResponseBean queryTstIdTxsByTstid(String tstId, int pageSize, int pageNumber) {

        int start = pageSize * (pageNumber - 1) < 0 ? 0 : pageSize * (pageNumber - 1);
        List<TstidTxDetailDto> tstidTxDetailDtos = tstidTxDetailMapper.selectTstidTxsByPage(tstId, start, pageSize);

        tstidTxDetailDtos.forEach(item -> {
            item.setDescription(Helper.templateTstIdOperation(item.getDescription()));
        });

        Integer count = tstidTxDetailMapper.selectTxCountByTstid(tstId);

        PageResponseBean pageResponseBean = new PageResponseBean(tstidTxDetailDtos, count);

        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), pageResponseBean);


    }

    @Override
    public ResponseBean queryTstidDdo(String tstId) {

        initSDK();
        String ddoStr = sdkService.getDDO(tstId);
        log.info("{} query ddo info:{}", tstId, ddoStr);
        if (Helper.isEmptyOrNull(ddoStr)) {
            return new ResponseBean(ErrorInfo.NOT_FOUND.code(), ErrorInfo.NOT_FOUND.desc(), false);
        }

        JSONObject ddoObj = JSON.parseObject(ddoStr);
        if (ddoObj.containsKey("Attributes")) {
            List<Object> formatedAttrList = formatDDOAttribute(ddoObj);
            ddoObj.replace("Attributes", formatedAttrList);
        }
        return new ResponseBean(ErrorInfo.SUCCESS.code(), ErrorInfo.SUCCESS.desc(), ddoObj);
    }

    /**
     * format ddo attribute
     *
     * @param ddoObj
     * @return
     */
    private List<Object> formatDDOAttribute(JSONObject ddoObj) {

        List<Object> formatedAttrList = new ArrayList<>();

        JSONArray attrList = ddoObj.getJSONArray("Attributes");

        for (Object obj :
                attrList) {
            JSONObject attrObj = (JSONObject) obj;
            String attrKey = attrObj.getString("Key");
            //standard claim attribute
            if (attrKey.startsWith(ConstantParam.CLAIM)) {

                log.info("Attributes contains claim");
                String attrValue = attrObj.getString("Value");
                log.info("attrValue:{}", attrValue);
                JSONObject attrValueObj = JSON.parseObject(attrValue);
                String claimContext = attrValueObj.getString("Context");
                String issuer = attrValueObj.getString("Issuer");

                JSONObject claimObj = new JSONObject();
                claimObj.put("IssuerTstId", issuer);
                claimObj.put("ClaimContext", claimContext);
                claimObj.put("ClaimId", attrKey.substring(ConstantParam.CLAIM.length(), attrKey.length()));
                if (ClaimContextEnum.GITHUB_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.GITHUB_CLAIM.desc());
                } else if (ClaimContextEnum.TWITTER_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.TWITTER_CLAIM.desc());
                } else if (ClaimContextEnum.FACEBOOK_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.FACEBOOK_CLAIM.desc());
                } else if (ClaimContextEnum.LINKEDIN_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.LINKEDIN_CLAIM.desc());
                } else if (ClaimContextEnum.SFP_DL_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.SFP_DL_CLAIM.desc());
                } else if (ClaimContextEnum.SFP_ID_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.SFP_ID_CLAIM.desc());
                } else if (ClaimContextEnum.SFP_PP_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.SFP_PP_CLAIM.desc());
                } else if (ClaimContextEnum.IDM_DL_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.IDM_DL_CLAIM.desc());
                } else if (ClaimContextEnum.IDM_ID_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.IDM_ID_CLAIM.desc());
                } else if (ClaimContextEnum.IDM_PP_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.IDM_PP_CLAIM.desc());
                } else if (ClaimContextEnum.CFCA_CLAIM.context().equals(claimContext)) {

                    claimObj.put("ContextDesc", ClaimContextEnum.CFCA_CLAIM.desc());
                }

                Map<String, Object> formatedAttrMap = new HashMap<>();
                formatedAttrMap.put("Claim", claimObj);
                formatedAttrList.add(formatedAttrMap);
            } else {
                //self-defined attribute
                Map<String, Object> unFormatedMap = new HashMap<>();
                unFormatedMap.put(attrKey, attrObj.getString("Value"));
                Map<String, Object> selfDefined = new HashMap<>();
                selfDefined.put("SelfDefined", unFormatedMap);
                formatedAttrList.add(selfDefined);
            }
        }

        return formatedAttrList;
    }


}
