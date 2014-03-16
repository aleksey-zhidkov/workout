import org.scalacheck.{Gen, Prop, Properties}

object AvlTreeProperties extends Properties("AVL Tree properties") {

  property("All unique elements inserted") =
    Prop.forAll(Gen.containerOf[Set, Int](Gen.choose(0, 100))) { x =>
      var tree: Tree[Int] = Empty[Int]()
      x.foreach { x =>
        tree = tree.insert(x)
      }
        tree.size == x.size
    }

}
