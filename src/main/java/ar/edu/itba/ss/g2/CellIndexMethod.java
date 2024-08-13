package ar.edu.itba.ss.g2;

import java.util.*;

public class CellIndexMethod {

    public static Map<Particle, Set<Particle>> calculate(List<Particle> particles, Long L, Long M, Double rc) {
        List<List<Set<Particle>>> grid = new ArrayList<List<Set<Particle>>>();
        // TODO: border
        for(int i = 0; i < M; i++) {
            grid.add(new ArrayList<>());
            for(int j = 0; j < M; j++) {
                grid.get(i).add(new HashSet<>());
            }
        }

        for(Particle p : particles) {
            int x = (int) ((p.getX() * M)/L);
            int y = (int) ((p.getY() * M)/L);
            grid.get(x).get(y).add(p);
        }

        Map<Particle, Set<Particle>> map = new HashMap<>();


        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid.get(i).size(); j++) {
                for(Particle p : grid.get(i).get(j)) {
                    checkAdjacent(grid, i, j, rc, p, map);
                }
            }
        }

        return map;
    }

    private static void checkAdjacent(List<List<Set<Particle>>> grid, int x, int y, double rc, Particle p, Map<Particle, Set<Particle>> map) {
        if(x < 0 || y < 0 || x >= grid.size() || y >= grid.get(x).size()) {
            return;
        }

        // tengo
        // - - -
        // - x -
        // - - -

        // reviso
        // x x -
        // x x -
        // - - -
        for(int i = Math.max(x-1, 0); i <= x; i++) {
            for(int j = y; j <= Math.min(y+1, grid.size() - 1); j++) {
                for(Particle p2 : grid.get(i).get(j)) {
                    if(!p.equals(p2)) {
                        addIfClose(p, p2, rc, map);
                    }
                }
            }
        }
        // reviso
        // - - x
        // - - -
        // - - -
        x = x+1;
        y = y+1;
        if (!(x >= grid.size() || y >= grid.get(x).size())) {
            for(Particle p2: grid.get(x).get(y)) {
                addIfClose(p, p2, rc, map);
            }
        }
    }

    private static void addIfClose(Particle p1, Particle p2, Double rc, Map<Particle, Set<Particle>> map) {
        if(p1.distanceTo(p2) < rc) {
            if(!map.containsKey(p1)) {
                map.put(p1, new HashSet<>());
            }
            if(!map.containsKey(p2)) {
                map.put(p2, new HashSet<>());
            }
            map.get(p1).add(p2);
            map.get(p2).add(p1);
        }
    }
}
