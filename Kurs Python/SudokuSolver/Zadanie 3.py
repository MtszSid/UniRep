
def SudokuPrinter(Sudoku):
    if Sudoku is None:
        print("No solution")
        return

    for i in range(3):
        print(" ".join(Sudoku[i][0:3]) + " | " + " ".join(Sudoku[i][3:6]) + " | " + " ".join(Sudoku[i][6:9]))

    print(21 * "-")

    for i in range(3, 6):
        print(" ".join(Sudoku[i][0:3]) + " | " + " ".join(Sudoku[i][3:6]) + " | " + " ".join(Sudoku[i][6:9]))

    print(21 * "-")

    for i in range(6, 9):
        print(" ".join(Sudoku[i][0:3]) + " | " + " ".join(Sudoku[i][3:6]) + " | " + " ".join(Sudoku[i][6:9]))

    return


def SudokuSolver(sudoku):
    def CheckLine(line, board):
        usedValues = set()
        for i in board[line]:
            if i in usedValues:
                return False
            if i != '0':
                usedValues.add(i)
        return True

    def CheckColumn(column, board):
        usedValues = set()
        for i in range(9):
            if board[i][column] in usedValues:
                return False
            if board[i][column] != '0':
                usedValues.add(board[i][column])
        return True

    def CheckSquare(squareNumber, board):
        usedValues = set()
        columnFactor = squareNumber % 3
        rowFactor = squareNumber // 3

        for i in range(3):
            for j in range(3):
                if board[3 * rowFactor + i][3 * columnFactor + j] in usedValues:
                    return False
                if board[3 * rowFactor + i][3 * columnFactor + j] != '0':
                    usedValues.add(board[3 * rowFactor + i][3 * columnFactor + j])
        return True

    def FindNextEmpty(beg, board):
        row = beg[0]

        for i in range(row, 9):
            for j in range(9):
                if board[i][j] == '0':
                    row = i
                    return row, j
        return -1, -1

    def SudokuSolverHelper(placeToStart, board):
        if placeToStart[0] == -1:
            return True
        for i in range(1, 10):
            board[placeToStart[0]][placeToStart[1]] = str(i)
            if CheckLine(placeToStart[0], board) and CheckColumn(placeToStart[1], board) and CheckSquare(placeToStart[0] // 3 * 3 + placeToStart[1] // 3, board):
                if SudokuSolverHelper(FindNextEmpty(placeToStart, board), board):
                    return True
        board[placeToStart[0]][placeToStart[1]] = '0'
        return False

    board = [list(i.replace("_", "0")) for i in sudoku.split('\n')]

    for i in range(9):
        if CheckLine(i, board) is False or CheckColumn(i, board) is False or CheckSquare(i, board) is False:
            return None

    if SudokuSolverHelper(FindNextEmpty((0, 0), board), board):
        return board
    else:
        return None


SudokuBoard = """53__7____
6__195___
_98____6_
8___6___3
4__8_3__1
7___2___6
_6____28_
___419__5
____8__79"""

SudokuPrinter(SudokuSolver(SudokuBoard))
