using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zadane_2
{
    
    class Memento
    {
        private List<Shape> _shapes;

        public Memento()
        {
            _shapes = new List<Shape>();
        }

        public IEnumerable<Shape> GetShapes()
        {
            return _shapes.AsReadOnly();
        }

        public void AddShape(Shape s)
        {
            _shapes.Add(s);
        }

        public void RemoveShape(Shape s)
        {
            _shapes.Remove(s);
        }
    }

    class ShapeEvent
    {
        public ShapeEvent(WorkMode type, Shape shape, int dx, int dy)
        {
            Type = type;
            Shape = shape;
            this.dx = dx;
            this.dy = dy;
        }

        public WorkMode Type { get; set; }
        public Shape Shape { get; set; }
        public int dx { get; set; }
        public int dy { get; set; }


    }

    class Caretaker
    {
        private Memento _memento;

        private Stack<ShapeEvent> _undoStack, _redoStack;

        public Caretaker()
        {
            _memento = new Memento();

            _undoStack = new Stack<ShapeEvent>();
            _redoStack = new Stack<ShapeEvent>();
        }

        public IEnumerable<Shape> GetShapes()
        {
            return _memento.GetShapes();
        }

        public void AddShape(Shape s)
        {
            if (s == null)
            {
                return;
            }

            _memento.AddShape(s);

            _redoStack.Clear();

            _undoStack.Push(new ShapeEvent(WorkMode.Draw, s, 0, 0));
        }

        public void RemoveShape(Shape s)
        {
            if (s == null)
            {
                return;
            }

            _memento.RemoveShape(s);

            _redoStack.Clear();

            _undoStack.Push(new ShapeEvent(WorkMode.Delete, s, 0, 0));
        }

        public void Undo()
        {
            if (_undoStack.Count > 0)
            {
                var m = _undoStack.Pop();

                switch (m.Type)
                {
                    case WorkMode.Draw:
                        _memento.RemoveShape(m.Shape);
                        break;
                    case WorkMode.Move:
                        m.Shape.x = m.Shape.x - m.dx;
                        m.Shape.y = m.Shape.y - m.dy;
                        break;
                    case WorkMode.Delete:
                        _memento.AddShape(m.Shape);
                        break;
                }

                _redoStack.Push(m);
            }
        }

        public void Redo()
        {
            if (_redoStack.Count > 0)
            {
                var m = _redoStack.Pop();

                switch (m.Type)
                {
                    case WorkMode.Draw:
                        _memento.AddShape(m.Shape);
                        break;
                    case WorkMode.Move:
                        m.Shape.x = m.Shape.x + m.dx;
                        m.Shape.y = m.Shape.y + m.dy;
                        break;
                    case WorkMode.Delete:
                        _memento.RemoveShape(m.Shape);
                        break;
                }

                _undoStack.Push(m);
            }
        }

        public void Move(Shape s, int x, int y)
        {
            if(s == null)
            {
                return;
            }

            _redoStack.Clear();

            _undoStack.Push(new ShapeEvent(WorkMode.Move, s, x - s.x, y - s.y));

            s.x = x;
            s.y = y;
        }

        public Shape SelectShape(int x, int y)
        {
            foreach(var i in GetShapes())
            {
                if(i.InTheShape(x, y))
                {
                    return i;
                }
            }
            return null;
        }
    }
}
