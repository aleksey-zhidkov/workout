sealed abstract class Tree[T <% Ordered[T]] {

  def height: Int

  def insert(data: T): Tree[T]

  def size: Int

}

case class Node[T <% Ordered[T]](
                                  data: T,
                                  left: Tree[T],
                                  right: Tree[T],
                                  override val height: Int)
  extends Tree[T] {

  override def size = left.size + 1 + right.size

  override def insert(data: T): Tree[T] =
    if (data == this.data) this
    else if (data < this.data) Node(this.data, left.insert(data), right, height + 1)
    else Node(this.data, left, right.insert(data), height + 1)
}

case class Empty[T <% Ordered[T]]() extends Tree[T] {

  override def size: Int = 0

  override def insert(data: T): Tree[T] = Node(data, Empty(), Empty(), 1)

  override def height: Int = 0

}