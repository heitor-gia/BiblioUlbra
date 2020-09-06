package com.hgianastasio.biblioulbrav2.data.base.historybooks

import com.hgianastasio.biblioulbrav2.data.disk.GenericCache
import com.hgianastasio.biblioulbrav2.data.models.historybooks.HistoryBookEntity

/**
 * Created by heitorgianastasio on 4/29/17.
 */
interface HistoryBooksOnCache : GenericCache<List<HistoryBookEntity>>