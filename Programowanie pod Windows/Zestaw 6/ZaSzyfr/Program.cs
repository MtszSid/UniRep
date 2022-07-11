using System;
using System.IO;
using System.IO.Compression;
using System.Security.Cryptography;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ZaSzyfr
{
    class Program
    {
        static void Main(string[] args)
        {
            string pathIn, pathOut;
            pathIn = Console.ReadLine();
            pathOut = Console.ReadLine();
            Aes aes = Aes.Create();
            byte[] key =
           {
                0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
                0x09, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16
            };
            aes.Key = key;

            byte[] iv = aes.IV;
            using (FileStream fs = new FileStream(pathIn, FileMode.Open, FileAccess.Read))
            using (StreamReader sr = new StreamReader(fs, Encoding.UTF8))
            using (FileStream wfs = new FileStream(pathOut, FileMode.Append, FileAccess.Write))
            using (CryptoStream cs = new CryptoStream(wfs, aes.CreateEncryptor(key, iv), CryptoStreamMode.Write))
            using (GZipStream gZipStream = new GZipStream(cs, CompressionMode.Compress))
            using (StreamWriter sw = new StreamWriter(gZipStream, Encoding.UTF8))
            {
                sw.Write(sr.ReadToEnd());
            }

        }
    }
}
