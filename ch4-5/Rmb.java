import java.io.*;
public class Rmb{
    private static char[] chineseNum = {'Áã','Ò¼','·¡','Èþ','ËÁ','Îé','Â½','Æâ','°Æ','¾Á',};
    private static char[] thousandUnit={' ','Ê°','°Û','Çª'};
    private static char[] pointUnit={'½Ç','·Ö'};
    private static char[] segUnit = {'Ôª','Íò','ÒÚ'};
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        double num;
        System.out.println("Input a number. Only 2 bits after point is considered.");
        try{
            num = Double.parseDouble(br.readLine());
        }catch(IOException e){
            System.out.println("IOException.");
            return;
        }
        String s=money2String(num);
        System.out.println(s);
    }
    /**
      Translate a number of money (less than 1e13) to Chinese.
      @param num: the number of money (less than 1e13).
    
    */
    private static String money2String(double num){
        num+=0.0001;
        String s="";
        long high = (long)num;
        double low  = num-high;
        if(high==0) s=""+chineseNum[0]+segUnit[0];
        else{
                for(int i=0;i<=2&&high>0;i++){
                    if(high%10000==0){
                        s = chineseNum[0]+s;
                    }else{
                        String seg = thousand2String((int)(high%10000));
                        s = seg + segUnit[i]+s;
                    }

                    high/=10000;
                }
                // Delete one of double ¡°Áã¡± to avoid double ¡°Áã¡±;
                for(int i=0;i<s.length()-1;){
                    if(s.charAt(i)==s.charAt(i+1)&&s.charAt(i)==chineseNum[0])
                        s=s.substring(0,i+1)+s.substring(i+2,s.length());
                    else i++;
                }
                if(s.charAt(0)==chineseNum[0]&&s.length()>2)s=s.substring(1,s.length());
                if(s.charAt(s.length()-2)==chineseNum[0]&&s.length()>2)
                    s=s.substring(0,s.length()-2)+s.charAt(s.length()-1);
        }

        String lowString = "";
        if(low<0.01){
        }else{
            for(int i=0;i<=1;i++){
                low=(low - (int)low)*10;
                int bit = (int)low;
                System.out.println(low);
                lowString+=""+chineseNum[bit]+(bit==0?"":pointUnit[i]);
            }
            while(lowString.charAt(lowString.length()-1)==chineseNum[0])
                lowString = lowString.substring(0,lowString.length()-1);
        }

        return s+lowString;
    }
    /**
     Translate a thousand number to Chinese.
     @param n: the number (less than 1e5).
    */
    private static String thousand2String(int n){
        if(n==0)return ""+chineseNum[0];
        String s = "";
        s+=(n%10)==0?"":chineseNum[n%10];
        System.out.println(s);
        n/=10;
        for(int i=1;i<=3;i++){
            if(i==1&&n%10==1){
                s=""+thousandUnit[i]+s;
            }else{
                s=""+chineseNum[n%10]+((n%10)==0?"":thousandUnit[i])+s;
            }
            n/=10;
            System.out.println(s);
        }
        while(s.length()>0&&s.charAt(s.length()-1)==chineseNum[0])
            s=s.substring(0,s.length()-1);
        return s;
    }
}