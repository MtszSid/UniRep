namespace Exercise_3;

public class TimedProxy: IAirport
{
    private Airport _airport;
    public TimedProxy(int size)
    {
        _airport = new Airport(size);
    }
    public PlaneWrapper GetPlane()
    {
        if (DateTime.Now.Hour is >= 8 and < 22)
        {
            throw new Exception("Airport is closed.");
        }
        return _airport.GetPlane();
    }
}