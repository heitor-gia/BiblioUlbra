package com.hgianastasio.biblioulbrav2.data.models.historybooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by heitorgianastasio on 4/25/17.
 */

public class HistoryBookEntityMapper extends Mapper<HistoryBook,HistoryBookEntity> {

    @Inject public HistoryBookEntityMapper() {}

    @Override
    public HistoryBookEntity transform(HistoryBook input) {
        HistoryBookEntity output = new HistoryBookEntity();
        output.setTitle(input.getTitle());
        output.setId(input.getId());
        output.setDeadline(new SimpleDateFormat("dd/MM/yyyy").format(input.getDeadline()));
        output.setDevolution(new SimpleDateFormat("dd/MM/yyyy").format(input.getDevolution()));
        output.setLibrary(input.getLibrary());
        return output;
    }



    @Override
    public HistoryBook transformBack(HistoryBookEntity input) {
        HistoryBook output = new HistoryBook();
        try {
            output.setTitle(input.getTitle());
            output.setId(input.getId());
            output.setDeadline(new SimpleDateFormat("dd/MM/yyyy").parse(input.getDeadline()));
            output.setDevolution(new SimpleDateFormat("dd/MM/yyyy").parse(input.getDevolution()));
            output.setLibrary(input.getLibrary());
            return output;
        }
        catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
