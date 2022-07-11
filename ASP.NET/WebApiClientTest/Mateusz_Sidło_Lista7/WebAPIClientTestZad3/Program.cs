using Microsoft.IdentityModel.Tokens;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Security.Claims;
using System.Text;
namespace WebAPIClientTestZad3
{
    class Program
    {
        static void Main(string[] args)
        {
            var plainTextSecurityKey = "This is my shared, not so secret, secret!";
            Test(plainTextSecurityKey);
            Console.WriteLine();
            Test("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            Console.ReadLine();
        }

        private static void Test(string plainTextSecurityKey)
        {
            var signingKey = new Microsoft.IdentityModel.Tokens.SymmetricSecurityKey(Encoding.UTF8.GetBytes(plainTextSecurityKey));
            var signingCredentials = new Microsoft.IdentityModel.Tokens.SigningCredentials(signingKey, Microsoft.IdentityModel.Tokens.SecurityAlgorithms.HmacSha256Signature);
            // -------------------------
            var claimsIdentity = new ClaimsIdentity(new List<Claim>()
            {
                new Claim(ClaimTypes.Name, "myemail@myprovider.com"),
                new Claim(ClaimTypes.Role, "Administrator"),
            }, "Custom");

            var securityTokenDescriptor = new Microsoft.IdentityModel.Tokens.SecurityTokenDescriptor()
            {
                Audience = "http://my.website.com",
                Issuer = "http://my.tokenissuer.com",
                Subject = claimsIdentity,
                SigningCredentials = signingCredentials
            };
            var tokenHandler = new JwtSecurityTokenHandler();
            var plainToken = tokenHandler.CreateToken(securityTokenDescriptor);
            var signedAndEncodedToken = tokenHandler.WriteToken(plainToken);

            HttpClient client = new HttpClient();
            // Add an Accept header for JSON format.
            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpRequestMessage request = new HttpRequestMessage()
            {
                Method = HttpMethod.Get,
                RequestUri = new Uri("http://localhost:54994/api/Zad3")
            };
            request.Headers.Add("api_key", signedAndEncodedToken);
            HttpResponseMessage response = client.SendAsync(request).Result;
            var users = response.Content.ReadAsStringAsync().Result;
            try
            {
                var deserialisedUsers = JsonConvert.DeserializeObject<IEnumerable<Person>>(users);
                foreach (var user in deserialisedUsers)
                {
                    Console.WriteLine(user.Name);
                }
            }
            catch (Exception)
            {

                Console.WriteLine(users);
            }
        }
    }

    public class Person
    {
        public string Name { get; set; }
    }
}

