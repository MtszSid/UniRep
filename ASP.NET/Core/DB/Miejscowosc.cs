using System.ComponentModel.DataAnnotations.Schema;

namespace Core.DB
{
    [Table("Mjejscowosc")]
    public class Miejscowosc
    {
        public int id { get; set; }
        public string Nazwa { get; set; }

        public ICollection<Student> Students { get; set; }
    }
}
