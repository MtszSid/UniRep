class Integer

    def czynniki
        czynniki = Array.new
        i = 1;
        czynniki.push(self)
        while 2 * i <= self
            if self % i == 0
                czynniki.push i
            end
            i+=1
        end
        czynniki
    end

    def ack y
        if self == 0
            y + 1
        elsif y == 0
            (self - 1).ack(y + 1)
        else
            (self - 1).ack self.ack(y - 1)
        end
    end

    def doskonala
        i = 0
        self.czynniki.each do |x|
            i += x
        end
        if self * 2 == i
            true
        else
            false
        end
    end

    def slownie
        if self == 0
            return ""
        end
        i = self % 10
        if i == 0
           s = "zero"
        elsif i == 1
           s = "jeden"
        elsif i == 2
            s ="dwa"
        elsif i == 3
            s = "trzy"
        elsif i == 4
            s ="cztery"
        elsif i == 5
            s = "pięć"
        elsif i == 6
            s = "sześć"
        elsif i == 7
            s = "siedem"
        elsif i == 8
            s = "osiem"
        elsif i == 9
            s = "dziewięć"
        end
        ((self - self % 10) / 10).slownie  + s + " "
    end
end

puts 13.czynniki.inspect
puts 6.czynniki.inspect
puts 75.czynniki.inspect
puts 144.czynniki.inspect
puts 2.czynniki.inspect
puts 1.ack 1
puts 1.ack 2
puts 2.ack 1
puts 2.ack 2
puts 6.doskonala
puts 15.doskonala
puts 28.doskonala
puts 1.doskonala
puts 145.slownie