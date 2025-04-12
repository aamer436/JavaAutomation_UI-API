package com.practice.basics;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Puzzles_1 {
    public static void main(String[] args) {

        String reverse_string=get_rev_string("value");
        System.out.println("reverse string "+reverse_string);

        int largest_num = get_largest_element_in_list(Arrays.asList(12, 4, 5, 6, 73, 3, 2));
        System.out.println("Largest num in list " + largest_num);

        int reverse_num = get_reverse_of_number(3478);
        System.out.println("Reverse number " + reverse_num);

        boolean is_pal = is_palindrome("abcba");
        System.out.println("is palindrome "+is_pal);

        int fact = get_factorial(4);
        System.out.println("Factorial value "+fact);

        int fib_value = get_fibonacci_series(8);
        System.out.println("Fib value "+fib_value);
        // 0,1,1,2,3,5,8,13,21,34 - here if input =6 return Fib value=13

        List<Integer> primes = get_prime_numbers(50);
        System.out.println("Prime number list "+primes);

        is_anagram("rescue","secure");

        List<String> reverse_words=reverse_each_word_in_sentence("I am an engineer");
        System.out.println("reverse words "+reverse_words);

        remove_duplicates_from_string("sdjfdsfjvfdjjdf");

        find_second_highest_element();

        find_common_elements_in_two_lists();

        swap_without_third_variable();

        extract_vowels_consonants();

        demonstrate_try_catch(10,2); //valid case
        demonstrate_try_catch(10,0); //throws exception

        int[] input = new int[]{1, 3, 4, 6, 7, 3, 1, 2, 3};
        remove_duplicates_from_array(input);

        SplitStringByNumbersAndReverse();

        shiftEvenOdd();

        stringImmutable();

        reverse_array();

        forEachExamples();

        countOccurenceOfCharacters();

        stringFunctions();

        swapStrings();

        replaceSecondO();

    }

    public static String get_rev_string(String input) {
        //Approach 1
        String reverse_string = "";
        for (int i = input.length() - 1; i >= 0; i--) {
            reverse_string = reverse_string + input.charAt(i);
        }
        System.out.println("Reverse String "+reverse_string);

        String rev_string1 = "";
        for (int i = 0; i < input.length(); i++) {
            rev_string1 = input.charAt(i)+ rev_string1 ;
        }
        System.out.println("Reverse String "+rev_string1);

        //return reverse_string;

        //Approach 2
        char[] chars = input.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
        for (char i : chars) {
            list.add(i);
        }
        Collections.reverse(list);
        String rev = "";
        for (Character i : list) {
            rev = rev + i;
        }
        //return rev;

        //Approach 3
        String rev_string = new StringBuilder(input).reverse().toString();
        return rev_string;
    }

    public static int get_largest_element_in_list(List<Integer> input) {
        System.out.println(" method name get_largest_element_in_list");
        //Approach 1
        int largest = input.get(0);
        for (Integer i : input) {
            if (i > largest)
                largest = i;
        }
        //return largest;

        //Approach 2
        //Collections.sort(input,Collections.reverseOrder());
        Collections.sort(input);
        int largest1 = input.get(input.size() - 1);
        int smallest1 = input.get(0);
        System.out.println(" Largest and Smallest "+largest1+" "+smallest1);
        //return largest1;

        //Approach 3
        input.sort(Integer::compareTo);
        int largest2 = input.get(input.size() - 1);
        int smallest2 = input.get(0);
        //return largest2;

        //Approach 4 using streams
        Optional<Integer> smallest3 = input.stream().min(Integer::compareTo);
        Optional<Integer> largest3 = input.stream().max(Integer::compareTo);
        System.out.println("Largest num is " + largest3);
        return 1;
    }

    public static int get_reverse_of_number(int input) {
        //Approach 1
        /*
        int reverse=0;
        while(input>0){
            reverse=(reverse*10)+(input%10);
            input /= 10;
        }
         */
        //return reverse;

        //Approach 2
        //String sb=new StringBuilder(String.valueOf(input)).reverse().toString();
        //return Integer.parseInt(sb);

        //Approach 3 if input is negative number
        int sign = (input < 0) ? -1 : 1;
        input = Math.abs(input);
        int reverse_num = 0;
        while (input != 0) {
            int digit = input % 10;
            reverse_num = reverse_num * 10 + digit;
            input /= 10;
        }
        return reverse_num * sign;
    }

    public static boolean is_palindrome(String input) {
        //Approach 1
        /*
        //Incase of string having non-alphanumeric characters like ';-
        String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        // -----------------
        boolean result = true;
        int left = 0;
        int right = input.length() - 1;
        while (left < right) {
            if (input.charAt(left) != input.charAt(right))
                result = false;
            left++;
            right--;
        }
        return result;
         */

        //Approach 2
        /*
        boolean result=false;
        List<String> a =Arrays.asList(input.split(""));
        String rev="";
        Collections.reverse(a);
        for(String i: a){
            rev += i;
        }
        System.out.println("Rev val "+rev);
        if (input.equalsIgnoreCase(rev))
            result=true;
        //return result;
        */
        //Approach 3
        boolean result=false;
        String rev = new StringBuilder(input).reverse().toString();
        if(input.equalsIgnoreCase(rev))
            result=true;
        return result;
    }


    public static int get_factorial(int input){
        //Approach 1
        /*
        int fact=1;
        while(input>0){
            fact *= input;
            fact --;
        }
        return fact;
        */

        //Approach 2
        /*
        int fact = 1;
        if (input ==0 || input ==1)
            return 1;
        else
            return input * get_factorial(input -1 );
         */

        //Approach 3 when dealing with large numbers, use big integers
        //
        BigInteger result= BigInteger.valueOf(1);
        for (int i=2;i<=input;i++){
            result=result.multiply(BigInteger.valueOf(i));
        }
        //return result;
        return 1;
    }

    public static int get_fibonacci_series(int input){
        //Approach 1
        /*
        if(input<=1)
            return 1;
        else
            return get_fibonacci_series(input-1)+get_fibonacci_series(input-2);
         */

        //Approach 2
        int a=0;
        int b=1;
        for(int i=1;i<=input;i++){
            int c=a+b;
            a=b;
            b=c;
        }
        return b;
    }

    public static List<Integer> get_prime_numbers(int input){
        //Approach 1
        /*
        List<Integer> primes = new ArrayList<Integer>();
        if(Arrays.asList(0,1).contains(input)){
            System.out.println("No Primes for given range");
            return Arrays.asList(0);
        }else{
            for(int i=2;i<input;i++){
                int count=0;
                for(j=1;j<=i;j++){
                    if(i%j==0)
                        count++;
                }
                if(count==2)
                    primes.add(i);
            }
        }
        return primes;
         */

        //Approach 2 using sqrt method
        List<Integer> primes = new ArrayList<Integer>();
        for(int i=2;i<=input;i++){
            int prime=0;
            if(i<=1){
                System.out.println("No primes for given range");
                return Arrays.asList(0);
            }else{
                for (int j=2;j<=Math.sqrt(i);j++){
                    if(i%j==0)
                        prime=1;
                }
                if(prime==0)
                    primes.add(i);
            }
        }
        return primes;
    }

    public static boolean is_anagram(String input1,String input2){
        //Approach 1
        Map<Character,Integer> count1=new HashMap<>();
        for(char i:input1.toCharArray()){
            if(!count1.keySet().contains(i))
                count1.put(i,1);
            else count1.put(i,count1.get(i)+1);
        }
        System.out.println("Count1 "+count1);

        Map<Character,Integer> count2=new HashMap<>();
        for(char i:input2.toCharArray()){
            if(!count2.keySet().contains(i))
                count2.put(i,1);
            else count2.put(i,count2.get(i)+1);
        }
        System.out.println("Count2 "+count2);

        //return true;

        //Approach 2
        //Remove spaces & convert to lowercase
        input1=input1.replaceAll("\\s+","").toLowerCase();
        input2=input2.replaceAll("\\s+","").toLowerCase();
        //Sort the characters
        char[] charArray1=input1.toCharArray();
        char[] charArray2=input2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        //Compare sorted Arrays
        return Arrays.equals(charArray1,charArray2);

    }

    public static List<String> reverse_each_word_in_sentence(String input){
        //Approach 1
        String[] words = input.split(" ");
        List<String> reverse_words=new ArrayList<>();
        for(String i:words){
            reverse_words.add(new StringBuilder(i).reverse().toString());
        }
        return reverse_words;
    }

    public static void demonstrate_try_catch(int numerator,int denominator){
        try {
            int result = numerator / denominator;
            System.out.println("Result :" + result);
        }catch (ArithmeticException e){
            System.out.println("Error: Cannot divide by zero");
        }finally {
            System.out.println("Execution of given method complete!");
        }
    }
    
    public static void remove_duplicates_from_array(int[] input){
        //Approach 1
        List<Integer> a = new ArrayList<>();
        for(int i:input){
            if(!a.contains(i))
                a.add(i);
        }
        System.out.println("Unique values in array "+a);

        //Approach 2
        List<Integer> input1 = Arrays.asList(1,34,6,3,4,2,4,1,6,445,3);
        Set<Integer> result = new HashSet<>(input1);
        System.out.println("Unique values using HashSet "+result);
    }

    public static void remove_duplicates_from_string(String input){
        //approach 1 using list
        char[] a1=input.toCharArray();
        List result1=new ArrayList<>();
        for(char i:a1){
            if(!result1.contains(i))
                result1.add(i);
            result1.add(i);
        }
        System.out.println("Unique chars in string "+result1);

        //approach 2 using set
        char[] a2=input.toCharArray();
        Set result2=new HashSet<>();
        for(char i:a2){
            result2.add(i);
        }
        System.out.println("Unique chars in string "+result2);

        List<Integer> i=Arrays.asList(2,3,4,2,4,2,4,2,3,2,4,8);
        Set result3=new HashSet<>(i);
        System.out.println(result3);
        String s="asdfijsnfjkjkdfj";
        //Not possible since HashSet expected a collection - List or Set
        //Set result4=new HashSet<>(i);
        //System.out.println(result4);
        //Approach 3 using Streams
        Set<Character> uniqueCharacters = input.chars().mapToObj(c->(char) c).collect(Collectors.toSet());
        System.out.println("Unique charcters in string "+uniqueCharacters);
    }

    public static void find_second_highest_element(){
        Map<String,Integer> input=new HashMap<>();
        input.put("sfkjshjhjdf",1);
        input.put("dsjkfvnfkj",2);
        input.put("dshfbdbhj",1);
        //Sort Map by Keys using TreeMap
        TreeMap<String,Integer> sortedByKeys=new TreeMap<>(input);
        System.out.println("Sorted By Keys "+sortedByKeys);

        //Sort map by keys using collections
        ArrayList<String> sortedKeys=new ArrayList<>(input.keySet());
        Collections.sort(sortedKeys);
        //Display the treemap which is by default sorted
        for(String x:sortedKeys){
            System.out.println("Key "+x+" :Value "+input.get(x));
        }
        //Second highest element
        System.out.println("Second highest element "+input.get(sortedKeys.get(1)));
        //Remove second highest element
        input.remove(sortedKeys.get(1));
        // ----------------LOGIC COMPLETED ABOVE -------------------

        //Sort map by values using Collections
        ArrayList<Integer> sortedValues = new ArrayList<Integer>(input.values());
        Collections.sort(sortedValues);

        Integer val = 1;
        for(Map.Entry<String,Integer> entry: input.entrySet()){
            if(entry.getValue()==val){
                System.out.println("The value of key : "+val+" is "+entry.getKey());
                break;
            }
        }
    }

    public static void find_common_elements_in_two_lists(){
        int[] a={1,2,3,4,56,5,2,3};
        int[] b={4,6,8,2,34,1,3,9,8,4,0};
        List<Integer> result=new ArrayList<>();
        for(int i:a){
            if(Arrays.stream(b).boxed().collect(Collectors.toList()).contains(i))
                result.add(i);
        }
        System.out.println("Common elements "+new HashSet<>(result));
    }

    public static void swap_without_third_variable(){
        //int a,b; initializing mul variables to same value
        //a=b = 2;
        int a=1,b=2;
        System.out.println("Before- a, b"+a+" "+b);
        a=a+b;
        b=a-b; //a+b-b
        a=a-b; //a+b-a
        System.out.println("After- a, b"+a+" "+b);
    }

    public static void extract_vowels_consonants(){
        String a = "kasdnhfjhkj3rfIjdnk35492";
        String vowelChars = "AEIOUaeiou";
        List<Character> vowels = new ArrayList<>();
        List<Character> consonants = new ArrayList<>();
        for(char i:a.toCharArray()){
            if(Character.isLetter(i)){
                if(vowelChars.indexOf(i)!= -1)
                    vowels.add(i);
                else
                    consonants.add(i);
            }
        }
        System.out.println("Vowels "+new HashSet<>(vowels));
        System.out.println("Consonants "+new HashSet<>(consonants));
    }

    public static void SplitStringByNumbersAndReverse() {
        // Test string
        String input = "Hello123World456Java789Programming";

        // Split the string based on numeric values
        String[] parts = input.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        // StringBuilder to build the result
        StringBuilder result = new StringBuilder();

        // Traverse the parts array
        for (String part : parts) {
            if (part.matches("\\D+")) {
                // Reverse non-numeric parts
                result.append(new StringBuilder(part).reverse());
            } else {
                // Keep numeric parts as they are
                result.append(part);
            }
        }

        // Output the result
        System.out.println("Original String: " + input);
        System.out.println("Processed String: " + result.toString());
    }

    public static void countOccurenceOfCharacters(){
        String input="sdfjdnkajsdf";
        Map<Character,Integer> count=new HashMap<>();
        for(char c: input.toCharArray()){
            if(!count.containsKey(c)){
                count.put(c,1);
            }else{
                count.put(c,count.get(c)+1);
            }
        }
        System.out.println(count);
    }

    public static void shiftEvenOdd(){
        //int[] a={1,2,3,4,5,6,7,8,9,10};
        int[] a={12,45,6,67,44,56,100,56,78,89,34,12,33};
        for(int j=0;j<a.length-1;j++) {
            for (int i = j; i < a.length - 1; i++) {
                if (a[i] % 2 != 0) {
                    int temp = a[i];
                    a[i] = a[i + 1];
                    a[i + 1] = temp;
                }
            }
        }
        for(int i:a) {
            System.out.println(i);
        }

    }

    public static void stringImmutable(){
        String str="Hello";
        System.out.println("Original String "+str); //new string str at memory location X

        //modifying the string str
        str=str+" world";
        System.out.println("modified string "+str); //modified string str at memory location Y

        StringBuilder sbd = new StringBuilder("Hello");
        sbd.append(" world"); //updates same string object instead of creating new one.
        System.out.println("Updated string builder string "+sbd);
    }

    public static void reverse_array(){
        Integer[] arr={2,34,5,3,5};
        List<Integer> a =Arrays.asList(arr);
        //Ascending
        Collections.sort(a);
        //Descending
        Collections.sort(a,Collections.reverseOrder());
        int[] b={1323,44,53,54,5,23};
        Arrays.sort(b);
        // THIS STATEMENT WON'T WORK -- Arrays.sort(b,Collections.reverseOrder());
        // THIS WORKS
        b = Arrays.stream(b)
                .boxed()  // Convert int[] to Integer[]
                .sorted(Comparator.reverseOrder())  // Sort in reverse order
                .mapToInt(Integer::intValue)  // Convert back to int[]
                .toArray();
    }

    public static void forEachExamples(){
        List<Integer> test=Arrays.asList(1,2,4,5,332,1,2,3);
        List<Integer> squares =test.stream().map(n->n*n).collect(Collectors.toList());
        System.out.println("Squares "+squares);

        List<Integer> even = test.stream().filter(n->n%2==0).collect(Collectors.toList());
        System.out.println("even "+even);

        int max = test.stream().max(Integer::compareTo).get();
        int sum = test.stream().mapToInt(Integer::intValue).sum();
        System.out.println("max -- sum  "+max+" "+sum);
    }

    public static String stringFunctions(){
        String input="asifsjidjjinini";
        String output=input.replaceAll("ini","abc00");
        System.out.println(output);
        return input;
    }

    public static void swapStrings(){
        String string1="Hello11";
        String string2="World26367";
        string1=string1+string2;
        string2=string1.substring(0,string1.length()-string2.length());
        string1=string1.substring(string2.length());

        System.out.println("String1 "+string1);
        System.out.println("String2 "+string2);
    }

    public static void replaceSecondO(){
        String input="tomorrow";
        String output="";
        int count=0;
        for(char i:input.toCharArray()){
            if (i=='o'){
                count++;
                if (count==2){
                    output=output+"$";
                }
                else{
                    output=output+i;
                }
            }else{
                output=output+i;
            }
        }
        System.out.println("Output string "+output);

    }
}