import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Alice {

    public static String copyHtmlText(String webUrl) throws Exception{

        final Document document = Jsoup.connect(webUrl).get();


        String title = document.text();
        title =  title.toLowerCase();

        return title;
    }

    public static int countChars(String text){

        int count = 0;
        for(int i = 0; i<text.length(); i++){
            if (text.charAt(i) != ' '){
                count++;
            }
        }
        return count;
    }

    public static int countWords(String text){

        StringTokenizer tokenizer = new StringTokenizer(text);
        int x = tokenizer.countTokens();
        return x;
    }

    public static Map countUniqueWords (String text){
        Map<String,Integer> jonsFødselsdagsGave = new HashMap<>();


        Pattern pattern = Pattern.compile("[a-zA-Z]+");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()){

            if (jonsFødselsdagsGave.containsKey(matcher.group())){
                jonsFødselsdagsGave.put(matcher.group(), jonsFødselsdagsGave.get(matcher.group()) +1);
            }else {
                jonsFødselsdagsGave.put(matcher.group(),1);
            }




        }

        return jonsFødselsdagsGave;
    }

    //metode som sorter et map, jeg har ikke selv lavet denne dog..
    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }



    public static void main(String[] args) throws Exception{


        String text = copyHtmlText("http://www.extremeprogramming.org/rules/early.html");
        System.out.println(text);
        System.out.println("This is the number of chars in test: " + countChars(text));
        System.out.println("This is the amount of words in the text: " + countWords(text));


        Map<String,Integer> mapUnsorted = countUniqueWords(text);
        Map<String,Integer> mapSorted = sortByValue(mapUnsorted);
        System.out.println("This is the unique words, and the amount of times they appear: " + mapSorted);


    }
}
