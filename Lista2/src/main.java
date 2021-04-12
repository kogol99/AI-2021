import CSP.ConstraintSatisfactionProblem;
import ColoringProblem.ColoringConstraint;
import EinsteinProblem.EinsteinConstraint;

import java.util.*;

public class main {

    public static void main(String[] args) {
        //ColoringMap();
        EinsteinProblem();
    }

    static public void ColoringMap(){
        List<String> variables = Arrays.asList("Opole", "opolski", "namysłowski", "kluczborski", "oleski", "brzeski",
                "strzelecki", "nyski", "prudnicki", "krapkowicki", "kędzierzyńsko-kozielski", "głubczycki");
        Map<String, List<String>> domains = new HashMap<>();
        for(String var: variables){
            domains.put(var, Arrays.asList("blue", "red", "purple"));
        }
        ConstraintSatisfactionProblem<String, String> csp = new ConstraintSatisfactionProblem<>(variables, domains);
        csp.addConstraint(new ColoringConstraint("Opole", "opolski"));
        csp.addConstraint(new ColoringConstraint("opolski", "namysłowski"));
        csp.addConstraint(new ColoringConstraint("opolski", "kluczborski"));
        csp.addConstraint(new ColoringConstraint("opolski", "oleski"));
        csp.addConstraint(new ColoringConstraint("opolski", "strzelecki"));
        csp.addConstraint(new ColoringConstraint("opolski", "krapkowicki"));
        csp.addConstraint(new ColoringConstraint("opolski", "prudnicki"));
        csp.addConstraint(new ColoringConstraint("opolski", "nyski"));
        csp.addConstraint(new ColoringConstraint("opolski", "brzeski"));
        csp.addConstraint(new ColoringConstraint("namysłowski", "brzeski"));
        csp.addConstraint(new ColoringConstraint("namysłowski", "kluczborski"));
        csp.addConstraint(new ColoringConstraint("kluczborski", "oleski"));
        csp.addConstraint(new ColoringConstraint("oleski", "strzelecki"));
        csp.addConstraint(new ColoringConstraint("strzelecki", "krapkowicki"));
        csp.addConstraint(new ColoringConstraint("strzelecki", "kędzierzyńsko-kozielski"));
        csp.addConstraint(new ColoringConstraint("nyski", "brzeski"));
        csp.addConstraint(new ColoringConstraint("nyski", "prudnicki"));
        csp.addConstraint(new ColoringConstraint("prudnicki", "krapkowicki"));
        csp.addConstraint(new ColoringConstraint("prudnicki", "głubczycki"));
        csp.addConstraint(new ColoringConstraint("prudnicki", "kędzierzyńsko-kozielski"));
        csp.addConstraint(new ColoringConstraint("krapkowicki", "kędzierzyńsko-kozielski"));
        csp.addConstraint(new ColoringConstraint("kędzierzyńsko-kozielski", "głubczycki"));

        Map<String, String> solution = csp.backTrackingSearch();
        if(solution == null) {
            System.out.println("None solution");
        } else {
            solution.forEach((key, value) -> System.out.println(key + ": " + value));
        }
    }

