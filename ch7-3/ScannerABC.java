import java.util.Scanner;
import java.util.regex.*;
import java.util.Arrays;

public class ScannerABC{
    public static void main(String[] args){
        String inputStr = "A1B2C3D4E5F6G7H8";
        int[] numberArray = new int[8];
        String[] strArray = new String[8];
        Pattern wPattern = Pattern.compile("[a-zA-Z]+");
        Pattern dPattern = Pattern.compile("\\d+");
        Matcher wMatcher = wPattern.matcher(inputStr);
        Matcher dMatcher = dPattern.matcher(inputStr);
        for(int i=0;i<8;i++){
            if(wMatcher.find())strArray[i]=wMatcher.group();
            if(dMatcher.find())numberArray[i]=Integer.parseInt(dMatcher.group());
        }
        System.out.println(Arrays.toString(strArray));
        System.out.println(Arrays.toString(numberArray));
        
    }
}