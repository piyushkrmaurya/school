package com.herokuapp.schoolmvc.form;

import java.util.ArrayList;
import java.util.List;

public class ResultForm {

    private List<SingleResult> results = new ArrayList<SingleResult>();

    public ResultForm() {
    }

    public List<SingleResult> getResults() {
        return results;
    }

    public void setResults(ArrayList<SingleResult> results) {
        this.results = results;
    }

    public void addResult(Long enrollId){
        this.results.add(new SingleResult(enrollId));
    }

}