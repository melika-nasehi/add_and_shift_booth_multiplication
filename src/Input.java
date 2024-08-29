import java.io.BufferedWriter;
import java.io.File ;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Input {
    public void getInput (){
        File input = new File("in.txt");
        Scanner myScn = new Scanner(System.in);
        String RESET = "\u001B[0m";
        String RED = "\u001B[31m";

        try {
            FileWriter myWriter = new FileWriter(input , true) ;
            BufferedWriter bf = new BufferedWriter(myWriter);
            String n_as_String  ;
            System.out.print( "enter the count of inputs : " );
            n_as_String = myScn.next() ;

            int n = Integer.parseInt(n_as_String);
            bf.write(n_as_String);
            bf.newLine();

            for (int i = n ; i > 0 ; i--) {
                int m, b, s, A, B ;
                //m
                System.out.print("enter 0(add & shift) or 1(boothe): " );
                m = myScn.nextInt();
                if (m!= 1 && m!= 0){
                    System.out.print(RED + "m should be 0 or 1" + RESET);
                    break ;
                }
                String m_as_string = Integer.toString(m);

                //b
                System.out.print("enter number of bits : ");
                b = myScn.nextInt();
                if (b>32){
                    System.out.println(RED + "max for b is 32" + RESET);
                    break;
                }
                String b_as_string = Integer.toString(b);

                //s
                System.out.print("enter 0(unsigned) or 1(signed) : ");
                s = myScn.nextInt();
                if (s!= 1 && s!= 0){
                    System.out.print(RED+ "s should be 0 or 1" +RESET);
                    break ;
                }
                String s_as_string = Integer.toString(s);

                //A
                System.out.print("enter A in decimal: ");
                A = myScn.nextInt();
                if (A < 0 && s == 0){
                    System.out.println(RED + "you said unsigned!" + RESET);
                    break;
                }
                if (Math.abs(A) >= Math.pow(2,b)){
                    System.out.println(RED + "OUT OF RANGE!" + RESET);
                    System.out.print(RED + b + " bits cant show number "+A + RESET);
                    break;
                }
                String A_as_string = Integer.toString(A);

                //B
                System.out.print("enter B in decimal: ");
                B = myScn.nextInt();
                if (B < 0 && s == 0){
                    System.out.println(RED + "you said unsigned!" + RESET);
                    break;
                }
                if (Math.abs(B) >= Math.pow(2,b)){
                    System.out.println(RED + "OUT OF RANGE!" + RESET);
                    System.out.print(RED + b + " bits cant show number "+B + RESET);
                    break;
                }
                String B_as_string = Integer.toString(B);

                //write in file
                bf.write(m_as_string);
                bf.newLine();
                bf.write(b_as_string);
                bf.newLine();
                bf.write(s_as_string);
                bf.newLine();
                bf.write(A_as_string);
                bf.newLine();
                bf.write(B_as_string);
                bf.newLine();
            }



            bf.close();

        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }



}
