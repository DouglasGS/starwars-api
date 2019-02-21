package br.com.b2w.infraestructure.client.dto;

import java.util.List;

public class PlanetSwapiResponseList {

    private Integer count;
    private String next;
    private String previous;
    private List<PlanetSwapiResponse> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetSwapiResponse> getResults() {
        return results;
    }

    public void setResults(List<PlanetSwapiResponse> results) {
        this.results = results;
    }

}
