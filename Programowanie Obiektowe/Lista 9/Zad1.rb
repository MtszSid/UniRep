class Funkcja
    def initialize (procedura)
        @funkcja = procedura
    end
    def value v
        @funkcja.call v
    end
    
end

