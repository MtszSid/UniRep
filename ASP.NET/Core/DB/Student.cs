using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Core.DB
{
    [Table("Student")]
    public class Student
    {
        public int ID { get; set; }
        public string Imie { get; set; }
        public string Nazwisko { get; set; }
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:dd/MM/yyyy}")]
        public DateTime DataUrodzenia { get; set; }
        public int MiejscowoscID { get; set; }
        public Miejscowosc Miejscowosc  { get; set; } 

    }
}
