package org.example.model.filters;

import org.example.IFilterPeople;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.stream.Collectors;

public class ByAgeToFilter implements IFilterPeople {
    private SearchParameters searchParameters;
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;

    }

    @Override
    public boolean canFilter() {
        return searchParameters.getAgeTo()>0;
    }

    @Override
    public List<Person> filter(List<Person> items) {
        return items.stream()
                .filter(p -> p.getAge() <= searchParameters.getAgeTo())
                .collect(Collectors.toList());
    }

}
