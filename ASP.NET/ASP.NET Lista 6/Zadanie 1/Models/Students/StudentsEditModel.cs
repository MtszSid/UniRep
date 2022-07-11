using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Zadanie_1.Models.Students
{
    public class StudentsEditModel
    {
        [Required]
        public int ID { get; set; }
        [Required]
        public String Name { get; set; }
        [Required]
        public String Surname { get; set; }
        [Required]
        [DataType(DataType.Date)]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd}")]
        public DateTime BirthDate { get; set; }
        public String CityName { get; set; }
    }
}