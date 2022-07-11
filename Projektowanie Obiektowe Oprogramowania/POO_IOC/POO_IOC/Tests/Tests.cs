using NUnit.Framework;

namespace POO_IOC.Tests;


public class Tests
{
    [SetUp]
    public void SetUp()
    {

    }

    [Test]
    public void SimpleCreationTest()
    {
        SimpleContainer c = new SimpleContainer();
        Foo fs = c.Resolve<Foo>();

        Assert.IsInstanceOf<Foo>(fs);
    }

    [Test]
    public void SingletonTest()
    {
        SimpleContainer c = new SimpleContainer();
        c.RegisterType<Foo>(true);
        Foo f1 = c.Resolve<Foo>();
        Foo f2 = c.Resolve<Foo>();

        Assert.AreEqual(f1, f2);
    }

    [Test]
    public void SingletonTest2()
    {
        SimpleContainer c = new SimpleContainer();
        c.RegisterType<Foo>(false);
        Foo f1 = c.Resolve<Foo>();
        Foo f2 = c.Resolve<Foo>();

        Assert.AreNotEqual(f1, f2);
    }

    [Test]
    public void InterfaceNotRegistered()
    {
        SimpleContainer c = new SimpleContainer();

        Assert.Catch<UnableToCreateInstanceInterfaceNotRegisteredException>(() => { c.Resolve<IFoo>(); });

    }

    [Test]
    public void InterfaceRegistration1()
    {
        SimpleContainer c = new SimpleContainer();
        c.RegisterType<IFoo, Foo>(false);
        IFoo f = c.Resolve<IFoo>();

        Assert.IsInstanceOf<Foo>(f);
    }

    [Test]
    public void InterfaceRegistration2()
    {
        SimpleContainer c = new SimpleContainer();
        c.RegisterType<IFoo, Foo>(false);
        IFoo f = c.Resolve<IFoo>();

        Assert.IsInstanceOf<Foo>(f);

        c.RegisterType<IFoo, Bar>(false);
        IFoo g = c.Resolve<IFoo>();

        Assert.IsInstanceOf<Bar>(g);
    }

    [Test]
    public void InterfaceRegistration3()
    {
        SimpleContainer c = new SimpleContainer();
        c.RegisterType<IFoo, Foo>(true);
        IFoo f = c.Resolve<IFoo>();

        Assert.IsInstanceOf<Foo>(f);

        c.RegisterType<IFoo, Bar>(true);
        IFoo g = c.Resolve<IFoo>();

        Assert.IsInstanceOf<Bar>(g);
    }

    [Test]
    public void InstanceRegistration1()
    {
        SimpleContainer c = new SimpleContainer();

        var foo = new Foo();

        c.RegisterInstance(foo);

        Foo f = c.Resolve<Foo>();

        Assert.AreEqual(f, foo);
    }

    [Test]
    public void InstanceRegistration2()
    {
        SimpleContainer c = new SimpleContainer();

        var foo = new Foo();

        c.RegisterInstance<IFoo>(foo);

        IFoo f = c.Resolve<IFoo>();

        Assert.AreEqual(f, foo);
    }

    [Test]
    public void InstanceRegistration3()
    {
        SimpleContainer c = new SimpleContainer();

        var foo = new Foo();

        c.RegisterInstance<IFoo>(foo);

        var f = c.Resolve<IFoo>();
        var g = c.Resolve<IFoo>();

        Assert.AreEqual(f, g);
    }

    [Test]
    public void InstanceRegistration4()
    {
        SimpleContainer c = new SimpleContainer();

        var foo = new Foo();

        c.RegisterInstance<IFoo>(new Bar());

        c.RegisterInstance<IFoo>(foo);

        var f = c.Resolve<IFoo>();


        Assert.AreEqual(f, foo);
    }

    [Test]
    public void InstanceRegistration5()
    {
        SimpleContainer c = new SimpleContainer();

        var foo = new Foo();

        c.RegisterInstance<IFoo>(new Bar());

        c.RegisterType<IFoo, Foo>(false);

        var f = c.Resolve<IFoo>();


        Assert.AreNotEqual(f, foo);
    }

    [Test]
    public void CreationTest1()
    {
        SimpleContainer c = new SimpleContainer();

        c.RegisterInstance<String>("str");
        var baz = c.Resolve<Baz>();
        
        Assert.IsInstanceOf<Baz>(baz);
        Assert.AreEqual(2, baz.Instance);
    }

    [Test]
    public void CreationTest2()
    {
        SimpleContainer c = new SimpleContainer();

        Assert.Catch<UnableToCreateObjectException>(() => { c.Resolve<Qux>(); });
        
        c.RegisterInstance<String>("str");
        var qux = c.Resolve<Qux>();
        
        Assert.IsInstanceOf<Qux>(qux);
        Assert.AreEqual(1, qux.Instance);
    }
    
    [Test]
    public void CreationTest3()
    {
        SimpleContainer c = new SimpleContainer();

        Assert.Catch<CyclicReferenceInConstructorException>(() => { c.Resolve<Quux>(); });
    }

    [Test]
    public void CreationTest4()
    {
        SimpleContainer c = new SimpleContainer();

        c.RegisterType<IC, C>(false);
        A a = c.Resolve<A>();

        Assert.IsNotNull(a.b);
        
        Assert.IsNotNull(a.c);
    
    }
    
    [Test]
    public void CreationTest5()
    {
        SimpleContainer c = new SimpleContainer();

        Assert.Catch<AmbiguousConstructorsException>(() => { c.Resolve<Waldo>(); });
    }
}