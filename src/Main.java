import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Matrix<ComplexNum> matrixA = new Matrix<>( 3, 3, ComplexNum.class);
        matrixA.setByIndex(0, 0, new ComplexNum(1.0, 0.0));
        matrixA.setByIndex(1, 0, new ComplexNum(3.0, 0.0));
        matrixA.setByIndex(2, 0, new ComplexNum(3.0, 0.0));
        matrixA.setByIndex(0, 1, new ComplexNum(4.0, 0.0));

        Matrix<ComplexNum> matrixB = new Matrix<>(3,3, ComplexNum.class);
        matrixB.setByIndex(0, 0, new ComplexNum(3.0, 0.0));
        matrixB.setByIndex(1, 0, new ComplexNum(1.0, 0.0));
        matrixB.setByIndex(2, 0, new ComplexNum(1.0, 0.0));
        matrixB.setByIndex(0, 1, new ComplexNum(0.0, 0.0));
        matrixB.setByIndex(1, 1, new ComplexNum(4.0, 0.0));
        matrixA.subtraction(matrixB);
        System.out.println(matrixA);

        matrixA.transposition();
        matrixA = matrixA.multiplication(matrixB);
        System.out.println(matrixA);
    }
}