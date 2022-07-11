using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Zadanie_1.Models.Students
{
    public class StudentsIndexModel
    {
        public PagedEnumerable<Student> Students { get; set; }
    }

    public class PagedEnumerable<T>
    {
        public int CurrentPage { get; set; }
        public int PageSize { get; set; }
        public int TotalCount { get; set; }

        public IEnumerable<T> Items { get; set; }
    }

    public class DataLayer
    {
        public IEnumerable<Student> GetUsers(string OrderBy, int StartRow, int RowCount)
        {
            var dataContext = CustomDataContext.GetDataContext();
            var orderSplit = OrderBy.Split(' ');
            var key = (orderSplit.Length >= 2 ? orderSplit[0] : "Imię");
            var order = (orderSplit.Length >= 2 ? orderSplit[1] : "ASC");

            if (order == "ASC")
            {
                switch (key)
                {
                    case "Imie": return (from st in dataContext.Students orderby st.Imie select st).Skip(StartRow).Take(RowCount);
                    case "Nazwisko": return (from st in dataContext.Students orderby st.Nazwisko select st).Skip(StartRow).Take(RowCount);
                    case "Miejscowosc": return (from st in dataContext.Students orderby st.Miejscowosc.Nazwa select st).Skip(StartRow).Take(RowCount);
                    case "DataUrodzenia": return (from st in dataContext.Students orderby st.DataUrodzenia select st).Skip(StartRow).Take(RowCount);
                }
            }
            else
            {
                switch (key)
                {
                    case "Imie": return (from st in dataContext.Students orderby st.Imie descending select st).Skip(StartRow).Take(RowCount);
                    case "Nazwisko": return (from st in dataContext.Students orderby st.Nazwisko descending select st).Skip(StartRow).Take(RowCount);
                    case "Miejscowosc": return (from st in dataContext.Students orderby st.Miejscowosc.Nazwa descending select st).Skip(StartRow).Take(RowCount);
                    case "DataUrodzenia": return (from st in dataContext.Students orderby st.DataUrodzenia descending select st).Skip(StartRow).Take(RowCount);
                }
            }

            return (from st in dataContext.Students select st).Skip(StartRow).Take(RowCount);
        }

        public int TotalUsers()
        {
            var dataContext = CustomDataContext.GetDataContext();

            var t = (from st in dataContext.Students select st).Count();

            return t;
        }
    }

    
}
