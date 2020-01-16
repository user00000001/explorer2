package com.github.TesraSupernet.explorer.statistics.service;

import com.github.TesraSupernet.TstSdk;
import com.github.TesraSupernet.core.governance.Configuration;
import com.github.TesraSupernet.core.governance.GovernanceView;
import com.github.TesraSupernet.explorer.statistics.common.ParamsConfig;
import com.github.TesraSupernet.network.exception.ConnectorException;
import com.github.TesraSupernet.sdk.exception.SDKException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Slf4j
@Service
@NoArgsConstructor
public class TstSdkService {

    private TstSdk sdk;

    private int nodeCount;

    private AtomicInteger currentNodeIndex;

    private ParamsConfig paramsConfig;

    @Autowired
    public TstSdkService(ParamsConfig paramsConfig) {
        this.paramsConfig = paramsConfig;
        this.nodeCount = paramsConfig.getHosts().size();
        this.currentNodeIndex = new AtomicInteger(0);
        this.sdk = TstSdk.getInstance();
        try {
            sdk.getRestful();
        } catch (SDKException e) {
            sdk.setRestful(paramsConfig.getHosts().get(this.currentNodeIndex.get()));
        }
    }

    GovernanceView getGovernanceView() {
        try {
            return sdk.nativevm().governance().getGovernanceView();
        } catch (ConnectorException | IOException | SDKException e) {
            log.warn("Getting governance view failed: {}", e.getMessage());
            switchSyncNode();
            log.info("Getting governance view again");
            return getGovernanceView();
        }
    }

    int getStakingChangeCount() {
        try {
            Configuration configuration = sdk.nativevm().governance().getConfiguration();
            if (configuration == null) {
                log.warn("Getting native vm configuration failed: configuration is null");
                switchSyncNode();
                log.info("Try to get native vm configuration again");
                return getStakingChangeCount();
            }
            return configuration.MaxBlockChangeView;
        } catch (ConnectorException | IOException | SDKException e) {
            log.warn("Getting native vm configuration failed: {}", e.getMessage());
            switchSyncNode();
            log.info("Getting native vm configuration again");
            return getStakingChangeCount();
        }
    }

    int getBlockHeight() {
        try {
            return sdk.getRestful().getBlockHeight();
        } catch (ConnectorException | IOException | SDKException e) {
            log.warn("Getting block height failed: {}", e.getMessage());
            switchSyncNode();
            log.info("Getting block height again");
            return getBlockHeight();
        }
    }

    void switchSyncNode() {
        if (currentNodeIndex.get() >= nodeCount) {
            currentNodeIndex.set(0);
        }
        sdk.setRestful(paramsConfig.getHosts().get(currentNodeIndex.getAndAdd(1)));
        try {
            log.warn("Using node: {}", sdk.getRestful().toString());
        } catch (SDKException e) {
            log.warn("Getting REST URL failed: {}", e.getMessage());
        }
    }

}
