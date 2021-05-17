package experiment4.yhicxu.bean;

import util.graphutil.GraphNode;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class Site implements GraphNode {

    private int id;

    private String name;

    private Set<Site> adjacentSites;

    public Site(int id, String name) {
        this.id = id;
        this.name = name;
        adjacentSites = new HashSet<>();
    }

    public void addAdjacentSite(Site adjacentSite) {
        adjacentSites.add(adjacentSite);
    }

    public void removeAdjacentSite(Site adjacentSite) {
        adjacentSites.remove(adjacentSite);
    }

    public Set<Site> getAdjacentSites() {
        return adjacentSites;
    }

    public int getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        return this.id == ((Site) o).id;
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
