package experiment4.yhicxu.bean;

import util.graphutil.GraphNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Set;

public class Site implements GraphNode {

    private String name;

    private Set<Site> adjacentSites;

    public Site(String name) {
        this.name = name;
        adjacentSites = new HashSet<>();
    }

    public void addAdjacentSite(Site adjacentSite) {
        adjacentSites.add(adjacentSite);
    }

    public Set<Site> getAdjacentSites(){
        return adjacentSites;
    }

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((Site) o).getName());
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return name;
    }

}
