package org.example.model.calculators;

import org.example.ICalculate;
import org.example.model.Person;
import org.example.queries.search.Funcs;
import org.example.queries.search.FunctionsParameters;

import java.util.List;
import java.util.function.Function;

public class GeneralCalculator implements ICalculate {
    private String fieldName;
    private Function<Person, Number> fieldGetter;
    public GeneralCalculator(String fieldName, Function<Person, Number> fieldGetter) {
        this.fieldName = fieldName;
        this.fieldGetter = fieldGetter;
    }

    @Override
    public String getFieldName() {
        return fieldName;
    }

    @Override
    public double calculate(FunctionsParameters functionsParameters, List<Person> sampleData) {
        double result = 0;
        if(functionsParameters.getFunction() == Funcs.SUM){
            for (Person p : sampleData){
                result += fieldGetter.apply(p).doubleValue();
            }
            return result;
        } else if (functionsParameters.getFunction() == Funcs.MAX) {
            result = Double.MIN_VALUE;
            for (Person p : sampleData){
                double currentValue = fieldGetter.apply(p).doubleValue();
                if (currentValue > result) {
                    result = currentValue;
                }
            }
        }else if(functionsParameters.getFunction() == Funcs.MIN){
            result = Double.MAX_VALUE;
            for (Person p : sampleData) {
                double currentValue = fieldGetter.apply(p).doubleValue();
                if (currentValue < result) {
                    result = currentValue;
                }
            }
        } else if (functionsParameters.getFunction() == Funcs.AVERAGE) {
            double sum = 0;
            for (Person p : sampleData) {
                sum += fieldGetter.apply(p).doubleValue();
            }
            return sum / sampleData.size();
        }
        return 0;
    }
}
