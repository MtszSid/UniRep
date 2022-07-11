using System.Reflection;
using POO_IOC.Tests;

namespace POO_IOC;

public class SimpleContainer
{
    private HashSet<Type> _registeredSingletonTypes;
    private Dictionary<Type, Type> _typeMapping;
    private Dictionary<Type, object?> _singletons;

    private HashSet<Type> _chainOfNotResolvedTypes;

    public SimpleContainer()
    {
        _registeredSingletonTypes = new HashSet<Type>();
        _singletons = new Dictionary<Type, object?>();
        _typeMapping = new Dictionary<Type, Type>();
        _chainOfNotResolvedTypes = new HashSet<Type>();
    }

    public void RegisterType<T>(bool singleton) where T : class
    {
        if (singleton)
        {
            _registeredSingletonTypes.Add(typeof(T));
        }
        else
        {
            _registeredSingletonTypes.Remove(typeof(T));
        }
    }

    public void RegisterType<TFrom, TTo>(bool singleton) where TTo : TFrom
    {
        if (singleton)
        {
            _registeredSingletonTypes.Add(typeof(TFrom));
            _singletons.Remove(typeof(TFrom));
        }
        else
        {
            _registeredSingletonTypes.Remove(typeof(TFrom));
            _singletons.Remove(typeof(TFrom));
        }

        _typeMapping[typeof(TFrom)] = typeof(TTo);
    }

    public T Resolve<T>()
    {
        try
        {
            var singleton = _registeredSingletonTypes.Contains(typeof(T));
            var isSaved = _singletons.ContainsKey(typeof(T));

            var registered = _typeMapping.ContainsKey(typeof(T));
            var isInterface = (typeof(T).Attributes & (TypeAttributes.Abstract | TypeAttributes.Interface)) != 0;

            var name = typeof(T).FullName;

            if (name is null)
            {
                throw new UnknownTypeException();
            }

            object? obj;

            if (isInterface && !registered && !isSaved)
            {
                throw new UnableToCreateInstanceInterfaceNotRegisteredException();
            }

            if (singleton && isSaved)
            {
                obj = _singletons[typeof(T)];
            }
            else
            {
                if (registered)
                {
                    var typ = _typeMapping[typeof(T)];

                    obj = typeof(SimpleContainer).GetMethod(nameof(Resolve)).MakeGenericMethod(typ)
                        .Invoke(this, null);
                }
                else
                {
                    var preferredConstructor = FindConstructor<T>();

                    var parameters = ResolveParameters(preferredConstructor);

                    obj = (T) preferredConstructor.Invoke(parameters);
                }

                if (singleton && (isSaved && _singletons[typeof(T)] is null || !isSaved))
                {
                    _singletons[typeof(T)] = obj;
                }
            }

            if (obj is null)
            {
                throw new UnableToCreateObjectException();
            }

            return (T) obj;
        }
        catch (TargetInvocationException e)
        {
            _chainOfNotResolvedTypes.Clear();
        
            if(e.InnerException is CyclicReferenceInConstructorException 
               or UnableToCreateObjectException
               or UnableToCreateInstanceInterfaceNotRegisteredException
               or UnknownTypeException)
                throw e.InnerException;
            else
                throw new UnableToCreateObjectException();
        }
    }

    private object[] ResolveParameters(ConstructorInfo? preferredConstructor)
    {
        if (preferredConstructor is null)
        {
            throw new UnableToCreateObjectException();
        }

        var paramInfo = preferredConstructor.GetParameters();
        var parameters = new Object[paramInfo.Length];

        for (var i = 0; i < paramInfo.Length; i++)
        {
            if (_chainOfNotResolvedTypes.Contains(paramInfo[i].ParameterType))
            {
                throw new CyclicReferenceInConstructorException();
            }

            _chainOfNotResolvedTypes.Add(paramInfo[i].ParameterType);

            parameters[i] = typeof(SimpleContainer).GetMethod(nameof(Resolve))
                .MakeGenericMethod(paramInfo[i].ParameterType).Invoke(this, null);

            _chainOfNotResolvedTypes.Remove(paramInfo[i].ParameterType);
        }

        return parameters;
    }

    private static ConstructorInfo? FindConstructor<T>()
    {
        var constructors = typeof(T).GetConstructors();
        ConstructorInfo? preferredConstructor = null;
        var conflict = false;
        var customAttribute = false; //TODO

        foreach (var constr in constructors)
        {
            var par = constr.GetParameters();
            var cstAtr = Attribute.IsDefined(constr, typeof(DependencyConstructorAttribute));
            if (preferredConstructor is null ||
                preferredConstructor.GetParameters().Length < par.Length && !customAttribute && !cstAtr)
            {
                conflict = false;
                preferredConstructor = constr;
                if (cstAtr && !customAttribute)
                {
                    customAttribute = true;
                }
            }
            else if (cstAtr && !customAttribute)
            {
                preferredConstructor = constr;
                customAttribute = true;
            }
            else if (cstAtr && customAttribute)
            {
                throw new AmbiguousConstructorsException();
            }
            else if (preferredConstructor.GetParameters().Length == par.Length)
            {
                conflict = true;
            }
        }

        if (conflict)
        {
            throw new AmbiguousConstructorsException();
        }

        return preferredConstructor;
    }

    public void RegisterInstance<T>(T Instance)
    {
        _registeredSingletonTypes.Add(typeof(T));
        _singletons[typeof(T)] = Instance;
    }
}