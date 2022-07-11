using System.Net.Mail;
using System.Net.Mime;

namespace Exercise_1;

public class SmtpFacade {
    public void Send(string from, string to,
        string subject, string body,
        Stream? attachment, string? attachmentMimeType)
    {
        SmtpClient client = new SmtpClient();
        MailMessage message = new MailMessage(from, to, subject, body);


        Attachment? attachmentMessage = null;
        if (attachment is not null && attachmentMimeType is not null)
        {
            attachmentMessage = new Attachment(attachment, new ContentType(attachmentMimeType));

            message.Attachments.Add(attachmentMessage);
        }

        client.Send(message);

        if (attachmentMessage is not null) attachmentMessage.Dispose();
        message.Dispose();
        client.Dispose();
    }
}
