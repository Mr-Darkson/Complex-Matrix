import java.awt.font.TextHitInfo;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

public class Matrix implements Cloneable{
    private ComplexNum[][] curMatrix;
    private int xSize;
    private int ySize;

    public Matrix(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        curMatrix = new ComplexNum[ySize][xSize];
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                curMatrix[i][j] = new ComplexNum(0.0, 0.0);
            }
        }
    }

    public Matrix(ComplexNum[][] curMatrix) {
        this.curMatrix = curMatrix;
        ySize = curMatrix.length;
        xSize = curMatrix[0].length;
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

    public void setByIndex(int x, int y, ComplexNum num) {
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
                ComplexNum tmp = curMatrix[y][x];
                curMatrix[y][x] = curMatrix[x][y];
                curMatrix[x][y] = tmp;
            }
        }
    }

    public Matrix multiplication(Matrix matrixB) { // ComplexNum
        if (this.xSize == matrixB.xSize && this.ySize == matrixB.ySize) {
            ComplexNum[][] tmpMatrix = new ComplexNum[ySize][xSize];
            for(int yFin = 0; yFin < ySize; yFin++) {
                for(int xFin = 0; xFin < xSize; xFin++) {
                    ComplexNum finCalc = new ComplexNum(0.0, 0.0);
                    for(int xRuner = 0, yRunner = 0; xRuner < xSize; xRuner++, yRunner++) {
                        finCalc.addition(curMatrix[yFin][xRuner].multiplication(matrixB.curMatrix[yRunner][xFin]));
                    }
                    tmpMatrix[yFin][xFin] = finCalc;
                }
            }
            return new Matrix(tmpMatrix);
        } else {
            System.out.println("Matrix should have the same size");
            return this;
        }
    }

    private void getCofactor(ComplexNum[][] mat, ComplexNum[][] temp,
                            int p, int q, int n)
    {
        int i = 0, j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                if (row != p && col != q) {
                    if(temp[i][j] == null) {
                        temp[i][j] = new ComplexNum(0.00);
                    }
                    temp[i][j++].setRealPart(mat[row][col].getRealPart());

                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }
    private ComplexNum determinantOfMatrix(ComplexNum[][] mat, int n)
    {
        ComplexNum D = new ComplexNum(0.0);

        if (n == 1)
            return mat[0][0];

        ComplexNum[][] temp = new ComplexNum[ySize][ySize];
        ComplexNum sign = new ComplexNum(1.0);

        for (int f = 0; f < n; f++) {
            getCofactor(mat, temp, 0, f, n);
            D.addition(sign.multiplication(mat[0][f]).multiplication(determinantOfMatrix(temp, n - 1)));

            sign.setRealPart(-1 * sign.getRealPart());
        }

        return D;
    }

    public ComplexNum[][] getCurMatrix() {
        return curMatrix;
    }

    public int getXSize() {
        return xSize;
    }

    public ComplexNum determinant() {
         return determinantOfMatrix(this.getCurMatrix(), this.getXSize());
    }

}
