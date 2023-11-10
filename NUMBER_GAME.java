import java.util.*;

public class NUMBER_GAME {
    public static void main(String [] args){
        int maxScore = 100;
        int count = 0;
        boolean guessed = true;
        int  guessedNumber=0, randomguessedNumber=0;
        int invalidAttempt = 0;
        Random random = new Random();
        Scanner inp = new Scanner(System.in);
        System.out.println("Enter Your Name !!");
        String userName = inp.nextLine();
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("\t\t\tNUMBER GAME\t\t");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("\nThis is the Fun Game based on Numbers\n\nSome Basic Rules:\n\t*1.Guees the Number Whatever If you Think !That may be Same as Generated Number \n\t*2.That Number should be within (1 to 100) \n\t*3.Finally score will be displayed based on the number of attempts taken or rounds won\n");
        randomguessedNumber = random.nextInt(100)+1;
        while(guessed){
            
            
            //System.out.println(randomguessedNumber);
            System.out.println("Enter the number: ");
            guessedNumber = inp.nextInt();

            if(guessedNumber==randomguessedNumber){
                System.out.println("Great! Your Guess is correct");
                guessed=false;
            }
            else if(guessedNumber<randomguessedNumber){
                System.out.println("Your's Guessing fewer than is generating number\nBetter luck !");
                System.out.println("Enter the numbe"+guessedNumber+"> ?");
                invalidAttempt++; 
            }
            else{
                System.out.println("Your's Guessing More than is generating number\nBetter luck !");
                System.out.println("Enter the number : "+guessedNumber+"< ?");
                 invalidAttempt++; 
            }     
             
             count++;
        }
        float scorePercentage = ((maxScore - (float)invalidAttempt) / maxScore) * 100;
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("\t\t\t "+userName+" 's Score\t\t");
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("No.of Attempt's "+count);
        // System.out.println("No.of Invalid Attempt's "+invalidAttempt);
        // float scores = ((count / (float) score) * 100);
        System.out.println("Your Scores is "+scorePercentage+" %");
    }
}