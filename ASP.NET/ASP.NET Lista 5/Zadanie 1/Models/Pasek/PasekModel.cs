using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Text;
using System.Web;

namespace Zadanie_1.Models.Pasek
{
    public class PasekModel
    {
        [Required]
        public String Imie { get; set; }
        [Required]
        public String Nazwisko { get; set; }
        [Required]
        public String Przedmiot { get; set; }
        [Required]
        public String Prowadzacy { get; set; }
        [Required]
        [DataType(DataType.Date)]
        [DisplayFormat(ApplyFormatInEditMode = true, DataFormatString = "{0:yyyy-MM-dd}")]
        public DateTime Data { get; set; }
        public int Zad1 { get; set; }
        public int Zad2 { get; set; }
        public int Zad3 { get; set; }
        public int Zad4 { get; set; }
        public int Zad5 { get; set; }
        public int Zad6 { get; set; }
        public int Zad7 { get; set; }
        public int Zad8 { get; set; }
        public int Zad9 { get; set; }
        public int Zad10 { get; set; }

        public PasekModel()
        {
            Data = DateTime.Now;
        }
        
        override public String ToString()
        {
            StringBuilder s = new StringBuilder();
            s.Append("Imie=");
            s.Append(Imie);
            s.Append("&Nazwisko=");
            s.Append(Nazwisko);
            s.Append("&Przedmiot=");
            s.Append(Przedmiot);
            s.Append("&Prowadzacy=");
            s.Append(Prowadzacy);
            s.Append("&Data=");
            s.Append(Data.ToString("d"));
            s.Append("&Zad1=");
            s.Append(Zad1);
            s.Append("&Zad2=");
            s.Append(Zad2);
            s.Append("&Zad3=");
            s.Append(Zad3);
            s.Append("&Zad4=");
            s.Append(Zad4);
            s.Append("&Zad5=");
            s.Append(Zad5);
            s.Append("&Zad6=");
            s.Append(Zad6);
            s.Append("&Zad7=");
            s.Append(Zad7);
            s.Append("&Zad8=");
            s.Append(Zad8);
            s.Append("&Zad9=");
            s.Append(Zad9);
            s.Append("&Zad10=");
            s.Append(Zad10);

            return s.ToString();
        }
    }
}