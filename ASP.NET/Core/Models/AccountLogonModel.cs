namespace Core.Models
{
    public class AccountLogonModel
    {
        public string UserName { get; set; }
        public string Password { get; set; }
        public string Message { get; set; }

        public AccountLogonModel()
        {
            UserName = "";
            Password = "";
            Message  = "";
        }
    }
}
