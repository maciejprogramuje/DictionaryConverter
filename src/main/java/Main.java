import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Main {
    // --------------------------------------------------------------------------
    public static final String LANGUAGE_CODE = "PL";
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------
    // --------------------------------------------------------------------------

    public static void main(String[] args) throws IOException {
        System.out.println("Start!");

        String lettersSmall = "abcdefghijklmnopqrstuvwxyz";
        String inputFileName = "input_" + LANGUAGE_CODE + ".txt";
        TreeMap<String, String> treeMap = new TreeMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "UTF8"));
        PrintWriter writer = null;

        for (int i = 0; i < lettersSmall.length(); i++) {
            File file = new File("output_" + LANGUAGE_CODE + "_" + lettersSmall.charAt(i) + ".txt");
            file.createNewFile();
        }

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);

            String tKey = line.substring(0, line.indexOf(" [ "));
            String tValue = line.substring(line.indexOf(" [ "))
                    .replace(" ", "")
                    .replace("=", " ")
                    .replace("[", "(")
                    .replace("]", ")")
                    .replace("(przymiotnik)", "(przym.)")
                    .replace("(rzeczownik)", "(rzecz.)")
                    .replace("(czasownik)", "(czas.)")
                    .replace("(przyimek)", "(przyim.)")
                    .replace("(przysłówek)", "(przysł.)");
            if (!treeMap.containsKey(tKey)) {
                treeMap.put(tKey, tValue);
            } else {
                treeMap.replace(tKey, treeMap.get(tKey) + "; " + tValue);
            }
        }

        Set<Map.Entry<String, String>> entrySet = treeMap.entrySet();
        for (Map.Entry<String, String> entry : entrySet) {
            String rawKey = entry.getKey()
                    .replace("ą", "a")
                    .replace("ć", "c")
                    .replace("ę", "e")
                    .replace("ł", "l")
                    .replace("ń", "n")
                    .replace("ó", "o")
                    .replace("ś", "s")
                    .replace("ź", "z")
                    .replace("ż", "z");

            String tLine = "[" + rawKey.toLowerCase() + "]" + entry.getKey() + " : " + entry.getValue();
            System.out.println(tLine);

            writer = new PrintWriter(new BufferedWriter(new FileWriter("output_" + LANGUAGE_CODE + "_" + rawKey.charAt(0) + ".txt", true)));
            writer.println(tLine);
            writer.close();
        }


        System.out.println("Koniec!");
    }
}
