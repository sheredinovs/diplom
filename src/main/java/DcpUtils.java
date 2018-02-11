/**
 * Created by Slavik on 24.05.2016.
 */
public class DcpUtils {

    private double valueCoefficient(int value){
        if (value == 0)
            return 1.0/Math.sqrt(2);
        else return 1;
    }

    public double[][] dcp(double mas[][]){
        int size = mas.length;
        double[][] res = new double[size][size];
        double U, V, temp;
        for (int v =0; v<size; v++) {
            for (int u = 0; u < size; u++) {
                V = valueCoefficient(v);
                U = valueCoefficient(u);
                temp = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        temp += mas[i][j] * Math.cos(Math.PI * v * (2 * i + 1) / (2 * size)) *
                                Math.cos(Math.PI * u * (2 * j + 1) / (2 * size));
                    }
                }
                res[v][u] = U * V * temp / (Math.sqrt(2 * size));
            }
        }
        return res;
    }
    public static int n = 8,m = 8;
    public static double pi = 3.142857;

     strictfp double[][] dctTransform(double[][] matrix)
    {
        int i, j, k, l;

        // dct will store the discrete cosine transform
        double[][] dct = new double[m][n];

        double ci, cj, dct1, sum;

        for (i = 0; i < m; i++)
        {
            for (j = 0; j < n; j++)
            {
                // ci and cj depends on frequency as well as
                // number of row and columns of specified matrix
                if (i == 0)
                    ci = 1 / Math.sqrt(m);
                else
                    ci = Math.sqrt(2) / Math.sqrt(m);

                if (j == 0)
                    cj = 1 / Math.sqrt(n);
                else
                    cj = Math.sqrt(2) / Math.sqrt(n);

                // sum will temporarily store the sum of
                // cosine signals
                sum = 0;
                for (k = 0; k < m; k++)
                {
                    for (l = 0; l < n; l++)
                    {
                        dct1 = matrix[k][l] *
                                Math.cos((2 * k + 1) * i * pi / (2 * m)) *
                                Math.cos((2 * l + 1) * j * pi / (2 * n));
                        sum = sum + dct1;
                    }
                }
                dct[i][j] = ci * cj * sum;
            }
        }
        return dct;
    }

    public double[][] idcp(double mas[][]){
        int size = mas.length;
        double[][] res = new double[size][size];
        double U, V, temp;
        for (int v=0; v<size; v++) {
            for (int u = 0; u < size; u++) {
                temp = 0;
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        V = valueCoefficient(i);
                        U = valueCoefficient(j);
                        temp += U * V * mas[i][j] * Math.cos(Math.PI * i * (2 * v + 1) / (2 * size)) *
                                Math.cos(Math.PI * j * (2 * u + 1) / (2 * size));
                    }
                }
                res[v][u] = temp / (Math.sqrt(2 * size));
            }
        }
        return res;
    }











}
