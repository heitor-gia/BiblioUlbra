package com.hgianastasio.biblioulbrav2.models.search.searchresult;

import com.hgianastasio.biblioulbrav2.core.base.boundaries.Mapper;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchModel;
import com.hgianastasio.biblioulbrav2.core.search.models.SearchResult;
import com.hgianastasio.biblioulbrav2.models.search.searchbook.SearchBookModelMapper;

import javax.inject.Inject;

/**
 * Created by heitor_12 on 31/05/17.
 */

public class SearchResultModelMapper extends Mapper<SearchResult,SearchResultModel>{

    private SearchBookModelMapper bookModelMapper;

    @Inject public SearchResultModelMapper(SearchBookModelMapper bookModelMapper) {this.bookModelMapper = bookModelMapper;}

    @Override
    public SearchResultModel transform(SearchResult input) {
        SearchResultModel output = new SearchResultModel();
        output.setCookie(input.getCookie());
        output.setCurrentPage(input.getCurrentPage());
        output.setItens(input.getItens());
        output.setStatus(input.getStatus());
        output.setResult(bookModelMapper.trasform(input.getResult()));
        output.setPages(input.getPages());
        return output;
    }

    @Override
    public SearchResult transformBack(SearchResultModel input) {
        SearchResult output = new SearchResult();
        output.setCurrentPage(input.getCurrentPage());
        output.setCookie(input.getCookie());
        output.setPages(input.getPages());
        output.setStatus(input.getStatus());
        output.setItens(input.getItens());
        return output;
    }
}
