package com.hgianastasio.biblioulbrav2.models.loanbooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 10/05/17.
 */

public class LoanBookModelMapper extends Mapper<LoanBook,LoanBookModel> {
    private static final long SIX_DAYS_IN_MILLISECONDS = 518400000;
    private static final long ONE_DAY_IN_MILLISECONDS = 86400000;


    @Inject
    public LoanBookModelMapper() {}

    @Override
    public LoanBookModel transform(LoanBook input) {
        LoanBookModel output = new LoanBookModel();
        output.setCode(input.getCode());
        output.setTitle(input.getTitle());
        output.setDeadline(new SimpleDateFormat("dd/MM/yyyy").format(input.getDeadline()));
        output.setDescription(input.getDescription());
        output.setId(input.getId());
        output.setLibrary(input.getLibrary());
        output.setPenalty(String.format("%.2f",input.getPenalty()));
        output.setOverdue(input.getDeadline().before(new Date(System.currentTimeMillis())));
        output.setSingleCopy(input.getCode().contains("ex.1"));
        output.setJustRenewed(input.getDeadline().after(new Date(getJustRenewedTreshold(output))));
        return output;
    }

    @Override
    public LoanBook transformBack(LoanBookModel input) {
        throw new IllegalStateException("Not implemented");
    }

    private long getJustRenewedTreshold(LoanBookModel model){
        if(model.isSingleCopy())
            return System.currentTimeMillis()+ONE_DAY_IN_MILLISECONDS;
        else
            return System.currentTimeMillis()+SIX_DAYS_IN_MILLISECONDS;

    }
}
