using System;
using System.Collections.Generic;
using System.Linq;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;

namespace CustomInterface
{
    [ServiceContract]
    public interface ICustom
    {
        [OperationContract]
        string DoSomething();
    }
}
