package je3.classes;

public class ComplexNumber implements  Comparable{

    private double real,imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double real() { return real; }

    public double imaginary() { return imaginary; }

    public double magnitude() { return Math.sqrt(real*real + imaginary*imaginary); }

    public String toString() {
        return "{" + real + "," + imaginary + "}";
    }

    public static ComplexNumber add(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real + b.real, a.imaginary + b.imaginary);
    }

    public ComplexNumber add(ComplexNumber a) {
        return  new ComplexNumber(real + a.real, imaginary + a.imaginary);
    }

    public static ComplexNumber multiply(ComplexNumber a, ComplexNumber b) {
        return new ComplexNumber(a.real * b.real - a.imaginary * b.imaginary,a.real * b.imaginary + a.imaginary*b.real);
    }

    public ComplexNumber multiply(ComplexNumber a) {
        return new ComplexNumber(real*a.real - imaginary*a.imaginary, real * a.imaginary + imaginary*a.real);
    }


    @Override
    public int compareTo(Object o) {
        ComplexNumber that = (ComplexNumber) o;
        if (this.real > that.real) {
            return 1;
        } else if (this.real < that.real) {
            return -1;
        } else if (this.imaginary > that.imaginary) {
            return 1;
        } else if (this.imaginary < that.imaginary) {
            return -1;
        }
        return 0;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ComplexNumber)) return false;
        ComplexNumber that = (ComplexNumber) o;
        return ((real == that.real) && (imaginary == that.imaginary))
    }

    public int hashCode() {
        return (int) (real *73 + imaginary *3);
    }
}
