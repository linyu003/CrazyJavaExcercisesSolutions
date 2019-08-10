import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
public class Trangle{
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        int n;
        try{
            n = Integer.parseInt(br.readLine());
        }catch(IOException e){
            System.out.println("IOException.");
            //e.printStack();
            return;
        }
        
        char[] str = new char[2*n-1];
        for(int i=1;i<=n;i++){
            Arrays.fill(str,' ');
            for(int j=n-i;j<n-i+2*i-1;j++){
                str[j]='*';
            }
            System.out.println(str);
        }
    }
}