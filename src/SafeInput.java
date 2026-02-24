import java.util.Scanner;

public class SafeInput {
    public static String getNonZeroLenString(Scanner pipe, String prompt)
    {
        String retString = "";
        do
        {
            System.out.print("\n" +prompt + ": ");
            retString = pipe.nextLine();

        }while(retString.isEmpty());

        return retString;
    }

    public static int getInt(Scanner pipe, String prompt){
        int num = 0;
        boolean r = true;

        do
        {
            System.out.print("\n" +prompt + ": ");

            if(pipe.hasNextInt()){
                num = pipe.nextInt();
                r = false;
            }else{
                pipe.nextLine();
            }
        }while(r);

        return num;
    }

    public static double getDouble(Scanner pipe, String prompt){
        double num = 0;
        boolean r = true;

        do
        {
            System.out.print("\n" +prompt + ": ");

            if(pipe.hasNextDouble()){
                num = pipe.nextDouble();
                r = false;
            }else{
                pipe.nextLine();
            }
        }while(r);

        return num;
    }

    public static int getRangedInt(Scanner pipe, String prompt,int min,int max){
        int num = 0;
        boolean r = true;

        do
        {
            System.out.print("\n" +prompt + ": ");

            if(pipe.hasNextInt()){
                num = pipe.nextInt();

                if(num>=min && num<=max){
                    r = false;
                }

            }else{
                pipe.nextLine();
            }
        }while(r);

        return num;
    }

    public static double getRangedDouble(Scanner pipe, String prompt,double min,double max){
        double num = 0;
        boolean r = true;

        do
        {
            System.out.print("\n" +prompt + ": ");

            if(pipe.hasNextDouble()){
                num = pipe.nextDouble();

                if(num>=min && num<=max){
                    r = false;
                }

            }else{
                pipe.nextLine();
            }
        }while(r);

        return num;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt){
        boolean val = false;
        String s = "";
        boolean r = true;

        do
        {
            System.out.print("\n" +prompt + ": ");

            s = pipe.nextLine();

            if(s.equalsIgnoreCase("Y")){
                val = true;
                r = false;
            }else if(s.equalsIgnoreCase("N")){
                val = false;
                r = false;
            }

        }while(r);

        return val;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx){
        boolean r = true;
        String s = "";

        do{
            System.out.print("\n" +prompt + ": ");

            if(pipe.hasNextLine()){
                s = pipe.nextLine();

                if(s.matches(regEx)){
                    r = false;
                }
            }else{
                pipe.nextLine();
            }
        }while(r);

        return s;
    }

    public static void prettyHeader(String msg){
        //Top of header

        for(int i = 0; i <= 59; i++){
            System.out.print("*");
        }
        System.out.print("\n");

        int padding = 54 - msg.length();
        int left = padding / 2;
        int right = padding - left;

        System.out.print("***");

        for(int i = 0; i < left; i++){
            System.out.print(" ");
        }

        System.out.print(msg);

        for(int i = 0; i < right; i++){
            System.out.print(" ");
        }

        System.out.print("***\n");

        for(int i = 0; i <= 59; i++){
            System.out.print("*");
        }

    }
}