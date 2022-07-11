using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Zadane_2
{
    public partial class Form1 : Form
    {
        Caretaker _caretaker;

        ShapeTypes _selectedShape;
        WorkMode _workMode;

        Shape _selectedObject;

        public Form1()
        {
            InitializeComponent();
            _caretaker = new Caretaker();

            _selectedShape = ShapeTypes.Rectangle;
            _workMode = WorkMode.Draw;

            _selectedObject = null;

            this.SetStyle(ControlStyles.AllPaintingInWmPaint | ControlStyles.UserPaint | ControlStyles.OptimizedDoubleBuffer, true);
        }

        
        private void OnPanePaint(object sender, PaintEventArgs e)
        {
            e.Graphics.Clear(SystemColors.Control);

            foreach(var i in _caretaker.GetShapes())
            {
                i.Paint(e);
            }
        }

        private void OnTick(object sender, EventArgs e)
        {
            //Invalidate(true);
        }

        private void CircleButton_Click(object sender, EventArgs e)
        {
            _workMode = WorkMode.Draw;
            _selectedShape = ShapeTypes.Circle;
        }

        private void SquareButton_Click(object sender, EventArgs e)
        {
            _workMode = WorkMode.Draw;
            _selectedShape = ShapeTypes.Square;
        }

        private void RectangeButton_Click(object sender, EventArgs e)
        {
            _workMode = WorkMode.Draw;
            _selectedShape = ShapeTypes.Rectangle;
        }

        private void MoveButton_Click(object sender, EventArgs e)
        {
            _workMode = WorkMode.Move;
        }

        private void DeleteButton_Click(object sender, EventArgs e)
        {
            _workMode = WorkMode.Delete;
        }

        private void UndoButton_Click(object sender, EventArgs e)
        {
            _caretaker.Undo();
            Invalidate(true);
        }

        private void RedoButton_Click(object sender, EventArgs e)
        {
            _caretaker.Redo();
            Invalidate(true);
        }

        private void OnMouseDown(object sender, MouseEventArgs e)
        {
            if(_workMode == WorkMode.Move)
            {
                _selectedObject = _caretaker.SelectShape(e.X, e.Y);
            }
        }

        private void OnMouseUp(object sender, MouseEventArgs e)
        {
            if(_workMode == WorkMode.Draw)
            {
                switch (_selectedShape)
                {
                    case ShapeTypes.Rectangle:
                        _caretaker.AddShape(new Rectangle(e.X, e.Y, 100, 50));
                        break;
                    case ShapeTypes.Square:
                        _caretaker.AddShape(new Square(e.X, e.Y, 100));
                        break;
                    case ShapeTypes.Circle:
                        _caretaker.AddShape(new Circle(e.X, e.Y, 50));
                        break;
                }
            }
            else if(_workMode == WorkMode.Move)
            {
                _caretaker.Move(_selectedObject, e.X, e.Y);
            }
            else if(_workMode == WorkMode.Delete)
            {
                _caretaker.RemoveShape(_caretaker.SelectShape(e.X, e.Y));
            }

            Invalidate(true);
        }
    }
}
