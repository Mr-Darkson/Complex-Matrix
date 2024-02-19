import java.io.IOException;
/*
        ComplexNum methods:
        -addition(ComplexNum cn)
        -subtraction(ComplexNum cn)
        -multiplication(ComplexNum cn)
        -divide(ComplexNum cn)
        -getters and setters for realPart and imagPart

        ---

        Matrix methods:
        -setByIndex(int x, int y, ComplexNum cn)
        -addition(Matrix m)
        -subtraction(Matrix m)
        -multiplication(Matrix m)
        -transposition()
        -determinant()
         */
public class Main {
    public static void main(String[] args) throws IOException {


       Matrix matA = new Matrix(2,2);
       matA.setByIndex(0,0, new ComplexNum(1.0));
       matA.setByIndex(1,0, new ComplexNum(2.0));
       matA.setByIndex(0,1, new ComplexNum(3.0));
       matA.setByIndex(1,1, new ComplexNum(4.0));

       Matrix matB = new Matrix(2,2);
       matB.setByIndex(0,0, new ComplexNum(5.0));
       matB.setByIndex(1,0, new ComplexNum(6.0));
       matB.setByIndex(0,1, new ComplexNum(7.0));
       matB.setByIndex(1,1, new ComplexNum(8.0));

       matA = matA.multiplication(matB);
        System.out.println(matA);
        System.out.println(matA.determinant());
    }
}