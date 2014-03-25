/* James Keller
 * ITCS 2215 - 002
 */
import java.util.*;

public class Verification 
{   
   static int count = 1;
   
   public static boolean verifyPairings(List<String> males, List<String> females,
          Map<String, String> pairings, Map<String, List<String>> malePreferences,
          Map<String, List<String>> femalePreferences) 
    {

        Map<String, String> reversedPairings = new TreeMap<>();
        for(Map.Entry<String, String> couple:pairings.entrySet())
        {
            //count++;
            reversedPairings.put(couple.getValue(), couple.getKey());
        }
 
        for(Map.Entry<String, String> pairing:pairings.entrySet())
        {
            List<String> femalePreference = femalePreferences.get(pairing.getKey());
            List<String> higherFemalePreference = new LinkedList<>();
            higherFemalePreference.addAll(femalePreference.subList(0, femalePreference.indexOf(pairing.getValue())));
            List<String> malesPreferences = malePreferences.get(pairing.getValue());
            List<String> higherMalePreference = new LinkedList<>();
            higherMalePreference.addAll(malesPreferences.subList(0, malesPreferences.indexOf(pairing.getKey())));
 
            for(String male:higherFemalePreference)
            {
                count++;
                String malesDeferredPairing = reversedPairings.get(male);
                List<String> thisMalesPreference = malePreferences.get(male);
                if(thisMalesPreference.indexOf(malesDeferredPairing) > thisMalesPreference.indexOf(pairing.getKey()))
                {
                    count++;
                    return false;
                }
            }
 
            for(String female:higherMalePreference)
            {
                count++;
                String femalesDeferredPairing = pairings.get(female);
                List<String> thisFemalesPreferences = femalePreferences.get(female);
                if(thisFemalesPreferences.indexOf(femalesDeferredPairing) > thisFemalesPreferences.indexOf(pairing.getValue()))
                {
                    count++;
                    return false;
                }
            }
        }
        //count++;
        return true;
    }
}
