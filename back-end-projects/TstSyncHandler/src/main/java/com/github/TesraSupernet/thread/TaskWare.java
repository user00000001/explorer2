/*
 * Copyright (C) 2019-2020 The TesraSupernet Authors
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


package com.github.TesraSupernet.thread;

import com.github.TesraSupernet.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author user00000001
 * @version 0.0.1
 * @date 2020/1/2
 */
@Slf4j
@Component
public class TaskWare {

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Autowired
    private BlockHandlerThread blockHandlerThread;

    private Future cancellable;

    @PostConstruct
    public void init() {
        log.info("Starting BlockHandlerThread");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        cancellable = executorService.submit(blockHandlerThread);
    }

    @PreDestroy
    public void shutdown() {
        if (cancellable != null) {
            log.info("Stopping BlockHandlerThread");
            cancellable.cancel(true);
        }
    }

}