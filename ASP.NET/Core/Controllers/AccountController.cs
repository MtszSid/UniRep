using Core.Models;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Mvc;
using System.Security.Claims;

namespace Core.Controllers
{
    public class AccountController : Controller
    {
        [HttpGet]
        public IActionResult Logon()
        {
            var model = new AccountLogonModel();
            return View(model);
        }
        [HttpPost]
        public async Task<IActionResult> Logon(AccountLogonModel model)
        {
            if (this.ModelState.IsValid)
            {
                if (!string.IsNullOrEmpty(model.UserName) && model.UserName == model.Password)
                {
                    List<Claim> claims = new List<Claim>
                                    {
                                         new Claim(ClaimTypes.Name, model.UserName)
                                    };
                    // create identity
                    ClaimsIdentity identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
                    ClaimsPrincipal principal = new ClaimsPrincipal(identity);
                    await this.HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);
                    return Redirect("/");
                }
                else
                {
                    model.Message = "Zła nazwa użytkownika lub hasło";
                    return View(model);
                }
            }
            else
            {
                return View(model);
            }
        }

        public async Task<IActionResult> LogOut()
        {
            await this.HttpContext.SignOutAsync();
            return Redirect("/Account/Logon");
        }
    }
}
