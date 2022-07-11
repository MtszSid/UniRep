"""Lista 3 Zadanie 2
Funkcje obliczające liczby doskonałe.
"""


def doskonale_imperatywna(n: int):
    """Imperatywna funkcja obliczająca liczby doskonałe < n"""
    Doskonale = []
    for i in range(6, n + 1):
        Dzielniki = []
        for j in range(1, i):
            if i % j == 0:
                Dzielniki.append(j)
        Suma = 0
        for a in Dzielniki:
            Suma += a
        if i == Suma:
            Doskonale.append(i)
    return Doskonale


def doskonale_skladana(n: int):
    """Funkcja obliczająca liczby doskonałe < n, przy użyciu list składanych
    """
    Rozklad = [sum([i for i in range(1, j) if j % i == 0])
               for j in range(6, n + 1)]
    return [x for x in range(6, n + 1) if x == Rozklad[x - 6]]


def doskonale_funkcyjna(n: int):
    """Funkcja obliczająca liczby doskonałe < n w sposób funkcyjny"""
    Rozklad = map(lambda x:
                  (x[0], sum(map(lambda t: t if x[0] % t == 0 else 0, x[1]))),
                  map(lambda x: (x, range(1, x)), range(6, n + 1)))
    return list(map(lambda x: x[0], filter(lambda x: x[0] == x[1], Rozklad)))


if __name__ == '__main__':
    print(doskonale_funkcyjna(10000))
    print(doskonale_imperatywna(10000))
    print(doskonale_skladana(10000))
