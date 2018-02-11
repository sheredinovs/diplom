/**
 * Created by kadyr on 04.02.2018.
 */
public class Chipher {
    public static final int THRESHOLD = 25;

    public static final int FIRST_COEFF = 3;
    public static final int SECOND_COEFF = 2;

    public byte compute(double[][] mass){
        if(Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF]) < 0){
            System.out.println("извлечение для 1  " + (Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF])));
            return 1;
        }

        else if(Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF]) > 0){
            System.out.println("извлечение для 0  " + (Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF])));
            return 0;
        }
        return -1;
    }

    public double[][] chipher(double[][] mass, byte l){
        if(l == 1){
            if(Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF]) < (- THRESHOLD)){
                System.out.println("погружение  для 1 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);
                return mass;
            }
            else {
                System.out.println("погружение  для 1 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);



                mass[SECOND_COEFF][FIRST_COEFF] = Math.signum(mass[SECOND_COEFF][FIRST_COEFF]) * THRESHOLD;



                System.out.println("погружение  для 1 " + mass[FIRST_COEFF][SECOND_COEFF] + "   ");
            }
        }
        else if(l == 0){
            if(Math.abs(mass[FIRST_COEFF][SECOND_COEFF]) - Math.abs(mass[SECOND_COEFF][FIRST_COEFF]) > THRESHOLD){
                System.out.println("погружение  для 0 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);
                return mass;
            }
            else {
                System.out.println("погружение  для 0 " + mass[FIRST_COEFF][SECOND_COEFF] + "   "
                        + mass[FIRST_COEFF][SECOND_COEFF] +  "   " + mass[SECOND_COEFF][FIRST_COEFF]);



                mass[FIRST_COEFF][SECOND_COEFF] = Math.signum(mass[FIRST_COEFF][SECOND_COEFF]) * THRESHOLD;




                System.out.println("погружение  для 0 " + mass[FIRST_COEFF][SECOND_COEFF] + "   ");
            }
        }
        return mass;
    }


//    public double rond(double value){
//        BigDecimal decimal = BigDecimal.valueOf(value);
//        decimal.setScale(5, BigDecimal.ROUND_HALF_UP);
//    }
}
