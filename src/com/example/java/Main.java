package com.example.java;

public class Main {



    public static void main(String[] args) {

    }

    // 1. palindrome
    public static boolean isPalindrome(String text){
        //ignore letter case and whitespaces
        String newText = text.toLowerCase().replaceAll("\\s","");
        //compare letters from the beginning and from the end till it reaches middle
        for (int i = 0; i < newText.length()/2; i++) {
            if (newText.charAt(i) != newText.charAt(text.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 2. coins
    public static int minSplit(int amount){
        int coinsList [] = {50, 20, 10, 5, 1};
        int numberOfCoins = 0;
        int amountLeft = amount;
        for (int denomination: coinsList) {
            numberOfCoins = numberOfCoins + amountLeft/denomination;
            amountLeft = amountLeft%denomination;
            if (amountLeft == 0) {
                break;
            }
        }
        return numberOfCoins;
    }

    // 3. minimum positive number missing
    public static int notContains(int[] array){
        //create an array of booleans to mark by index-1 if the number is present in the original array
        //the array is initialized with 'false' values
        //the array length is the length of original array,
        //since the possible answer is between 1 and the length of original array +1
        boolean[] check = new boolean[array.length];
        //mark the corresponding index in the new array as 'true' for all numbers in the original array
        for (int num : array) {
            if (num > 0 && num <= array.length) {
                check[num-1] = true;
            }
        }
        //if there is a 'false' in the new array, the corresponding index is our answer
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                return i+1;
            }
        }
        //if there was no 'false' in the new array, the original array contained every number from 1 to its length
        //then the answer is the length of the original array + 1
        return array.length+1;
    }

    // 4. parenthesis
    public static boolean isProperly(String sequence){
        //initialize counter
        int counter = 0;
        for (int i = 0; i < sequence.length(); i++) {
            //for every opening parenthesis the counter goes up
            if (sequence.charAt(i) == '(') {
                counter++;
            }
            //for every closing parenthesis the counter goes down
            if (sequence.charAt(i) == ')') {
                counter--;
            }
            //if the counter goes below 0, that means the closing parenthesis precedes the opening one
            if (counter < 0) {
                return false;
            }
        }
        //if the counter is 0 again that means each opened parenthesis was closed, else something went wrong
        if (counter == 0) {
            return true;
        } else return false;
    }

    // 5. stairs
    public static int countVariants(int stearsCount){
        //the function works recursively
        //if the number of stairs is not positive there are no possible variants
        if (stearsCount <= 0) {
            return 0;
        } else if (stearsCount == 1 ) {  //if the number of stairs is 1, there is only one possible variant
            return 1;
        } else if (stearsCount == 2) { //if the number of stairs is 2, there are two possible variants
            return 2;
        }
        //if the number of stairs is greater than 2
        //sum the possibilities of after making 1 step and after making 2 steps
        return countVariants(stearsCount - 1) + countVariants(stearsCount - 2);
    }
}
