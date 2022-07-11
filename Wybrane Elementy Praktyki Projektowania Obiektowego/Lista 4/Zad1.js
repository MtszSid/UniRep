function Tree(val, left, right) {
    this.left = left;
    this.right = right;
    this.val = val;
}
Tree.prototype[Symbol.iterator] = function* () {
    var queue = []
    
    var node
    yield this.val
    if(this.left != undefined) queue.push(this.left)
    if(this.right != undefined) queue.push(this.right)
    while (queue.length != 0){
        node = queue.splice(0,1)
        yield node[0].val
        if(node[0].left != undefined) queue.splice(queue.length, 0, node[0].left)
        if(node[0].right != undefined) queue.splice(queue.length, 0, node[0].right)
    }
    
}
var root = new Tree(1,
    new Tree(2, new Tree(3)), new Tree(4));
for (var e of root) {
    console.log(e);
}
    // 1 2 3 4