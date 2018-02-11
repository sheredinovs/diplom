/**
 * Created by kadyr on 04.02.2018.
 */
public class Chipher {
    public static final int THRESHOLD = 25;

    public static final int FIRST_COEFF = 4;
    public static final int SECOND_COEFF = 5;

    public byte compute(double[][] mass){
        if(mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF] > 0){
            System.out.println("извлечение для 0  " + mass[FIRST_COEFF][SECOND_COEFF]);
            return 1;
        }

        else if(mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF] < 0){
            System.out.println("извлечение для 1  " + mass[FIRST_COEFF][SECOND_COEFF]);
            return 0;
        }
        return -1;
    }

    public double[][] chipher(double[][] mass, byte l){
        if(l == 1){
            if(mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF] < (0 - THRESHOLD)){
                return mass;
            }
            else {
                System.out.println("погружение  для 1 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);



                mass[FIRST_COEFF][SECOND_COEFF] = (-1 - THRESHOLD) - mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF];



                System.out.println("погружение  для 1 " + mass[FIRST_COEFF][SECOND_COEFF] + "   ");
            }
        }
        else if(l == 0){
            if(mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF] > THRESHOLD){
                return mass;
            }
            else {
                System.out.println("погружение  для 0 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);




                mass[FIRST_COEFF][SECOND_COEFF] = (THRESHOLD + 1) + (mass[FIRST_COEFF][SECOND_COEFF] - mass[SECOND_COEFF][FIRST_COEFF]);




                System.out.println("погружение  для 0 " + mass[FIRST_COEFF][SECOND_COEFF] + "   ");
            }
        }
        return mass;
    }
}
