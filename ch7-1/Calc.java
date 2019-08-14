import java.util.Scanner;
public class Calc{
    
    public static void main(String[] args){
        int[] a = new int[10];
        Scanner sc = new Scanner(System.in);
        System.out.println("Please input 10 Integer:");
        double average=0,maxValue=0,minValue=0;
        for(int i=0;i<10;i++){
            a[i]=sc.nextInt();
            average+=a[i];
            if(i==0){
                maxValue=a[i];
                minValue=a[i];
            }else{
                maxValue = Math.max(maxValue,a[i]);
                minValue = Math.min(minValue,a[i]);
            }
        }
        average/=10;
        System.out.println("average="+average+",maxValue="+maxValue+",minValue="+minValue);
        
    }
}