from itertools import product


class UnboundVariable(Exception):
    """Wyjątek zwracany podczas próby obliczenia wartości
    formuły zawierającej zmienne niezwiązane."""
    pass


class Formula:
    """Reprezentuje formułę logiczną"""
    def __add__(self, other):
        return Or(self, other)

    def __mul__(self, other):
        return And(self, other)

    def uprosc(self):
        """Upraszcza formułę."""
        raise NotImplementedError()

    def oblicz(self, zmienne: dict):
        """Oblicza wartość logiczną dla formuły"""
        raise NotImplementedError()

    def zmienne_wolne(self):
        """Wylicza zmienne wolne dla formuły"""
        raise NotImplementedError()

    @staticmethod
    def tautologia(formula):
        """Sprawdza, czy dana formuła jest tautologią."""
        zmienne = list(formula.zmienne_wolne())
        lim = len(zmienne)
        comb = product([True, False], repeat=lim)
        for i in comb:
            j = 0
            wiazanie_zmiennych = {}
            while j < lim:
                wiazanie_zmiennych[zmienne[j]] = i[j]
                j += 1

            if (formula.oblicz(wiazanie_zmiennych)) is False:
                return False

        return True


class Stala(Formula):
    def __init__(self, value):
        self.value = value

    def oblicz(self, zmienne):
        return self.value

    def __str__(self):
        if self.value:
            return "⊤"
        else:
            return "⊥"

    def uprosc(self):
        return self

    def zmienne_wolne(self):
        return set()


class Zmienna(Formula):
    def __init__(self, name):
        self.name = name

    def oblicz(self, zmienne):
        if self.name in zmienne:
            return zmienne[self.name]
        raise UnboundVariable(self.name + " is unbounded")

    def __str__(self):
        return self.name

    def uprosc(self):
        return self

    def zmienne_wolne(self):
        return set(self.name)


class And(Formula):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def oblicz(self, zmienne):
        return self.left.oblicz(zmienne) and self.right.oblicz(zmienne)

    def __str__(self):
        return "(" + str(self.left) + ") ∧ (" + str(self.right) + ")"

    def uprosc(self):
        left_expr = self.left.uprosc()
        right_expr = self.right.uprosc()

        if type(right_expr) is Stala and right_expr.oblicz({}) is True:
            return left_expr
        if type(left_expr) is Stala and left_expr.oblicz({}) is True:
            return right_expr
        if type(right_expr) is Stala and type(left_expr) is Stala and (
                right_expr.oblicz({}) is False or left_expr.oblicz({}) is False):
            return Stala(False)

        return And(left_expr, right_expr)

    def zmienne_wolne(self):
        return set.union(self.left.zmienne_wolne(), self.right.zmienne_wolne())


class Or(Formula):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def oblicz(self, zmienne):
        return self.left.oblicz(zmienne) or self.right.oblicz(zmienne)

    def __str__(self):
        return "(" + str(self.left) + ") ∨ (" + str(self.right) + ")"

    def uprosc(self):
        left_expr = self.left.uprosc()
        right_expr = self.right.uprosc()

        if type(right_expr) is Stala and right_expr.oblicz({}) is False:
            return left_expr
        if type(left_expr) is Stala and left_expr.oblicz({}) is False:
            return right_expr
        if (type(right_expr) is Stala and right_expr.oblicz({}) is True) or (
                type(left_expr) is Stala and left_expr.oblicz({}) is True):
            return Stala(True)

        return Or(left_expr, right_expr)

    def zmienne_wolne(self):
        return set.union(self.left.zmienne_wolne(), self.right.zmienne_wolne())


class Implies(Formula):
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def oblicz(self, zmienne):
        return not (self.left.oblicz(zmienne)) or self.right.oblicz(zmienne)

    def __str__(self):
        return "(" + str(self.left) + ") ⇒ (" + str(self.right) + ")"

    def uprosc(self):
        left_expr = self.left.uprosc()
        right_expr = self.right.uprosc()

        if type(left_expr) is Stala and left_expr.oblicz({}) is True:
            return right_expr
        if type(left_expr) is Stala and left_expr.oblicz({}) is False:
            return Stala(True)

        return Implies(left_expr, right_expr)

    def zmienne_wolne(self):
        return set.union(self.left.zmienne_wolne(), self.right.zmienne_wolne())


class Iff(Formula):
    # If and only if
    def __init__(self, left, right):
        self.left = left
        self.right = right

    def oblicz(self, zmienne):
        left_expr = self.left.oblicz(zmienne)
        right_expr = self.right.oblicz(zmienne)
        return (left_expr and right_expr) or (not left_expr and not right_expr)

    def __str__(self):
        return "(" + str(self.left) + ") ⇔ (" + str(self.right) + ")"

    def uprosc(self):
        left_expr = self.left.uprosc()
        right_expr = self.right.uprosc()

        if type(right_expr) is Stala and type(left_expr) is Stala and right_expr.oblicz({}) is left_expr.oblicz({}):
            return Stala(True)
        if type(right_expr) is Stala and type(left_expr) is Stala and right_expr.oblicz({}) is not left_expr.oblicz({}):
            return Stala(False)

        return Iff(left_expr, right_expr)

    def zmienne_wolne(self):
        return set.union(self.left.zmienne_wolne(), self.right.zmienne_wolne())


class Not(Formula):
    def __init__(self, expression):
        self.expression = expression

    def oblicz(self, zmienne):
        return not (self.expression.oblicz(zmienne))

    def __str__(self):
        return "¬ (" + str(self.expression) + ")"

    def uprosc(self):
        expression_upr = self.expression.uprosc()

        if type(expression_upr) is Stala:
            return Stala(not expression_upr.oblicz({}))
        return Not(expression_upr)

    def zmienne_wolne(self):
        return self.expression.zmienne_wolne()


if __name__ == "__main__":
    formula_1 = And(Not(Iff(Zmienna("a"), Stala(True))), Or(Zmienna("b"), Zmienna("c")))
    print(str(formula_1) + "  " + str(formula_1.oblicz({"a": True, "b": False, "c": True})))

    formula_2 = Iff(Implies(Zmienna("a"), Zmienna("b")), Or(Not(Zmienna("a")), Zmienna("b")))
    print(str(formula_2) + "  " + str(formula_2.oblicz({"a": True, "b": False})))

    formula_3 = Stala(True) + Zmienna("x") + (Stala(True) * Stala(True) * Zmienna("x")) + Stala(False)
    print(str(formula_3) + " ≡ " + str(formula_3.uprosc()))

    formula_4 = Zmienna("y") + Zmienna("x") + (Stala(True) * Stala(True) * Zmienna("x")) + Stala(False)
    print(str(formula_4) + " ≡ " + str(formula_4.uprosc()))

    formula_5 = Implies(And(Implies(Zmienna("p"), Zmienna("q")), Not(Zmienna("q"))), Not(Zmienna("p")))

    print(Formula.tautologia(formula_4))
    print(Formula.tautologia(formula_5))
    print(Formula.tautologia(formula_2))

    try:
        print(formula_5.oblicz({}))
    except UnboundVariable as var:
        print("\r\nException:")
        print(var)
