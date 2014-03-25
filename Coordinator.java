/* James Keller
 * ITCS 2215 - 002
 */
import java.util.*;

public class Coordinator 
{
    public static Map<String, String> pair(List<String> males,
           Map<String, List<String>> malePreferences, Map<String, List<String>> femalePreferences)
    {
        Map<String, String> pairing = new TreeMap<>();
        List<String> unassignedMales = new LinkedList<>();
        unassignedMales.addAll(males);
        while(!unassignedMales.isEmpty())
        {
            String thisMale = unassignedMales.remove(0);
            List<String> thisMalesPreferences = malePreferences.get(thisMale);
            for(String female:thisMalesPreferences)
            {
                if(pairing.get(female) == null)
                {
                    pairing.put(female, thisMale);
                    break;
                }
                else
                {
                    String anotherMale = pairing.get(female);
                    List<String> thisGirlPrefers = femalePreferences.get(female);
                    if(thisGirlPrefers.indexOf(thisMale) < thisGirlPrefers.indexOf(anotherMale))
                    {
                        pairing.put(female, thisMale);
                        unassignedMales.add(anotherMale);
                        break;
                    }
                }
            }
        }
        return pairing;
    }
}