    static public void EinsteinProblem(){
        List<List<String>> variables = new ArrayList<>();
        List<List<String>> owners = new ArrayList<>();
        owners.add(Arrays.asList("owner", "Norweg")); //0
        owners.add(Arrays.asList("owner", "Anglik")); //1
        owners.add(Arrays.asList("owner", "Duńczyk")); //2
        owners.add(Arrays.asList("owner", "Niemiec")); //3
        owners.add(Arrays.asList("owner", "Szwed")); //4

        List<List<String>> homeColors = new ArrayList<>();
        homeColors.add(Arrays.asList("homeColor", "czerwony"));
        homeColors.add(Arrays.asList("homeColor", "zielony"));
        homeColors.add(Arrays.asList("homeColor", "biały"));
        homeColors.add(Arrays.asList("homeColor", "żółty"));
        homeColors.add(Arrays.asList("homeColor", "niebieski"));

        List<List<String>> drinks = new ArrayList<>();
        drinks.add(Arrays.asList("drink", "herbata"));
        drinks.add(Arrays.asList("drink", "mleko"));
        drinks.add(Arrays.asList("drink", "woda"));
        drinks.add(Arrays.asList("drink", "piwo"));
        drinks.add(Arrays.asList("drink", "kawa"));

        List<List<String>> cigarettes = new ArrayList<>();
        cigarettes.add(Arrays.asList("cigarette", "papierosy light"));
        cigarettes.add(Arrays.asList("cigarette", "fajka"));
        cigarettes.add(Arrays.asList("cigarette", "cygaro"));
        cigarettes.add(Arrays.asList("cigarette", "papierosy bez filtra"));
        cigarettes.add(Arrays.asList("cigarette", "mentolowe"));

        List<List<String>> animals = new ArrayList<>();
        animals.add(Arrays.asList("animal", "koty"));
        animals.add(Arrays.asList("animal", "ptaki"));
        animals.add(Arrays.asList("animal", "psy"));
        animals.add(Arrays.asList("animal", "konie"));
        animals.add(Arrays.asList("animal", "rybki"));

        variables.addAll(owners);
        variables.addAll(homeColors);
        variables.addAll(drinks);
        variables.addAll(cigarettes);
        variables.addAll(animals);

        List<String> houses = Arrays.asList("1","2","3","4","5");

        Map<List<String>, List<String>> domains = new HashMap<>();
        for(List<String> var: variables){
            domains.put(var, houses);
        }

        ConstraintSatisfactionProblem<List<String>, String> csp
                = new ConstraintSatisfactionProblem<>(variables, domains);

//        1.Norweg zamieszkuje pierwszy dom
        domains.put(owners.get(0), Arrays.asList(houses.get(0)));
//        2. Anglik mieszka w czerwonym domu.
        csp.addConstraint(new EinsteinConstraint(owners.get(1), homeColors.get(0)));
//        3. Zielony dom znajduje się bezpośrednio po lewej stronie domu białego.
        csp.addConstraint(new EinsteinConstraint(homeColors.get(1), homeColors.get(2), true, true));
//        4. Duńczyk pija herbatkę.
        csp.addConstraint(new EinsteinConstraint(owners.get(2), drinks.get(0)));
//        5. Palacz papierosów light mieszka obok hodowcy kotów.
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(0), animals.get(0), true));
//        6. Mieszkaniec żółtego domu pali cygara.
        csp.addConstraint(new EinsteinConstraint(homeColors.get(3),cigarettes.get(2)));
//        7. Niemiec pali fajkę.
        csp.addConstraint(new EinsteinConstraint(owners.get(3), cigarettes.get(1)));
//        8. Mieszkaniec środkowego domu pija mleko.
        domains.put(drinks.get(1), Arrays.asList(houses.get(2)));
//        9. Palacz papierosów light ma sąsiada, który pija wodę.
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(0), drinks.get(2), true));
//        10.Palacz papierosów bez filtra hoduje ptaki.
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(3), animals.get(1)));
//        11.Szwed hoduje psy.
        csp.addConstraint(new EinsteinConstraint(owners.get(4), animals.get(2)));
//        12.Norweg mieszka obok niebieskiego domu.
        domains.put(homeColors.get(4), Arrays.asList(houses.get(1)));
        //csp.addConstraint(new EinsteinConstraint(owners.get(0), homeColors.get(4), true));
//        13.Hodowca koni mieszka obok żółtego domu.
        csp.addConstraint(new EinsteinConstraint(animals.get(3), homeColors.get(3), true));
//        14.Palacz mentolowych pija piwo.
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(4), drinks.get(3)));
//        15.W zielonym domu pija się kawę.
        csp.addConstraint(new EinsteinConstraint(homeColors.get(1), drinks.get(4)));


        Map<List<String>,String> solution = csp.backTrackingSearch();
        Map<List<String>,String> solutionHouse1 = new HashMap<>();
        Map<List<String>,String> solutionHouse2 = new HashMap<>();
        Map<List<String>,String> solutionHouse3 = new HashMap<>();
        Map<List<String>,String> solutionHouse4 = new HashMap<>();
        Map<List<String>,String> solutionHouse5 = new HashMap<>();
        for(Map.Entry<List<String>, String> entry: solution.entrySet()){
            if (Integer.parseInt(entry.getValue()) == 1){
                solutionHouse1.put(entry.getKey(), entry.getValue());
            } else if(Integer.parseInt(entry.getValue()) == 2){
                solutionHouse2.put(entry.getKey(), entry.getValue());
            } else if(Integer.parseInt(entry.getValue()) == 3){
                solutionHouse3.put(entry.getKey(), entry.getValue());
            } else if(Integer.parseInt(entry.getValue()) == 4){
                solutionHouse4.put(entry.getKey(), entry.getValue());
            } else if(Integer.parseInt(entry.getValue()) == 5){
                solutionHouse5.put(entry.getKey(), entry.getValue());
            }
        }
        for(int i=1; i<6; i++){
            if (i == 1){
                for(Map.Entry<List<String>, String> entry: solutionHouse1.entrySet()){
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else if (i == 2){
                for(Map.Entry<List<String>, String> entry: solutionHouse2.entrySet()){
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else if (i == 3){
                for(Map.Entry<List<String>, String> entry: solutionHouse3.entrySet()){
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else if (i == 4){
                for(Map.Entry<List<String>, String> entry: solutionHouse4.entrySet()){
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            } else {
                for(Map.Entry<List<String>, String> entry: solutionHouse5.entrySet()){
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            }
        }
    }
}
