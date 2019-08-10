import java.io.*;

public class SubString{
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );
        String s;int begin,end;
        try{
            s = br.readLine();
            begin = Integer.parseInt(br.readLine());
            end = Integer.parseInt(br.readLine());
        }catch(IOException e){
            System.out.println("IOException.");
            return;
        }
        String ans;
        try{
            ans = subStringByByte(s,begin,end);
        }catch(UnsupportedEncodingException e){
            System.out.println("UnsupportedEncodingException.");
            return;
        }
        System.out.println(ans);

        
    }
    private static boolean isChineseChar(char c) throws UnsupportedEncodingException{
        return String.valueOf(c).getBytes("UTF-8").length>1;
    }
    private static String subStringByByte(String s,int begin,int end)throws UnsupportedEncodingException{
        int sbegin = 0;//begin index of s.
        int bytesnum = 0;//num of bytes that is already counted.
        for(;bytesnum<begin&&sbegin<s.length();sbegin++){
            if(isChineseChar(s.charAt(sbegin))){
                bytesnum+=2;
            }else{
                bytesnum+=1;
            }
        }
        int idx = sbegin;//end index of s.
        for(;bytesnum<end&&idx<s.length();idx++){
            if(isChineseChar(s.charAt(idx))){
                bytesnum+=2;
            }else{
                bytesnum++;
            }
        }
        // if the last accepted one is a ChineseChar, the bytes will be over the limit end, so we cut 1;
        if(bytesnum>end){
            idx--;
        }
        return s.substring(sbegin,idx);
    }
    
}