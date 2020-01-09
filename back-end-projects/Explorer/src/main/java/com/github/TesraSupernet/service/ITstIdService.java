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


package com.github.TesraSupernet.service;

import com.github.TesraSupernet.model.common.ResponseBean;

/**
 * @author zhouq
 * @version 1.0
 * @date 2018/2/27
 */
public interface ITstIdService {

    /**
     * query tstId list
     *
     * @return
     */
    ResponseBean queryLatestTstIdTxs(int count);

    /**
     * query tstId list by page
     *
     * @param pageSize
     * @param pageNumber
     * @return
     */
    ResponseBean queryTstidTxsByPage(int pageSize, int pageNumber);

    /**
     * query tstId detail
     *
     * @param tstId
     * @return
     */
    ResponseBean queryTstIdTxsByTstid(String tstId, int pageSize, int pageNumber);


    ResponseBean queryTstidDdo(String tstId);



}
