namespace Exercise_3;

public class Airport: IAirport
{
    private List<Plane> _releasedPlanes;
    private List<Plane> _availablePlanes;
    private int _maxPlains;

    public Airport(int size)
    {
        if (size <= 0)
        {
            throw new ArgumentException();
        }

        _maxPlains = size;
        _releasedPlanes = new ();
        _availablePlanes = new();
    }

    public PlaneWrapper GetPlane()
    {
        if (_availablePlanes.Count == 0 && _releasedPlanes.Count >= _maxPlains)
        {
            throw new MaxNumberOfObjectsExceededException();
        }

        if (_availablePlanes.Count == 0)
        {
            _availablePlanes.Add(new Plane());
        }

        var plane = _availablePlanes[0];
        _availablePlanes.RemoveAt(0);
        _releasedPlanes.Add(plane);
        return new PlaneWrapper(this, plane);
    }
    
    
    public void Release(Plane plane)
    {
        if (_availablePlanes.Contains(plane) || !_releasedPlanes.Contains(plane))
        {
            throw new ArgumentException();
        }
        
        _releasedPlanes.Remove(plane);
        _availablePlanes.Add(plane);
    }
}