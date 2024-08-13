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
        List<Particle> adjacentParticles = new ArrayList<>();

        // tengo
        // - - -
        // - - -
        // - - -

        // reviso
        // - x x
        // - x x
        // - - -
        for(int i = Math.max(x-1, 0); i <= x; i++) {
            for(int j = y; j <= Math.min(y+1, grid.size() - 1); j++) {
                for(Particle p2 : grid.get(i).get(j)) {
                    if(!p.equals(p2) && getDistance(p, p2) < rc) {
                        if(!map.containsKey(p)) {
                            map.put(p, new HashSet<>());
                        }
                        if(!map.containsKey(p2)) {
                            map.put(p2, new HashSet<>());
                        }
                        map.get(p).add(p2);
                        map.get(p2).add(p);
                    }
                }
            }
        }
        // reviso
        // - - -
        // - - -
        // - - x
        x = x+1;
        y = y+1;
        if (!(x >= grid.size() || y >= grid.get(x).size())) {
            for(Particle p2: grid.get(x).get(y)) {
                if(getDistance(p, p2) < rc) {
                    if(!map.containsKey(p)) {
                        map.put(p, new HashSet<>());
                    }
                    if(!map.containsKey(p2)) {
                        map.put(p2, new HashSet<>());
                    }
                    map.get(p).add(p2);
                    map.get(p2).add(p);
                }
            }
        }

    }

    private static double getDistance(Particle p1, Particle p2) {
        double c1 = p1.getX() - p2.getX();
        double c2 = p1.getY() - p2.getY();
        return Math.sqrt(Math.pow(c1, 2) + Math.pow(c2, 2));
    }
}
