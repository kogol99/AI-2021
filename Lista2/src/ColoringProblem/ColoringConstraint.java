package ColoringProblem;

import CSP.Constraint;

import java.util.Arrays;
import java.util.Map;

public class ColoringConstraint extends Constraint<String, String> {
    public String place1;
    public String place2;

    public ColoringConstraint(String place1, String place2){
        super(Arrays.asList(place1, place2));
        this.place1 = place1;
        this.place2 = place2;
    }

    @Override
    public boolean satisfied(Map<String, String> assignment) {
        if(!assignment.containsKey(place1) || !assignment.containsKey(place2)){
            return true;
        } else {
            return !assignment.get(place1).equals(assignment.get(place2));
        }
    }
}
