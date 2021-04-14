import CSP.ConstraintSatisfactionProblem;
import ColoringProblem.ColoringConstraint;
import EinsteinProblem.EinsteinConstraint;

import java.util.*;

public class main {
    private static final int NO_OF_OPERATIONS_OF_THE_TEST = 100;

    public static void main(String[] args) {

        ConstraintSatisfactionProblem<String, String> mapCSP = ColoringEuropeMap();
        /*
        System.out.println("########### Rozwiązanie dla backTrackingSearch ###########");
        PrintColoringMapSolution((mapCSP.backTrackingSearch()));
        System.out.println(mapCSP.getVisiting());
        System.out.println("\n\n########### Rozwiązanie dla forwardCheckingSearch ###########");
        PrintColoringMapSolution((mapCSP.forwardCheckingSearchLoop()));
        System.out.println(mapCSP.getVisiting());
         */
/*
        System.out.println("Back Tracking Search");
        test(mapCSP, "backTrackingSearch");

        System.out.println("Froward Checking Search");
        test(mapCSP, "forwardCheckingSearch");
*/

        ConstraintSatisfactionProblem<List<String>, String> einsteinCSP = EinsteinProblem();
        System.out.println("\n\n########### Rozwiązanie dla backTrackingSearch ###########");
        PrintEinsteinSolution((einsteinCSP.backTrackingSearch()));
        System.out.println(einsteinCSP.getVisiting());
        System.out.println("\n\n########### Rozwiązanie dla forwardCheckingSearch ###########");
        PrintEinsteinSolution((einsteinCSP.forwardCheckingSearchLoop()));
        System.out.println(einsteinCSP.getVisiting());

    }

    static public ConstraintSatisfactionProblem<String, String> ColoringOpolskieMap(){
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

        return csp;
    }

