using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Zad1_2_2
{

    class BinTreeNode<T>
    {
        T _val;
        BinTreeNode<T> _left;
        BinTreeNode<T> _right;

        BinTreeNode(){}
        BinTreeNode(T v)
        {
            _val = v;
            _left = null;
            _right = null;
        }

        BinTreeNode(T v, BinTreeNode<T> l, BinTreeNode<T> r)
        {
            _val = v;
            _left = l;
            _right = r;
        }

        public IEnumerator<T> DFS()
        {
            yield return _val;
            if (_left != null)
                _left.DFS();
            if (_right != null)
                _right.DFS();

        }

       
    }
    class Program
    {
        static void Main(string[] args)
        {
        }
    }
}
