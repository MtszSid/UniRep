using System.Collections;

namespace Exercise_4;

public class ComparerAdapter<T> : IComparer
{
    private readonly Comparison<T> _comparison;
    public ComparerAdapter(Comparison<T> comparison)
    {
        _comparison = comparison;
    }
    
    public int Compare(object? x, object? y)
    {
        if (x is null || y is null)
        {
            throw new ArgumentException("NULL as a Compare argument");
        }
        if(typeof(T) != x.GetType() || typeof(T) != y.GetType())
        {
            throw new ArgumentException("Invalid types of a Compare argument");
        }

        return _comparison((T)x, (T)y);
    }
}