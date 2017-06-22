package com.hgianastasio.biblioulbrav2.data.models.loanbooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.loanbooks.LoanBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/29/17.
 */

public class LoanBookEntityMapper extends Mapper<LoanBook,LoanBookEntity> {

    @Inject public LoanBookEntityMapper() {}

    @Override
    public LoanBookEntity transform(LoanBook input) {
        LoanBookEntity output = new LoanBookEntity();
        output.setLibrary(input.getLibrary());
        output.setTitle(input.getTitle());
        output.setCode(input.getCode());
        output.setDeadline(new SimpleDateFormat("dd/MM/yyyy").format(input.getDeadline()));
        output.setPenalty(String.format("%.2f",input.getPenalty()));
        output.setDescription(input.getDescription());
        output.setId(input.getId());
        return output;
    }

    @Override
    public LoanBook transformBack(LoanBookEntity input) {
        LoanBook output = new LoanBook();
        try {
            output.setId(input.getId());
            output.setCode(input.getCode());
            output.setTitle(input.getTitle());
            output.setLibrary(input.getLibrary());
            output.setDescription(input.getDescription());
            output.setDeadline(new SimpleDateFormat("dd/MM/yyyy").parse(input.getDeadline()));
            output.setPenalty(input.getPenalty().isEmpty()?0 : Double.parseDouble(input.getPenalty()));
            return output;
        } catch (ParseException e) {
            return null;
        }

    }
}
