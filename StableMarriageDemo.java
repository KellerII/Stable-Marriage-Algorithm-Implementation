/* James Keller
 * ITCS 2215 - 002
 */
import java.util.*;
import static java.util.Arrays.asList;
 
public class StableMarriageDemo {
    //Creating lists of males and females
    static List<String> males = asList("Adam", "Ben", "Cameron");
    static List<String> females = asList("Anna", "Becky", "Chelsea");
    static Map<String, List<String>> malePreferences =
            new HashMap<String, List<String>>(){{
        put("Adam", asList("Anna", "Becky", "Chelsea"));
        put("Ben", asList("Becky", "Chelsea", "Anna"));
        put("Cameron", asList("Chelsea", "Anna", "Becky"));}};
    //Mapping female and male preferences
    static Map<String, List<String>> femalePreferences =
            new HashMap<String, List<String>>(){{
        put("Anna", asList("Ben", "Cameron", "Adam"));
        put("Becky", asList("Cameron", "Adam", "Ben"));
        put("Chelsea", asList("Adam", "Ben", "Cameron"));}};  
    //Mapping with unstable pairings for testing  
    static Map<String, String> unstablePairingsTest = new HashMap<String, String>(){{
        put("Anna", "Adam");
        put("Becky", "Cameron");
        put("Chelsea", "Ben");}};
    //Mapping with stable pairings
    static Map<String, String> stablePairingsTest = new HashMap<String, String>(){{
        put("Anna", "Adam");
        put("Becky", "Ben");
        put("Chelsea", "Cameron");}};
    
    public static void main(String[] args)
    {   
        String status;
        
        do
        {
            int choice;
            System.out.println("Welcome to the Stable Marriage Demo!\n");
            System.out.println("Menu Choices:\n");
            System.out.println("\tPairing");
            System.out.println("------------------------------------------");
            System.out.println("1: Create stable pairings based on the men's preferences.");
            System.out.println("2: Create stable pairings based on the females' preferences.");
            System.out.println("\n\tVerification");
            System.out.println("-------------------------------------------");
            System.out.println("3: Check pairing stability.");

            System.out.print("\nEnter your choice now: ");
            Scanner keyboard = new Scanner(System.in);
            choice = keyboard.nextInt();

            switch(choice)
            {
                case 1:
                    System.out.println("\nCouple Preferences Matrix:");
                    System.out.println("            Anna    Becky   Chelsea");
                    System.out.println("            _______________________");
                    System.out.println("Adam        |1,3    2,2     3,1    ");
                    System.out.println("Ben         |3,1    1,3     2,2    ");
                    System.out.println("Cameron     |2,2    3,1     1,3    ");
                    System.out.println("\nMale Preference Based Pairings:");
                    Map<String, String> pairings = Coordinator.pair(males, malePreferences, femalePreferences);
                    for(Map.Entry<String, String> pairing:pairings.entrySet())
                    {
                        System.out.println(pairing.getKey() + " is paired with " + pairing.getValue() + ".");
                    }
                    if(Verification.verifyPairings(males, females, pairings, malePreferences, femalePreferences))
                    {
                        System.out.println("\nThese pairings have been verified as stable.\n");
                    }
                    else
                    {
                        System.out.println("\nThese pairings have been verified as unstable.\n");
                    } 
                    break;
                case 2:
                    System.out.println("\nCouple Preferences Matrix:");
                    System.out.println("            Anna    Becky   Chelsea");
                    System.out.println("            _______________________");
                    System.out.println("Adam        |1,3    2,2     3,1    ");
                    System.out.println("Ben         |3,1    1,3     2,2    ");
                    System.out.println("Cameron     |2,2    3,1     1,3    ");
                    System.out.println("\nFemale Preference Based Pairings:");
                    Map<String, String> pairingsReversed = Coordinator.pair(females, femalePreferences, malePreferences);
                    for(Map.Entry<String, String> pairing:pairingsReversed.entrySet())
                    {
                        System.out.println(pairing.getKey() + " is paired with " + pairing.getValue() + ".");
                    }
                    if(Verification.verifyPairings(females, males, pairingsReversed, femalePreferences, malePreferences))
                    {
                        System.out.println("\nThese pairings have been verified as stable.\n");
                    }
                    else
                    {
                        System.out.println("\nThese pairings have been verified as unstable.\n");
                    } 
                    break;
                case 3:
                    System.out.println("\nCouple Preferences Matrix:");
                    System.out.println("            Anna    Becky   Chelsea");
                    System.out.println("            _______________________");
                    System.out.println("Adam        |1,3    2,2     3,1    ");
                    System.out.println("Ben         |3,1    1,3     2,2    ");
                    System.out.println("Cameron     |2,2    3,1     1,3    ");
                    System.out.println("\nLet's check whether the following pairs "
                            + " are stable based on the men's preferences:");
                    System.out.println("Adam + Anna, Ben + Becky, Cameron + Chelsea");
                    if(Verification.verifyPairings(males, females, stablePairingsTest, malePreferences, femalePreferences))
                    {
                        System.out.println("\nThese pairings have been verified as stable.");
                    }
                    else
                    {
                        System.out.println("\nThese pairings have been verified as unstable.");  
                    }
            
                    System.out.println("\nLet's reassign our pairings and reuse our verification method "
                            + "to check stability.");
                    System.out.println("Our new pairings are: \nAdam + Anna, Cameron + Becky"
                            + ", Ben + Chelsea");
                    if(Verification.verifyPairings(males, females, unstablePairingsTest, malePreferences, femalePreferences))
                    {
                        System.out.println("\nThese pairings have been verified as stable.\n");
                    }
                    else
                    {
                        System.out.println("\nThese pairings have been verified as unstable.\n");  
                    }
                    break;
                default:
                    System.out.println("\nYou didn't select a valid option. Please try again.\n");
                    status = "Y";
                    break;
            }
         
         Scanner input = new Scanner (System.in);
         System.out.print("Would you like to run the demo again? " + "(Y or N): ");
         status = input.nextLine();
         System.out.println("");
         
        }while(status.equalsIgnoreCase("Y") || status.equalsIgnoreCase("Yes"));
    }
}