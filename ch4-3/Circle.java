import java.lang.Math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
public class Circle{
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        double r;
        try{
            r = Double.parseDouble(br.readLine());
        }
        catch(IOException e){
            System.out.println("IOException.");
            return;
        }
        int o = (int)r+2-1;//0index pos of center;
        char[][] mp = new char[2*o][2*o];
        for(var row : mp){
            Arrays.fill(row,' ');
        }
        for(int i=0;i<2*o;i++){
            for(int j=0;j<2*o;j++){
                if(
                    Math.abs(Math.sqrt(Math.pow(i-o,2)+Math.pow(j-o,2))-r)<0.5
                ){
                    mp[i][j]='*';
                }
            }
        }
        for(int i=0;i<2*o;i++){
            System.out.println(mp[i]);
        }
    }
}