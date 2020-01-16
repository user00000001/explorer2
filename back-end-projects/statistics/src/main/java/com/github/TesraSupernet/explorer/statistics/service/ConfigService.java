package com.github.TesraSupernet.explorer.statistics.service;

import com.github.TesraSupernet.explorer.statistics.common.ParamsConfig;
import com.github.TesraSupernet.explorer.statistics.mapper.ConfigMapper;
import com.github.TesraSupernet.explorer.statistics.model.Config;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class ConfigService {

    private ParamsConfig paramsConfig;

    private ConfigMapper configMapper;

    private TstSdkService tstSdkService;

    @Autowired
    public ConfigService(ParamsConfig paramsConfig, ConfigMapper configMapper, TstSdkService tstSdkService) {
        this.paramsConfig = paramsConfig;
        this.configMapper = configMapper;
        this.tstSdkService = tstSdkService;
    }


    public String getMaxStakingChangeCount() {
        Config config = configMapper.selectByPrimaryKey(ParamsConfig.Field.maxStakingChangeCount);
        return config == null ? "" : config.getValue();
    }

    public String updateMaxStakingChangeCount() {
        int maxStakingChangeCount = tstSdkService.getStakingChangeCount();
        Config config = new Config(ParamsConfig.Field.maxStakingChangeCount, String.valueOf(maxStakingChangeCount));
        Config selectConfig = configMapper.selectByPrimaryKey(ParamsConfig.Field.maxStakingChangeCount);
        int result;
        if (selectConfig == null) {
            result = configMapper.insert(config);
        } else {
            result = configMapper.updateByPrimaryKeySelective(config);
        }
        if (result != 1) {
            log.warn("Updating max block change view to {} failed", config.getValue());
            return "";
        }
        log.info("Updating max block change view to {} success", config.getValue());
        paramsConfig.setMaxStakingChangeCount(maxStakingChangeCount);
        log.info("Max staking change count has been updated to {}", paramsConfig.getMaxStakingChangeCount());
        return config.getValue();
    }


}
