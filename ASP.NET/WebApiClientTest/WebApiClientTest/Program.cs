using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace WebApiClientTest
{
    class Program
    {
        static HttpClient client = new HttpClient();
        static Program()
        {
            client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
        }
        static void Main(string[] args)
        {
            Get();
            Post();
            Console.ReadLine();
        }
        async static Task Get()
        {
            var responseString = await client.GetStringAsync("http://localhost:54994/api/Zad1");
           
            var response = JsonConvert.DeserializeObject<IEnumerable<Person>>(responseString);
            foreach (var person in response)
            {
                Console.WriteLine(string.Format("{0} {1} {2} {3}", person.ID, person.Name, person.Surname, person.BirthDate))
               ;
            }
        }
        async static Task Post()
        {
            var person = new Zad1Person() { Name = "aaaaa", Surname = "bbbbb", BirthDate = System.DateTime.Now };
            var request = JsonConvert.SerializeObject(person);
            StringContent content = new StringContent(request, Encoding.UTF8, "application/json");
           
            var response = await client.PostAsync("http://localhost:54994/api/Zad1", content);
           
            var responseString = await response.Content.ReadAsStringAsync();
            var personResp = JsonConvert.DeserializeObject<Zad1Person>(responseString);
            Console.WriteLine(string.Format("{0} {1}", personResp.Name, personResp.Surname));
        }
    }
    public class Person
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public string Surname { get; set; }
        public DateTime BirthDate { get; set; }
    }

    public class Zad1Person
    {
        public string Name { get; set; }
        public string Surname { get; set; }
        public DateTime BirthDate { get; set; }
    }

}
