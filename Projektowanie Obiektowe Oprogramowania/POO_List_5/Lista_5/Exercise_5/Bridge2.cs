namespace Exercise_5;

public interface IPersonGetter
{
    public IEnumerable<Person> GetPeople();
}
public abstract class Bridge2
{
    private IPersonGetter _personGetter;

    public Bridge2(IPersonGetter getter)
    {
        _personGetter = getter;
    }

    public IEnumerable<Person> GetPeople()
    {
        return _personGetter.GetPeople();
    }

    public abstract void NotifyPeople();
}