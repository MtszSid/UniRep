namespace Exercise_2;

public class CaesarStream: Stream
{
    private readonly Stream _stream;
    private readonly int _shift;

    public CaesarStream(Stream str, int shift)
    {
        _stream = str;
        _shift = shift;
    }
    
    public override void Flush()
    {
        _stream.Flush();
    }

    public override int Read(byte[] buffer, int offset, int count)
    {
        var ret = _stream.Read(buffer, offset, count);
        for (int i = 0; i < ret; i++)
        {
            buffer[i] = (byte) (buffer[i] + _shift);
        }

        return ret;
    }

    public override long Seek(long offset, SeekOrigin origin)
    {
        return _stream.Seek(offset, origin);
    }

    public override void SetLength(long value)
    {
        _stream.SetLength(value);
    }

    public override void Write(byte[] buffer, int offset, int count)
    {
        for (int i = 0; i < count; i++)
        {
            buffer[i] = (byte) (buffer[i] + _shift);
        }
        
        _stream.Write(buffer, offset, count);
    }

    public override bool CanRead => _stream.CanRead;
    public override bool CanSeek => _stream.CanSeek;
    public override bool CanWrite => _stream.CanWrite;
    public override long Length => _stream.Length;
    public override long Position
    {
        get => _stream.Position;
        set => _stream.Position = value;
    }


    public new void Dispose()
    {
        _stream.Dispose();
    }
}