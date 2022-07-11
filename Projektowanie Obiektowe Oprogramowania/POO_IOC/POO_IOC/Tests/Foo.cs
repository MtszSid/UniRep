using System.Runtime.CompilerServices;

namespace POO_IOC.Tests;

public interface IFoo
{
    
}

public class Foo : IFoo
{
    
}

public class Bar : Foo
{
    
}

public class Baz : IFoo
{
    public int Instance { get; }
    public Baz()
    {
        Instance = 0;
    }

    public Baz(String s)
    {
        Instance = 1;
    }

    public Baz(string s, string s2)
    {
        Instance = 2;
    }
}

public class Qux : IFoo
{
    public int Instance { get; }
    public Qux()
    {
        Instance = 0;
    }

    [DependencyConstructor]
    public Qux(String s)
    {
        Instance = 1;
    }

    public Qux(string s, string s2)
    {
        Instance = 2;
    }
}

public class DependencyConstructorAttribute : Attribute
{
}

public class Quux
{
    public Quux(Quuz q)
    {
        
    }
}

public class Quuz
{
    public Quuz(ABC a)
    {
        
    }
}

public class ABC
{
    public ABC(Quux q)
    {
        
    }
}

public class A
{
    public B b;
    public IC c;
    public A( B b, IC c )
    {
        this.b = b;
        this.c = c;
    }
}
public class B { }
public interface IC { }
public class C : IC { }

public class Waldo
{
    [DependencyConstructor]
    public Waldo()
    {
        
    }
    
    [DependencyConstructor]
    public Waldo(String s)
    {
        
    }
}

