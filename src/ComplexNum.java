import java.text.DecimalFormat;

public class ComplexNum {
    //Colorize
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private Double realPart = 0.0;
    private Double imagPart = 0.0;
    String symbol = "i";


    @Override
    public String toString() {
        DecimalFormat di = new DecimalFormat("0.0");
        DecimalFormat dr = new DecimalFormat("0.00");
        return dr.format(realPart) + (imagPart == 0.0 ? "" : "+" + di.format(imagPart) + "i");
    }

    public ComplexNum(Double realPart, Double imagPart) {
        this.realPart = realPart;
        this.imagPart = imagPart;
    }

    public ComplexNum(Double realPart) {
        this.realPart = realPart;
    }

    public ComplexNum(String num) {
        num.replaceAll(" ", "");
        num.replaceAll(symbol, "");
        String[] params = num.split("\\+");
        if(params.length < 1 || params.length > 2) {
            System.out.println("Некорректный ввод данных.\n" + ANSI_RED + "Комплексное число должно быть формата a + bi , " +
                    "где a и b числа типа double" + ANSI_RESET);
        }
        else {
            realPart = Double.parseDouble(params[0]);
            if(params.length == 2) {
                imagPart = Double.parseDouble(params[1]);
            }
        }

    }

    public ComplexNum addition (ComplexNum operand) {
        this.realPart += operand.realPart;
        this.imagPart +=  operand.imagPart;
        return this;
    }

    public ComplexNum subtraction(ComplexNum operand) {
        this.realPart -= operand.realPart;
        this.imagPart -= operand.imagPart;
        return this;
    }

    public ComplexNum multiplication(ComplexNum operand) {
        ComplexNum num1 = new ComplexNum(this.realPart * operand.realPart, this.realPart * operand.imagPart);
        ComplexNum num2 = new ComplexNum((this.imagPart * operand.imagPart) * -1, this.imagPart * operand.realPart);
        return num1.addition(num2);
    }


    public ComplexNum divide(ComplexNum operand) {
        double real2 = operand.realPart;
        double image2 = operand.imagPart;
        double newReal = (realPart*real2 + imagPart*image2)/(real2*real2 + image2*image2);
        double newImage = (imagPart*real2 - realPart*image2)/(real2*real2 + image2*image2);
        return new ComplexNum(newReal,newImage);
    }

    public Double getRealPart() {
        return realPart;
    }

    public void setRealPart(Double realPart) {
        this.realPart = realPart;
    }

    public Double getImagPart() {
        return imagPart;
    }

    public void setImagPart(Double imagPart) {
        this.imagPart = imagPart;
    }

}
