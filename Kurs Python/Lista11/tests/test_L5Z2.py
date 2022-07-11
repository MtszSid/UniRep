import unittest
from unittest import TestCase
from Source.L5Z2 import *


class TestFormula(TestCase):
    formula_1 = And(Not(Iff(Zmienna("a"), Stala(True))), Or(Zmienna("b"), Zmienna("c")))
    formula_2 = Iff(Implies(Zmienna("a"), Zmienna("b")), Or(Not(Zmienna("a")), Zmienna("b")))
    formula_3 = Stala(True) + Zmienna("x") + (Stala(True) * Stala(True) * Zmienna("x")) + Stala(False)
    formula_4 = Zmienna("y") + Zmienna("x") + (Stala(True) * Stala(True) * Zmienna("x")) + Stala(False)
    formula_5 = Implies(And(Implies(Zmienna("p"), Zmienna("q")), Not(Zmienna("q"))), Not(Zmienna("p")))

    def test_uprosc(self):
        self.assertEqual("⊤", str(self.formula_3.uprosc()))
        self.assertEqual("((y) ∨ (x)) ∨ (x)", str(self.formula_4.uprosc()))

    def test_oblicz(self):
        self.assertEqual(self.formula_1.oblicz({"a": True, "b": False, "c": True}), False)
        self.assertEqual(self.formula_2.oblicz({"a": True, "b": False}), True)
        self.assertRaises(UnboundVariable, self.formula_5.oblicz, {})

    def test_tautologia(self):
        self.assertEqual(Formula.tautologia(self.formula_4), False)
        self.assertEqual(Formula.tautologia(self.formula_5), True)
        self.assertEqual(Formula.tautologia(self.formula_2), True)


if __name__ == '__main__':
    unittest.main()
