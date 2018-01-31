//THIS WILL ABSOLUTELY CHANGE BASED ON THE INPUT FROM THE REGISTRY GROUP; FOR RIGHT NOW, WE DON'T KNOW WHAT THEY ARE GOING TO GIVE US
package com.company;

import java.util.ArrayList; //necessary import for ArrayList

public class TwosComplement {
    private static ArrayList<Object> allPossibleTypes = new ArrayList<>(); //ArrayList that holds all of the different representations of the two's complement
    private static String strBComplemented = ""; //the String that holds the string when we perform one's complement
    private static String finStrBComplemented = ""; //the String that holds the string when we add 1 (the final two's complement number)
    private static int intBComplemented; //final two's complement as an int data type
    private static byte byteBComplemented; //final two's complement as a byte (or as a decimal)
    private static String BOriginal; //the original B that is not manipulated in any way
    private static LogicGate notGate = new LogicGate(0); //NOT gate that we use to perform one's complement on all binary digits
    private static LogicGate andGate = new LogicGate(1); //XOR gate that we use to calculate the "sum" part of two's complement
    private static LogicGate xorGate = new LogicGate(3); //AND gate that we use to calculate the "carry" part of two's complement
    private static int digit; //the individual digit that we look at within the binary number
    private static boolean input; //the first input we send to XOR and AND, and also the only input we send to NOT; boolean value determined by the value of the "digit" integer
    private static boolean input2; //the second input we send to XOR and AND; boolean value determined by the value of the "carry" integer
    private static boolean output; //the variable that holds the output of any three of our gates based on the input values from above
    private static int sum; //the output of the XOR gate
    private static int carry = 1; //the output of the AND gate; default set to 1 since we always have to set the "carry" equal to perform two's complement with a half adder only

    public static void main(String[] args) {
        String B = "00001101"; //placeholder for the Registry object that the Registry class will send to this method
        System.out.println("Original: " + B); //shows the original value of the binary object sent to this method
        ArrayList<Object> C = TwosComplement.twosComplement(B); //holds all of the data types we can use to represent the two's complement of the original binary number
        System.out.println("Complimented (String): " + C.get(0) + "\nComplimented (int): " + C.get(1) + "\nComplimented Decimal Value: " + C.get(2)); //showing all of the data types we can use to represent the two's complement
    }

    public static ArrayList<Object> twosComplement(String B) {
        BOriginal = B; //setting local variable equal to the Registry the Registry group gives us

        for(int i = 0; i < BOriginal.length(); i++) { //loops through individual digits within the orignal binary number given
            digit = Integer.parseInt(BOriginal.substring(i,i + 1)); //making the digit equal to an int rather than a String
            if(digit == 1) //if the digit is 1
                input = true; //then our input to the NOT gate is true
            else //if the digit is 0
                input = false; //then our input to the NOT gate is false

            notGate.setInput1(input); //giving our input to the NOT gate so it can perform logic to give us an output
            output = notGate.getOutput(); //setting local output variable equal to the output of the NOT gate

            if(output) //if the output of the NOT gate is true
                digit = 1; //then we switch the digit in the original from 0 to 1 in the one's complement binary number
            else //if the output of the NOT gate is false
                digit = 0; //then we switch the digit in the original from 1 to 0 in the one's complement binary number

            strBComplemented += Integer.toString(digit); //creating a String that holds the complete one's complement


        }

        for(int i = strBComplemented.length() - 1; i >= 0; i--) { //looping through every digit of the one's complement binary number, starting with the least significant digit
            digit = Integer.parseInt(strBComplemented.substring(i,i + 1)); //making the digit equal to an int rather than a String
            if(digit == 1) //if the digit is 1
                input = true; //then the first input of the XOR and AND gates is true
            else //if the digit is 0
                input = false; //then the first input of the XOR and AND gates is false

            if(carry == 1) //if the carry determined by the XOR gate is equivalent to 1
                input2 = true; //then the second input is true
            else ////if the carry determined by the XOR gate is equivalent to 0
                input2 = false; //then the second input is false

            xorGate.setInput1(input); //giving first input for XOR gate
            xorGate.setInput2(input2); //giving second input for XOR gate
            andGate.setInput1(input); //giving first input for AND gate
            andGate.setInput2(input2); //giving second input for AND gate

            if(xorGate.getOutput()) //if the output of the XOR gate based on the two inputs is true
                sum = 1; //then the sum is 1
            else ////if the output of the XOR gate based on the two inputs is false
                sum = 0; //then the sum is 0

            if(andGate.getOutput()) //if the output of the AND gate based on the two inputs is true
                carry = 1; //then the carry is 1
            else //if the output of the AND gate based on the two inputs is false
                carry = 0; //then the carry is 0

            finStrBComplemented = Integer.toString(sum) + finStrBComplemented; //creates new String that represents the two's complement of the original binary number
        }
        intBComplemented = Integer.parseInt(finStrBComplemented); //converts the two's complement String into an int
        byteBComplemented = (byte)Integer.parseInt(finStrBComplemented,2); //converts the two's complement String into a byte; shows actual decimal representation of the two's complement

        allPossibleTypes.add(finStrBComplemented); //adds String representation to ArrayList for organization
        allPossibleTypes.add(intBComplemented); //adds int representation to ArrayList for organization
        allPossibleTypes.add(byteBComplemented); //adds byte representation to ArrayList for organization
        return allPossibleTypes; //returns the ArrayList so we display all three representations
        //instead of returning an ArrayList, we will return a Register Object. However, at the moment what makes up a Register object is unknown so I cannot do that yet
    }
}
