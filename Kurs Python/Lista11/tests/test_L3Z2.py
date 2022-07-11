import unittest
from unittest import TestCase
from Source.L3Z2 import *


class Test(TestCase):
    n = 10000
    ans = [6, 28, 496, 8128]

    def test_doskonale_imperatywna(self):
        val = doskonale_imperatywna(self.n)
        self.assertEqual(len(self.ans), len(val))
        for i in range(len(val)):
            self.assertEqual(self.ans[i], val[i])

    def test_doskonale_skladana(self):
        val = doskonale_skladana(self.n)
        self.assertEqual(len(self.ans), len(val))
        for i in range(len(val)):
            self.assertEqual(self.ans[i], val[i])

    def test_doskonale_funkcyjna(self):
        val = doskonale_funkcyjna(self.n)
        self.assertEqual(len(self.ans), len(val))
        for i in range(len(val)):
            self.assertEqual(self.ans[i], val[i])


if __name__ == '__main__':
    unittest.main()
