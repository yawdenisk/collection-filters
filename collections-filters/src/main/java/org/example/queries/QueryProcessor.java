package org.example.queries;

import org.example.ICalculate;
import org.example.ICutToPage;
import org.example.IFilterPeople;
import org.example.PageCutter;
import org.example.model.Person;
import org.example.queries.results.FunctionResult;
import org.example.queries.results.Results;
import org.example.queries.search.FunctionsParameters;
import org.example.queries.search.SearchParameters;

import java.util.ArrayList;
import java.util.List;

public class QueryProcessor {
    private List<IFilterPeople> filter = new ArrayList<>();
    private List<ICalculate> calculators = new ArrayList<>();
    private ICutToPage iCutToPage;

        public Results GetResults(SearchParameters parameters, List<Person> data){
            Results results = new Results();
            for(IFilterPeople f : filter){
                f.setSearchParameters(parameters);
                if(f.canFilter()){
                    data = f.filter(data);
                }
            }
            results.setItems(data);
                List<FunctionResult> functionResults = new ArrayList<>();
                for(ICalculate c : calculators){
                    for(FunctionsParameters f : parameters.getFunctions()){
                        if (c.getFieldName().equals(f.getFieldName())){
                            FunctionResult result = new FunctionResult();
                            double count = c.calculate(f, data);
                            result.setFieldName(f.getFieldName());
                            result.setFunction(f.getFunction());
                            result.setValue(count);
                            functionResults.add(result);
                        }
                    }
                }
                results.setFunctionResults(functionResults);
            List<Person> paginatedData = iCutToPage.cut(parameters.getPage(), data);
            results.setItems(paginatedData);
            int totalPages = (int) Math.ceil((double) data.size() / parameters.getPage().getSize());
            results.setPages(totalPages);
            results.setCurrentPage(parameters.getPage().getPageNumber());
            return results;
        }

    public QueryProcessor addFilter(IFilterPeople filter) {
        this.filter.add(filter);
        return this;
    }

    public QueryProcessor addCalculation(ICalculate incomeCalculator) {
        this.calculators.add(incomeCalculator);
        return this;
    }

    public void addPageCutter(ICutToPage pageCutter) {
            this.iCutToPage = pageCutter;
    }
}
