package com.hgianastasio.biblioulbrav2.models.historybooks;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.historybooks.HistoryBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 03/05/17.
 */

public class HistoryBookModelMapper extends Mapper<HistoryBook,HistoryBookModel> {
    @Inject public HistoryBookModelMapper() {}

    @Override
    public HistoryBookModel transform(HistoryBook input) {
        HistoryBookModel output = new HistoryBookModel();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        output.setTitle(input.getTitle());
        output.setId(input.getId());
        output.setLibrary(input.getLibrary());
        output.setDeadline(dateFormat.format(input.getDeadline()));
        output.setDevolution(dateFormat.format(input.getDevolution()));
        return output;
    }

    @Override
    public HistoryBook transformBack(HistoryBookModel input) {
        HistoryBook output = new HistoryBook();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            output.setTitle(input.getTitle());
            output.setId(input.getId());
            output.setLibrary(input.getLibrary());
            output.setDeadline(dateFormat.parse(input.getDeadline()));
            output.setDevolution(dateFormat.parse(input.getDevolution()));
            return output;
        }catch (ParseException ex){
            return null;
        }
    }
}
