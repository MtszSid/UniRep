using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadanie_3
{
    internal class Program
    {
        static void Main(string[] args)
        {


        }
    }

    public class TaxCalculator
    {
        public Decimal CalculateTax(Decimal Price, decimal tax = 0.22M) { return Price * tax; }
    }
    public class Item
    {
        public Decimal Price { get; }
        public string Name { get; }
    }
    public class CashRegister
    {
        public TaxCalculator taxCalc = new TaxCalculator();
        public Decimal CalculatePrice(Item[] Items)
        {
            Decimal _price = 0;
            foreach (Item item in Items)
            {
            _price += item.Price + taxCalc.CalculateTax(item.Price);
            }
            return _price;
        }
        public void PrintBill(Item[] Items)
        {
            PrintBill(Items, e => e.Name);
        }
        public void PrintBill(Item[] Items, Func<Item, IComparable> func)
        {
            var orderedItems = Items.OrderBy(func);
            foreach (var item in orderedItems)
                Console.WriteLine("towar {0} : cena {1} + podatek {2}",
                item.Name, item.Price, taxCalc.CalculateTax(item.Price));
        }
    }
}
