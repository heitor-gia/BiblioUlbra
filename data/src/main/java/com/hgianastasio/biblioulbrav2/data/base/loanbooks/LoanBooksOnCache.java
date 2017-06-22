package com.hgianastasio.biblioulbrav2.data.base.loanbooks;

import com.hgianastasio.biblioulbrav2.data.disk.GenericCache;
import com.hgianastasio.biblioulbrav2.data.models.loanbooks.LoanBookEntity;

import java.util.List;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public interface LoanBooksOnCache extends GenericCache<List<LoanBookEntity>> {}
