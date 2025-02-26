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

public interface IContractService {

    ResponseBean queryContractsByPage(Integer pagesize, Integer pageNum);

    ResponseBean queryContractDetail(String contractHash);

    ResponseBean queryTxsByContractHash(String contractType, String contractHash, Integer pageNumber, Integer pageSize);

    ResponseBean queryTxsByContractHash(String contractHash, Integer pageNumber, Integer pageSize);

    ResponseBean queryDappBindedInfo(String dappNameArrayStr, long startTime, long endTime);

    ResponseBean queryBindedWalletDappInfo(long startTime, long endTime);

    ResponseBean queryBindedNodeDappInfo(long startTime, long endTime);

    ResponseBean queryDappstoreDappsInfo(Integer pageSize, Integer pageNumber);

    ResponseBean queryDappstoreDappsSummary();

}
