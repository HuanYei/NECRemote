package com.zwt.necremote.utli;

public class  NECutli {
    static String keyCode;

    public static String getKeyCode() {
        return keyCode;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel(String IRCode) {

        String IRCodeBinary = new StringBuilder(eraj(IRCode)).reverse().toString();
        int pattern[]=new int[IRCodeBinary.length()*2+4];
        pattern[0] = 9000;
        pattern[1] = 4500;
        int i = 2;
        for (char ir:IRCodeBinary.toCharArray()){
            if (ir == '1') {
                pattern[i] = 560;
                pattern[i+1] = 1680;
            }else {
                pattern[i] = 560;
                pattern[i+1] = 560;
            }
            i += 2;
        }
        pattern[i] = 560;
        pattern[i+1] = 20000;

        return pattern;
    }
    public static String eraj(String Hexstring) {
        String form = "%4s";
        StringBuilder resoultBinaryString = new StringBuilder();
        for (char ch : Hexstring.toCharArray()){
            resoultBinaryString.append(String.format(form, Integer.toBinaryString(Integer.parseInt(String.valueOf(ch), 16))).replace(' ', '0'));
        }
        return resoultBinaryString.toString();
    }

    public static  int[] toRKTCODE(String IRCODEKEY){
        keyCode = IRCODEKEY;
        String code=IRCODEKEY;
        code=codeFF(code);
        String Headcode="20df";
//        System.out.println(Headcode);
        String a=Headcode.substring(0,2);
        String b=Headcode.substring(2,4);
        code=code+b+a;
        IRCODEKEY=code;
//        System.out.println(IRCODEKEY);
        return IRlevel(IRCODEKEY);
    }

    public static  int[] toPublicRTKCODE(String IRCODEKEY,String USERCODE){
        keyCode = IRCODEKEY;
        String code=IRCODEKEY;
        code=codeFF(code);
        String Headcode=codeFF(USERCODE);
        String a=Headcode.substring(0,2);
        String b=Headcode.substring(2,4);
        code=code+b+a;
        IRCODEKEY=code;
        return IRlevel(IRCODEKEY);
    }

    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel_KK(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";

        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }

        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(3000);list.add(3000);
        for (int i =0; i <=ajer.length()-1; i++) {
            if (ajer.charAt(i)=='1') {
                list.add(500);list.add(2500);
            }else {
                list.add(500);list.add(1500);
            }
        }
        list.add(500);list.add(40000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }

    // 根据十六进制遥控码值转换成遥控发射数组
    public static int[] IRlevel_SAM(String IRCODE) {
        String ma=IRCODE;
        char aj[]=ma.toCharArray();
        String ajer="";
        for (int i = 0; i < aj.length; i++) {
            ajer+=eraj(aj[i]+"");
        }
        System.out.println(ajer);
        List<Integer> list=new ArrayList<Integer>();
        list.add(4500);list.add(4500);
        for (int i =ajer.length()-1; i >=0; i--) {
            if (ajer.charAt(i)=='1') {
                list.add(560);list.add(1690);
            }else {
                list.add(560);list.add(560);
            }
        }
        list.add(560);list.add(20000);
        int pattern[]=new int[list.size()];
        for (int i = 0; i < pattern.length; i++) {
            pattern[i]=list.get(i);
        }
        System.out.println(list.toString());
        return pattern;
    }


    private static String codeFF(String code) {
//        System.out.println(code);
        Integer a=Integer.parseInt(code,16);
        Integer c=255-a;
        return Integer.toHexString(c)+code;
    }
}
