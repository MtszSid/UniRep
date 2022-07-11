using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Security.Principal;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Web;
using System.Web.Http.Filters;
using System.Web.Http.Results;

namespace ASP.NET_Lista_7.Controllers.API
{
    public class CustomAuthenticationFilter : Attribute, IAuthenticationFilter
    {
        const string plainTextSecurityKey = "This is my shared, not so secret, secret!";
        const string header_name = "api_key";
        const string header_value = "123";
        public bool AllowMultiple
        {
            get
            {
                return true;
            }
        }
        public Task AuthenticateAsync(
        HttpAuthenticationContext context,
        CancellationToken cancellationToken)
        {
            var request = context.Request;
            IEnumerable<string> headers;
            if (request.Headers.TryGetValues(header_name, out headers))
            {

                var signedAndEncodedToken = headers.FirstOrDefault();

                var plainTextSecurityKey = "This is my shared, not so secret, secret!";
                var signingKey = new Microsoft.IdentityModel.Tokens.SymmetricSecurityKey(Encoding.UTF8.GetBytes(plainTextSecurityKey));
                var tokenHandler = new JwtSecurityTokenHandler();
                var tokenValidationParameters = new TokenValidationParameters()
                {
                    ValidAudiences = new string[]
                    {
                    "http://my.website.com",
                    "http://my.otherwebsite.com"
                    },
                    ValidIssuers = new string[]
                    {
                    "http://my.tokenissuer.com",
                    "http://my.othertokenissuer.com"
                    },
                    IssuerSigningKey = signingKey
                };
                try
                {
                    SecurityToken validatedToken;
                    
                    var validatedPrincipal = tokenHandler.ValidateToken(signedAndEncodedToken,
                    tokenValidationParameters, out validatedToken);

                    if (validatedPrincipal.Identity.IsAuthenticated)
                    {
                        context.Principal = validatedPrincipal;

                        return Task.FromResult(0);
                    }
                }
                catch{ }
            }
            // fallback - brak nagłówka lub niepoprawny nagłówek
            context.ErrorResult =
            new ResponseMessageResult(
            request.CreateErrorResponse(HttpStatusCode.Unauthorized, "unauthorized") );
        return Task.FromResult(0);
        }
        public Task ChallengeAsync(
        HttpAuthenticationChallengeContext context,
        CancellationToken cancellationToken)
        {
            return Task.FromResult(0);
        }
    }
}