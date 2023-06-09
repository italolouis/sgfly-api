package br.com.sgfly.model.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Graph {
    private String name;

    private List<SerieItem> series;
}
