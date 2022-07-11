using Core.DB;
using Microsoft.AspNetCore.Authentication.Cookies;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

var services = builder.Services;

services.AddDistributedMemoryCache();
services.AddSession(options =>
{
 options.IdleTimeout = TimeSpan.FromSeconds(10);
 options.Cookie.HttpOnly = true;
});

services.AddScoped<DaneStudentowDataContext>(container =>
{
    var config = container.GetService<IConfiguration>();
    var conectionString = config["AppSetings:db"];
    return new DaneStudentowDataContext(conectionString);
});

services
 .AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
 .AddCookie(CookieAuthenticationDefaults.AuthenticationScheme, options =>
 {
     options.LoginPath = "/Account/Logon";
     options.SlidingExpiration = true;
 });

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseSession();

app.UseAuthentication();

app.UseAuthorization();

var val = builder.Configuration["Section:Foo"];
var val2 = builder.Configuration["Section:Qux"];
Console.WriteLine(val);
Console.WriteLine(val2);

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
