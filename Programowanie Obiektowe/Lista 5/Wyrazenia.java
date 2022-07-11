//Mateusz Sidło Zad.1 cz.1

import java.util.Hashtable;
public class Wyrazenia {
    public static void main(String[] args) {
    expression.set_variable_value("a", 3);
    expression.set_variable_value("x", 10);
    expression wyr = new add(new subtract(new constant(20), new constant(10)), new multiply (new constant(17), new divide(new variable("x"), new variable("a"))));
    System.out.println(wyr.ToString());
    System.out.println(wyr.calculate());
    
    }
}

class expression{
    expression x;
    expression y;
    static Hashtable<String, Integer> Vals = new Hashtable<String, Integer>();
    public static void set_variable_value(String K, Integer V){
        Vals.put(K,V);
    }
    int get(String K){
        return Vals.get(K);
    }
    expression(expression a, expression b){
        x = a;
        y = b;
    }
    expression(){};
    public int calculate(){
        return 0;
    }
    public String ToString(){
        return "?";
    }
}

class add extends expression{
    public add(expression a, expression b){
        super(a, b);
    }
    public int calculate(){
        return x.calculate() + y.calculate();
    };
    public String ToString(){
        return x.ToString() + "+" + y.ToString();
    }
}

class multiply extends expression{
    public multiply(expression a, expression b){
        super(a, b);
    }
    public int calculate(){
        return x.calculate() * y.calculate();
    };
    public String ToString(){
        return x.ToString() + "*" + y.ToString();
    }
}

class subtract extends expression{
    public subtract(expression a, expression b){
        super(a, b);
    }
    public int calculate(){
        return x.calculate() - y.calculate();
    };
    public String ToString(){
        return x.ToString() + "-" + y.ToString();
    }
}

class divide extends expression{
    public divide(expression a, expression b){
        super(a, b);
    }
    public int calculate(){
        return x.calculate() / y.calculate();
    };
    public String ToString(){
        return x.ToString() + "/" + y.ToString();
    }
}

class constant extends expression{
    Integer value;

    public constant(int n){
        value = n;
    }
    public int calculate(){
        return value;
    }
    public String ToString(){
        return value.toString();
    }
}

class variable extends expression{
    String var;
    public variable(String n){
        var = n;
    }
    public int calculate(){
        return get(var);
    }
    public String ToString(){
        return var;
    }
}