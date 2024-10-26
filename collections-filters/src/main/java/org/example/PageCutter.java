package org.example;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.ArrayList;
import java.util.List;

public class PageCutter implements ICutToPage {
    @Override
    public List<Person> cut(Page page, List<Person> sampleData) {
        int start = (page.getPageNumber() -1) * page.getSize();
        int end = Math.min(start + page.getSize(), sampleData.size());
        if (start >= sampleData.size() || start < 0) {
            return new ArrayList<>();
        }
        return sampleData.subList(start, end);
    }
}
