using System;

namespace NTon
{

    public class TimeNTon
    {
        TimeNTon()
        {
            if (hour <= end && hour >= start)
            {
                info = "W godzinach pracowni, instancja:" + (number + 1).ToString();
            }
            else
            {
                info = "Poza godzinami pracowni";
            }
        }
        private string info;
        static DateTime moment = DateTime.Now;
        static int hour = moment.Hour;
        static int N = 3;
        static int number = 0;
        static readonly int start = 18, end = 20;
        static TimeNTon[] Instances = new TimeNTon[N + 1];
        public static TimeNTon Instance()
        {
            if (hour <= end && hour >= start)
            {
                number %= N;
                if (Instances[number + 1] == null)
                {

                    Instances[number + 1] = new TimeNTon();
                }
                return Instances[number++ + 1];
            }
            else
            {
                if (Instances[0] == null)
                {
                    Instances[0] = new TimeNTon();
                }
                return Instances[0];
            }
        }

        public string Info()
        {
            return info;
        }
    }
}
