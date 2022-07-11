class Jawna
    def initialize(slowo)
        @slowo = slowo
    end

    def to_s
        @slowo
    end

    def zaszyfruj klucz
        w = ''
        @slowo.split('').each do |l|
            w << klucz[l] 
        end
        w
    end
end

class Zaszyfrowane
    def initialize(slowo)
        @slowo = slowo
    end

    def to_s
        @slowo
    end

    def odszyfruj klucz
        w = ''
        @slowo.split('').each do |l|
            w << klucz[l] 
        end
        w
    end
end

klucz =
{   'a' => 'b',
    'b' => 'r',
    'r' => 'y',
    'y' => 'u',
    'u' => 'a'}

klucz2 ={   'a' => 'u',
    'b' => 'a',
    'r' => 'b',
    'y' => 'r',
    'u' => 'y'}

a = Jawna.new 'ruby'
b = Zaszyfrowane.new a.zaszyfruj klucz
puts a.zaszyfruj klucz
puts b.odszyfruj klucz2