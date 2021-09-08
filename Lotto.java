import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lotto {

      private static List<Boolean> isGewinn(List<Integer> tipps, List<Integer> gewinn) {//List<Integer> tipp, List<Integer> Gewinn
            List<Boolean> ergebnis = new ArrayList<>();
            //tipps.stream().filter(tipp -> gewinn.contains(tipp)).collect(Collectors.toList()).forEach(succesfulltip -> System.out.println(succesfulltip));
            tipps.stream().forEach(tipp -> ergebnis.add(gewinn.contains(tipp)));
            return ergebnis;
      }

      public static void main(String[] args) {
            List<Integer> lst = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
            List<Integer> gewinn = Arrays.asList(1, 2, 7);
            List<Boolean> ergebnis = isGewinn(lst, gewinn);
            ergebnis.stream().forEach(x -> System.out.println(x));
      }
}
// vllt. Gewinnklassen 1 - 9 als Objekt? 
/*
 * https://stackoverflow.com/questions/30279317/sending-data-from-java-to-php-file
 * https://www.codejava.net/java-se/jdbc/read-file-data-from-database-using-jdbc
 * 
 * 
 */
