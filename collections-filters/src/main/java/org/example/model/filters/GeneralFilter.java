package org.example.model.filters;

import org.example.IFilterPeople;
import org.example.model.Person;
import org.example.queries.search.SearchParameters;

import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GeneralFilter implements IFilterPeople {
    private SearchParameters searchParameters;
    private Predicate<SearchParameters> canFilterPredicate;
    private BiPredicate<SearchParameters, Person> filterDualPredicate;
    @Override
    public void setSearchParameters(SearchParameters searchParameters) {
        this.searchParameters = searchParameters;
    }

    @Override
    public boolean canFilter() {
        return canFilterPredicate.test(searchParameters);
    }

    @Override
    public List<Person> filter(List<Person> items) {
        return items.stream()
                .filter(person -> filterDualPredicate.test(searchParameters, person))
                .collect(Collectors.toList());
    }

    public GeneralFilter(Predicate<SearchParameters> canFilterPredicate, BiPredicate<SearchParameters, Person> filterDualPredicate) {
        this.canFilterPredicate = canFilterPredicate;
        this.filterDualPredicate = filterDualPredicate;
    }
}
