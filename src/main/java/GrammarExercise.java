import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GrammarExercise {

    static List<String> AppendList = new ArrayList<>() ;
    public static void main(String[] args) throws Exception {
        //
        String firstWordList = "";
        String secondWordList = "";
        Scanner in = new Scanner(System.in);

        //Get input String
        System.out.println("Enter the first string: ");
        firstWordList = in.nextLine();
        System.out.println("Enter the second string: ");
        secondWordList = in.nextLine();
        in.close();

        //repeat element
        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //output
        //output the repeat
        result.stream().forEach(str->System.out.print(str +","));

        System.out.println("\n");
        //output the no-repeat list
        AppendList.stream().forEach(str->System.out.println(str+","));
    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) throws Exception {

        //test if legitimate
        if (!(ifLegitimate(firstWordList)&&ifLegitimate(secondWordList))){
            throw new RuntimeException("input not valid");
        }

        //when legal
        // 1.split and to list

        List<String> firstArray = Arrays.asList(firstWordList.split(","));
        List<String> secondArray = Arrays.asList(secondWordList.split(","));

        List<String> repeat = new ArrayList<>();
        repeat.addAll(firstArray);
        repeat.addAll(secondArray);
        // 2.no-repeat element  add to the AppendList,but remove from repeat list

        //List<String> finalRepeat = repeat;
        firstArray.stream().filter(str->(AppendList.stream().filter(a->a.equalsIgnoreCase(str)).count())==0
        ).forEach(str->{AppendList.add(str);
            repeat.remove(str);});
        secondArray.stream().filter(str->(AppendList.stream().filter(a->a.equalsIgnoreCase(str)).count())==0
        ).forEach(str->{AppendList.add(str);
            repeat.remove(str);});
        // 3.sort
        Collections.sort(AppendList);
        //to uppercase and add space
        ArrayList<String> uppercaseWithSpacelist = new ArrayList<>();
        repeat.stream().forEach(str->uppercaseWithSpacelist.add(AddSpace(str.toUpperCase())));

        //remove element in repeat list
        Set<String> deduplication = uppercaseWithSpacelist.stream().collect(Collectors.toSet());
        List<String> SortedNoRepeat = new ArrayList<>(deduplication);
        Collections.sort(SortedNoRepeat);
        return SortedNoRepeat;
    }

    //if match then legitimate
    public static boolean ifLegitimate(String toBeTest){
        String str = toBeTest;
        String pattern = "([A-Za-z]+[,]?)+";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        System.out.println(m.matches());
        return m.matches();
    }

    public static String AddSpace (String origin){
        char[] arr = origin.toCharArray();
        String result = "";
        for (int i = 0 ; i < arr.length-1 ; i++){
            result = result + arr[i] + " ";
        }
        result = result + arr[arr.length-1];
        return result;
    }
}
