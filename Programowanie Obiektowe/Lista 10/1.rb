class Kolekcja
    
    def initialize
    @kol = Array.new
    end

    def add (i)
        @kol.push i 
    end

    def swap (i, j)
        p = @kol[i]
        @kol[i] = @kol[j]
        @kol[j] = p
    end

    def length()
        @kol.length
    end

    def get (i)
        @kol[i]
    end

    def display
        puts @kol.inspect 
    end
end

class Sortowanie 
    
    def sortowanie1 (kol)
        (kol.length - 1).times do |i|
            (kol.length - i - 1).times do |j|
                if (kol.get j) > (kol.get (j + 1)) 
                    kol.swap(j, j + 1)
                end
            end
        end
        kol
    end
    
    def sortowanie2 (kol)
        
        (kol.length ).times do |i|
            g = i
            (kol.length - i).times do |j|
                if (kol.get g) > (kol.get (j + i))
                    g = j + i
                end
            end
            kol.swap(i, g)
        end
        kol
    end
end

sort = Sortowanie.new
a = Kolekcja.new
b = Kolekcja.new
a.add 6 
a.add 5
a.add 1
a.add 9
a.add 0
a.add 11
a.add 2

b.add 6 
b.add 5
b.add 1
b.add 9
b.add 0
b.add 11
b.add 2

sort.sortowanie1(a)
sort.sortowanie2(b)
a.display
b.display