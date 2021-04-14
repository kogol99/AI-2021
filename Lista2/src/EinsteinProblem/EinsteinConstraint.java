package EinsteinProblem;

import CSP.Constraint;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EinsteinConstraint extends Constraint<List<String>, String> {
    public List<String> spec1;
    public List<String> spec2;
    public boolean neighbours;
    public boolean isLeftNeighbours;

    public EinsteinConstraint(List<String> spec1, List<String> spec2) {
        super(Arrays.asList(spec1, spec2));
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.neighbours = false;
        this.isLeftNeighbours = false;
    }

    public EinsteinConstraint(List<String> spec1, List<String> spec2, boolean neighbours) {
        super(Arrays.asList(spec1, spec2));
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.neighbours = neighbours;
        this.isLeftNeighbours = false;
    }

    public EinsteinConstraint(List<String> spec1, List<String> spec2, boolean neighbours, boolean isLeftNeighbours) {
        super(Arrays.asList(spec1, spec2));
        this.spec1 = spec1;
        this.spec2 = spec2;
        this.neighbours = neighbours;
        this.isLeftNeighbours = isLeftNeighbours;
    }


    @Override
    public boolean satisfied(Map<List<String>,String> assignment) {
        // sprawdzanie czy poprawnie są sąsiadami
        if(this.neighbours && assignment.containsKey(this.spec1) && assignment.containsKey(this.spec2)){
            if (!this.isLeftNeighbours) {
                if (Math.abs(Integer.parseInt(assignment.get(this.spec1)) - Integer.parseInt(assignment.get(this.spec2))) != 1) {
                    return false;
                }
            }
            else {
                if(Integer.parseInt(assignment.get(this.spec1)) - Integer.parseInt(assignment.get(this.spec2)) != -1){
                    return false;
                }
            }
        }

        // sprawdzenie czy nie ma np domu w ktorym przypisane są dwa napoje
        for(Map.Entry<List<String>, String> entry: assignment.entrySet()){
            for(Map.Entry<List<String>, String> entry2: assignment.entrySet()){
                if(entry != entry2 && entry.getKey().get(0).equals(entry2.getKey().get(0))){
                    if (assignment.get(entry.getKey()).equals(assignment.get(entry2.getKey()))) {
                        return false;
                    }
                }
            }
        }

        if(!assignment.containsKey(this.spec1) || !assignment.containsKey(this.spec2)) {
            return true;
        }

        if(!this.neighbours){
            return assignment.get(this.spec1).equals(assignment.get(this.spec2));
        }

        return true;
    }
}
