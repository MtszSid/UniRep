using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace CustomInterface
{
    [ServiceContract]
    public interface MyInterface
    {
        [OperationContract]
        string DoSomething(string str);
    }
}
