package com.hgianastasio.biblioulbrav2.data.models.search.searchbook;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 09/05/17.
 */

public class SearchBookEntityMapper extends Mapper<SearchBook, SearchBookEntity> {
    @Inject public SearchBookEntityMapper() {}

    @Override
    public SearchBookEntity transform(SearchBook input) {
        throw new IllegalStateException("Not implemented");
    }

    @Override
    public SearchBook transformBack(SearchBookEntity input) {
        SearchBook output = new SearchBook();
        output.setAuthor(input.getAuthor());
        output.setCatchedExps(input.getCatchedExps().split("(?<=\\))"));
        output.setNumber(Long.parseLong(input.getNumber()));
        output.setTitle(input.getTitle());
        try {
            output.setYear(new SimpleDateFormat("yyyy").parse(input.getYear()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return output;
    }

}