    static public ConstraintSatisfactionProblem<String, String> ColoringEuropeMap(){
        List<String> variables = Arrays.asList("Iceland",
                "United Kingdom",
                "Ireland",
                "Norway",
                "Sweden",
                "Finland",
                "Estonia",
                "Latvia",
                "Lithuania",
                "Belarus",
                "Ukraine",
                "Moldova",
                "Poland",
                "Slovakia",
                "Czech Republic",
                "Germany",
                "Austria",
                "Hungary",
                "Romania",
                "Bulgaria",
                "Macedonia",
                "Greece",
                "Albania",
                "Serbia",
                "Bosnia and Herzegovina",
                "Croatia",
                "Slovenia",
                "Montenegro",
                "Liechenstein",
                "Monaco",
                "France",
                "Andorra",
                "Spain",
                "Portugal",
                "Belgium",
                "Netherlands",
                "Luxemburg",
                "Denmark",
                "Italy",
                "Malta",
                "Switzerland");
        Map<String, List<String>> domains = new HashMap<>();
        for(String var: variables){
            domains.put(var, Arrays.asList("blue", "red", "purple", "yellow"));
        }
        ConstraintSatisfactionProblem<String, String> csp = new ConstraintSatisfactionProblem<>(variables, domains);
        csp.addConstraint(new ColoringConstraint("Norway", "Sweden"));
        csp.addConstraint(new ColoringConstraint("Estonia", "Latvia"));
        csp.addConstraint(new ColoringConstraint("Latvia", "Lithuania"));
        csp.addConstraint(new ColoringConstraint("Lithuania", "Belarus"));
        csp.addConstraint(new ColoringConstraint("Latvia", "Belarus"));
        csp.addConstraint(new ColoringConstraint("Belarus", "Ukraine"));
        csp.addConstraint(new ColoringConstraint("Ukraine", "Moldova"));
        csp.addConstraint(new ColoringConstraint("Ukraine", "Poland"));
        csp.addConstraint(new ColoringConstraint("Ukraine", "Slovakia"));
        csp.addConstraint(new ColoringConstraint("Ukraine", "Hungary"));
        csp.addConstraint(new ColoringConstraint("Ukraine", "Romania"));
        csp.addConstraint(new ColoringConstraint("Moldova", "Romania"));
        csp.addConstraint(new ColoringConstraint("Romania", "Hungary"));
        csp.addConstraint(new ColoringConstraint("Romania", "Serbia"));
        csp.addConstraint(new ColoringConstraint("Romania", "Bulgaria"));
        csp.addConstraint(new ColoringConstraint("Bulgaria", "Serbia"));
        csp.addConstraint(new ColoringConstraint("Bulgaria", "Macedonia"));
        csp.addConstraint(new ColoringConstraint("Bulgaria", "Greece"));
        csp.addConstraint(new ColoringConstraint("Greece", "Macedonia"));
        csp.addConstraint(new ColoringConstraint("Greece", "Albania"));
        csp.addConstraint(new ColoringConstraint("Albania", "Macedonia"));
        csp.addConstraint(new ColoringConstraint("Albania", "Serbia"));
        csp.addConstraint(new ColoringConstraint("Albania", "Montenegro"));
        csp.addConstraint(new ColoringConstraint("Montenegro", "Serbia"));
        csp.addConstraint(new ColoringConstraint("Montenegro", "Bosnia and Herzegovina"));
        csp.addConstraint(new ColoringConstraint("Bosnia and Herzegovina", "Croatia"));
        csp.addConstraint(new ColoringConstraint("Bosnia and Herzegovina", "Serbia"));
        csp.addConstraint(new ColoringConstraint("Serbia", "Croatia"));
        csp.addConstraint(new ColoringConstraint("Serbia", "Hungary"));
        csp.addConstraint(new ColoringConstraint("Serbia", "Macedonia"));
        csp.addConstraint(new ColoringConstraint("Hungary", "Croatia"));
        csp.addConstraint(new ColoringConstraint("Hungary", "Slovenia"));
        csp.addConstraint(new ColoringConstraint("Hungary", "Austria"));
        csp.addConstraint(new ColoringConstraint("Hungary", "Slovakia"));
        csp.addConstraint(new ColoringConstraint("Slovakia", "Austria"));
        csp.addConstraint(new ColoringConstraint("Slovakia", "Czech Republic"));
        csp.addConstraint(new ColoringConstraint("Slovakia", "Poland"));
        csp.addConstraint(new ColoringConstraint("Poland", "Czech Republic"));
        csp.addConstraint(new ColoringConstraint("Poland", "Belarus"));
        csp.addConstraint(new ColoringConstraint("Poland", "Germany"));
        csp.addConstraint(new ColoringConstraint("Poland", "Lithuania"));
        csp.addConstraint(new ColoringConstraint("Germany", "Czech Republic"));
        csp.addConstraint(new ColoringConstraint("Czech Republic", "Austria"));
        csp.addConstraint(new ColoringConstraint("Austria", "Slovenia"));
        csp.addConstraint(new ColoringConstraint("Austria", "Italy"));
        csp.addConstraint(new ColoringConstraint("Austria", "Liechenstein"));
        csp.addConstraint(new ColoringConstraint("Austria", "Germany"));
        csp.addConstraint(new ColoringConstraint("Liechenstein", "Switzerland"));
        csp.addConstraint(new ColoringConstraint("Croatia", "Slovenia"));
        csp.addConstraint(new ColoringConstraint("Italy", "Slovenia"));
        csp.addConstraint(new ColoringConstraint("Italy", "Switzerland"));
        csp.addConstraint(new ColoringConstraint("Italy", "France"));
        csp.addConstraint(new ColoringConstraint("France", "Monaco"));
        csp.addConstraint(new ColoringConstraint("France", "Andorra"));
        csp.addConstraint(new ColoringConstraint("France", "Spain"));
        csp.addConstraint(new ColoringConstraint("France", "Belgium"));
        csp.addConstraint(new ColoringConstraint("France", "Luxemburg"));
        csp.addConstraint(new ColoringConstraint("France", "Germany"));
        csp.addConstraint(new ColoringConstraint("France", "Switzerland"));
        csp.addConstraint(new ColoringConstraint("Luxemburg", "Belgium"));
        csp.addConstraint(new ColoringConstraint("Luxemburg", "Germany"));
        csp.addConstraint(new ColoringConstraint("Germany", "Belgium"));
        csp.addConstraint(new ColoringConstraint("Netherlands", "Belgium"));
        csp.addConstraint(new ColoringConstraint("Germany", "Netherlands"));
        csp.addConstraint(new ColoringConstraint("Germany", "Denmark"));
        csp.addConstraint(new ColoringConstraint("Germany", "Switzerland"));
        csp.addConstraint(new ColoringConstraint("Andorra", "Spain"));
        csp.addConstraint(new ColoringConstraint("Spain", "Portugal"));
        csp.addConstraint(new ColoringConstraint("Ireland", "United Kingdom"));
        csp.addConstraint(new ColoringConstraint("France", "United Kingdom"));
        csp.addConstraint(new ColoringConstraint("Denmark", "Sweden"));
        csp.addConstraint(new ColoringConstraint("Finland", "Sweden"));
        csp.addConstraint(new ColoringConstraint("Iceland", "United Kingdom"));
        csp.addConstraint(new ColoringConstraint("Iceland", "Norway"));
        csp.addConstraint(new ColoringConstraint("Estonia", "Finland"));

        return csp;
    }

