namespace Exercise_3;

public class PlaneWrapper
{
    public Plane Plane { get; }
    private Airport _airport;

    public PlaneWrapper(Airport airport, Plane plane)
    {
        _airport = airport;
        Plane = plane;
    }

    public void Release()
    {
        _airport.Release(Plane);
    }
}