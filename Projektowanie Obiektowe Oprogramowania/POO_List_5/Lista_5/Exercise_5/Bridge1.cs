namespace Exercise_5;

public interface INotifier
{
    public void NotifyPeople(IEnumerable<Person> collection);
}


public abstract class Bridge1
{
    private INotifier _notifier;

    public Bridge1(INotifier notifier)
    {
        _notifier = notifier;
    }

    public abstract IEnumerable<Person> GetPeople();

    public void NotifyPeople()
    {
        _notifier.NotifyPeople(GetPeople());
    }
}