    static public ConstraintSatisfactionProblem<List<String>, String> EinsteinProblem(){
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

//        1.Norweg zamieszkuje pierwszy dom+
        domains.put(owners.get(0), Arrays.asList(houses.get(0)));
//        2. Anglik mieszka w czerwonym domu.+
        csp.addConstraint(new EinsteinConstraint(owners.get(1), homeColors.get(0)));
//        3. Zielony dom znajduje się bezpośrednio po lewej stronie domu białego.+
        csp.addConstraint(new EinsteinConstraint(homeColors.get(1), homeColors.get(2), true, true));
//        4. Duńczyk pija herbatkę.+
        csp.addConstraint(new EinsteinConstraint(owners.get(2), drinks.get(0)));
//        5. Palacz papierosów light mieszka obok hodowcy kotów.+
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(0), animals.get(0), true));
//        6. Mieszkaniec żółtego domu pali cygara.+
        csp.addConstraint(new EinsteinConstraint(homeColors.get(3),cigarettes.get(2)));
//        7. Niemiec pali fajkę.+
        csp.addConstraint(new EinsteinConstraint(owners.get(3), cigarettes.get(1)));
//        8. Mieszkaniec środkowego domu pija mleko.+
        domains.put(drinks.get(1), Arrays.asList(houses.get(2)));
//        9. Palacz papierosów light ma sąsiada, który pija wodę.+
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(0), drinks.get(2), true));
//        10.Palacz papierosów bez filtra hoduje ptaki.+
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(3), animals.get(1)));
//        11.Szwed hoduje psy.+
        csp.addConstraint(new EinsteinConstraint(owners.get(4), animals.get(2)));
//        12.Norweg mieszka obok niebieskiego domu.+
        csp.addConstraint(new EinsteinConstraint(owners.get(0), homeColors.get(4), true));
//        13.Hodowca koni mieszka obok żółtego domu.
        csp.addConstraint(new EinsteinConstraint(animals.get(3), homeColors.get(3), true));
//        14.Palacz mentolowych pija piwo.+
        csp.addConstraint(new EinsteinConstraint(cigarettes.get(4), drinks.get(3)));
//        15.W zielonym domu pija się kawę.+
        csp.addConstraint(new EinsteinConstraint(homeColors.get(1), drinks.get(4)));

        return csp;
    }

    public static void PrintColoringMapSolution(Map<String, String> solution){
        if(solution == null) {
            System.out.println("None solution");
        } else {
            solution.forEach((key, value) -> System.out.println(key + ": " + value));
        }
    }

    public static void PrintEinsteinSolution(Map<List<String>,String> solution){
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

    private static void test(ConstraintSatisfactionProblem<String, String> csp, String type){
        int totalVisiting = 0;
        long totalTime = 0;
        for(int i=0; i<NO_OF_OPERATIONS_OF_THE_TEST; i++){
            if(type.equals("backTrackingSearch")){
                long startTime = System.nanoTime();
                csp.backTrackingSearch();
                long stopTime = System.nanoTime();
                totalTime += stopTime - startTime;
                totalVisiting += csp.getVisiting();
            } else if(type.equals("forwardCheckingSearch")){
                long startTime = System.nanoTime();
                csp.forwardCheckingSearchLoop();
                long stopTime = System.nanoTime();
                totalTime += stopTime - startTime;
                totalVisiting += csp.getVisiting();
            } else {
                throw new IllegalArgumentException("Select the correct CSR type [backTrackingSearch, forwardCheckingSearch]");
            }
        }
        System.out.println("Avg time: " + totalTime/NO_OF_OPERATIONS_OF_THE_TEST);
        System.out.println("Avg visiting: " + totalVisiting/NO_OF_OPERATIONS_OF_THE_TEST);
    }
}
