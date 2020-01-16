package com.github.TesraSupernet.explorer.statistics;

import com.github.TesraSupernet.explorer.statistics.task.NodeSchedule;
import com.github.TesraSupernet.explorer.statistics.task.DailyInfoSchedule;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExplorerStatisticsApplicationTests {

    @Autowired
    DailyInfoSchedule dailyInfoSchedule;

    @Autowired
    NodeSchedule nodeSchedule;

    @Test
    public void testUpdateDailyInfo() {
        dailyInfoSchedule.updateDailyInfo();
    }

    @Test
    public void testUpdateApprovedContractInfo() {
        dailyInfoSchedule.updateApprovedContractInfo();
    }

    @Test
    public void testUpdateCandidateNodeINfo() {
        nodeSchedule.updateNodeInfo();
    }

}
