package com.github.TesraSupernet.service.impl;

import com.github.TesraSupernet.config.ParamsConfig;
import com.github.TesraSupernet.mapper.ConfigMapper;
import com.github.TesraSupernet.model.dao.Config;
import com.github.TesraSupernet.service.IConfigService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@NoArgsConstructor
public class ConfigServiceImpl implements IConfigService {

    private ConfigMapper configMapper;

    @Autowired
    public ConfigServiceImpl(ConfigMapper configMapper) {
        this.configMapper = configMapper;
    }

    @Override
    public Long getMaxStakingChangeCount() {
        Config config = configMapper.selectByPrimaryKey(ParamsConfig.Field.maxStakingChangeCount);
        return config == null ? 0L : Long.parseLong(config.getValue());
    }

}
