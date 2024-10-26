package org.example.model.filters;

import org.example.IFilterPeople;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ByNameFilter implements IFilterPeople {
    private SearchParameters searchParameters;

    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return searchParameters.getName() != null;
    }

    @Override
    public List<Person> filter(List<Person> items) {
        return items.stream()
                .filter(p -> p.getName().equals(searchParameters.getName()))
                .collect(Collectors.toList());
    }

}
