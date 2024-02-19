import java.awt.font.TextHitInfo;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix<T extends MatrixDigits> {
    private final Class<? extends MatrixDigits> cls;
    private MatrixDigits[][] curMatrix;
    private int xSize;
    private int ySize;

    public Matrix(int xSize, int ySize, Class<? extends MatrixDigits> cls) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.cls = cls;
        curMatrix = (T[][]) Array.newInstance(cls, ySize, xSize);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (cls.getName() == "ComplexNum") {
                    curMatrix[i][j] = new ComplexNum(0.0, 0.0);
                } else if (cls.getName() == "RealNum") {
                    curMatrix[i][j] = new RealNum(0.0);
                } else {
                    curMatrix[i][j] = new MatrixDigits(0.0);
                }
                MatrixDigits point = curMatrix[i][j];
            }
        }
    }

    public Matrix(MatrixDigits[][] curMatrix, Class<? extends MatrixDigits> cls) {
        this.curMatrix = curMatrix;
        this.cls = cls;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int yPoint = 0; yPoint < ySize; yPoint++) {
            for (int xPoint = 0; xPoint < xSize; xPoint++) {
                builder.append(curMatrix[yPoint][xPoint].toString() + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public void setByIndex(int x, int y, MatrixDigits num) {
        curMatrix[y][x] = num;
    }

    public void addition(Matrix matrixB) {
        if (xSize == matrixB.xSize && ySize == matrixB.ySize) {
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    curMatrix[y][x].addition(matrixB.curMatrix[y][x]);
                }
            }
        } else {
            System.out.println("Matrix should have the same size");
        }
    }

    public void subtraction(Matrix matrixB) {
        if (xSize == matrixB.xSize && ySize == matrixB.ySize) {
            for (int y = 0; y < ySize; y++) {
                for (int x = 0; x < xSize; x++) {
                    curMatrix[y][x].subtraction(matrixB.curMatrix[y][x]);
                }
            }
        } else {
            System.out.println("Matrix should have the same size");
        }
    }

    public void transposition() {
        for (int y = 0; y < ySize; y++) {
            for (int x = y; x < xSize; x++) {
                MatrixDigits tmp = curMatrix[y][x];
                curMatrix[y][x] = curMatrix[x][y];
                curMatrix[x][y] = tmp;
            }
        }
    }

    public Matrix multiplication(Matrix<T> matrixB) { // RealNum/ComplexNum
        if (this.xSize == matrixB.xSize && this.ySize == matrixB.ySize) {
            MatrixDigits[][] tmpMatrix = (T[][]) Array.newInstance(matrixB.getClass(), xSize, ySize);
            for(int yFin = 0; yFin < ySize; yFin++) {
                for(int xFin = 0; xFin < xSize; xFin++) {
                    MatrixDigits finCalc =
                            (matrixB.getClass().getName().equals("ComplexNum") ? new ComplexNum(0.0, 0.0) : new RealNum(0.0));
                    for(int xRuner = 0, yRunner = 0; xRuner < xSize; xRuner++, yRunner++) {
                        finCalc.addition(curMatrix[yFin][xRuner].multiplication(matrixB.curMatrix[yRunner][xFin]));
                    }
                    tmpMatrix[yFin][xFin] = finCalc;
                }
            }
            return new Matrix(tmpMatrix, cls);
        } else {
            System.out.println("Matrix should have the same size");
            return this;
        }
    }

}
