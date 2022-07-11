using System.Text;

namespace Exercise_3;

public class LoggingProxy : IAirport, IDisposable
{

    private Airport _airport;
    private StreamWriter _file;
    public LoggingProxy(int size)
    {
        _airport = new Airport(size);
        _file = new StreamWriter(File.Create(@"log.txt"));
    }
    
    public PlaneWrapper GetPlane()
    {
        var sb = new StringBuilder();
        sb.Append("Start: ");
        sb.Append(DateTime.Now);
        sb.Append(", Method: GetPlane, ");


        PlaneWrapper? pw = null;
        try
        {
            pw = _airport.GetPlane();
        }
        catch (MaxNumberOfObjectsExceededException e)
        {
            
        }

        sb.Append("End: ");
        sb.Append(DateTime.Now);
        if (pw is null)
        {
            
            sb.Append(", Exception: MaxNumberOfObjectsExceededException");
            throw new MaxNumberOfObjectsExceededException();
        }

        sb.Append(", Returned: Plane -- ");
        sb.Append(pw.Plane);
        return pw;
    }

    public void Dispose()
    {
        _file.Dispose();
    }
}