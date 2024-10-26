package org.example;

import org.example.model.Person;
import org.example.queries.search.Page;

import java.util.List;

public interface ICutToPage {
    List<Person> cut(Page page, List<Person> sampleData);
}
