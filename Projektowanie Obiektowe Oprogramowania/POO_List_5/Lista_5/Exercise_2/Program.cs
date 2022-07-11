// See https://aka.ms/new-console-template for more information

using Exercise_2;

var file = File.Create(@"zad_output.txt");
var writer = new StreamWriter(new CaesarStream(file, 5));
writer.WriteLine("Hello.");
writer.Flush();
writer.Dispose();
file.Dispose();

file = new FileStream(@"zad_output.txt", FileMode.Open);
var reader = new StreamReader(file);
Console.WriteLine(reader.ReadLine());
reader.Dispose();
file.Dispose();

file = new FileStream(@"zad_output.txt", FileMode.Open);
var reader2 = new StreamReader(new CaesarStream(file, -5));
Console.WriteLine(reader2.ReadLine());

file.Dispose();
reader2.Dispose();


