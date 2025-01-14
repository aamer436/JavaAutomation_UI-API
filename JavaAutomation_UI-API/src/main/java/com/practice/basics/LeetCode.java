package com.practice.basics;

import java.nio.file.LinkPermission;
import java.util.*;

public class LeetCode {
    public static void main(String[] args){
        System.out.println("two sum output ");
        int[] result=twoSum();
        Arrays.toString(result);
        List<Integer> result1=new ArrayList<>();
        for(int i:result){
            System.out.println(i);
            result1.add(i);
        }
        result1.stream().forEach(System.out::println);
        //result1.stream().forEach(e->System.out.println(e));

        System.out.println("Palindrome output "+is_palindrome());

        System.out.println("Roman to Integer "+roman_to_integer());

        System.out.println("Single number "+singleNumber());

        System.out.println("Intersection of two arrays "+intersectionOfTwoArrays());

        System.out.println("FizzBuzz "+fizzBuzz());

        System.out.println("Upper to Lower "+toLowerCase());

        System.out.println("House Robber output "+house_robber());

        System.out.println("Bubble Sort ");
        int[] bubbleSortResult=bubbleSort();
        for(int i:bubbleSortResult){
            System.out.println(i);
        }


    }
    public static int[] twoSum(){
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i< nums.length;i++){
            int compliment=target - nums[i];
            if(map.containsKey(compliment)){
                return new int[]{map.get(compliment),i};
            }
            map.put(nums[i],i);
        }
        return new int[]{};
    }

    public static boolean is_palindrome() {
        //Approach 1
        String input="ADCDA";
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
    }

    public static int roman_to_integer(){
        String roman="MMMDCCXXIV";
        Map<Character,Integer> roman_map = new HashMap<>();
        roman_map.put('I',1);
        roman_map.put('V',5);
        roman_map.put('X',10);
        roman_map.put('L',50);
        roman_map.put('C',100);
        roman_map.put('D',500);
        roman_map.put('M',1000);
        int total=0;
        for(int i=0;i<roman.length();i++){
            int current=roman_map.get(roman.charAt(i));
            if(i+1<roman.length() && current < roman_map.get(roman.charAt(i+1))){
                total -=current;
            }else {
                total += current;
            }
        }
        return total;

    }

    public static int singleNumber(){
        int[] input={4,1,2,1,2};
        int result=0;
        for(int i:input){
            result ^=i;
        }
        return result;
    }

    public static Set<Integer> intersectionOfTwoArrays(){
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        Set<Integer> result=new HashSet<>();
        for(int i:nums1){
            for(int j:nums2){
                if(i==j){
                    result.add(i);
                }
            }
        }
        //return result;
        Set<Integer> result1=new HashSet<>();
        Set<Integer> setNum1=new HashSet<>();
        for(int i:nums1){
            setNum1.add(i);
        }
        Set<Integer> setNum2=new HashSet<>();
        for(int i:nums2){
            setNum2.add(i);
        }
        for(Integer i:setNum1){
            if(setNum2.contains(i)){
                result1.add(i);
            }
        }
        return result1;

    }

    public static List<String> fizzBuzz(){
        int n=18;
        List<String> result=new ArrayList<>();
        for(int i=1;i<n;i++){
            if(i%3!=0 && i%5!=0) {
                result.add(String.valueOf(i));
            } else if (i%3==0 && i%5==0) {
                result.add("FizzBuzz");
            } else if (i%3==0) {
                result.add("Fizz");
            } else if (i%5==0) {
                result.add("Buzz");
            }
        }
        return result;
    }

    public static String toLowerCase(){
        String input="Hello World!!";
        String output="";
        for(char i:input.toCharArray()){
            int ascii=(int)i;
            if(ascii >=65 && ascii <=90){
                output=output+(char)(ascii+32);
            }else{
                output=output+(char)ascii;
            }
        }
        return output;
    }

    public static int house_robber(){
        //int[] input={2, 7, 9, 3, 1};
        int[] input={1, 7, 6, 4, 3, 2, 2};
        if(input==null || input.length==0){
            return 0;
        }
        if(input.length==1){
            return input[0];
        }

        int prev1=input[0];
        int prev2=Math.max(input[0],input[1]);

        for(int i=2;i<input.length;i++){
            int current = Math.max(prev2,prev1+input[i]);
            prev1=prev2;
            prev2=current;
        }
        return prev2;
    }

    public static int[] bubbleSort(){
        //int[] input={2,4,4,3,2,1,2,3,4,2,12,21,1};
        int[] input={5, 3, 8, 4, 2};
        int n=input.length;

        for(int i=0;i<n-1;i++){
            for(int j=0;j<n-i-1;j++){
                if(input[j]>input[j+1]){
                    int temp=input[j];
                    input[j]=input[j+1];
                    input[j+1]=temp;
                }
            }
        }
        return input;
    }
}
