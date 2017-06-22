package com.hgianastasio.biblioulbrav2.models.search.searchbook;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchBook;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 31/05/17.
 */

public class SearchBookModelMapper extends Mapper<SearchBook,SearchBookModel> {
    @Inject public SearchBookModelMapper() {}

    @Override
    public SearchBookModel transform(SearchBook input) {
        SearchBookModel output = new SearchBookModel();
        output.setAuthor(input.getAuthor());
        output.setTitle(input.getTitle());
        output.setCatchedExps(input.getCatchedExps());
        output.setNumber(input.getNumber());
        try {
            output.setYear(new SimpleDateFormat("yyyy").format(input.getYear()));
        }catch (Exception e){
            output.setYear("Não informado");
        }
        return output;
    }

    @Override
    public SearchBook transformBack(SearchBookModel input) {
        throw new IllegalStateException("Not implemented.");
    }
}
