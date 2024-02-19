import java.text.DecimalFormat;

public class RealNum extends MatrixDigits {

    public RealNum(double realPart) {
        this.realPart = realPart;
    }
    public RealNum(String realPart) {
        this.realPart = Double.parseDouble(realPart);
    }

    @Override
    public String toString() {
        DecimalFormat dr = new DecimalFormat("0.0000");
        return dr.format(realPart);
    }

    @Override
    public MatrixDigits addition (MatrixDigits operand) {
        this.realPart += operand.realPart;
        return this;
    }
    @Override
    public MatrixDigits subtraction(MatrixDigits operand) {
        this.realPart -= operand.realPart;
        return this;
    }
    @Override
    public MatrixDigits multiplication(MatrixDigits operand) {
        return new RealNum(this.realPart * operand.realPart);
    }
    @Override
    public MatrixDigits divide(MatrixDigits operand) {
        return new RealNum(this.realPart / operand.realPart);
    }
}
