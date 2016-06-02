object Listes {
  var l1=List(1,3,5,4,6,2,7,9,8)
  var l2=List("a","b","c","e","g","d")
  var lkv=List(("a",1),("b",2),("c",3))

  def find_in_int(l:List[Int], e:Int): Boolean = {
    l match {
      case Nil => false
      case x::tails => x==e || find_in_int(tails,e)
    }
  }

}

Listes.find_in_int(Listes.l1,2)
Listes.find_in_int(Listes.l1,10)
